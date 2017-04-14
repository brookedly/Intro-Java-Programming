//Brooke Ly brookedl 33256705
//Sunaina Kumar sunainak 27947698

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TrainSimulation
{
	int MAXSTATIONS = 5;
    private TrainSystemManager manager;
    private Train listOfTrains[];
    private Thread[] trainThreads = new Thread[MAXSTATIONS];
    private ArrayList<ArrayList<PassengerArrival>> passengerList;
    private Scanner s;
    private int totalSimulationTime;
    private int simulatedSecondRate;

    public TrainSimulation()
    {
        manager = new TrainSystemManager();
        listOfTrains = new Train[MAXSTATIONS];
        for (int i = 0; i < MAXSTATIONS; ++i)
        {
        	listOfTrains[i] = new Train(i, manager);
        }
        passengerList = new ArrayList<ArrayList<PassengerArrival>>(5);
        
        for (int i = 0; i < MAXSTATIONS; i++){
        	passengerList.add(new ArrayList<PassengerArrival>());
        }
    }

    public void start()
    {
        int success = readTheFile();
        if (success == 1){
	        try
	        {
	        	System.out.println("Running threads...");
	        	System.out.println("Printing the Train State at 50, 100, 150, 500, and 1000");
	        	for (int i = 0; i < MAXSTATIONS; i++) {
	        		trainThreads[i] = new Thread(listOfTrains[i]);
	        		trainThreads[i].start();
	    		}
	         
	            while (SimClock.getTime() < totalSimulationTime)
	            {
	                Thread.sleep(simulatedSecondRate);
	                SimClock.tick();
	                int time = SimClock.getTime();
				    for(int i = 0; i < 5; ++i) {
						for(PassengerArrival pa: passengerList.get(i)) {
							if(pa.getExpectedTimeOfArrival() == SimClock.getTime()) {
								int etofa = time + pa.getTimePeriod();
								pa.setExpectedTimeOfArrival(etofa);
								int dts = pa.getDestinationTrainStation();
								int gnp = pa.getNumPassengers();
								manager.setPassengerRequests(i, dts, gnp);
								manager.setTotalDestinationRequests(i, dts, gnp);
							}
						}
					}
				    if (SimClock.getTime() == 50){
				    	System.out.println("Train State at " + SimClock.getTime() + " simulated seconds.");
				    	printTrainState();
				    }
				    if (SimClock.getTime() == 100){
				    	System.out.println("Train State at " + SimClock.getTime() + " simulated seconds.");
				    	printTrainState();
				    }
				    if (SimClock.getTime() == 150){
				    	System.out.println("Train State at " + SimClock.getTime() + " simulated seconds.");
				    	printTrainState();
				    }
				    if (SimClock.getTime() == 500){
				    	System.out.println("Train State at " + SimClock.getTime() + " simulated seconds.");
				    	printTrainState();
				    }

	                if (SimClock.getTime() == 1000){
	                	System.out.println("Train State at " + SimClock.getTime() + " simulated seconds.");
	                	printTrainState();
	                }
	            }
	                 
	            for (int i = 0; i < MAXSTATIONS; i++){			
					trainThreads[i].interrupt();
				}
	        }
	        catch (InterruptedException e)
	        {
	            System.out.println("Thread has been interrupted");
	        }
        }
        else {
        	System.out.println("ERROR file does not exist - could not open file");
        }
    }

    private int readTheFile()
    {
        File trainFile = new File("src/TrainConfig.txt");
        String tempString = "";
        try
        {
            s = new Scanner(trainFile);
            totalSimulationTime = Integer.parseInt(s.nextLine());
            simulatedSecondRate = Integer.parseInt(s.nextLine());
            while (s.hasNextLine()){
            	for (int i = 0; i < MAXSTATIONS; ++i)
            	{
            		String[] temp;
            		temp = s.nextLine().split(";");
                    for (int k = 0; k < temp.length; k++)
                    {                        
                        int a = Integer.parseInt(temp[k].split(" ")[0]);
                        int b = Integer.parseInt(temp[k].split(" ")[1]);
                        int c = Integer.parseInt(temp[k].split(" ")[2]);
                        int d = Integer.parseInt(temp[k].split(" ")[2]);
                        PassengerArrival pa = new PassengerArrival(a,b,c,d);
                        passengerList.get(i).add(pa);
                    }
                }	
            }
            s.close();
            return 1;
        }
        catch (FileNotFoundException e)
        {
            return -1;
        }
    }

    public void printTrainState()
    {
        String result = "";
        System.out.println("Current time: " + SimClock.getTime());
        result += manager.getAll();
        result += "Trains: \n";
        result += "---------------------------------------------------------------------------------------------------------\n";
        result += "Train #       " + "Entered                " +"Exited                   " + "  Headed to: t0  t1  t2  t3  t4\n";
        result += "---------------------------------------------------------------------------------------------------------\n";
        for (Train train: listOfTrains) {
            result += train.getAll();
            result += "\n";
        }
        System.out.print(result);
    }
}