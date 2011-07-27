package com.bricou.mineteryarpg;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DataLoader {
	
	MineteryaRPG plugin;
	Connection dbConnection;
	String dbconn = "";

	public DataLoader(MineteryaRPG instance)
	{
		this.plugin = instance;
		//On crée la bdd si elle n'existe pas
		try
		{
			Class.forName("org.sqlite.JDBC");
			dbconn = "jdbc:sqlite:" + instance.getDataFolder().toString() + File.separator + "easyrpg.db";
			dbConnection = DriverManager.getConnection(dbconn);
			Statement statement = dbConnection.createStatement();
			statement.execute("CREATE TABLE IF NOT EXISTS players (`id` INTEGER PRIMARY KEY, `name` varchar(32), `class` varchar(32), `experience` FLOAT, `level` INTEGER);");
			dbConnection.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	
}
