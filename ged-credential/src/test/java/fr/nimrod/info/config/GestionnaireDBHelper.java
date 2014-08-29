package fr.nimrod.info.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import lombok.SneakyThrows;

public enum GestionnaireDBHelper {

	INSTANCE;
	
	/** Service Connexion. */
	private Connection connection;
	
	/** driver JDBC. */
	private String jdbcDriver = "org.hsqldb.jdbcDriver";
	
	/** mode m�moire. */
	private String database = "jdbc:hsqldb:mem:database";
	
	/** utilisateur qui se connecte � la base de donn�es. */
	private String user = "sa";
	
	/** mot de passe pour se connecter � la base de donn�es. */
	private String password = "";
	
	/**
	 * Fonction de connexion � la base de donn�es.
	 */
	@SneakyThrows
	public void connectionDB() {
		Class.forName(jdbcDriver).newInstance();
		connection = DriverManager.getConnection(database, user, password);
	}
	
	/**
	 * Arr�te correctement HSQLDB.
	 */
	@SneakyThrows
	public void shutdownDB() {
		Statement statement = connection.createStatement();
		
		statement.execute("SHUTDOWN");
		
		connection.close();
	}
	
	/**
	 * Execute la requete pass�e en param�tre.
	 * @param requete contient la requ�te SQL
	 */
	@SneakyThrows
	public void executerUpdate(String requete) {
		Statement statement;
		statement = connection.createStatement();
		statement.executeUpdate(requete);
	}
 
	/**
	 * Execute la requete pass�e en param�tre.
	 * @param requete contient la requ�te SQL
	 */
	@SneakyThrows
	public ResultSet executerRequete(String requete) {
		Statement statement;
		statement = connection.createStatement();
		ResultSet resultat = statement.executeQuery(requete);
		return resultat;
	}
	
}
