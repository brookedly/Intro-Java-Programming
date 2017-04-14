//Brooke Ly brookedl
//Jesus Lopez lopezjs1

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MovieManagerTest
{
	MovieManager m;
	Renter a = new Renter (1, "Brooke", "Ly");
	Renter b = new Renter (2, "Amy", "Lu");
	Renter c = new Renter (3, "Chance", "Rapper");
	Renter d = new Renter (4, "Faith", "Hill");
	Renter e = new Renter (5, "Jesus", "Lopez");
	Renter f = new Renter (6, "Jesus", "Lopex");
	Renter g = new Renter (7, "Luke", "Hemmings");
	Renter h = new Renter (81, "Stephen", "Curry");
	Renter i = new Renter (1231, "Barack", "Obama");
	Renter j = new Renter (43, "Kelly", "Ly");
	Renter k = new Renter (90, "Jesus", "Lopez");
	Renter l = new Renter (90, "Lia", "Perez");
	Renter dup = new Renter(90, "Bobby", "Smith");
	Renter missing_name = new Renter(80, null, null);
	Movies one = new Movies ("WC", "White Chicks", 0, new Renter[20]);
	Movies two = new Movies ("HP", "Harry Potter", 0, new Renter[20]);
	Movies three = new Movies ("IO", "Inside Out", 0, new Renter[20]);
	Movies four = new Movies ("TS3", "Toy Story 3", 0, new Renter[20]);
	Movies five = new Movies ("TLK", "The Lion King", 0, new Renter[20]);
	Movies six = new Movies ("FG", "Forrest Gump", 0, new Renter[20]);
	Movies seven = new Movies ("TWO", "The Wizard of Oz", 0, new Renter[20]);
	Movies eight = new Movies ("LLL", "La La Land", 0, new Renter[20]);
	Movies nine = new Movies ("RH3", "Rush Hour 3", 0, new Renter[20]);
	Movies ten = new Movies ("COW", "Cowspiracy", 0, new Renter[20]);
	Movies eleven = new Movies ("FD", "Finding Dory", 0, new Renter[20]);
	Movies twelve = new Movies ("ZOO", "Zootopia", 0, new Renter[20]);
	Movies thirteen = new Movies ("SOC", "Straight Outta Compton", 0, new Renter[20]);
	Movies fourteen = new Movies ("F7", "Furious 7", 0, new Renter[20]);
	Movies fifteen = new Movies ("TLS", "The Last Song", 0, new Renter[20]);
	Movies sixteen = new Movies ("THG", "The Hunger Games", 0, new Renter[20]);
	Movies seventeen = new Movies ("TPM", "The Peanuts Movie", 0, new Renter[20]);
	Movies eighteen = new Movies ("22JS", "22 Jump Street", 0, new Renter[20]);
	Movies nineteen = new Movies ("BH6", "Big Hero 6", 0, new Renter[20]);
	Movies twenty = new Movies ("MU", "Monster University", 0, new Renter[20]);
	Movies twentyone = new Movies ("DM2", "Despicable Me 2", 0, new Renter[20]);
	Movies dup1 = new Movies ("WC", "Despicable Me 2", 0, new Renter[20]);
	Movies empty = new Movies ("", "", 0, new Renter[20]);

	
	@Before // Executed before each test in this class 
	public void executeBeforeEachTest() {
		m = new MovieManager();
	}
	
	//Thrown when a movie already exists when trying to add one.
	@Test(expected=DuplicateMovieException.class) 
	public void testDupMovie() throws MovieLimitException, EmptyMovieInfoException, DuplicateMovieException {
		m.addMovie(one);
		m.addMovie(dup1);
	}
	
	//Thrown when a movie is trying to be added and the number of movies is at the max.
	@Test(expected=MovieLimitException.class) 
	public void testAddMovie() throws MovieLimitException, EmptyMovieInfoException, DuplicateMovieException {
		//assertEquals(m, new Movies ("", "", 0, new Renter[20]));
		m.addMovie(one);
		m.addMovie(two);
		m.addMovie(three);
		m.addMovie(four);
		m.addMovie(five);
		m.addMovie(six);
		m.addMovie(seven);
		m.addMovie(eight);
		m.addMovie(nine);
		m.addMovie(ten);
		m.addMovie(eleven);
		m.addMovie(twelve);
		m.addMovie(thirteen);
		m.addMovie(fourteen);
		m.addMovie(fifteen);
		m.addMovie(sixteen);
		m.addMovie(seventeen);
		m.addMovie(eighteen);
		m.addMovie(nineteen);
		m.addMovie(twenty);
		m.addMovie(twentyone);
	}
	
	//Thrown when the movie code and/or movie name is empty when trying to add one
	@Test(expected=EmptyMovieInfoException.class) 
	public void testEmptyMovie() throws EmptyMovieInfoException, MovieLimitException, DuplicateMovieException {
		m.addMovie(empty);
	}
	
	//Thrown when a movie that does not exist.
	@Test(expected=MovieNotFoundException.class) 
	public void testMovieNotFound() throws MovieNotFoundException, EmptyMovieListException, RentedMovieException, MovieLimitException, 
	EmptyMovieInfoException, DuplicateMovieException {
		m.addMovie(one);
		m.addMovie(two);
		m.addMovie(three);
		m.addMovie(four);
		m.discontinueMovie("PIE"); //this movieCode does not exist in the Movie Array 
	}
	
	//Thrown when trying to return a movie when the inventory list is empty.
	@Test(expected=EmptyMovieListException.class) 
	public void testEmptyMovieList() throws MovieNotFoundException, EmptyMovieListException, RentedMovieException {
		assertEquals(0, m.getNumOfMovies());
		m.discontinueMovie("WC");
	}
	
	
	//Thrown when trying to return a movie that does not have any copies currently being rented..
	@Test(expected=EmptyRenterListException.class) 
	public void testEmptyRenterList() throws MovieLimitException, EmptyMovieInfoException, DuplicateMovieException, 
	RenterNotFoundException, EmptyRenterListException, MovieNotFoundException, InvalidRenterIDException {
		m.addMovie(one);
		m.returnRental(1, "WC");
	}
	
	//Thrown when a renter is renting to a movie without any available copies.
	@Test(expected=RenterLimitException.class) 
	public void testRenterLimit() throws RenterLimitException, DuplicateRenterException, MovieNotFoundException, 
	EmptyRenterNameException, MovieLimitException, EmptyMovieInfoException, DuplicateMovieException {
		m.addMovie(one);
		m.rentMovie("WC", a);
		m.rentMovie("WC", b);
		m.rentMovie("WC", c);
		m.rentMovie("WC", d);
		m.rentMovie("WC", e);
		m.rentMovie("WC", f);
		m.rentMovie("WC", g);
		m.rentMovie("WC", h);
		m.rentMovie("WC", i);
		m.rentMovie("WC", j);
		m.rentMovie("WC", k);
		
	}
	
	//Thrown when trying to remove a renter for a movie that doesn’t exist.
	@Test(expected=RenterNotFoundException.class) 
	public void testRenterNotFound() throws MovieLimitException, EmptyMovieInfoException, DuplicateMovieException, RenterLimitException, 
	DuplicateRenterException, MovieNotFoundException, EmptyRenterNameException, RenterNotFoundException, EmptyRenterListException, InvalidRenterIDException {
		m.addMovie(one);
		m.addMovie(two);
		m.addMovie(three);
		m.addMovie(four);
		m.rentMovie("WC", a);
		m.rentMovie("WC", b);
		m.returnRental(408, "WC");
	}
	
	// Thrown when a movie already exists when trying to add one.
	@Test(expected=DuplicateRenterException.class) 
	public void testDupRenter() throws MovieLimitException, EmptyMovieInfoException, DuplicateMovieException, 
	RenterLimitException, DuplicateRenterException, MovieNotFoundException, EmptyRenterNameException {
		m.addMovie(one);
		m.addMovie(two);
		m.addMovie(three);
		m.addMovie(four);
		m.rentMovie("WC", a);
		m.rentMovie("WC", a);
	}	
	
	//Thrown when the user enters an invalid renter ID.
	@Test(expected=InvalidRenterIDException.class) 
	public void testInvalidRenterID() throws MovieLimitException, EmptyMovieInfoException, DuplicateMovieException, 
	RenterLimitException, DuplicateRenterException, MovieNotFoundException, EmptyRenterNameException, 
	RenterNotFoundException, EmptyRenterListException, InvalidRenterIDException {
		m.addMovie(one);
		m.addMovie(two);
		m.rentMovie("WC", b);
		m.rentMovie("HP", a);
		m.returnRental(-1, "WC");
	}
	
	
	// Thrown when the user enters an empty first and/or last name for the renter.
	@Test(expected=EmptyRenterNameException.class) 
	public void testRenterMissingName() throws MovieLimitException, EmptyMovieInfoException, DuplicateMovieException, 
	RenterLimitException, DuplicateRenterException, MovieNotFoundException, EmptyRenterNameException {
		m.addMovie(twenty);
		m.rentMovie("MU", missing_name);
	}
	
	//Thrown when the user attempts to discontinue a movie that has copies currently rented out.
	@Test(expected=RentedMovieException.class) 
	public void testAlreadyRented() throws MovieLimitException, EmptyMovieInfoException, DuplicateMovieException, 
	RenterLimitException, DuplicateRenterException, MovieNotFoundException, EmptyRenterNameException, 
	EmptyMovieListException, RentedMovieException {
		m.addMovie(one);
		m.addMovie(two);
		m.addMovie(three);
		m.addMovie(four);
		m.rentMovie("WC", a);
		m.rentMovie("WC", b);
		m.discontinueMovie("WC");
	}
}

