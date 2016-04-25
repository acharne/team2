package Dice_Game;

import java.util.Scanner;
public class gameWithoutGUI {

		static int currentRollValue;
		static int diceValues[];
		static int selectionP1;
		static int selectionP2;
		static int turn;
		static int p1goal;
		static int p2goal;
		static int p1penaltyTurns;
		static int p2penaltyTurns;
		static int choice;
		static int numDice;
		static int menuChoice;
		public static Player list;
		static Scanner scan = new Scanner(System.in);
		
		public static void main(String[] args)
		{
			gameWithoutGUI game = new gameWithoutGUI();
			
			//load existing player data
			Player.playerList = gameWithoutGUI.list.loadList();
			
			
			//print start menu with switch statement
			while (menuChoice != 1 || menuChoice != 2 || menuChoice != 3 || menuChoice != 4)
			{
				printMenu();
				menuChoice = scan.nextInt();
				
				switch (menuChoice)
				{
					case 1: //create player
						System.out.println("Please enter your name");
						String pName = scan.next();
						game.addPlayer(pName);
						if (game.searchForPlayer(pName) != false)
							System.out.println("Player Successfully Added");
						list.saveList(list);
						break;
						
					case 2: //delete player
						System.out.println("Please enter your name");
						pName = scan.next();
						game.removePlayer(pName);
						if (game.searchForPlayer(pName) == false)
							System.out.println("Player Successfully Removed");
						list.saveList(list);
						break;
						
					case 3://play game with 2 players
						String playerone = "";
						String playertwo = "";
						
						//request player name until found in player list
						while(game.searchForPlayer(playerone) == false)
						{
							System.out.println("To select player 1, Please type a valid account name");
							playerone = scan.next();
						}
						
						while(game.searchForPlayer(playertwo) == false)
						{
							System.out.println("To select player 2, Please type a valid account name");
							playertwo = scan.next();
						}
						
						//start a game with two players
						game.gameVsPlayer(playerone, playertwo);
						break;
						
					case 4:// play game vs computer
						String playeroneVsComputer = "";
						
						//request player name until found in player list
						while(game.searchForPlayer(playeroneVsComputer) == false)
						{
							System.out.println("To select player 1, Please type a valid account name");
							playeroneVsComputer = scan.next();
						}
						
						//start a game with one player
						game.gameVsComputer(playeroneVsComputer);
						break;
						
					case 5://view stats
						System.out.println("Please enter the name of the player you "
								+ "want to view the stats of.");
						String thisPlayer = scan.next();
						
						if(game.searchForPlayer(thisPlayer) == true)
						{
							System.out.println(game.findStats(thisPlayer));
						}
						else
						{
							System.out.println("Player does not exist");
						}
						break;
						
					case 6://print leaderboard
						System.out.println("Which leader board would you like to see?\n" 
								+ "1: Wins\n" + "2: Losses\n" + "3: Games Played\n" 
								+ "4: Best Rolls\n" + "5: Worst Rolls\n");
						int leaderChoice = scan.nextInt();
						switch(leaderChoice)
						{
						case 1:
							System.out.println(list.leaderBoard("wins"));
							break;
							
						case 2:
							System.out.println(list.leaderBoard("losses"));
							break;
							
						case 3:
							System.out.println(list.leaderBoard("games"));
							break;
							
						case 4:
							System.out.println(list.leaderBoard("bestRolls"));
							break;
							
						case 5:
							System.out.println(list.leaderBoard("worstRolls"));
							break;
							
							default:
								System.out.println(list.leaderBoard("wins"));
								break;
						}
						break;
				}
			}
			scan.close();
		}
		
		/**
		 * Prints the game menu.
		 */
		public static void printMenu()
		{
			System.out.println("Welcome to the ____ Game! \n 1: Create Player \n "
					+ "2: Delete Player \n 3: Play game (2 players) \n "
					+ "4: Play game vs computer (1 player) \n" + " 5: View Statistics\n" + 
					" 6: Check Leaderboard\n");
		}
		
		/**
		 * Initiates a text-based game.
		 */
		public gameWithoutGUI()
		{
			list = new Player();
		}
		
		/**
		 * Retrieves the current amount of penalty turns for player 1.
		 * 
		 * @return Player 1 penalty turns
		 */
		public int getP1PenaltyTurns()
		{
			return p1penaltyTurns;
		}
		
		/**
		 * Retrieves the current amount of penalty turns for player 2.
		 * 
		 * @return Player 2 penalty turns
		 */
		public int getP2PenaltyTurns()
		{
			return p2penaltyTurns;
		}
		
