package com.bricou.mineteryarpg;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class RPGCommands {
	
	MineteryaRPG plugin;
	
	public RPGCommands(MineteryaRPG instance)
	{
		plugin = instance;
	}
	
	public boolean handleCommand(String command, Player player, String[] args)
	{
		if (command.equalsIgnoreCase("rpghelp")) {
			// Pas besoin de vérifier le nombre d'arguments
			player.sendMessage(ChatColor.AQUA + "[MineteryaRPG] Aide");
			player.sendMessage(ChatColor.AQUA + "/rpghelp - Affiche ce menu");
			return true;
		}
		return false;
	}
	
}
