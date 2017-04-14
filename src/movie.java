public class movie
{
	public int duration;
	public int year;
	public double price; // in USD
	public String title;
	director d = new director();
	//static = shared with any other movie object that is created
	
	movie()
	{
		
	}
	
	movie (movie cMovie) //copy constructor
	{
		duration = cMovie.duration;
		year= cMovie.year;
		price = cMovie.price;
		title = cMovie.title;
		d = cMovie.d;
	}

	public int getDuration()
	{
		return duration;
	}
	
	public void setDuration(int the_duration)
	{
		this.duration = the_duration;
	}

	public int getYear()
	{
		return year;
	}

	public void setYear(int the_year)
	{
		this.year = the_year;
	}

	public double getPrice()
	{
		return price;
	}

	public void setPrice(double cost)
	{
		this.price = cost;
	}

	public String getTitle()
	{
		return title;
	}


	public void setTitle(String new_title)
	{
		this.title = new_title;
	}
	
	public void setDirector(String firstName, String lastName, int birthYear, int numFilms){
		this.d.first_name = firstName;
		this.d.last_name = lastName;
		this.d.birth_year = birthYear;
		this.d.numOfFilms = numFilms;
	}
	
	
	public String toString()
	{
		return "Title: " + title + "\n" + "Published in: " + year + "\n" + "Number of Pages: " 
				+ duration + "\n" + "Price: " + price + "\n"+ "Written by " + d.getFirst_name() 
				+ " " + d.getLast_name() + ", who was born in " + d.getBirth_year() + " and has " 
				+ d.getNumOfFilms() + " releases"; 
	}
	
	public static void main(String []args){
		movie theMovie = new movie();
		theMovie.setTitle("Harry Potter and the Goblet of Fire"); 
		theMovie.setPrice(19.99);
		theMovie.setYear(2000);
		theMovie.setDuration(734);
		theMovie.setDirector("J.K,", "Rowling", 1965, 7);
		System.out.println("Testing the copy constructor");
		movie movieTwo = new movie(theMovie);
		System.out.println(theMovie.toString());
		System.out.println(movieTwo.toString());
		System.out.println("Testing the increment ");

		movieTwo.d.numOfFilms++;
		System.out.println(theMovie.toString());
		
	}



}
