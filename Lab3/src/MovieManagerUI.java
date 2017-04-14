//Brooke Ly brookedl
//Jesus Lopez lopezjs1

import java.util.Scanner;

public class MovieManagerUI
{
	public static Scanner s = new Scanner(System.in);
	static void printMenu(){
		System.out.println("--------------------");
		System.out.println("am: Add Movie");
		System.out.println("dm: Discontinue Movie");
		System.out.println("rm: Rent Movie");
		System.out.println("rr: Return Rental");
		System.out.println("p: Print Movie Inventory");
		System.out.println("q: Quit Program");
		System.out.println("--------------------");
	}
	
	//Gets the command of user and compares to see if it is valid
	public static String getCommand(){
		System.out.print("Enter command: ");
		String in = s.nextLine().toLowerCase(); 
		//take in the next line and converts it to lower case 
		//OR it moves the scanner position to the next line and returns the value as a string.
		while(!(in.equals("am") || in.equals("dm") || in.equals("rm") ||in.equals("rr") ||in.equals("p") ||in.equals("q"))){
			System.out.print("Invalid Input. Enter Command: ");
			in = s.nextLine().toLowerCase();
		}
		return in;
	}
	
	//am
	///Getting the movie information when adding a new movie.
	public static Movies getMovieInfo(){
		System.out.print("Enter in a movie code: ");
		String code = s.nextLine();
		System.out.print("Enter in a Movie name: ");
		String name = s.nextLine();
		return new Movies(code, name, 0, new Renter[50]);
	}
	
	//dm 
	///Getting the movie code when adding a renter to a movie or trying to remove a movie.
	public static String getMovieCode(){
		System.out.print("Enter in a movie code: ");
		return s.nextLine();
	}
	
	//rm
	///Getting the renter information when adding a renter to a movie.
	public static Renter getRenterInfo(){
		int id;
		while(true){
			try{
			System.out.print("Enter RenterID: ");
			id = Integer.parseInt(s.nextLine());
			break;
			}catch(Exception e){
				System.out.print("Invalid input. ");
			}
		}
		System.out.print("Enter in first name: ");
		String first = s.nextLine();
		System.out.print("Enter in last name: ");
		String last = s.nextLine();
		return new Renter(id, first, last);
	}

	//rr
	///Getting the renter ID when trying to remove a renter from a movie.
	public static int getRenterId(){
		int i;
		while(true){
			try{
			System.out.print("Enter RenterID: ");
			i = Integer.parseInt(s.nextLine());
			break;
			}catch(Exception e){
				System.out.print("Invalid input. ");
			}
		}
		return i;
	}
}
