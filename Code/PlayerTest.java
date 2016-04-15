package GameStuff;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlayerTest {

	@Test
	public void testConstructor() {
		Player test1 = new Player();
		test1.addPlayer(test1.createPlayer("john"));
		assertEquals(test1.getList().get(0).getName(), "john");
	}

	@Test
	public void testSearch() {
		Player test1 = new Player();
		test1.addPlayer(test1.createPlayer("jim"));
		assertEquals(test1.searchPlayer("jim"), 0);
		test1.addPlayer(test1.createPlayer("hello"));
		assertEquals(test1.searchPlayer("hello"), 1);
	}
	
	@Test
	public void testDelete() {
		Player test1 = new Player();
		test1.addPlayer(test1.createPlayer("john"));
		assertEquals(test1.getList().get(0).getName(), "john");
		assertEquals(test1.getList().size(), 1);
		test1.deletePlayer("john");
		assertEquals(0, test1.getList().size());
	}
	
	@Test
	public void testDelete2() {
		Player test1 = new Player();
		test1.addPlayer(test1.createPlayer("john"));
		assertEquals(test1.getList().get(0).getName(), "john");
		assertEquals(test1.getList().size(), 1);
		test1.addPlayer(test1.createPlayer("jim"));
		test1.deletePlayer("john");
		assertEquals(1, test1.getList().size());
		assertEquals("jim", test1.getList().get(0).getName());
	}
	
	@Test
	public void testAll()
	{
		Player test1 = new Player();
		test1.addPlayer(test1.createPlayer("Andy"));
		test1.addPlayer(test1.createPlayer("Jim"));
		test1.addPlayer(test1.createPlayer("John"));
		test1.addPlayer(test1.createPlayer("Bill"));
		test1.addPlayer(test1.createPlayer("Ariel"));
		test1.addPlayer(test1.createPlayer("Bob"));
		assertEquals(6, test1.getList().size());
		test1.deletePlayer("Jim");
		assertEquals(5, test1.getList().size());
		assertEquals(test1.print(), "Andy John Bill Ariel Bob ");
		test1.deletePlayer("Bill");
		assertEquals(test1.print(), "Andy John Ariel Bob ");
		test1.addPlayer(test1.createPlayer("Bill"));
		assertEquals(test1.print(), "Andy John Ariel Bob Bill ");
		assertEquals(2, test1.searchPlayer("Ariel"));
	}
	
	@Test
	public void testDeleteLast()
	{
		Player test1 = new Player();
		test1.addPlayer(test1.createPlayer("Andy"));
		test1.addPlayer(test1.createPlayer("Jim"));
		test1.addPlayer(test1.createPlayer("John"));
		test1.addPlayer(test1.createPlayer("Bill"));
		test1.addPlayer(test1.createPlayer("Ariel"));
		test1.addPlayer(test1.createPlayer("Bob"));
		test1.deletePlayer("Bob");
		assertEquals(test1.print(), "Andy Jim John Bill Ariel ");
	}
	
	@Test
	public void testStats()
	{
		Player test1 = new Player();
		test1.addPlayer(test1.createPlayer("Andy"));
		assertEquals(0, test1.getList().get(test1.searchPlayer("Andy")).getStats().getGamesPlayed());
		test1.getList().get(test1.searchPlayer("Andy")).getStats().incrementGamesPlayed();
		assertEquals(1, test1.getList().get(test1.searchPlayer("Andy")).getStats().getGamesPlayed());
		
	}
}
