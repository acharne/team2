package Dice_Game;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlayerTest {

	@Test
	public void test() {
		Player myPlayers = new Player();
		myPlayers.addPlayer(myPlayers.createPlayer("Adam"));
		for (int i = 0; i < 6; i++)
		{
			myPlayers.getList().get(myPlayers.searchPlayer("Adam")).getStats().incrementGamesWon();
		}
		
		myPlayers.addPlayer(myPlayers.createPlayer("Bob"));
		for (int i = 0; i < 10; i++)
		{
			myPlayers.getList().get(myPlayers.searchPlayer("Bob")).getStats().incrementGamesWon();
		}
		
		myPlayers.addPlayer(myPlayers.createPlayer("Sam"));
		for (int i = 0; i < 15; i++)
		{
			myPlayers.getList().get(myPlayers.searchPlayer("Sam")).getStats().incrementGamesWon();
		}
		
		String testLeader = myPlayers.leaderBoard("wins");
		
		assertEquals(testLeader, "Sam\t15 wins\nBob\t10 wins\nAdam\t6 wins\n");
	}

}
