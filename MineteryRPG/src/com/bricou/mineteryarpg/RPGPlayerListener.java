package com.bricou.mineteryarpg;

import java.util.logging.Logger;

import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Crochet d'思始ements par le joueur
 * @author Bricou & Dr.Jack
 *
 */
public class RPGPlayerListener extends PlayerListener
{

	// D残laration
	MineteryaRPG plugin;
	public Logger log = Logger.getLogger("Minecraft");

	
	/**
	 * Ecoute des 思始ements du joueur
	 * @param MineteryaRPG instance : instance du plugin 
	 */
	public RPGPlayerListener(MineteryaRPG instance)
	{
		this.plugin = instance;

	}

	
	/**
	 * D師ection de la connection du joueur
	 * @param PlayerJoinEvent event : 思始ement de connexion du joueur
	 */
	public void onPlayerJoin(PlayerJoinEvent event)
	{
		log.info("[MineteryaRPG] Connexion de : " + event.getPlayer().getName());
		// Si le joueur existe, on charge ses infos
		if (this.plugin.dataLoader.playerExist(event.getPlayer().getName()))
		{
			log.info("[MineteryaRPG] Le joueur " + event.getPlayer().getName() + " existe, on charge ses donnees");
		}
		else
		{
			log.info("[MineteryaRPG] Le joueur " + event.getPlayer().getName() + " n'existe pas dans la base de donnees");
		}
	}

	
	/**
	 * D師ection de la d残onnection du joueur
	 * @param PlayerQuitEvent : 思始ement de d残onnexion du joueur
	 */
	public void onPlayerQuit(PlayerQuitEvent event)
	{
		log.info("[MineteryaRPG] Deconnexion de : " + event.getPlayer().getName());
		if (this.plugin.dataLoader.playerExist(event.getPlayer().getName()))
		{
			log.info("[MineteryaRPG] Sauvegarde de : " + event.getPlayer().getName());
			this.plugin.dataLoader.savePlayer(event.getPlayer());
			this.plugin.rpgPlayers.remove(event.getPlayer());
		}
	}

}
