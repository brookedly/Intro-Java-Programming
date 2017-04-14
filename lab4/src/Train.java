//Brooke Ly brookedl 33256705
//Sunaina Kumar sunainak 27947698

import java.util.ArrayList;

public class Train implements Runnable
{
    private int trainID;
    private int currentTrainStation;
    private int numPassengers;
    private int totalLoadedPassengers;
    private int totalUnloadedPassengers;
    private ArrayList<TrainEvent> moveQueue;
    private int[] passengerDestinations;
    private TrainSystemManager manager;

    public Train(int id, TrainSystemManager m)
    {
        this.trainID = id;
        this.manager = m;
        currentTrainStation = 0;
        numPassengers = 0;
        totalLoadedPassengers = 0;
        totalUnloadedPassengers = 0;
        moveQueue = new ArrayList<TrainEvent>();
        passengerDestinations = new int[5];
    }

    public void run()
    {
        while (!Thread.interrupted())
        {
            if (!moveQueue.isEmpty())
            {
                if (moveQueue.get(0).getExpectedArrival() == SimClock.getTime())
                {
                	currentTrainStation = moveQueue.get(0).getDestination();
                    if (numPassengers == 0)
                    {
                    	//See if need to go up or go down and loads passengers in that determined location
                    	//After loading must set the currentTrainStation to -1 - indicating no more train are approaching it
                        nextDirection();
                        manager.setApproachingTrain(currentTrainStation, -1);
                    }
                    else
                    {
                    	 int countPeopleRequest = passengerDestinations[currentTrainStation];
                    	 addToTotalUnloadedPassengers(passengerDestinations[currentTrainStation]);
                         numPassengers = numPassengers - countPeopleRequest;
                         manager.setArrivedPassengers(currentTrainStation, trainID, countPeopleRequest);
                         passengerDestinations[currentTrainStation] = 0;
                    }
                    moveQueue.remove(0);
                }
            }
            else
            {
                int newStation = manager.calculateNextTrain(trainID);
                if (newStation != -1){
                	TrainEvent newTrainEvent = new TrainEvent();
                	newTrainEvent = new TrainEvent(newStation, SimClock.getTime() + 5 * Math.abs(newStation - currentTrainStation) + 10);
                	moveQueue.add(newTrainEvent);
                }
            }
        }
    }

    public void nextDirection ()
    {
    	int toLoadUnload = 1;
        int loadStation = -1;
        int goDown = -1;
    	for (int i = currentTrainStation + 1; i < 5; i++)
    	{
    		if (manager.getPassengerRequests(currentTrainStation,i) >0)
    		{
    			goDown = 1;  //set to 1 - meaning we should not search downwards
               	toLoadUnload++;
    			loadStation = i;
    			
               int countPeopleRequest = manager.getPassengerRequests(currentTrainStation, i);
    	       totalLoadedPassengers = totalLoadedPassengers + countPeopleRequest;
    	       numPassengers = numPassengers + countPeopleRequest;
    	       manager.setPassengerRequests(currentTrainStation, loadStation, -(countPeopleRequest));
    	       passengerDestinations[loadStation] = countPeopleRequest;
    	       moveQueue.add(new TrainEvent(loadStation,(Math.abs(currentTrainStation-loadStation) *5) + SimClock.getTime() + toLoadUnload * 10));
    		}
    	}
    	//never picked up any passengers
    	if (goDown == -1)
    	{
			for (int i = currentTrainStation -1; i >= 0; i--)
			{
	    		if (manager.getPassengerRequests(currentTrainStation,i) >0)
	    		{
	              	toLoadUnload++;    	                
	              	loadStation = i;
	              	int countPeopleRequest = manager.getPassengerRequests(currentTrainStation, i);
	              	totalLoadedPassengers = totalLoadedPassengers + countPeopleRequest;
	              	numPassengers = numPassengers + countPeopleRequest;
	              	manager.setPassengerRequests(currentTrainStation, loadStation, -(countPeopleRequest));
	              	passengerDestinations[loadStation] = countPeopleRequest;
	              	moveQueue.add(new TrainEvent(loadStation,(Math.abs(currentTrainStation-loadStation) *5) + SimClock.getTime() + toLoadUnload * 10));     	       
	    		}
			}
    	}
    }   
    
	public ArrayList<TrainEvent> getMoveQueue()
	{
		return moveQueue;
	}

	public void setMoveQueue(ArrayList<TrainEvent> moveQueue)
	{
		this.moveQueue = moveQueue;
	}
    
    public void setNumPassengers(int np) 
    {
        this.numPassengers = np;
    }

    public void setPassengerDestinations(int d, int i) 
    {
        this.passengerDestinations[i] = d;
    }

    public int getTrainID() {
        return trainID;
    }
    
    public void setTrainID(int id){
    	this.trainID = id;
    }

    public int getNumPassengers() {
        return numPassengers;
    }

    public int getTotalLoadedPassengers() {
        return totalLoadedPassengers;
    }

    public int getPassengerDestinations(int i) {
        return passengerDestinations[i];
    }

    public int getTotalUnloadedPassengers() {
        return totalUnloadedPassengers;
    }

    public int getCurrentStation() {
        return currentTrainStation;
    }
    
    public TrainSystemManager getManager()
	{
		return manager;
	}

	public void setManager(TrainSystemManager manager)
	{
		this.manager = manager;
	}
    
	public void addToTotalLoadedPassengers(int tlp) 
	{
		this.totalLoadedPassengers = this.totalLoadedPassengers + tlp;
	}
	
	public void addToTotalUnloadedPassengers(int tup) 
	{
		this.totalUnloadedPassengers = this.totalUnloadedPassengers + tup;
	}
    public String getAll(){
        return "Train #" + trainID + " has " + this.totalLoadedPassengers + " Entered passengers and "
        		+ this.totalUnloadedPassengers + " Exited passengers which are headed to  " + passengerDestinations[0] +"  " +
                passengerDestinations[1] + "  " + passengerDestinations[2] +"  " + passengerDestinations[3] +"  " + 
        		passengerDestinations[4];
    }
}
