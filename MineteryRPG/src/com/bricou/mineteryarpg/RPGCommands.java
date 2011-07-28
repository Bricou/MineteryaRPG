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
		if (command.equalsIgnoreCase("rpgaide"))
		{
			if (args.length == 0)
			{
				player.sendMessage(ChatColor.AQUA + "[MineteryaRPG] Aide");
				player.sendMessage(ChatColor.AQUA + "/rpgaide - Affiche ce menu");
				player.sendMessage(ChatColor.AQUA + "/rpgaide classes - Affiche les informations à propos des classes");
				return true;
			}
			else if (args.length == 1)
			{
				if(args[0].equalsIgnoreCase("classes"))
				{
					player.sendMessage(ChatColor.AQUA + "[MineteryaRPG] Informations sur les classes");
					player.sendMessage(ChatColor.AQUA + "Guerrier - Classe pouvant porter des coups puissants ou réduire les dégâts subis");
					return true;
				}
				return false;
			}
		}
		return false;
	}
	
}
