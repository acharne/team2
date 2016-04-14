package Dice_Game;

import java.util.Random;

public class dice 
{
	
	int face[];
	Random rand;

	public dice ()
	{

		face = new int[3];
		rand = new Random();
		
	}
	
	public int[] roll(int numDice)
	{
		for (int rollIndex = 0; rollIndex < numDice; rollIndex++)
		{
			face[rollIndex] = rand.nextInt(6) + 1;
		}
		
		return face;
		
	}
	
	public int sum(int faces)
	{
		int sum = 0;
		
		for (int faceValue = 0; faceValue < faces; faceValue++)
		{
			sum += face[faceValue];
		}
		
		return sum;
	}
	
	public int computerDice(int score)
	{
		int dice = 0;
		
		if(score > 20)
		{
			dice = 3;
		}
		else if(score < 20 && score > 8)
		{
			dice = 2;
		}
		else
		{
			dice = 1;
		}
		
		return dice;
	}
	
}