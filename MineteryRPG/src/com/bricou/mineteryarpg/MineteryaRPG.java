package com.bricou.mineteryarpg;

import java.util.HashMap;
import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;

/**
 * Plugin de gestion RPG pour Mineterya (Bukkit)
 * @author Bricou & Dr.Jack
 * 
 */
public class MineteryaRPG extends JavaPlugin
{

	// D�claration
	private PluginManager pluginManager;
	public RPGPlayerListener playerListener;
	public DataLoader dataLoader;
	public RPGCommands rpgCommands;
	public HashMap<Player, RPGPlayer> rpgPlayers = new HashMap<Player, RPGPlayer>(); 
																					
	// Initialisation du logger
	public Logger log = Logger.getLogger("Minecraft");

	/**
	 * Initialisation du plugin
	 */
	public void onEnable()
	{
		log.info("[MineteryaRPG] Initialisation du plugin");

		// Initialisation du contexte
		pluginManager = getServer().getPluginManager();
		playerListener = new RPGPlayerListener(this);
		dataLoader = new DataLoader(this);
		rpgCommands = new RPGCommands(this);

		// Enregistrement des events
		pluginManager.registerEvent(Type.PLAYER_JOIN, playerListener, Priority.Normal, this);
		pluginManager.registerEvent(Type.PLAYER_QUIT, playerListener, Priority.Normal, this);
		//pluginManager.registerEvent(Type.ENTITY_DEATH, playerListener, Priority.Normal, this);

		// On charge les donn�es de joueurs d�j� connect�s
		for (Player player : getServer().getOnlinePlayers())
		{
			if (this.dataLoader.playerExist(player.getName()))
			{
				// Le joueur existe, chargement des donn�es
				log.info("[MineteryaRPG] Le joueur " + player.getName() + " est connect�");
				this.dataLoader.loadPlayer(player);
			}
			else
			{
				// Le joueur n'existe pas
				log.info("[MineteryaRPG] Le joueur " + player.getName() + " n'existe pas dans la base de donn�es");
			}
		}

		// Fin du chargement du plugin
		log.info("[MineteryaRPG] Le plugin est charg�");
	}

	/**
	 * D�sactivation du plugin
	 */
	public void onDisable()
	{
		log.info("[MineteryaRPG] Desactivation du plugin");

		// On parcours la liste des RPGPlayer pour les sauvegarder et les d�charger
		for (RPGPlayer rpgPlayer : rpgPlayers.values())
		{
			dataLoader.savePlayer(rpgPlayer);
			this.rpgPlayers.remove(rpgPlayer);
		}

		// Fin de d�sactivation du plugin
		log.info("[MineteryaRPG] Le plugin est desactive");
	}

	/**
	 * Traitement des commandes
	 * @param CommandSender sender : Objet ayant �mis la commande
	 * @param Command cmd : Commande �mise
	 * @param String commandLabel : Label de la commande
	 * @param String args[] : Tableau d'arguments pass�s � la commande
	 * @return boolean onCommand : true si la commande a correctement �t� trait�e
	 */
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		// V�rification que la commande provient d'un joueur
		if (!(sender instanceof Player))
		{
			// La commande ne provient pas d'un joueur
			return false;
		}

		// R�cup�ration et ex�cution de la commande
		String command = cmd.getName();
		Player player = (Player) sender;
		/** @todo gestion des droits */
		return this.rpgCommands.handleCommand(command, player, args);
	}
}
