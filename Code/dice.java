import java.util.Random;

public class Dice 
{
	
	int face;
	Random rand;

	public Dice ()
	{

		face = 0;
		rand = new Random();
		
	}
	
	public int roll()
	{

		face = rand.nextInt(6);
		face++;
		
		return face;
		
	}
	
}