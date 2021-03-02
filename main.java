
//import java.util.Random;
import java.util.Scanner;
import java.util.PriorityQueue;

class Main
{
  public static void main(String[] args)
  {
    run();
  }

  public static void run()
  {
    // Arrival time: random int uniform dist.
    // Total CPU Time: random int gaussian dist.
    // Remaining CPU Time: initialized to Ti.

    Scanner scan = new Scanner(System.in);

    // Number of processes n
    System.out.println("Enter The Number Of Processes: ");

    int n = scan.nextInt();

    // A value for k, which is the time interval during which processes may arrive
    System.out.println("Enter a Time Interval Value: ");
    int k = scan.nextInt();

    // The mean of total CPU time, d.
    System.out.println("Enter The Mean Total CPU Time: ");

    double d = scan.nextDouble();

    // The standard deviation of total CPU time, v
    System.out.println("Enter The Standard Deviation CPU Time: ");

    double v = scan.nextDouble();

    // The quantum q, for the last scheduling algorithm.
    System.out.println("Enter a Time Quantum: ");

    int q = scan.nextInt();

    System.out.println("Number of Processes Entered: " + n);

    System.out.println("Time Interval: " + k);

    System.out.println("Mean CPU Time: " + d);

    System.out.println("Standard Deviation CPU Time: " + v);

    System.out.println("Time Quantum is: " + q);

    System.out.println("\n~~~~~~~~~~TABLE OF PROCESSES:~~~~~~~~~~\n");
    process obj = new process(n, 1);
    double[][] mainArray = new double[n][7];
    obj.setProcess(mainArray);
    obj.setActive(mainArray);
    obj.setTimeInterval(k);
    obj.setArrivalTime(mainArray);
    obj.setCPUTime(d, v, mainArray);
    obj.setPriorityLevel(mainArray);
    obj.outPut(mainArray);

    int currentTime = 0;

    for (int row = 0; row < n; row++)
    {
      while(mainArray[row][obj.indRemainingCPUTime] != 0)
      {
        // check if each process is active, while no process is active, increment t
        if (mainArray[row][obj.indActive] == 0)
        {
          currentTime++;
        }

        FIFO fifo = new FIFO();
        fifo.algo(mainArray);



      }

      // Choose active processes pi according to scheduling algorithm
      mainArray[row][obj.indRemainingCPUTime] = mainArray[row][obj.indRemainingCPUTime--];

      if (mainArray[row][4] == 0)
      {
        // set active flag of pi to 0
        mainArray[n][1] = 0;
        mainArray[n][5] = currentTime - mainArray[n][2];
      }
    }
  }
}