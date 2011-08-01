package com.bricou.mineteryarpg;

/**
 * EntitŽ joueur
 * 
 * @author Bricou & Dr.Jack
 * 
 */
public class RPGPlayer
{

	// Déclaration
	private MineteryaRPG plugin;
	private String playerName;
	private String playerClasse;
	private float playerExperience;
	private int playerLevel;

	/**
	 * Création du joueur
	 * @param String[] data : caractéristiques du joueur
	 * @param instance
	 */
	public RPGPlayer(String[] data, MineteryaRPG instance)
	{
		plugin = instance;
		this.playerName = data[0];
		this.playerClasse = data[1];
		this.playerExperience = Float.valueOf(data[2]);
		this.playerLevel = Integer.valueOf(data[3]);
	}

	/**
	 * Récupération de la classe du joueur
	 * @return String : Classe du joueur
	 */
	public String getClasse()
	{
		return this.playerClasse;
	}

	/**
	 * Récupération du niveau du joueur
	 * @return int : Niveau du joueur
	 */
	public int getLevel()
	{
		return this.playerLevel;
	}

	/**
	 * Récupération du niveau d'expérience du joueur
	 * @return float : Niveau d'expérience du joueur
	 */
	public float getExperience()
	{
		return this.playerExperience;
	}

	/**
	 * Récupération du nom du joueur
	 * @return String : Nom du joueur
	 */
	public String getName()
	{
		return this.playerName;
	}

}
