package Dice_Game;

import java.io.*;
import java.util.*;

public class Player implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * List of players
	 */
	static ArrayList<PlayerClass> playerList;
	
	/**
	 * Default Constructor
	 */
	public Player()
	{
		playerList = new ArrayList<PlayerClass>();
	}
	
	/**
	 * Add a player to the list of players
	 * 
	 * @param addMe Name of player
	 */
	public void addPlayer(PlayerClass addMe)
	{
		playerList.add(addMe);
	}
	
	/**
	 * Remove a player from the list of players
	 * 
	 * @param deleteMe Name of player
	 */
	public void deletePlayer(String deleteMe)
	{
		int pos = searchPlayer(deleteMe);
		if (pos != -1)
		{
			playerList.remove(pos);
		}
	}
	
	/**
	 * Search for a player
	 * 
	 * @param name Name of player
	 * 
	 * @return Index in player list
	 */
	public int searchPlayer(String name)
	{
		int pos = -1;
		for (int index = 0; index < playerList.size(); index++)
		{
			if(playerList.get(index).getName().equals(name))
			{
				pos = index;
			}
		}
		return pos;
	}
	

	public ArrayList<PlayerClass> getList()
	{
		return playerList;
	}
	
	/**
	 * Create a new player.
	 * 
	 * @param name Name of new player
	 * 
	 * @return player object
	 */
	public PlayerClass createPlayer(String name)
	{
		PlayerClass p1 = new PlayerClass(name);
		return p1;
	}
	
	/**
	 * Load player data from file.
	 * 
	 * @return List of players and their associated data
	 */
	public ArrayList<PlayerClass> loadList()
	{
		ArrayList<PlayerClass> load = null;
		
		File file = new File("TEST2.ser");
		if (file.exists())
		{
			try
		      {
		         FileInputStream fileIn = new FileInputStream(file);
		         ObjectInputStream in = new ObjectInputStream(fileIn);
		         load = (ArrayList<PlayerClass>) in.readObject();
		         in.close();
		         fileIn.close();
		      }catch(IOException i)
		      {
		         i.printStackTrace();
		         //return;
		      }catch(ClassNotFoundException c)
		      {
		         System.out.println("Save data not found");
		         c.printStackTrace();
		         //return;
		      }
		}
		else
		{
			load = Player.playerList;
		}
		
		return load;
	}
	
	/**
	 * Save player data to file
	 * 
	 * @param list List of players to be saved
	 */
	public void saveList(Player list)
	{
		try
	      {
	         FileOutputStream fileOut = new FileOutputStream("TEST2.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(Player.playerList);
	         out.close();
	         fileOut.close();
	      }catch(IOException i)
	      {
	          i.printStackTrace();
	      }
	}
	
	public String print()
	{
		String result = "";
		for (int index = 0; index < playerList.size(); index++)
		{
			result += playerList.get(index).getName() + " ";
		}
		return result;
	}
	
	/**
	 * Comparator method used to sort players based on their statistics. Statistics other than
	 * games played and losses only recorded on wins.
	 * 
	 * @param stat Statistic used for sorting
	 * 
	 * @return String used for leaderboard printing
	 */
	public String leaderBoard(String stat)
	{
		String leader = "";
		int rank = 1;
		
		if (stat.compareTo("wins") == 0)
		{
			Collections.sort(Player.playerList, PlayerClass.PlayerWinsComp);
			
			for (PlayerClass str: playerList)
			{
				if (str.getStats().getGamesPlayed() != 0)
				{
					leader += (rank + ". " + str.name + "\t" + str.getStats().getGamesWon() + " wins\n");
					rank++;
				}
			}
		}
		else if (stat.compareTo("games") == 0)
		{
			Collections.sort(Player.playerList, PlayerClass.PlayerGamesComp);
			
			for (PlayerClass str: playerList)
			{
				leader += (rank + ". " + str.name + "\t" + str.getStats().getGamesPlayed() + " games\n");
				rank++;
			}
		}
		else if (stat.compareTo("losses") == 0)
		{
			Collections.sort(Player.playerList, PlayerClass.PlayerLostComp);
			
			for (PlayerClass str: playerList)
			{
				if (str.getStats().getGamesPlayed() != 0)
				{
					leader += (rank + ". " + str.name + "\t" + str.getStats().getGamesLost() + " losses\n");
					rank++;
				}
			}
		}
		else if (stat.compareTo("bestRolls") == 0)
		{
			Collections.sort(Player.playerList, PlayerClass.PlayerBestRollsComp);
			
			for (PlayerClass str: playerList)
			{
				if (str.getStats().getGamesWon() != 0)
				{
					leader += (rank + ". " + str.name + "\t" + str.getStats().getLowestRolls() + " rolls\n");
					rank++;
				}
			}
		}
		else if (stat.compareTo("worstRolls") == 0)
		{
			Collections.sort(Player.playerList, PlayerClass.PlayerWorstRollsComp);
			
			for (PlayerClass str: playerList)
			{
				if (str.getStats().getGamesWon() != 0)
				{
					leader += (rank + ". " + str.name + "\t" + str.getStats().getHighestRolls() + " rolls\n");
					rank++;
				}
			}
		}
		
		return leader;
	}
	
	
	public static class PlayerClass implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		String name;
		Statistics stat;
		
		/**
		 * Default constructor
		 */
		public PlayerClass()
		{
			name = "";
			stat = new Statistics();
		}
		
		/**
		 * Override constructor
		 * 
		 * @param newName Name of new player
		 */
		public PlayerClass(String newName)
		{
			name = newName;
			stat = new Statistics();
		}
		
		/**
		 * Set the players name
		 * 
		 * @param thisName Name of player
		 */
		public void setName(String thisName)
		{
			name = thisName;
		}
		
		/**
		 * Retrieve player name.
		 * 
		 * @return Player name
		 */
		public String getName()
		{
			return name;
		}
		
		/**
		 * Retrieve the statistics for a player
		 * 
		 * @return Player Statistics
		 */
		public Statistics getStats()
		{
			return stat;
		}
		
		/**
		 * Comparator for the players list comparing number of games won.
		 */
		public static Comparator<PlayerClass> PlayerWinsComp = new Comparator<PlayerClass>()
		{
			public int compare(PlayerClass p1, PlayerClass p2)
			{
				int wins1 = p1.getStats().getGamesWon();
				int wins2 = p2.getStats().getGamesWon();
				
				return wins2 - wins1;
			}
		};
		
		/**
		 * Comparator for the players list comparing games played.
		 */
		public static Comparator<PlayerClass> PlayerGamesComp = new Comparator<PlayerClass>()
		{
			public int compare(PlayerClass p1, PlayerClass p2)
			{
				int wins1 = p1.getStats().getGamesPlayed();
				int wins2 = p2.getStats().getGamesPlayed();
				
				return wins2 - wins1;
			}
		};
		
		/**
		 * Comparator for the players list comparing games lost.
		 */
		public static Comparator<PlayerClass> PlayerLostComp = new Comparator<PlayerClass>()
		{
			public int compare(PlayerClass p1, PlayerClass p2)
			{
				int wins1 = p1.getStats().getGamesLost();
				int wins2 = p2.getStats().getGamesLost();
				
				return wins2 - wins1;
			}
		};
		
		/**
		 * Comparator for the players list comparing best rolls.
		 */
		public static Comparator<PlayerClass> PlayerBestRollsComp = new Comparator<PlayerClass>()
		{
			public int compare(PlayerClass p1, PlayerClass p2)
			{
				int wins1 = p1.getStats().getLowestRolls();
				int wins2 = p2.getStats().getLowestRolls();
				
				return wins1 - wins2;
			}
		};
		
		/**
		 * Comparator for the players list comparing worst rolls.
		 */
		public static Comparator<PlayerClass> PlayerWorstRollsComp = new Comparator<PlayerClass>()
		{
			public int compare(PlayerClass p1, PlayerClass p2)
			{
				int wins1 = p1.getStats().getHighestRolls();
				int wins2 = p2.getStats().getHighestRolls();
				
				return wins2 - wins1;
			}
		};

	}
}
