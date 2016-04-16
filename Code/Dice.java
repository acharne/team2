package cse360assign3;
import java.util.Random;

/**
* Arizona State University
* CSE 360 - SOFTWARE ENGINEERING
* Final Project
* @author Team 2
* @version 04.15.2016
* 
* Description: This class represents a common dice.
*/

public class Dice 
{
	
	int face; //this atribute represents a face that appears upward, simulating the dice in real life
	Random rand; //random variable

	/**
	 * Constructor that initalizes the variables
	 */
	public Dice ()
	{

		face = 0;
		rand = new Random();
		
	}
	
	/**
	 * This method simulate a roll of a dice and returns the result (face)
	 * @return face
	 */
	public int roll()
	{

		face = rand.nextInt(6);
		face++;
		
		return face;
		
	}
	
}