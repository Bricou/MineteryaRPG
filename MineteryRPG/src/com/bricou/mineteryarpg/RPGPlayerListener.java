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
		log.info("Connexion de : " + event.getPlayer().getName());
	}
	
	public void onPlayerQuit(PlayerQuitEvent event)
	{
		log.info("Deconnexion de : " + event.getPlayer().getName());
	}
	
}
