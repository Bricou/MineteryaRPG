package com.bricou.mineteryarpg;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class RPGCommands {
	
	MineteryaRPG plugin;
	public Logger log = Logger.getLogger("Minecraft");
	
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
		else if (command.equalsIgnoreCase("rpgclasse"))
		{
			if (args.length == 0)
			{
				player.sendMessage(ChatColor.AQUA + "[MineteryaRPG] Liste des classes");
				player.sendMessage(ChatColor.AQUA + "Guerrier");
				player.sendMessage(ChatColor.AQUA + "Mage");
				return true;
			}
			else if (args.length == 1)
			{
				if (this.plugin.dataLoader.playerExist(player.getName()))
				{
					player.sendMessage(ChatColor.AQUA + "Vous avez deja choisi une classe !");
					return true;
				}
				else if (args[0].equalsIgnoreCase("guerrier"))
				{
					this.plugin.dataLoader.playerCreate(player, "guerrier");
					player.sendMessage(ChatColor.AQUA + "Vous avez choisi la classe guerrier !");
					return true;
				}
				else if (args[0].equalsIgnoreCase("mage"))
				{
					this.plugin.dataLoader.playerCreate(player, "mage");
					player.sendMessage(ChatColor.AQUA + "Vous avez choisi la classe mage !");
					return true;
				}
				player.sendMessage(ChatColor.AQUA + "Cette classe n'éxiste pas !");
				return false;
			}
		}
		else if (command.equalsIgnoreCase("rpgstats"))
		{
			if (args.length == 0)
			{
				//On affiche les stats du sender
				log.info("toto");
				RPGPlayer rpgPlayer = (RPGPlayer)this.plugin.rpgPlayers.get(player);
				log.info("titi");
				player.sendMessage(ChatColor.AQUA + "[MineteryaRPG] Informations personnage");
				player.sendMessage(ChatColor.AQUA + "Classe : " + rpgPlayer.getClasse());
				player.sendMessage(ChatColor.AQUA + "Niveau : " + String.valueOf(rpgPlayer.getLevel()));
				return true;
			}
			else if (args.length == 1)
			{
				//On affiche les stats du joueur demandé
				return true;
			}
			return false;
		}
		return false;
	}
	
}
