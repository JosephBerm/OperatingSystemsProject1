import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Arrays;


public class FIFO
{
    public static int indActive = 1; 
    public static int indArrivalTime = 2;
    public static int indTotalCPUTime = 3;
    public static int indRemainingCPUTime = 4;
    public static int indTurnAroundTime = 5;
    public static int indPriorityLevel = 6;


    public static double algo(double [][] arr, int n)
    {
        Arrays.sort(arr, new Comparator<double[]>()
        {
            @Override
            public int compare(double[] o1, double[] o2)
            {
                double diff = o1[2] - o2[2];
                return diff < 0 ? - 1 : diff == 0 ? 0 : 1;
            }
            
        });
        return arr[n][indRemainingCPUTime];
    }
}
/**/

        /*int peek = pQueue.peek();
        pQueue.poll();
        return peek;*/
        