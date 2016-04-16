package cse360assign3;

/**
* Arizona State University
* CSE 360 - SOFTWARE ENGINEERING
* Final Project
* @author Team 2
* @version 04.15.2016
* 
* Description: This class tests the Dice class.
*/

import static org.junit.Assert.*;

import org.junit.Test;

public class DiceTest {

	@Test
	/**
	 * This method verify if the constructor does not return null
	 */
	public void testDiceObject(){
		Dice dice = new Dice();
		assertNotNull(dice);
	}
	
	@Test
	/**
	 * This method verify if the dice is within the range of values (1, 2, 3, 4, 5, 6)
	 */
	public void testValues(){
		
		Dice dice = new Dice();
		for(int i = 0; i < 1000000; i++){
			if(dice.roll() > 6 || dice.roll() < 1)
				fail("Value unacceptable.");
		}
		
	}
	
	@Test
	/**
	 * This method verify the precision of the dice, considering that precision above 5% is unacceptable
	 */
	public void testDicePrecision(){
		Dice dice = new Dice();
		int face1 = 0;
		int face2 = 0;
		int face3 = 0;
		int face4 = 0;
		int face5 = 0;
		int face6 = 0;
		
		int MIL = 1000000;
		
		for(int i = 0; i < 6000000; i++){
			switch (dice.roll()){
				case 1:
					face1++;
					break;
					
				case 2:
					face2++;
					break;
					
				case 3:
					face3++;
					break;
					
				case 4:
					face4++;
					break;
					
				case 5:
					face5++;
					break;
					
				case 6:
					face6++;
					break;
			}
		}
		
		System.out.println(face6 +" "+Math.abs((face1/10000)-100));
		
		if(Math.abs((face1/10000)-100) >= 5)
			fail("Precision above 5%.");
		
		if(Math.abs((face2/10000)-100) >= 5)
			fail("Precision above 5%.");
		
		if(Math.abs((face3/10000)-100) >= 5)
			fail("Precision above 5%.");
		
		if(Math.abs((face4/10000)-100) >= 5)
			fail("Precision above 5%.");
		
		if(Math.abs((face5/10000)-100) >= 5)
			fail("Precision above 5%.");
		
		if(Math.abs((face6/10000)-100) >= 5)
			fail("Precision above 5%.");
	}
	
}
