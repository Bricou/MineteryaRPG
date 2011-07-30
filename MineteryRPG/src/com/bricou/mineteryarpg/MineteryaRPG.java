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

public class MineteryaRPG extends JavaPlugin {
	
	private PluginManager pluginManager;
	public RPGPlayerListener playerListener;
	public DataLoader dataLoader;
	public RPGCommands rpgCommands;
	public HashMap<Player, RPGPlayer> rpgPlayers = new HashMap<Player, RPGPlayer>(); // Need explication
	public Logger log = Logger.getLogger("Minecraft");
	
	public void onEnable()
	{
		log.info("[MineteryaRPG] Initialisation du plugin");
		
		pluginManager = getServer().getPluginManager(); // ?
		playerListener = new RPGPlayerListener(this); // ?
		dataLoader = new DataLoader(this); // ?
		rpgCommands = new RPGCommands(this); // ?
		
		//Enregistrement des events
		pluginManager.registerEvent(Type.PLAYER_JOIN, playerListener, Priority.Normal, this);
		pluginManager.registerEvent(Type.PLAYER_QUIT, playerListener, Priority.Normal, this);
		
		//On charge les données de joueurs déjà connectés
		for (Player player : getServer().getOnlinePlayers())
		{
			if (this.dataLoader.playerExist(player.getName()))
			{
				//On charge les données du joueurs
			} else {
				//Le joueur n'existe pas
				log.info("[MineteryaRPG] Le joueur " + player.getName() + "n'existe pas dans la base de données");
			}
		}
		
		//Fin du chargement du plugin
		log.info("[MineteryaRPG] Le plugin est chargé");
	}
	
	public void onDisable()
	{
		
	}
	
	// Les commandes utilisable en jeu doivent être déclarées dans le fichier plugin.yml
	// Apparemment, on indique la classe principale (com.bricou.mineteryarpg.MineteryaRPG)
	// donc l'interception des commandes doit se faire ici
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		// ? Surrement pour savoir si c'est un joueur ou la console qui envoie la commande
		if (!(sender instanceof Player))
		{
			return false;
		}
		String command = cmd.getName(); // Recupere la commande (ie. rpghelp pour /rpghelp)
		Player player = (Player)sender; // On passe d'un type sender a un type player, obligé pour handleCommand qui demande un argument de type player
		
		// Ajouter le controle des permissions ici
		
		return this.rpgCommands.handleCommand(command, player, args);
	}
}
