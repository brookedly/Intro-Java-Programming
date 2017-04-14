//Brooke Ly brookedl
//Jesus Lopez lopezjs1

public class Movies
{
	private String movieCode;
	private String movieName;
	private int amountRented;
	private Renter renters[];
	private int maxRented = 0;
	
	public Movies() {
	
	}
	
	//constructor
	public Movies(String code, String name, int amount, Renter[] s){
		movieCode = code;
		movieName = name;
		amountRented = amount;
		renters = s; 
	}
	
	//Adds a renter to the movie.
	///Throws a DuplicateRenterException because RenterIDs are unique
	public void rentMovie(Renter r) throws RenterLimitException, DuplicateRenterException {
		if (maxRented == 10){
			throw new RenterLimitException();
		}
		for (int x = 0; x < maxRented; x++){
			if (renters[x].getRenterID() == r.getRenterID()){
				throw new DuplicateRenterException(); 
			}
		}
		renters[maxRented++] = r;
		amountRented++;
		for(int y = 0;y!=amountRented;y++)
		{
			for(int x = 0;x!=amountRented;x++)
			{
				if(renters[x+1]==null)
				{}
				else {
					if(renters[x].getLastName().toLowerCase().compareTo(renters[x+1].getLastName())>0)
					{
						Renter new_renter = renters[x];
						renters[x] = renters[x+1];
						renters[x+1] = new_renter;
					}
					else if(renters[x].getLastName().toLowerCase().compareTo(renters[x+1].getLastName())==0)
					{
						if(renters[x].getRenterID()>renters[x+1].getRenterID())
						{
							Renter new_renter = renters[x];
							renters[x] = renters[x+1];
							renters[x+1] = new_renter;
						}
					}
				}
			}
		}
	}


	//Removes a renter from the movie based on their renters ID.
	public void returnRental(int renterID) throws EmptyRenterListException, RenterNotFoundException, InvalidRenterIDException{
		if (maxRented == 0){
			throw new EmptyRenterListException();
		}
		boolean foundid = false;
		for (int x = 0; x < maxRented; x++){
			if (renters[x] == null) {}
			else if (renters[x].getRenterID() == renterID){
				foundid = true;
				
				renters[x] = null;
			}
		}
		if (foundid == false){ 
			throw new RenterNotFoundException(); 
		}
		if (foundid == true){
			amountRented--; 
		}
	}
	
	
	///Any setters / getters you need to use in your program.
	public String getMovieCode()
	{
		return movieCode;
	}

	public String getMovieName()
	{
		return movieName;
	}

	public int getAmountRented()
	{
		return amountRented;
	}

	public Renter[] getRenting()
	{
		return renters;
	}	
	
	public int getMaxRented()
	{
		return maxRented;
	}
	
}
