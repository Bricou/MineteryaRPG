package com.bricou.mineteryarpg;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.logging.Logger;
import org.bukkit.entity.Player;

/**
 * Classe de chargement et d'initialisation des données
 * @author Bricou & Dr.Jack
 * 
 */
public class DataLoader
{
	// Déclaration
	public MineteryaRPG plugin;
	public Connection dbConnection;
	public String dbconn = "";
	public PluginConfigFile configFile;
	public HashMap<String,String> configFileMap;
	public Logger log = Logger.getLogger("Minecraft");

	/**
	 * Mise en place de la BDD
	 * @param MineteryaRPG instance : instance du plugin
	 */
	public DataLoader(MineteryaRPG instance)
	{
		this.plugin = instance;
		this.configFile = new PluginConfigFile();
		this.configFileMap = new HashMap<String,String>();
		// On créé l'environnement du plugin s'il n'existe pas
		try
		{
			// Contrôle/Création du répertoire du plugin
			File mineteryaRPGFile = plugin.getDataFolder();
			if (!mineteryaRPGFile.exists())
			{
				if (mineteryaRPGFile.mkdir())
				{
					plugin.log.info("[MineteryaRPG] Création du répertoire d'environnement");
				}
				else
				{
					plugin.log.severe("[MineteryaRPG] Erreur à la création du répertoire d'environnement");
				}
			}
			
			// Contrôle/Création du fichier de paramétrage du plugin
			File mineteryaConfigFile = new File(plugin.getDataFolder() + File.separator + "MineteryaRPG.properties");
			if (!mineteryaConfigFile.exists())
			{
				// Le fichier de configuration n'existe pas, création du fichier par défaut
				plugin.log.info("[MineteryaRPG] Création du fichier de configuration du plugin par défaut");
				configFileMap = configFile.initializeProperties(mineteryaConfigFile);
			}
			else
			{
				// Le fichier de configuration existe, chargement du paramétrage du plugin
				plugin.log.info("[MineteryaRPG] Chargement du fichier de configuration du plugin");
				configFileMap = configFile.loadProperties(mineteryaConfigFile);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		// On crée la bdd si elle n'existe pas
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

	/**
	 * Teste si le joueur existe dans la BDD
	 * @param String player : Nom du joueur
	 * @return boolean : true si le joueur existe
	 */
	public boolean playerExist(String player)
	{
		try
		{
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
				// Le joueur existe
				return true;
			}
			else
			{
				// Le joueur n'existe pas
				return false;
			}
		}
		catch (Exception e)
		{
			return false;
		}
	}

	/**
	 * Création d'un joueur
	 * @param Player player : Instance du joueur
	 * @param String classe : Classe du joueur
	 */
	public void createPlayer(Player player, String classe)
	{
		try
		{
			dbConnection = DriverManager.getConnection(dbconn);
			Statement statement = dbConnection.createStatement();
			statement.executeUpdate("INSERT INTO `players` (`id`,`name`,`class`,`experience`,`level`) VALUES (NULL, '" + player.getName() + "', '" + classe + "', 0, 0);");
			this.loadPlayer(player);
			statement.close();
			dbConnection.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Chargement du joueur
	 * @param Player player : Instance du joueur
	 */
	public void loadPlayer(Player player)
	{
		try
		{
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

	/**
	 * Sauvegarde du joueur
	 * @param Player player : Instance du joeur
	 */
	public void savePlayer(RPGPlayer rpgPlayer)
	{
		try
		{
			dbConnection = DriverManager.getConnection(dbconn);
			Statement statement = dbConnection.createStatement();

			statement.executeUpdate("UPDATE players SET experience='" + rpgPlayer.getExperience() + "', level='" + rpgPlayer.getLevel() + "' WHERE name='" + rpgPlayer.getName() + "';");

			statement.close();
			dbConnection.close();
			log.info("[MineteryaRPG] Sauvegarde du joueur : " + rpgPlayer.getName());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
