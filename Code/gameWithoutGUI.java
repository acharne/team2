package GameStuff;
import java.util.*;
import java.util.Scanner;
public class gameWithoutGUI {
		static int currentRollValue;
		static int selectionP1;
		static int selectionP2;
		static int turn;
		static int p1goal;
		static int p2goal;
		static int p1penaltyTurns;
		static int p2penaltyTurns;
		static int choice;
		static int menuChoice;
		public static Player list;
		static Scanner scan = new Scanner(System.in);
		public static void main(String args)
		{
			gameWithoutGUI game = new gameWithoutGUI();
			//print start menu with switch statement
			while (menuChoice != 1 || menuChoice != 2 || menuChoice != 3 || menuChoice != 4)
			{
			printMenu();
			switch (menuChoice){
			case 1: //chose create player
				System.out.println("Please enter your name");
				String pName = scan.next();
				game.addPlayer(pName);
				System.out.println("Player Successfully Added");
				break;
			case 2: //chose view stats
				System.out.println("Please enter the name of the player you want to view the stats of.");
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
			case 3://chose play game 2 player
				String playerone="";
				String playertwo="";
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
				game.gameVsPlayer(playerone, playertwo);
				break;
			case 4:// chose play game vs computer
				String playeroneVsComputer="";
				while(game.searchForPlayer(playeroneVsComputer) == false)
				{
					System.out.println("To select player 1, Please type a valid account name");
					playeroneVsComputer = scan.next();
				}
				game.gameVsComputer(playeroneVsComputer);
				break;
			}
			}
			scan.close();
		}
		public static void printMenu()
		{
			System.out.println("Welcome to the ____ Game! \n 1: Create Player \n 2: View Statistics \n 3: Play game (2 players) \n 4: Play game vs computer (1 player) \n");
		}
		public gameWithoutGUI()
		{
			list = new Player();
		}
		public int getP1PenaltyTurns()
		{
			return p1penaltyTurns;
		}
		public int getP2PenaltyTurns()
		{
			return p2penaltyTurns;
		}
		public int returnCurrentRoll()
		{
			return currentRollValue;
		}
		public int getTurnCount()
		{
			return turn;
		}
		public int getPlayerOnePoints()
		{
			return p1goal;
		}
		public int getPlayerTwoPoints()
		{
			return p2goal;
		}
		public void addPlayer(String name)
		{
			list.addPlayer(list.createPlayer(name));
		}
		public boolean searchForPlayer(String name)//does this player exist? T/F
		{
			boolean exists = false;
			if(list.searchPlayer(name) != -1)
			{
				exists = true;
			}
			return exists;
		}
		public String findStats(String name)
		{
			String listOfStats = "";
			if (searchForPlayer(name) == true)
			{
				listOfStats = list.getList().get(list.searchPlayer(name)).getStats().toString();
			}
			return listOfStats;
		}
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
		public static void selectPlayerOne(String name)// return true if selected successfully
		{
			selectionP1 = list.searchPlayer(name);
		}
		public static void selectPlayerTwo(String name)// return true if selected successfully
		{
			selectionP2 = list.searchPlayer(name);
		}
		//initiate game
		public void gameVsPlayer(String p1, String p2)
		{
			p1penaltyTurns = 0;
			p2penaltyTurns = 0;
			choice = -1;// must be determined by actionListener for the button in the game each turn
			turn = 1;
			p1goal = 100;
			p2goal = 100;
			dice diceRoll = new dice();
			selectPlayerOne(p1);
			selectPlayerTwo(p2);
			
			while(p1goal != 0 || p2goal != 0)
			{
				if(isEven(turn) == false)// player 1 turn
				{
					currentRollValue = diceRoll.roll();
					//calculate choice here via actionListener
					System.out.println("Select a choice: \n 0: Roll dice to subtract from your score. \n 1: Roll penalty dice to add to your opponent's score \n");
					choice = scan.nextInt();
					
					if (choice == 0)// choose to roll dice to subtract from own score
					{
						if((p1goal - currentRollValue) >= 0)
						{
							p1goal = p1goal - currentRollValue;
						}
					}
					else if (choice == 1)// choose to roll penalty dice to add to opponent score
					{
						if(p1penaltyTurns == 0)
						{
							p1penaltyTurns = 3;
							p2goal += currentRollValue;
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
					currentRollValue = diceRoll.roll();
					//calculate choice here via actionListener
					if(choice == 0)
					{
						if((p2goal - currentRollValue) >= 0)
						{
							p2goal = p2goal - currentRollValue;
						}
					}
					else if (choice == 1)// choose to roll penalty dice to add to opponent score
					{
						if(p2penaltyTurns == 0)
						{
							p2penaltyTurns = 3;
							p1goal += currentRollValue;
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
				list.getList().get(selectionP1).getStats().incrementGamesWon();
				list.getList().get(selectionP1).getStats().incrementGamesPlayed();
				list.getList().get(selectionP2).getStats().incrementGamesPlayed();
				list.getList().get(selectionP2).getStats().incrementGamesLost();
				//if turns is < current record for lowest rolls, update
				if(list.getList().get(selectionP1).getStats().getLowestRolls() > turn)
				{
					list.getList().get(selectionP1).getStats().setLowestRolls(turn);
				}
				if(list.getList().get(selectionP1).getStats().getHighestRolls() < turn)
				{
					list.getList().get(selectionP1).getStats().setHighestRolls(turn);
				}
				
			}
			if(p2goal == 0)//p2 won, p1 lost
			{
				list.getList().get(selectionP1).getStats().incrementGamesPlayed();
				list.getList().get(selectionP1).getStats().incrementGamesLost();
				list.getList().get(selectionP2).getStats().incrementGamesPlayed();
				list.getList().get(selectionP2).getStats().incrementGamesWon();
				//if turns is < current record for lowest rolls, update
				if(list.getList().get(selectionP2).getStats().getLowestRolls() > turn)
				{
					list.getList().get(selectionP2).getStats().setLowestRolls(turn);
				}
				if(list.getList().get(selectionP2).getStats().getHighestRolls() < turn)
				{
					list.getList().get(selectionP2).getStats().setHighestRolls(turn);
				}
			}
			
		}//end entire game
		
		public void gameVsComputer (String name)
		{
			p1penaltyTurns = 0;
			p2penaltyTurns = 0;//aka computer
			choice = -1;// must be determined by actionListener for the button in the game each turn(only for the player)
			turn = 1;
			p1goal = 100;
			p2goal = 100;//aka computer
			dice diceRoll = new dice();
			
			selectPlayerOne(name);

			
			while(p1goal != 0 || p2goal != 0)
			{
				if(isEven(turn) == false)// player 1 turn
				{
					//calculate choice here via actionListener
					System.out.println("Select a choice: \n 0: Roll dice to subtract from your score. \n 1: Roll penalty dice to add to your opponent's score \n");
					choice = scan.nextInt();
					if (choice == 0)// choose to roll dice to subtract from own score
					{
						currentRollValue = diceRoll.roll();
						if((p1goal - currentRollValue) >= 0)
						{
							p1goal = p1goal - currentRollValue;
						}
					}
					else if (choice == 1)// choose to roll penalty dice to add to opponent score
					{
						if(p1penaltyTurns == 0)
						{
							p1penaltyTurns = 3;
							p2goal += currentRollValue;
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
					currentRollValue = diceRoll.roll();
					if(choice == 0)
					{
						if((p2goal - currentRollValue) >= 0)
						{
							p2goal = p2goal - currentRollValue;
						}
					}
					else if (choice == 1)// choose to roll penalty dice to add to opponent score
					{
						if(p2penaltyTurns == 0)
						{
							p2penaltyTurns = 3;
							p1goal += currentRollValue;
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
				list.getList().get(selectionP1).getStats().incrementGamesWon();
				list.getList().get(selectionP1).getStats().incrementGamesPlayed();
				//if turns is < current record for lowest rolls, update
				if(list.getList().get(selectionP1).getStats().getLowestRolls() > turn)
				{
					list.getList().get(selectionP1).getStats().setLowestRolls(turn);
				}
				if(list.getList().get(selectionP1).getStats().getHighestRolls() < turn)
				{
					list.getList().get(selectionP1).getStats().setHighestRolls(turn);
				}
				
			}
			else// computer won then, update p1 stats
			{
				list.getList().get(selectionP1).getStats().incrementGamesLost();
				list.getList().get(selectionP1).getStats().incrementGamesPlayed();
				if(list.getList().get(selectionP1).getStats().getLowestRolls() > turn)
				{
					list.getList().get(selectionP1).getStats().setLowestRolls(turn);
				}
				if(list.getList().get(selectionP1).getStats().getHighestRolls() < turn)
				{
					list.getList().get(selectionP1).getStats().setHighestRolls(turn);
				}
			}
			
		}
	}


