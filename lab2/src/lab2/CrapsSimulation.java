/*
 * Kanan Sasaki 38399655
 * Brooke Ly 33256705
 * 
 * This file contains the code for both the initial setup for the game (aka. the user input) 
 * as well as runs the game itself and updates the user's balance until reaching $0. Recursively,
 * calls the function again if the user wishes to continue.
*/
package lab2;
import java.util.Scanner;
import lab2.CrapsGame;
import lab2.CrapsMetricsMonitor;

public class CrapsSimulation
{

	private CrapsMetricsMonitor monitor;
	private CrapsGame game;
	private Scanner input;
	private String user_name;
	private int user_balance;
	private int user_bet;
	private int current_win_streak;
	private int current_loss_streak;
	
	CrapsSimulation()
	{
		this.monitor = new CrapsMetricsMonitor();
		this.game = new CrapsGame(monitor);
		this.input = new Scanner(System.in);
		this.user_name = "name";
		this.user_balance = 0;
		this.user_bet = 0;
		this.current_win_streak = 0;
		this.current_loss_streak = 0;
	}
	
	public void start()
	{	
		//Enter the user's starting info
		System.out.print("Welcome to SimCraps! Enter your user name: ");
		user_name = input.nextLine();
		System.out.println("Hello " + user_name + "!");
		System.out.print("Enter the amount of money you will bring to the table: ");
		user_balance = input.nextInt();
		input.nextLine();
		monitor.update_max_balance(user_balance);
		System.out.print("Enter the bet amount between $1 and $" + user_balance + ": ");
		user_bet = input.nextInt();
		input.nextLine();
		
		//checks to see whether a bet is valid
		while(user_bet < 1 || user_bet > user_balance || user_bet > 1000)
		{
			System.out.print("Invalid Bet! Enter the bet amount between $1 and $" + user_balance + ": ");
			user_bet = input.nextInt();
			input.nextLine();
		}
		
		int original_bet_amount = user_bet;
		
		//start main game loop
		while(user_balance > 0)
		{
			//adjust bets depending on how much money the user has left
			user_bet = original_bet_amount;
			if(user_bet > user_balance)
			{
				user_bet = user_balance;
			}
			System.out.println(user_name + " bets $" + user_bet);
			
			//play a game and update games played
			boolean won = game.playGame();
			monitor.update_games_played(1);
			
			//update win/loss statistics
			if(won)
			{
				monitor.update_games_won(1);
				current_win_streak++;
				current_loss_streak = 0;
				user_balance += user_bet;
				
				if(current_win_streak > monitor.get_max_win_streak())
				{
					monitor.update_max_win_streak(current_win_streak);
				}
			}
			else
			{
				monitor.update_games_lost(1);
				current_loss_streak++;
				current_win_streak = 0;
				user_balance -= user_bet;
				
				if(current_loss_streak > monitor.get_max_loss_streak())
				{
					monitor.update_max_loss_streak(current_loss_streak);
				}
			}
			
			//update number of rolls
			if(game.get_number_of_rolls() > monitor.get_max_rolls())
			{
				monitor.update_max_rolls(game.get_number_of_rolls());
			}
			game.reset_number_of_rolls();
			
			//update the user's balance
			if(user_balance > monitor.get_max_balance())
			{
				monitor.update_max_balance(user_balance);
				monitor.update_max_balance_game(monitor.get_games_played());
			}
			
			System.out.println(user_name + "'s balance: $" + user_balance + ". Playing a new game...");
		}
		
		//print out the end statistics
		System.out.println();
		monitor.printStatistics();
		
		//check for replay and execute the program recursively if yes
		System.out.print("Replay? Enter 'y' or 'n': ");
		String replay = input.nextLine();
		
		while(!(replay.equals("y") || replay.equals("Y") || replay.equals("n") || replay.equals("N")))
		{
			System.out.print("Replay? Enter 'y' or 'n': ");
			replay = input.nextLine();
		}
		if(replay.equals("y") || replay.equals("Y"))
		{
			System.out.println();
			current_win_streak = 0;
			current_loss_streak = 0;
			monitor.reset();
			game.reset_number_of_rolls();
			start();
		}
		
	}
	
}
