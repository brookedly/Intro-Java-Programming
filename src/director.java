
public class director
{	
	public String first_name;
	public String last_name;
	public int birth_year;
	public int numOfFilms;

	public void setDirectorInfo(String firstname, String lastname, int birthyr, int filmnum){
		this.first_name = firstname;
		this.last_name = lastname;
		this.birth_year = birthyr;
		this.numOfFilms = filmnum;
	}
	
	public String getFirst_name()
	{
		return first_name;
	}
	public void setFirst_name(String first_name)
	{
		this.first_name = first_name;
	}
	public String getLast_name()
	{
		return last_name;
	}
	public void setLast_name(String last_name)
	{
		this.last_name = last_name;
	}
	public String getBirth_year()
	{
		return Integer.toString(birth_year);
	
	}
	public void setBirth_year(int birth_year)
	{
		this.birth_year = birth_year;
	}
	public String getNumOfFilms()
	{
		return Integer.toString(numOfFilms);
	}
	public void setNumOfFilms(int numOfFilms)
	{
		this.numOfFilms = numOfFilms;
	}
	

}
