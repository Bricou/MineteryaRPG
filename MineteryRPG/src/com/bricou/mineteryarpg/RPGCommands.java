package com.bricou.mineteryarpg;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * Gestion des commandes
 * @author Bricou & Dr.Jack
 * 
 */
public class RPGCommands
{

	// D�claration
	MineteryaRPG plugin;
	public Logger log = Logger.getLogger("Minecraft");

	/**
	 * Gestion des commandes
	 * @param Mineterya instance : instance du plugin
	 */
	public RPGCommands(MineteryaRPG instance)
	{
		plugin = instance;
	}

	/**
	 * R�cup�ration et traitement des commandes
	 * @param String command : commande pass�e par le joueur
	 * @param Player player : objet joueur
	 * @param String [] args : arguments pass�s dans la commande du joueur
	 * @return boolean : true si la commande a �t� correctement trait�e
	 */
	public boolean handleCommand(String command, Player player, String[] args)
	{
		if (command.equalsIgnoreCase("rpgaide"))
		{
			if (args.length == 0)
			{
				player.sendMessage(ChatColor.AQUA + "+---------------------------------------------------+");
				player.sendMessage(ChatColor.AQUA + "+ Mineterya RPG V1.0 par Bricou & Dr.Jack (08/2011)           +");
				player.sendMessage(ChatColor.AQUA + "+---------------------------------------------------+");
				player.sendMessage(ChatColor.AQUA + "[MineteryaRPG] Aide");
				player.sendMessage(ChatColor.AQUA + "/rpgaide - Affiche ce menu");
				player.sendMessage(ChatColor.AQUA + "/rpgaide classes - Affiche les informations de classes");
				player.sendMessage(ChatColor.AQUA + "/rpgstats - Affiche les statistiques du joueur");
				player.sendMessage(ChatColor.AQUA + "/rpgclasse [classe] - affecte une classe au joueur");
				return true;
			}
			else if (args.length == 1)
			{
				if (args[0].equalsIgnoreCase("classes"))
				{
					player.sendMessage(ChatColor.AQUA + "[MineteryaRPG] Informations sur les classes");
					player.sendMessage(ChatColor.AQUA + "Guerrier - Classe pouvant porter des coups puissants ou r�duire les d�g�ts subis");
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
					this.plugin.dataLoader.createPlayer(player, "guerrier");
					player.sendMessage(ChatColor.AQUA + "Vous avez choisi la classe guerrier !");
					return true;
				}
				else if (args[0].equalsIgnoreCase("mage"))
				{
					this.plugin.dataLoader.createPlayer(player, "mage");
					player.sendMessage(ChatColor.AQUA + "Vous avez choisi la classe mage !");
					return true;
				}
				player.sendMessage(ChatColor.AQUA + "Cette classe n'�xiste pas !");
				return false;
			}
		}
		else if (command.equalsIgnoreCase("rpgstats"))
		{
			if (args.length == 0)
			{
				if (this.plugin.dataLoader.playerExist(player.getName()))
				{
					// On affiche les stats du sender
					Bargraph bargraph = new Bargraph();
					RPGPlayer rpgPlayer = this.plugin.rpgPlayers.get(player);
					player.sendMessage(ChatColor.AQUA + "[MineteryaRPG] Informations personnage");
					player.sendMessage(ChatColor.AQUA + "Classe : " + rpgPlayer.getClasse());
					//player.sendMessage(ChatColor.AQUA + "Niveau : " + String.valueOf(rpgPlayer.getLevel()));
					player.sendMessage(ChatColor.AQUA + "Niveau : " + bargraph.generate(rpgPlayer.getLevel()));
					return true;
				}
				else
				{
					player.sendMessage(ChatColor.AQUA + "Vous n'avez pas choisi de classe !");
					return true;
				}
			}
			else if (args.length == 1)
			{
				// On affiche les stats du joueur demand�
				return true;
			}
			return false;
		}
		else if (command.equalsIgnoreCase("rpghash"))
		{
			log.info(this.plugin.rpgPlayers.toString());
		}
		return false;
	}

}
