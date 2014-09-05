package fr.nimrod.info.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lombok.SneakyThrows;

import org.apache.commons.dbcp.BasicDataSource;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.DatabaseDataSourceConnection;
import org.dbunit.database.DatabaseSequenceFilter;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.CompositeDataSet;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.FilteredDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.operation.DatabaseOperation;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

import com.google.common.io.Resources;

public class DbUnitRule implements MethodRule {

	@Retention(RetentionPolicy.RUNTIME)
	@Target({ ElementType.METHOD })
	public static @interface Schema {
		String[] value();
	}

	@Retention(RetentionPolicy.RUNTIME)
	@Target({ ElementType.METHOD })
	public static @interface Data {
		String[] values();
	}

	private final Class<?> resourceBase;

	private IDatabaseTester databaseTester;
	private IDatabaseConnection dbUnitConnection;

	private Connection connection;
	private java.sql.Statement statement;
	private BasicDataSource dataSource;

	@SneakyThrows
	public DbUnitRule(Class<?> resourceBase, Class<?> driver, String url, String user, String password) {

		this.dataSource = new BasicDataSource();
		dataSource.setDriverClassName(driver.getCanonicalName());
		dataSource.setUrl(url);
		dataSource.setUsername(user);
		dataSource.setPassword(password);

		this.resourceBase = resourceBase;
		try {
			databaseTester = new JdbcDatabaseTester(driver.getName(), url, user, password);
			dbUnitConnection = databaseTester.getConnection();
			connection = dbUnitConnection.getConnection();
			statement = connection.createStatement();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Statement apply(final Statement base, final FrameworkMethod method, final Object target) {

		return new Statement() {

			@Override
			public void evaluate() throws Throwable {

				try {
					Schema ddl = method.getAnnotation(Schema.class);
					if (ddl != null) {
						String[] values = ddl.value();
						for (String value : values) {
							executeUpdate(Resources.toString(resourceBase.getResource(value), Charset.defaultCharset()));
						}
					}

					Data data = method.getAnnotation(Data.class);
					if (data != null) {
						String[] dataSetFiles = data.values();
						List<IDataSet> dataSets = new ArrayList<IDataSet>(dataSetFiles.length);
						for (String dataSetFile : dataSetFiles) {
							IDataSet ds;
							if (dataSetFile.endsWith(".json"))
								ds = new JsonDataSet(resourceBase.getResourceAsStream(dataSetFile));
							else
								throw new IllegalStateException(
										"DbUnitRule ne supporte que le format json... Pour l'instant");
							dataSets.add(ds);
						}
						CompositeDataSet dataSet = new CompositeDataSet(dataSets.toArray(new IDataSet[dataSets.size()]));
						DatabaseDataSourceConnection databaseDataSourceConnection = new DatabaseDataSourceConnection(
								dataSource);
						IDataSet fkDataSet = new FilteredDataSet(new DatabaseSequenceFilter(
								databaseDataSourceConnection), dataSet);
						DatabaseOperation.CLEAN_INSERT.execute(databaseDataSourceConnection, fkDataSet);
					}

					base.evaluate();
				} finally {
					// databaseTester.onTearDown();
				}
			}
		};
	}

	@SneakyThrows
	public java.sql.Connection getConnection() {
		return dataSource.getConnection();
	}

	public void executeUpdate(String sql) throws SQLException {
		statement.executeUpdate(sql);
	}

	public ResultSet executeQuery(String sql) throws SQLException {
		return statement.executeQuery(sql);
	}

	public IDataSet jsonDataSet(String datasetResource) {
		return new JsonDataSet(resourceBase.getResourceAsStream(datasetResource));
	}

	public ITable createQueryTable(String string, String string2) throws DataSetException, SQLException {
		return dbUnitConnection.createQueryTable(string, string2);
	}
}