package com.bricou.mineteryarpg;

import java.util.logging.Logger;

import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerQuitEvent;


public class RPGPlayerListener extends PlayerListener {
	
	MineteryaRPG plugin;
	public Logger log = Logger.getLogger("Minecraft");
	
	public RPGPlayerListener(MineteryaRPG instance)
	{
		this.plugin = instance;
		
	}
	
	public void onPlayerJoin(PlayerJoinEvent event)
	{
		log.info("[MineteryaRPG] Connexion de : " + event.getPlayer().getName());
		//Si le joueur existe, on charge ses infos
		if (this.plugin.dataLoader.playerExist(event.getPlayer().getName()))
		{
			log.info("[MineteryaRPG] Le joueur " + event.getPlayer().getName() + " existe, on charge ses donnees");
		} else {
			log.info("[MineteryaRPG] Le joueur " + event.getPlayer().getName() + " n'existe pas dans la base de donnees");
		}
	}
	
	public void onPlayerQuit(PlayerQuitEvent event)
	{
		log.info("[MineteryaRPG] Deconnexion de : " + event.getPlayer().getName());
		if (this.plugin.dataLoader.playerExist(event.getPlayer().getName()))
		{
			log.info("[MineteryaRPG] Sauvegarde de : " + event.getPlayer().getName());
			this.plugin.dataLoader.playerSave(event.getPlayer().getName());
		}
	}
	
}