		/**
		 * Retrieves the value of the current dice roll.
		 * 
		 * @return Value of current dice roll
		 */
		public int returnCurrentRoll()
		{
			return currentRollValue;
		}
		
		/**
		 * Retrieves the current turn number (a count of how many turns have occurred).
		 * 
		 * @return The turn number
		 */
		public int getTurnCount()
		{
			return turn;
		}
		
		/**
		 * Retrieves the current amount of points needed to win for Player 1.
		 * 
		 * @return Player 1 points remaining
		 */
		public int getPlayerOnePoints()
		{
			return p1goal;
		}
		
		/**
		 * Retrieves the current amount of points needed to win for Player 2.
		 * 
		 * @return Player 2 points remaining
		 */
		public int getPlayerTwoPoints()
		{
			return p2goal;
		}
		
		/**
		 * Adds a player to the database.
		 * 
		 * @param name Name of player to be added
		 */
		public void addPlayer(String name)
		{
			list.addPlayer(list.createPlayer(name));
		}
		
		public void removePlayer(String name)
		{
			list.deletePlayer(name);
		}
		
		/**
		 * Search the database for a player.
		 * 
		 * @param name Name of player to be searched
		 * 
		 * @return True if player is in database, false otherwise
		 */
		public boolean searchForPlayer(String name)//does this player exist? T/F
		{
			boolean exists = false;
			if(list.searchPlayer(name) != -1)
			{
				exists = true;
			}
			return exists;
		}
		
		/**
		 * Retrieve the statistics for a player.
		 * 
		 * @param name Name of player to be searched.
		 * 
		 * @return String of players current statistics.
		 */
		public String findStats(String name)
		{
			String listOfStats = "";
			if (searchForPlayer(name) == true)
			{
				listOfStats = list.getList().get(list.searchPlayer(name)).getStats().toString();
			}
			return listOfStats;
		}
		
