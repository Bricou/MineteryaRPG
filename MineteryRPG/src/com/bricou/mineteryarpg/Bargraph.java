package com.bricou.mineteryarpg;

/**
 * Bargraphe d'affichage de caratéristique
 * @author Bricou & Dr.Jack
 *
 */
public class Bargraph
{
	public Bargraph()
	{
		super();
 	}
	
	public String generate(int value)
	{
		StringBuffer bargraph = new StringBuffer();
		bargraph.append("[");
		for (int i=1; i<20; i++)
		{
			if ((i==5) || (i==10) || (i==15))
			{
				bargraph.append("+");
			}
			else if (i<value)
			{
				bargraph.append("#");
			}
			else
			{
				bargraph.append("-");
			}
		}
		bargraph.append("] ");
		bargraph.append(value);
		return bargraph.toString();
	}
}
