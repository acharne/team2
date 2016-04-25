package Dice_Game;

import java.io.Serializable;
import java.util.Comparator;

import Dice_Game.Player.PlayerClass;

public class Statistics implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int gamesPlayed;
	private int gamesWon;
	private int gamesLost;
	private int lowestRolls;
	private int highestRolls;
	
	public Statistics()
	{
		gamesPlayed = 0;
		gamesWon = 0;
		gamesLost = 0;
		lowestRolls = 0;
		highestRolls = 0;
	}
	
	public void incrementGamesPlayed()
	{
		gamesPlayed++;
	}
	
	public int getGamesPlayed()
	{
		return gamesPlayed;
	}
	
	public void incrementGamesWon()
	{
		gamesWon++;
	}
	
	public int getGamesWon()
	{
		return gamesWon;
	}
	
	public void incrementGamesLost()
	{
		gamesLost++;
	}
	
	public int getGamesLost()
	{
		return gamesLost;
	}
	
	public void setLowestRolls(int lowRolls)
	{
		lowestRolls = lowRolls;
	}
	
	public int getLowestRolls()
	{
		return lowestRolls;
	}
	
	public void setHighestRolls(int highRolls)
	{
		highestRolls = highRolls;
	}
	
	public int getHighestRolls()
	{
		return highestRolls;
	}
	
	
	public String toString()
	{
		return "You've played " + gamesPlayed + " games with a record of " + gamesWon + 
				" wins and " + gamesLost + " losses. \nYou're best game used " + lowestRolls + 
				" rolls and you're worst game used " + highestRolls + " rolls.";
	}

}
