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
	
	

}
