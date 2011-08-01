package com.bricou.mineteryarpg;

import java.util.logging.Logger;

import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Crochet d'événements par le joueur
 * @author Bricou & Dr.Jack
 * 
 */
public class RPGPlayerListener extends PlayerListener
{

	// Déclaration
	MineteryaRPG plugin;
	public Logger log = Logger.getLogger("Minecraft");

	/**
	 * Ecoute des événements du joueur
	 * @param MineteryaRPG instance : instance du plugin
	 */
	public RPGPlayerListener(MineteryaRPG instance)
	{
		this.plugin = instance;

	}

	/**
	 * Détection de la connection du joueur
	 * @param PlayerJoinEvent event : événement de connexion du joueur
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
	 * Détection de la déconnection du joueur
	 * @param PlayerQuitEvent : événement de dŽconnexion du joueur
	 */
	public void onPlayerQuit(PlayerQuitEvent event)
	{
		log.info("[MineteryaRPG] Deconnexion de : " + event.getPlayer().getName());
		if (this.plugin.dataLoader.playerExist(event.getPlayer().getName()))
		{
			RPGPlayer rpgPlayer = this.plugin.rpgPlayers.get(event.getPlayer());
			this.plugin.dataLoader.savePlayer(rpgPlayer);
			this.plugin.rpgPlayers.remove(event.getPlayer());
		}
	}

}
