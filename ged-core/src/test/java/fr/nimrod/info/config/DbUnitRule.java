package fr.nimrod.info.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import lombok.SneakyThrows;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

import com.google.common.io.Resources;

public class DbUnitRule implements MethodRule {

	@Retention(RetentionPolicy.RUNTIME)
	@Target({ ElementType.METHOD })
	private static @interface Ddls {
		Ddl[] value();
	}

	@Retention(RetentionPolicy.RUNTIME)
	@Target({ ElementType.METHOD })
	@Repeatable(Ddls.class)
	public static @interface Ddl {
		String value();
	}

	@Retention(RetentionPolicy.RUNTIME)
	@Target({ ElementType.METHOD })
	public static @interface JsonData {
		String value();
	}

	private final Class<?> resourceBase;

	private IDatabaseTester databaseTester;

	private IDatabaseConnection dbUnitConnection;

	private Connection connection;

	private java.sql.Statement statement;

	@SneakyThrows
	public DbUnitRule(Class<?> resourceBase, Class<?> driver, String url,
			String user, String password) {

		this.resourceBase = resourceBase;
		databaseTester = new JdbcDatabaseTester(driver.getName(), url, user,
				password);
		dbUnitConnection = databaseTester.getConnection();
		connection = dbUnitConnection.getConnection();
		statement = connection.createStatement();
	}

	@Override
	public Statement apply(Statement statement, FrameworkMethod framework,
			Object object) {
		return new Statement() {

			@Override
			@SneakyThrows
			public void evaluate() throws Throwable {

				Ddl ddl = framework.getAnnotation(Ddl.class);
				if (ddl != null) {
					// for (Ddl ddl : ddls.value()) {
					String value = ddl.value();

					executeUpdate(Resources.toString(
							resourceBase.getResource(value),
							Charset.defaultCharset()));
					// }
				}

				JsonData data = framework.getAnnotation(JsonData.class);
				if (data != null) {
					IDataSet ds = new JsonDataSet(
							resourceBase.getResourceAsStream(data.value()));
					databaseTester.setDataSet(ds);
				}
				databaseTester.onSetup();
				statement.evaluate();

			}
		};
	}

	public java.sql.Connection getConnection() {
		return connection;
	}

	@SneakyThrows
	private void executeUpdate(String sql) {
		statement.executeUpdate(sql);
	}

	@SneakyThrows
	public ResultSet executeQuery(String sql) {
		return statement.executeQuery(sql);
	}

	public IDataSet jsonDataSet(String datasetResource) {
		return new JsonDataSet(
				resourceBase.getResourceAsStream(datasetResource));
	}

	public ITable createQueryTable(String string, String string2)
			throws DataSetException, SQLException {
		return dbUnitConnection.createQueryTable(string, string2);

	}

}
