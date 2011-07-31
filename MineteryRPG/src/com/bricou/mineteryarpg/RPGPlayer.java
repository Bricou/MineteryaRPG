package com.bricou.mineteryarpg;

public class RPGPlayer {

	private MineteryaRPG plugin;
	private String playerName;
	private String playerClasse;
	private float playerExperience;
	private int playerLevel;
	
	public RPGPlayer(String[] data, MineteryaRPG instance)
	{
		plugin = instance;
		this.playerName = data[0];
		this.playerClasse = data[1];
		this.playerExperience = Float.valueOf(data[2]);
		this.playerLevel = Integer.valueOf(data[3]);
	}
	
	public String getClasse()
	{
		return this.playerClasse;
	}
	
	public int getLevel()
	{
		return this.playerLevel;
	}
	
}
