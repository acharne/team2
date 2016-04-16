package Dice_Game;

import static org.junit.Assert.*;

import org.junit.Test;

public class StatisticsTest {

	@Test
	public void testConstructor() {
		
		Statistics stats = new Statistics();
		
		assertNotNull(stats);
	}
	
	@Test
	public void testGamesPlayed() {
		
		Statistics stats = new Statistics();
		stats.incrementGamesPlayed();
		
		assertEquals(stats.getGamesPlayed(), 1);
	}
	
	@Test
	public void testGamesWon() {
		
		Statistics stats = new Statistics();
		stats.incrementGamesWon();
		
		assertEquals(stats.getGamesWon(), 1);
	}
	
	@Test
	public void testGamesLost() {
		
		Statistics stats = new Statistics();
		stats.incrementGamesLost();
		
		assertEquals(stats.getGamesLost(), 1);
	}
	
	@Test
	public void testLowestRolls() {
		
		Statistics stats = new Statistics();
		stats.setLowestRolls(5);
		
		assertEquals(stats.getLowestRolls(), 5);
	}
	
	@Test
	public void testHighestRolls() {
		
		Statistics stats = new Statistics();
		stats.setHighestRolls(5);
		
		assertEquals(stats.getHighestRolls(), 5);
	}
	
	@Test
	public void testToString() {
		
		Statistics stats = new Statistics();
		stats.incrementGamesPlayed();
		stats.incrementGamesPlayed();
		stats.incrementGamesWon();
		stats.incrementGamesLost();
		stats.setLowestRolls(19);
		stats.setHighestRolls(56);
		
		
		assertEquals(stats.toString(), "You've played 2 games with a record of 1 wins and 1 losses. "
				+ "You're best game used 19 rolls and you're worst game used 56 rolls.");
	}

}
