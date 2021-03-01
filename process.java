import java.text.DecimalFormat;
import java.util.Random;

public class process
{
    int indActive = 1; 
    int indArrivalTime = 2;
    int indTotalCPUTime = 3;
    int indRemainingCPUTime = 4;
    int indTurnAroundTime = 5;
    int indPriorityLevel = 6;
    int processNum;
    int active;
    int arrivalTime;
    double totalCPUTimes;
    double RemainingCPUTimes;
    int turnAroundTime;
    int priorityLevel;
    int timeInterval;
    int currentTime = 0;

    Random rand = new Random();
    DecimalFormat numberFormatter = new DecimalFormat("#.00");

    public process(int PN, int A)
    {
        processNum = PN;
        active = A;
    }

    public double[][] mainArray = new double[processNum][7];

    //look at pseudocode

    public void setActive()
    {
        for(int i = 0; i < processNum; i++)
        {
            mainArray[i][indActive] = active;
            System.out.println(i);
        }
    }

    public void setTimeInterval(int k)
    {
        timeInterval = k;
    }

    public void setArrivalTime()
    {
        for (int i = 0; i < processNum;i++) 
        {
          arrivalTime = Math.abs(rand.nextInt(timeInterval));
          mainArray[i][indArrivalTime] = arrivalTime;
          setTurnAroundTime(currentTime, arrivalTime);
        }
    }

    public void setCPUTime(double d, double v)
    {
        for (int i = 0; i < processNum; i++) 
        {
            double cpuTime = rand.nextGaussian() *d+v;
            numberFormatter.format(cpuTime);

            RemainingCPUTimes = cpuTime;
            mainArray[i][indTotalCPUTime] = cpuTime;
            mainArray[i][indRemainingCPUTime] = RemainingCPUTimes;
        }
    }

    public void setTurnAroundTime(int currentT, int arrivalTime)
  {
    for (int i = 0; i < processNum; i++) 
    {
      int turnAround = currentT - arrivalTime;
      mainArray[i][indTurnAroundTime] = turnAround;
    }
  }

    public void setPriorityLevel()
    {
        for (int i = 0; i < processNum; i++) 
        {
          priorityLevel = rand.nextInt(10) + 1;
          mainArray[i][indPriorityLevel] = priorityLevel;
        }
    }

    public void outPut()
    {
        System.out.println("Process\tActive\tTotal CPU Time\tRemaining\tTurnaround Time\tPriority Level");
        for(int i = 0; i < processNum; i++)
        {
            System.out.println(i + ")\t" + mainArray[i][indActive] + "\t" +
            mainArray[i][indArrivalTime] + "\t" + mainArray[i][indTotalCPUTime] + "\t" +
            + mainArray[i][indRemainingCPUTime] + "\t" + mainArray[i][indTurnAroundTime] + "\t" +
            mainArray[i][indPriorityLevel]);
        }
    }
}