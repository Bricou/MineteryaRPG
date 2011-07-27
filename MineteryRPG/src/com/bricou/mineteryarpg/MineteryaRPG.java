package com.bricou.mineteryarpg;

import java.util.logging.Logger;

import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;

public class MineteryaRPG extends JavaPlugin {
	
	private PluginManager pluginManager;
	public RPGPlayerListener playerListener;
	public DataLoader dataLoader;
	public Logger log = Logger.getLogger("Minecraft");
	
	public void onEnable()
	{
		log.info("[MineteryaRPG] Chargement en cours ...");
		
		pluginManager = getServer().getPluginManager();
		playerListener = new RPGPlayerListener(this);
		dataLoader = new DataLoader(this);
		
		//Handler
		pluginManager.registerEvent(Type.PLAYER_JOIN, playerListener, Priority.Normal, this);
		pluginManager.registerEvent(Type.PLAYER_QUIT, playerListener, Priority.Normal, this);
	}
	
	public void onDisable()
	{
		
	}
}