		/**
		 * Determines if a number is even.
		 * 
		 * @param num Number to check
		 * 
		 * @return True if even, false otherwise
		 */
		public static boolean isEven(int num)
		{
			if(num%2 == 0)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		
		/**
		 * Find the list index of player 1.
		 * 
		 * @param name Name of player 1
		 */
		public static void selectPlayerOne(String name)// return true if selected successfully
		{
			selectionP1 = list.searchPlayer(name);
		}
		
		/**
		 * Find the list index of player 2.
		 * 
		 * @param name Name of player 2
		 */
		public static void selectPlayerTwo(String name)// return true if selected successfully
		{
			selectionP2 = list.searchPlayer(name);
		}
		
		/**
		 * Initiates a two player game.
		 * 
		 * @param p1 Name of first player
		 * 
		 * @param p2 Name of second player
		 */
		public void gameVsPlayer(String p1, String p2)
		{
			p1penaltyTurns = 0;
			p2penaltyTurns = 0;
			choice = -1;// must be determined by actionListener for the button in the game each turn
			turn = 1;
			p1goal = 50;
			p2goal = 50;
			dice diceRoll = new dice();
			selectPlayerOne(p1);
			selectPlayerTwo(p2);
			
			while(p1goal != 0 && p2goal != 0)
			{
				System.out.println(p1 + "'s score: " + p1goal + " | " + p2 + "'s Score: " + p2goal + "\n\n");
				if(isEven(turn) == false)// player 1 turn
				{
					//calculate choice here via actionListener
					System.out.println("Select a choice: \n 0: Roll dice to subtract from your score. "
							+ "\n 1: Roll penalty dice to add to your opponent's score \n");
					choice = scan.nextInt();
					
					//can't subtract from opponent while under penalty
					while (choice == 1 && p1penaltyTurns > 0)
					{
						System.out.println("Cannot add to your opponent's score for " + p1penaltyTurns
								+ " more turns! \n Enter choice: \n");
						choice = scan.nextInt();
					}
					
					if (choice == 0)// choose to roll dice to subtract from own score
					{
						System.out.println("How many dice do you want to roll? (1, 2, or 3)");
						
						numDice = scan.nextInt(); //choose number of dice to roll
						
						diceValues = diceRoll.roll(numDice); //store rolls in array
						
						currentRollValue = diceRoll.sum(numDice); //total roll value
						
						//individual dice values
						for(int printDice = 0; printDice < numDice; printDice++)
						{
							if(printDice == 0)
							{
								System.out.println("Your first die: " + diceValues[printDice]);
							}
							else if(printDice == 1)
							{
								System.out.println("Your second die: " + diceValues[printDice]);
							}
							else if(printDice == 2)
							{
								System.out.println("Your third die: " + diceValues[printDice]);
							}
						}
						
						if((p1goal - currentRollValue) >= 0)
						{
							p1goal = p1goal - currentRollValue;
							System.out.println("You rolled a " + currentRollValue + ", " 
							+ p1goal + " more to go! \n");
						}
						else
						{
							System.out.println("You rolled a " + currentRollValue);
							System.out.println("Surpassed goal, points not deducted!");
						}
					}
					else if (choice == 1)// choose to roll penalty dice to add to opponent score
					{
						System.out.print("How many dice do you want to roll? (1, 2, or 3)");
						
						numDice = scan.nextInt();
						
						diceValues = diceRoll.roll(numDice);
						
						currentRollValue = diceRoll.sum(numDice);
						
						//individual dice values
						for(int printDice = 0; printDice < numDice; printDice++)
						{
							if(printDice == 0)
							{
								System.out.println("First die: " + diceValues[printDice]);
							}
							else if(printDice == 1)
							{
								System.out.println("Second die: " + diceValues[printDice]);
							}
							else if(printDice == 2)
							{
								System.out.println("Third die: " + diceValues[printDice]);
							}
						}
						
						if(p1penaltyTurns == 0)
						{
							p1penaltyTurns = 3;
							p2goal += currentRollValue;
							System.out.println(currentRollValue + " added to opponents score. \n");
						}
						
					}
					turn++;
					if(p1penaltyTurns > 0)
					{
						p1penaltyTurns --;
					}
				}
				else// player 2 turn
				{
					//calculate choice here via actionListener
					System.out.println("Select a choice: \n 0: Roll dice to subtract from your score. "
							+ "\n 1: Roll penalty dice to add to your opponent's score \n");
					choice = scan.nextInt();
					
					//can't subtract from opponent while under penalty
					while (choice == 1 && p2penaltyTurns > 0)
					{
						System.out.println("Cannot add to your opponent's score for " + p2penaltyTurns
								+ " more turns! \n Enter choice: \n");
						choice = scan.nextInt();
					}
					
					if (choice == 0)// choose to roll dice to subtract from own score
					{
						System.out.println("How many dice do you want to roll? (1, 2, or 3)");
						
						numDice = scan.nextInt(); //choose number of dice to roll
						
						diceValues = diceRoll.roll(numDice); //store rolls in array
						
						currentRollValue = diceRoll.sum(numDice); //total roll value
						
						//individual dice values
						for(int printDice = 0; printDice < numDice; printDice++)
						{
							if(printDice == 0)
							{
								System.out.println("Your first die: " + diceValues[printDice]);
							}
							else if(printDice == 1)
							{
								System.out.println("Your second die: " + diceValues[printDice]);
							}
							else if(printDice == 2)
							{
								System.out.println("Your third die: " + diceValues[printDice]);
							}
						}
						
						if((p2goal - currentRollValue) >= 0)
						{
							p2goal = p2goal - currentRollValue;
							System.out.println("You rolled a " + currentRollValue + ", " 
							+ p2goal + " more to go! \n");
						}
						else
						{
							System.out.println("You rolled a " + currentRollValue);
							System.out.println("Surpassed goal, points not deducted!");
						}
					}
					else if (choice == 1)// choose to roll penalty dice to add to opponent score
					{
						System.out.print("How many dice do you want to roll? (1, 2, or 3)");
						
						numDice = scan.nextInt();
						
						diceValues = diceRoll.roll(numDice);
						
						currentRollValue = diceRoll.sum(numDice);
						
						//individual dice values
						for(int printDice = 0; printDice < numDice; printDice++)
						{
							if(printDice == 0)
							{
								System.out.println("First die: " + diceValues[printDice]);
							}
							else if(printDice == 1)
							{
								System.out.println("Second die: " + diceValues[printDice]);
							}
							else if(printDice == 2)
							{
								System.out.println("Third die: " + diceValues[printDice]);
							}
						}
						
						if(p2penaltyTurns == 0)
						{
							p2penaltyTurns = 3;
							p2goal += currentRollValue;
							System.out.println(currentRollValue + " added to opponents score. \n");
						}
						
					}
					turn++;
					if(p2penaltyTurns > 0)
					{
						p2penaltyTurns --;
					}
				}
			}//game ended, update stats data now
			
			if(p1goal == 0)// p1 won, p2 lost update stats.
			{
				/*Player one will always have the odd turn and win with half the rolls
				 * plus the winning roll*/
				int p1Rolls = (turn / 2) + 1;
				
				System.out.println(list.getList().get(selectionP1).getName() + " wins!");
				
				//update player one wins and games
				list.getList().get(selectionP1).getStats().incrementGamesWon();
				list.getList().get(selectionP1).getStats().incrementGamesPlayed();
				
				//update player two losses and games
				list.getList().get(selectionP2).getStats().incrementGamesPlayed();
				list.getList().get(selectionP2).getStats().incrementGamesLost();
				
				//if turns is < current record for lowest rolls, update
				if(list.getList().get(selectionP1).getStats().getLowestRolls() > p1Rolls || 
						list.getList().get(selectionP1).getStats().getLowestRolls() == 0)
				{
					list.getList().get(selectionP1).getStats().setLowestRolls(p1Rolls);
				}
				if(list.getList().get(selectionP1).getStats().getHighestRolls() < p1Rolls || 
						list.getList().get(selectionP1).getStats().getHighestRolls() == 0)
				{
					list.getList().get(selectionP1).getStats().setHighestRolls(p1Rolls);
				}
				
				list.saveList(list);
			}
			if(p2goal == 0)//p2 won, p1 lost
			{
				int p2Rolls = turn / 2;
				
				//update player one games played/lost
				list.getList().get(selectionP1).getStats().incrementGamesPlayed();
				list.getList().get(selectionP1).getStats().incrementGamesLost();
				
				//update player two games played/won
				list.getList().get(selectionP2).getStats().incrementGamesPlayed();
				list.getList().get(selectionP2).getStats().incrementGamesWon();
				
				//if rolls is < current record for lowest rolls, update
				if(list.getList().get(selectionP2).getStats().getLowestRolls() > p2Rolls)
				{
					list.getList().get(selectionP2).getStats().setLowestRolls(p2Rolls);
				}
				if(list.getList().get(selectionP2).getStats().getHighestRolls() < p2Rolls)
				{
					list.getList().get(selectionP2).getStats().setHighestRolls(p2Rolls);
				}
			}
			
		}//end entire game
		
		/**
		 * Initiates a one player game.
		 * 
		 * @param name Name of player
		 */
		public void gameVsComputer (String name)
		{
			p1penaltyTurns = 0;
			p2penaltyTurns = 0;//aka computer
			choice = -1;// must be determined by actionListener for the button in the game each turn(only for the player)
			turn = 1;
			p1goal = 50;
			p2goal = 50;//aka computer
			dice diceRoll = new dice();
			
			selectPlayerOne(name);

			while(p1goal != 0 && p2goal != 0)
			{
				System.out.println("Your score: " + p1goal + " | Opponent Score: " + p2goal + "\n\n");
				if(isEven(turn) == false)// player 1 turn
				{
					//calculate choice here via actionListener
					System.out.println("Select a choice: \n 0: Roll dice to subtract from your score. "
							+ "\n 1: Roll penalty dice to add to your opponent's score.");
					
					choice = scan.nextInt();
					
					//can't subtract from opponent while under penalty
					while (choice == 1 && p1penaltyTurns > 0)
					{
						System.out.println("Cannot add to your opponent's score for " + p1penaltyTurns
								+ " more turns! \n Enter choice: \n");
						choice = scan.nextInt();
					}
					
					if (choice == 0)// choose to roll dice to subtract from own score
					{
						System.out.println("How many dice do you want to roll? (1, 2, or 3)");
						
						numDice = scan.nextInt(); //choose number of dice to roll
						
						diceValues = diceRoll.roll(numDice); //store rolls in array
						
						currentRollValue = diceRoll.sum(numDice); //total roll value
						
						//individual dice values
						for(int printDice = 0; printDice < numDice; printDice++)
						{
							if(printDice == 0)
							{
								System.out.println("Your first die: " + diceValues[printDice]);
							}
							else if(printDice == 1)
							{
								System.out.println("Your second die: " + diceValues[printDice]);
							}
							else if(printDice == 2)
							{
								System.out.println("Your third die: " + diceValues[printDice]);
							}
						}
						
						if((p1goal - currentRollValue) >= 0)
						{
							p1goal = p1goal - currentRollValue;
							System.out.println("You rolled a " + currentRollValue + ", " 
							+ p1goal + " more to go! \n");
						}
						else
						{
							System.out.println("You rolled a " + currentRollValue);
							System.out.println("Surpassed goal, points not deducted!");
						}
					}
					else if (choice == 1)// choose to roll penalty dice to add to opponent score
					{
						System.out.print("How many dice do you want to roll? (1, 2, or 3)");
						
						numDice = scan.nextInt();
						
						diceValues = diceRoll.roll(numDice);
						
						currentRollValue = diceRoll.sum(numDice);
						
						//individual dice values
						for(int printDice = 0; printDice < numDice; printDice++)
						{
							if(printDice == 0)
							{
								System.out.println("First die: " + diceValues[printDice]);
							}
							else if(printDice == 1)
							{
								System.out.println("Second die: " + diceValues[printDice]);
							}
							else if(printDice == 2)
							{
								System.out.println("Third die: " + diceValues[printDice]);
							}
						}
						
						if(p1penaltyTurns == 0)
						{
							p1penaltyTurns = 3;
							p2goal += currentRollValue;
							System.out.println(currentRollValue + " added to opponents score. \n");
						}
						
					}
					turn++;
					if(p1penaltyTurns > 0)
					{
						p1penaltyTurns --;
					}
				}
				else// Computer's turn
				{
					//calculate choice here via intel
					if(p1goal < 20 && p2penaltyTurns == 0)
					{
						choice = 1;
						
					}
					else
					{
						choice = 0;
					}
					//end choice logic
					
					if(choice == 0)
					{
						numDice = diceRoll.computerDice(p2goal);
						
						diceValues = diceRoll.roll(numDice);
						
						currentRollValue = diceRoll.sum(numDice);
						
						//individual dice values
						for(int printDice = 0; printDice < numDice; printDice++)
						{
							if(printDice == 0)
							{
								System.out.println("Opponent's first die: " + diceValues[printDice]);
							}
							else if(printDice == 1)
							{
								System.out.println("Opponent's second die: " + diceValues[printDice]);
							}
							else if(printDice == 2)
							{
								System.out.println("Opponent's third die: " + diceValues[printDice]);
							}
						}
						
						if((p2goal - currentRollValue) >= 0)
						{
							p2goal = p2goal - currentRollValue;
							System.out.println("Your opponent rolled a " + currentRollValue + 
									", " + p2goal + " more to go. \n");
						}
						else
						{
							System.out.println("Your opponent rolled a " + currentRollValue);
							System.out.println("Surpassed goal, points not deducted!");
						}
					}
					else if (choice == 1)// choose to roll penalty dice to add to opponent score
					{
						diceValues = diceRoll.roll(3);
						
						currentRollValue = diceRoll.sum(3);
						
						//individual dice values
						for(int printDice = 0; printDice < numDice; printDice++)
						{
							if(printDice == 0)
							{
								System.out.println("Opponent's first die: " + diceValues[printDice]);
							}
							else if(printDice == 1)
							{
								System.out.println("Opponent's second die: " + diceValues[printDice]);
							}
							else if(printDice == 2)
							{
								System.out.println("Opponent's third die: " + diceValues[printDice]);
							}
						}
						if(p2penaltyTurns == 0)
						{
							p2penaltyTurns = 3;
							p1goal += currentRollValue;
							System.out.println("Your opponent added " + currentRollValue 
									+ " to your score! \n");
						}
					}
					turn++;
					if(p2penaltyTurns > 0)
					{
						p2penaltyTurns --;
					}
				}
			}//game ended, update stats data now
			if(p1goal == 0)// p1 won, computer lost update stats.
			{
				System.out.println(list.getList().get(selectionP1).getName() + " wins!");
				
				list.getList().get(selectionP1).getStats().incrementGamesWon();
				list.getList().get(selectionP1).getStats().incrementGamesPlayed();
				
				//if turns is < current record for lowest rolls, update
				if(list.getList().get(selectionP1).getStats().getLowestRolls() > turn || 
						list.getList().get(selectionP1).getStats().getLowestRolls() == 0)
				{
					list.getList().get(selectionP1).getStats().setLowestRolls(turn);
				}
				if(list.getList().get(selectionP1).getStats().getHighestRolls() < turn || 
						list.getList().get(selectionP1).getStats().getHighestRolls() == 0)
				{
					list.getList().get(selectionP1).getStats().setHighestRolls(turn);
				}
				
				list.saveList(list);
			}
			else// computer won then, update p1 stats
			{
				list.getList().get(selectionP1).getStats().incrementGamesLost();
				list.getList().get(selectionP1).getStats().incrementGamesPlayed();
				if(list.getList().get(selectionP1).getStats().getLowestRolls() > turn || 
						list.getList().get(selectionP1).getStats().getLowestRolls() == 0)
				{
					list.getList().get(selectionP1).getStats().setLowestRolls(turn);
				}
				if(list.getList().get(selectionP1).getStats().getHighestRolls() < turn || 
						list.getList().get(selectionP1).getStats().getHighestRolls() == 0)
				{
					list.getList().get(selectionP1).getStats().setHighestRolls(turn);
				}
				
				list.saveList(list);

				
			}
			
		}
	}