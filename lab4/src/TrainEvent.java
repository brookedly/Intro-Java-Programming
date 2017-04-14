//Brooke Ly brookedl 33256705
//Sunaina Kumar sunainak 27947698
public class TrainEvent
{
	private int destination;
	private int expectedArrival;
	
	public TrainEvent () {
		destination = 0;
        expectedArrival = 0;
	}
	
	public TrainEvent (int d, int ea) {
		this.destination = d;
		this.expectedArrival = ea;
	}
	
	/*Returns and sets the train station that the train needs to move to.*/
	public int getDestination()
	{
		return destination;
	}

	public void setDestination(int d)
	{
		this.destination = d;
	}

	/*Returns and sets the expected simulated time that the train will spend on that train station*/
	public int getExpectedArrival()
	{
		return expectedArrival;
	}

	public void setExpectedArrival(int ea)
	{
		this.expectedArrival = ea;
	}
}
