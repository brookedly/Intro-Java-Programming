/*
 * Kanan Sasaki 38399655
 * Brooke Ly 33256705
 * 
 * This file contains the code keeping track of the main statistical data over the 
 * course of multiple games of craps. In addition, it formats the printout for the 
 * statistics into an easy to read format.
*/

package lab2;

public class CrapsMetricsMonitor
{

	private int games_played;
	private int games_won;
	private int games_lost;
	private int max_rolls;
	private int naturals;
	private int craps;
	private int max_win_streak;
	private int max_loss_streak;
	private int max_balance;
	private int max_balance_game;
	
	CrapsMetricsMonitor()
	{
		this.games_played = 0;
		this.games_won = 0;
		this.games_lost = 0;
		this.max_rolls = 0;
		this.naturals = 0;
		this.craps = 0;
		this.max_win_streak = 0;
		this.max_loss_streak = 0;
		this.max_balance = 0;
		this.max_balance_game = 0;
	}
	
	//update/increment/decrement methods
	public int get_games_played()
	{
		return games_played;
	}
	
	public void update_games_played(int amount)
	{
		games_played+=amount;
	}
	
	public void update_games_won(int amount)
	{
		games_won+=amount;
	}
	
	public void update_games_lost(int amount)
	{
		games_lost+=amount;
	}
	
	public int get_max_rolls()
	{
		return max_rolls;
	}
	
	public void update_max_rolls(int amount)
	{
		max_rolls = amount;
	}
	
	public void update_naturals(int amount)
	{
		naturals+=amount;
	}
	
	public void update_craps(int amount)
	{
		craps+=amount;
	}
	
	public int get_max_win_streak()
	{
		return max_win_streak;
	}
	
	public void update_max_win_streak(int amount)
	{
		max_win_streak = amount;
	}
	
	public int get_max_loss_streak()
	{
		return max_loss_streak;
	}
	
	public void update_max_loss_streak(int amount)
	{
		max_loss_streak = amount;
	}
	
	public int get_max_balance()
	{
		return max_balance;
	}
	
	public void update_max_balance(int amount)
	{
		max_balance = amount;
	}
	
	public void update_max_balance_game(int amount)
	{
		max_balance_game = amount;
	}
	
	//prints out the simulation statistics
	public void printStatistics()
	{
		System.out.println("*****************************");
		System.out.println("*** SIMULATION STATISTICS ***");
		System.out.println("*****************************");
		System.out.println("Games played: " + games_played);
		System.out.println("Games won: " + games_won);
		System.out.println("Games lost: " + games_lost);
		System.out.println("Maximum Rolls in a single game: " + max_rolls);
		System.out.println("Natural Count: " + naturals);
		System.out.println("Craps Count: " + craps);
		System.out.println("Maximum Winning Streak: " + max_win_streak);
		System.out.println("Maximum Loss Streak: " + max_loss_streak);
		System.out.println("Maximum balance: " + max_balance + " during game " + (max_balance_game+1));
	}
	
	//reset the statistics back to 0
	public void reset()
	{
		games_played = 0;
		games_won = 0;
		games_lost = 0;
		max_rolls = 0;
		naturals = 0;
		craps = 0;
		max_win_streak = 0;
		max_loss_streak = 0;
		max_balance = 0;
		max_balance_game = 0;
	}
}
