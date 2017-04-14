//Brooke Ly brookedl 33256705
//Sunaina Kumar sunainak 27947698

public class TrainStation
{
    private int[] totalDestinationRequests;
    private int[] arrivedPassengers;
    private int[] passengerRequests;
    private int approachingTrain;
    int waiting;
	int exited;
	int request;

    public TrainStation()
    {
        totalDestinationRequests = new int[5];
        arrivedPassengers = new int[5];
        passengerRequests = new int[5];
        approachingTrain = -1;
    }

    /*An array where the ith element is
	representing the number of passengers who has requested to go to the ith
	train station throughout the simulation. */
    public int getTotalDestinationRequests(int d) {
        return totalDestinationRequests[d];
    }

    /*An array where the ith element is representing the
	number of passengers who have arrived on this train station from a train
	throughout the simulation.*/
    public int getArrivedPassengers(int i) {
        return arrivedPassengers[i];
    }

    /* An array where the ith element represents the
	number of people who currently want to travel to the ith train station of
	the train system.*/
    public int getPassengerRequests(int i) {
        return passengerRequests[i];
    }

    /*The train ID that is currently heading to the train station for passenger pickup*/
    public int getApproachingTrain() {
        return approachingTrain;
    }
    
    public void getInfo(){
    	request = 0;
    	waiting = 0;
    	exited = 0;
		for (int i = 0; i < totalDestinationRequests.length; i++){
			request += totalDestinationRequests[i];
		}
		for (int i = 0; i < passengerRequests.length; i++){
			 waiting += passengerRequests[i];
		}
		for (int i = 0; i < arrivedPassengers.length; i++){
			exited += arrivedPassengers[i];
		}
	}

    public void setTotalDestinationRequests(int p, int dr) {
        this.totalDestinationRequests[p] = dr;
    }

    public void setArrivedPassengers(int s, int ap) {
        this.arrivedPassengers[s] = ap;
    }

    public void setPassengerRequests(int s, int pr) {
        this.passengerRequests[s] = pr;
    }

    /*Set approaching train */
    public void setApproachingTrain(int approachingTrain) {
        this.approachingTrain = approachingTrain;
    }

    public String getAll() {
    	getInfo();
        return String.format("%12d %12d %9d %9d", request, exited, waiting, approachingTrain);
    }
}