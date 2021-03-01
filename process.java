import java.text.NumberFormat;
import java.text.DecimalFormat;
import java.util.Random;

public class process
{
    public static int indActive = 1; 
    public static int indArrivalTime = 2;
    public static int indTotalCPUTime = 3;
    public static int indRemainingCPUTime = 4;
    public static int indTurnAroundTime = 5;
    public static int indPriorityLevel = 6;
    public static int processNum;
    public static int active;
    public static int arrivalTime;
    public static double totalCPUTimes;
    public static double RemainingCPUTimes;
    public static int turnAroundTime;
    public static int priorityLevel;
    public static int timeInterval;
    public static int currentTime = 0;


    static Random rand = new Random();
    static NumberFormat numberFormatter = new DecimalFormat("#0.00");

    public process(int PN, int A)
    {
        processNum = PN;
        active = A;
    }

    

    //look at pseudocode

    public static void setActive(double[][] arr)
    {
        //mainArray = new double[processNum][7];
        System.out.println("main array length: " + arr.length);
       // int row = 0;
        //while(row < mainArray)
        for(int row = 0; row < arr.length; row++)
        {
            arr[row][indActive] = active;
            //System.out.print("SET ACTIVE " + mainArray[row][indActive]);
        }
    }

    public static void setTimeInterval(int k)
    {
        timeInterval = k;
    }

    public static void setArrivalTime(double[][] arr)
    {
        //mainArray = new double[processNum][7];
        for (int row = 0; row < arr.length; row++) 
        {
          arrivalTime = Math.abs(rand.nextInt(timeInterval));
          arr[row][indArrivalTime] = arrivalTime;
          //System.out.print("\nSET ARRIVAL TIME" + mainArray[row][indArrivalTime]);
          setTurnAroundTime(currentTime, arrivalTime, row, arr);
          
        }
    }

    public static void setCPUTime(double d, double v, double[][] arr)
    {
        //mainArray = new double[processNum][7];
        for (int row = 0; row < arr.length; row++) 
        {
            double cpuTime = rand.nextGaussian() *d+v;
            numberFormatter.format(cpuTime);
            System.out.println("THIS IS THE CPU TIME: " + numberFormatter.format(cpuTime));
            RemainingCPUTimes = cpuTime;
            arr[row][indTotalCPUTime] = cpuTime;
            arr[row][indRemainingCPUTime] = RemainingCPUTimes;
            //System.out.println("SET CPUTime " + mainArray[row][indTotalCPUTime]);
        }
    }

    public static void setTurnAroundTime(int currentT, int arrivalTime, int row, double[][] arr)
  {
      //mainArray = new double[processNum][7];

      int turnAround = currentT - arrivalTime;
      arr[row][indTurnAroundTime] = turnAround;

      //System.out.println(row + ")\t" + "SET TAT" + mainArray[row][indTurnAroundTime]);
  }

    public static void setPriorityLevel(double[][] arr)
    {
        //mainArray = new double[processNum][7];
        for (int row = 0; row < arr.length; row++) 
        {
          priorityLevel = rand.nextInt(10) + 1;
          arr[row][indPriorityLevel] = priorityLevel;
          //System.out.print("\nSET PL " + mainArray[row][indPriorityLevel]);
        }
    }

    public static String outPut(double[][] arr)
    {
       // mainArray = new double[processNum][7];
        System.out.println("~P~\t~Act~\t~ATime~\t~CPUT~\t~RT~\t~TAT~\t~PL~");
        //System.out.println("\n\n1/0 process 4: " + mainArray[4][1] + "\n\n");
        for(int row = 0; row < arr.length; row++)
        {
            System.out.print("\n");
            for(int column = 0; column < arr[row].length; column++)
            {
                System.out.print("~" + arr[row][column] + "~" + "\t");
            }
        }
        return "";
    }

    
}