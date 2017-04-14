//Brooke Ly brookedl
//Jesus Lopez lopezjs1


public class MovieManager
{
	private Movies [] arrayOfMovies;
	private int numOfMovies;
	
	//default constructor
	public MovieManager(){
		arrayOfMovies = new Movies[20];
		numOfMovies = 0;
	}
	
	//constructor
	public MovieManager(Movies[] m, int num){
		arrayOfMovies = m;
		numOfMovies = num;
	}
	
	/*
	 * Runs the main loop of the program. This displays the menu, accepts user input, 
	 * and handles each of the user commands. Handles the custom Exceptions and 
	 * displays the messages to the user.
	 */
	public void run(){
		System.out.println("Welcome to Class Movie Manager!");
		System.out.println("Select an action based on the following menu: ");
		while (true){
			try {
				MovieManagerUI.printMenu();	
				String command = MovieManagerUI.getCommand();
				switch(command){
				case "am": 
					addMovie(MovieManagerUI.getMovieInfo());
					break;
				case "dm":
					discontinueMovie(MovieManagerUI.getMovieCode());
					break;
				case "rm":
					rentMovie(MovieManagerUI.getMovieCode(), MovieManagerUI.getRenterInfo());
					break;
				case "rr":
					returnRental(MovieManagerUI.getRenterId(), MovieManagerUI.getMovieCode());
					break;
				case "p":
					printInventory();
					break;
				case "q":
					return;
				}
			}catch (DuplicateMovieException e){
				System.err.println("DuplicateMovieException: Movie already exists in the system.");
			}
			catch (MovieLimitException e){
				System.err.println("MovieLimitException: Movie list has exceeded 20.");
			}
			catch (EmptyMovieInfoException e){
				System.err.println("EmptyMovieInfoException: Movie information is not filled.");
			}
			catch (MovieNotFoundException e){
				System.err.println("MovieNotFoundException: Movie does not exist.");
			}
			catch (EmptyMovieListException e){
				System.err.println("EmptyMovieListException: The movie list is empty.");
			}
			catch (EmptyRenterListException e){
				System.err.println("EmptyRenterListException: The renter list is empty.");
			}
			catch (RenterLimitException e){
				System.err.println("RenterLimitException: Renter list has exceeded 10.");
			}
			catch (RenterNotFoundException e){
				System.err.println("RenterNotFoundException: Renter does not exist.");
			}
			catch (DuplicateRenterException e){
				System.err.println("DuplicateRenterException: Renter is already in the system.");
			}
			catch (EmptyRenterNameException e){
				System.err.println("EmptyRenterNameException: Missing Renter's name.");
			}
			catch (RentedMovieException e){
				System.err.println("RentedMovieException: People are currently renting that movie. Cannot discontinue.");
			}
			catch (InvalidRenterIDException e){
				System.err.println("InvalidRenterIDException: Not a valid Renter ID. ID must be positive.");
			}
		}
	}
	
	//Adds a movie to the array of movies
	//throw MovieLimitException is numOfMovies >= 20
	//throw DuplicateMovieException if the movie to add is already in the Movie class
	 public void addMovie(Movies m) throws MovieLimitException, EmptyMovieInfoException, DuplicateMovieException {
		 if (numOfMovies == 20) {
			 throw new MovieLimitException();
		 }
		
		 if (m.getMovieCode().isEmpty() && m.getMovieName().isEmpty() && m.getAmountRented() == 0 ){
			 throw new EmptyMovieInfoException();
		 }
		 for (int i =0; i < numOfMovies; i++){
			 if(arrayOfMovies[i].getMovieCode().toLowerCase().equals(m.getMovieCode().toLowerCase()))
				 throw new DuplicateMovieException();
		 }
		 arrayOfMovies[numOfMovies++] = m;
	 }
	
	 //Removes a movie from the array of Movies.
	 public void discontinueMovie(String movieCode) throws MovieNotFoundException, EmptyMovieListException, RentedMovieException {
		 boolean foundcode = false;
		 if (numOfMovies == 0){
			 throw new EmptyMovieListException();
		 }
		 for (int i = 0; i < numOfMovies; i++){
			 if (arrayOfMovies[i] == null ){}
			 else if (arrayOfMovies[i].getMovieCode().toLowerCase().equals(movieCode.toLowerCase())){
				 //remove this movie from the Movie array
				 if (arrayOfMovies[i].getAmountRented() == 0) {
				 foundcode = true;
				 arrayOfMovies[i] = null;
				 numOfMovies--;
				 }
				 else {
					 throw new RentedMovieException();
				 }
			 }	 
		}
		if (foundcode == false){
			throw new MovieNotFoundException();
		}
	 }
	
	 //Adds a renter to an already existing movieCode.
	 public void rentMovie(String movieCode, Renter s) throws RenterLimitException, DuplicateRenterException, 
	 MovieNotFoundException, EmptyRenterNameException {
		boolean foundcode = false;
		if((s.getFirstName()==null||s.getLastName()==null)||(s.getFirstName()==""||s.getLastName()==""))
		 {
			 throw new EmptyRenterNameException();
		 }
		for (int x = 0; x< numOfMovies; x++){
			if (arrayOfMovies[x].getMovieCode().toLowerCase().equals(movieCode.toLowerCase())){
				foundcode = true;
				arrayOfMovies[x].rentMovie(s);
				return;
			}
		}
		//otherwise if you looped through the entire arrayOfMovies and couldn't find the movieCode therefore
		//that movie might not exist and you can't add a renter to it. Throw error
		if (foundcode == false) {
			throw new MovieNotFoundException();
		}
	 }
	 
	 //Removes a renter from an already existing movieCode.
	 public void returnRental (int renterId, String movieCode) throws RenterNotFoundException,
	 EmptyRenterListException, MovieNotFoundException, InvalidRenterIDException {	
		 boolean foundcode = false;
		 if (renterId < 0)
			 throw new InvalidRenterIDException();
		 for (int x = 0; x < numOfMovies; x++){
			 if (arrayOfMovies[x].getMovieCode().toLowerCase().equals(movieCode.toLowerCase())){
				 //remove the passed Rental since movie codes (i.e HPDH2) are equal
				 foundcode = true;
				 if(arrayOfMovies[x].getAmountRented()==0)
				 {
					 throw new EmptyRenterListException();
				 }
				 arrayOfMovies[x].returnRental(renterId);
				 return;
			 }
		 }
		 if (foundcode == false){
			 throw new MovieNotFoundException();
		 }

	 }
	 
	 //Prints the information for all movies and their renters.
	 public void printInventory(){
		 System.out.println("**************");
		 //check for null renter object
		 for (int i = 0; i < numOfMovies; i++)
		 {
			 if(arrayOfMovies[i]==null)
				 System.out.print("");
			 else
			 {
				 System.out.println(arrayOfMovies[i].getMovieCode() + ": " + arrayOfMovies[i].getMovieName());
				 System.out.println("Number Currently Rented: " + arrayOfMovies[i].getAmountRented());
				 for(int x = 0;x!=arrayOfMovies[i].getRenting().length;x++)
				 {
					 if(arrayOfMovies[i].getRenting()[x]==null)
					 {}
					 else
					 {
						 System.out.print(arrayOfMovies[i].getRenting()[x].getRenterID()+" | "+
							 arrayOfMovies[i].getRenting()[x].getLastName()+", "+arrayOfMovies[i].getRenting()[x].getFirstName()+"\n");
					 }
				 }
			 }
				 
		 }
			 //print renter information
		 System.out.println("**************");
	 }

	public int getNumOfMovies()
	{
		return numOfMovies;
	}
	 
}
