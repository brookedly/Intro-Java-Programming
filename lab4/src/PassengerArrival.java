//Brooke Ly brookedl 33256705
//Sunaina Kumar sunainak 27947698
public class PassengerArrival {
    private int numPassengers;
    private int destinationStation;
    private int timePeriod;
    private int expectedTimeOfArrival;

    public PassengerArrival()
    {
        this.numPassengers = 0;
        this.destinationStation = 0;
        this.timePeriod = 0;
        this.expectedTimeOfArrival = 0;
    }

    public PassengerArrival(int nop, int dfs, int tp, int etoa)
	{
		numPassengers = nop;
		destinationStation = dfs;
		timePeriod = tp;
		expectedTimeOfArrival = etoa;
	}

    /*Gets the number of passengers that will request a train for this specific behavior.*/
    public int getNumPassengers() {
        return numPassengers;
    }

    /*Gets the desired destination train station of the passengers.*/
    public int getDestinationTrainStation() {
        return destinationStation;
    }

    /*Gets the periodic time period these passengers will request train access.*/
    public int getTimePeriod() {
        return timePeriod;
    }

    /*Gets the simulated time where the next batch of passengers will enter the simulation.*/
    public int getExpectedTimeOfArrival() {
        return expectedTimeOfArrival;
    }

    /*Sets the number of passengers that will request a train for this specific behavior.*/
    public void setNumPassengers(int passengers) {
        this.numPassengers = passengers;
    }

    /*Sets the desired destination train station of the passengers.*/
    public void setDestinationTrainStation(int destination) {
        this.destinationStation = destination;
    }

    /*Sets the periodic time period these passengers will request train access.*/
    public void setTimePeriod(int time) {
        this.timePeriod = time;
    }

    /*Sets the simulated time where the next batch of passengers will enter the simulation.*/
    public void setExpectedTimeOfArrival(int expected) {
        this.expectedTimeOfArrival = expected;
    }
    
    public String getAll(){
		return "numPassengers: " + numPassengers + " destinationTrain: " + this.destinationStation + " timePeriod: " 
				+timePeriod + " expectedTimeOfArrival: " + expectedTimeOfArrival;
	}
}