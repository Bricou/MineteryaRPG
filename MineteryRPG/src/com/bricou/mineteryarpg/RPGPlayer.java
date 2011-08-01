package com.bricou.mineteryarpg;

/**
 * Entit� joueur
 * 
 * @author Bricou & Dr.Jack
 * 
 */
public class RPGPlayer
{

	// D�claration
	private MineteryaRPG plugin;
	private String playerName;
	private String playerClasse;
	private float playerExperience;
	private int playerLevel;

	/**
	 * Cr�ation du joueur
	 * @param String[] data : caract�ristiques du joueur
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
	 * R�cup�ration de la classe du joueur
	 * @return String : Classe du joueur
	 */
	public String getClasse()
	{
		return this.playerClasse;
	}

	/**
	 * R�cup�ration du niveau du joueur
	 * @return int : Niveau du joueur
	 */
	public int getLevel()
	{
		return this.playerLevel;
	}

	/**
	 * R�cup�ration du niveau d'exp�rience du joueur
	 * @return float : Niveau d'exp�rience du joueur
	 */
	public float getExperience()
	{
		return this.playerExperience;
	}

	/**
	 * R�cup�ration du nom du joueur
	 * @return String : Nom du joueur
	 */
	public String getName()
	{
		return this.playerName;
	}

}
