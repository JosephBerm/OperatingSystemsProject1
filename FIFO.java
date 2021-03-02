import java.util.PriorityQueue;


public class FIFO
{
    public static int indActive = 1; 
    public static int indArrivalTime = 2;
    public static int indTotalCPUTime = 3;
    public static int indRemainingCPUTime = 4;
    public static int indTurnAroundTime = 5;
    public static int indPriorityLevel = 6;


    public static int algo(double [][] arr)
    {
        double smallest;
        for(int row = 0; row < arr.length; row++)
        {
            smallest = arr[row][indArrivalTime];
            if(smallest < arr[row][indArrivalTime])
            {
                
            }
        }

        /*int peek = pQueue.peek();
        pQueue.poll();
        return peek;*/
        return 0;
    }
}
