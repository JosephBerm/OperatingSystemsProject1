import java.text.DecimalFormat;

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
    int totalCPUTimes;
    int RemainingCPUTimes;
    int turnAroundTime;
    int priorityLevel;

    DecimalFormat numberFormatter = new DecimalFormat("#.00");

    public process(int PN, int A, int AT, int TCT,
    int RCT, int PL)
    {
        processNum = PN;
        active = A;
        arrivalTime = AT;
        totalCPUTimes = TCT;
        RemainingCPUTimes = RCT;
        priorityLevel = PL;
    }

    int[][] mainArray = new int[processNum][7];

    //look at pseudocode
    
}
