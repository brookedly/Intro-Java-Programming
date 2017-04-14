/*
 * Kanan Sasaki 38399655
 * Brooke Ly 33256705
 * 
 * This file contains the code running the craps game algorithm. It maintains a count
 * of the number of rolled dice, number of Craps, Naturals and updates the monitor
 * accordingly.
*/

package lab2;
import lab2.CrapsMetricsMonitor;

public class CrapsGame
{

	private CrapsMetricsMonitor monitor;
	private int number_of_rolls;
	
	CrapsGame(CrapsMetricsMonitor monitor)
	{
		this.monitor = monitor;
		this.number_of_rolls = 1;
	}
	
	//returns the number of rolls from the current game
	public int get_number_of_rolls()
	{
		return number_of_rolls;
	}
	
	//resets the number of rolls for the current game back to 1
	public void reset_number_of_rolls()
	{
		number_of_rolls = 1;
	}
	
	//algorithm for an actual game
	public boolean playGame()
	{
		//roll initial dice
		int random1 = (int)(Math.random() * 6 + 1);
		int random2 = (int)(Math.random() * 6 + 1);
		int total = random1 + random2;
		
		System.out.println("Rolled a " + total);
		
		//check for Craps
		if(total == 2 || total == 3 || total == 12)
		{
			monitor.update_craps(1);
			System.out.println("*****Craps! You lose.*****");
			return false;
		}
		//check for Naturals
		else if(total ==  7 || total == 11)
		{
			monitor.update_naturals(1);
			System.out.println("*****Natural! You win.*****");
			return true;
		}
		//Roll until point number or crap out
		else
		{
			//
			int point_number = total;
			while(true)
			{
				random1 = (int)(Math.random() * 6 + 1);
				random2 = (int)(Math.random() * 6 + 1);
				total = random1 + random2;
				
				number_of_rolls++;
				
				System.out.println("Rolled a " + total);
				
				if(total == 7)
				{
					System.out.println("*****Crap out! You lose.*****");
					return false;
				}
				else if(total == point_number)
				{
					System.out.println("*****Rolled the point! You win!*****");
					return true;
				}
			}
		}
		
	}
}
