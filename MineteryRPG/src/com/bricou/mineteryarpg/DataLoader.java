package com.bricou.mineteryarpg;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.bukkit.entity.Player;

public class DataLoader {
	
	MineteryaRPG plugin;
	Connection dbConnection;
	String dbconn = "";

	public DataLoader(MineteryaRPG instance)
	{
		this.plugin = instance;
		//On cr�e la bdd si elle n'existe pas
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
	
	public boolean playerExist(String player)
	{
		try {
			int id = -1;
			dbConnection = DriverManager.getConnection(dbconn);
			Statement statement = dbConnection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT `id` FROM `players` WHERE `name`='" + player + "';");
			while (rs.next()) 
			{
				id = rs.getInt("id");
			}
			rs.close();
			statement.close();
			dbConnection.close();
			if (id >= 0)
			{
				//Le joueur existe
				return true;
			} else {
				//Le joueur n'existe pas
				return false;
			}
		}
		catch (Exception e)
		{
			return false;
		}
	}
	
	public void playerCreate(Player player, String classe)
	{
		try {
			dbConnection = DriverManager.getConnection(dbconn);
			Statement statement = dbConnection.createStatement();
			
			statement.executeUpdate("INSERT INTO `players` (`id`,`name`,`class`,`experience`,`level`) VALUES (NULL, '" + player.getName() + "', '" + classe + "', 0, 0);");
			
			this.playerLoad(player);
			
			statement.close();
			dbConnection.close();
		}
		catch (Exception e) 
	    {
			e.printStackTrace();
	    }
	}
	
	public void playerLoad(Player player)
	{
		try {
			String info[] = { "", "", "", "" };
			
			dbConnection = DriverManager.getConnection(dbconn);
			Statement statement = dbConnection.createStatement();
			
			ResultSet rs = statement.executeQuery("SELECT `name`, `class`, `experience`, `level` FROM players WHERE `name`='" + player.getName() + "';");
			
			while (rs.next())
			{
				info[0] = rs.getString("name");
				info[1] = rs.getString("class");
				info[2] = rs.getString("experience");
				info[3] = rs.getString("level");
			}
			
			RPGPlayer playerToAdd = new RPGPlayer(info, this.plugin);
			this.plugin.rpgPlayers.put(player, playerToAdd);
			
			statement.close();
			dbConnection.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void playerSave(String player)
	{
		
	}
	
}
