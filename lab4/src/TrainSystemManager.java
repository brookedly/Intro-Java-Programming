//Brooke Ly brookedl 33256705
//Sunaina Kumar sunainak 27947698

public class TrainSystemManager{

    private TrainStation[] stations;
    int MAXSTATIONS = 5;

    public TrainSystemManager()
    {
        stations = new TrainStation[MAXSTATIONS];
        for (int i = 0; i < MAXSTATIONS; ++i)
        {
            stations[i] = new TrainStation();
        }
    }
    
    public synchronized int calculateNextTrain(int trainid){
    	int result = 0;
		for (int index = 0; index < MAXSTATIONS; index++){
			if (stations[index].getApproachingTrain() == -1){
				for (int index1 = 0; index1 < MAXSTATIONS; index1++){
					result = ((stations[index].getPassengerRequests(index1) >0) ? setApproachingTrain(index, trainid) : returnNegOne());
					if (result == index ){
						return index;
					}
				}
			}
		}
		return -1; //-1 to used to represent no train currently heading in that direction
	}

    public synchronized int returnNegOne(){
    	return -1;
    }
    public synchronized int getTotalDestinationRequests(int currentStation, int destination)
    {
        return stations[currentStation].getTotalDestinationRequests(destination);
    }

    public synchronized int getArrivedPassengers(int currentStation, int trainID)
    {
        return stations[currentStation].getArrivedPassengers(trainID);
    }

    public synchronized int getPassengerRequests(int currentStation, int destination)
    {
        return stations[currentStation].getPassengerRequests(destination);
    }

    public synchronized int getApproachingTrain(int station)
    {
        return stations[station].getApproachingTrain();
    }

    public synchronized void setTotalDestinationRequests(int currentStation, int destination, int change)
    {
    	//int n = stations[currentStation].getTotalDestinationRequests(destination) + change;
        stations[currentStation].setTotalDestinationRequests(destination, stations[currentStation].getTotalDestinationRequests(destination) + change);
    }

    public synchronized void setArrivedPassengers(int currentStation, int trainID, int change)
    {
    	//int n = stations[currentStation].getTotalDestinationRequests(trainID) + change;
        stations[currentStation].setArrivedPassengers(trainID, stations[currentStation].getTotalDestinationRequests(trainID) + change);
    }
    
	public synchronized void setPassengerRequests(int cur_station_num, int destination, int numpass)
	{
		//int n = stations[cur_station_num].getTotalDestinationRequests(destination) + numpass;
		stations[cur_station_num].setPassengerRequests(destination, stations[cur_station_num].getTotalDestinationRequests(destination) + numpass);
	}

    public synchronized int setApproachingTrain(int currentStation, int trainID)
    {
        stations[currentStation].setApproachingTrain(trainID);
        return currentStation;
    }

    public String getAll() {
    	String result = "";
        result = "Train Stations: \n";
        result += "----------------------------------------------------------------------\n";
        result += "Station # " + "Requested   " + "Exited   " + "Waiting   " + "Heading\n";
        result += "----------------------------------------------------------------------\n";
        for (int i = 0; i < 5; i++) {
        	result += " " + i + stations[i].getAll() + "\n";
        }
        return result;
    }

}