//Brooke Ly brookedl
//Jesus Lopez lopezjs1

public class Renter
{
	private int renterID;
	private String firstName;
	private String lastName;
	
	///default constructors 
	public Renter (){
//		renterID = 0;
//		firstName = "";
//		lastName = "";
	}
	
	//constructors
	public Renter(int id, String first, String last){
		renterID = id;
		firstName = first;
		lastName = last;
	}
	
	//Any setters / getters you need to use in your program.
	public int getRenterID()
	{
		return renterID;
	}
	public void setRenterID(int renterID)
	{
		this.renterID = renterID;
	}
	public String getFirstName()
	{
		return firstName;
	}
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	public String getLastName()
	{
		return lastName;
	}
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
}
