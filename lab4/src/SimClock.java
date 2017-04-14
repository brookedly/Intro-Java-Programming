//Brooke Ly brookedl 33256705
//Sunaina Kumar sunainak 27947698
public class SimClock {
    private static int tick;

    public SimClock()
    {
        tick = 0;
    }

    public static void tick(){
        tick++;
    }

    public static int getTime(){
        return tick;
    }
}