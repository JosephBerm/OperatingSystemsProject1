import java.util.Random;
import java.util.Scanner;

import java.util.PriorityQueue;

class Main {
  public static void main(String[] args)
  {
    run();
  }


  public static void run()
  {
    int process;
    boolean active;
    //Arrival time: random int uniform dist.
    //Total CPU Time: random int gaussian dist.
    //Remaining CPU Time: initialized to Ti.
    
    
    process ob = new process(15,1,0,20,20,6);
    

    Scanner scan = new Scanner(System.in);
    
    //Number of processes n
    System.out.println("Enter The Number Of Processes: ");

    int n = scan.nextInt();



  //A value for k, which is the time interval during which processes may arrive
    System.out.println("Enter a Time Interval Value: ");
    int k = scan.nextInt();
    


  //The mean of total CPU time, d.
    System.out.println("Enter The Mean Total CPU Time: ");

    int d = scan.nextInt();

 
 //The standard deviation of total CPU time, v
    System.out.println("Enter The Standard Deviation CPU Time: ");

    Double v = scan.nextDouble();

    
  //The quantum q, for the last scheduling algorithm.
    System.out.println("Enter a Time Quantum: ");

    int q = scan.nextInt();




    System.out.println("Number of Processes Entered: " + n);

    System.out.println("Time Interval: " + k);

    System.out.println("Mean CPU Time: " + d);

    System.out.println("Standard Deviation CPU Time: " + v);

    System.out.println("Time Quantum is: " + q);
    
     Random rand = new Random();
    
    
    //Total CPU Time, integer chosen randomly from gaussian distribution with an average d and a standard deviation v, where d and v are simulation parameters
    for (int i = 0; i < n; i++) 
    {
      double cpuTime = rand.nextGaussian() *d+v;

      System.out.println( i + ")\t" + "CPU Time generated: " + cpuTime);

      double remainingCPUTime = cpuTime;
    }


    //Using a random number generator, derive m arrival times, Ai, for all processes, distributed uniformly within the interval [0:k].

    for (int i = 0; i < n;i++) 
    {
      
      int arrivalTime = rand.nextInt(k + 1);

       System.out.println("Random arrival time generated is: " + arrivalTime);

    }
    
    //Priority level for the last algorithm
    for (int i = 0; i < n; i++) 
  {

    int priorityLevel = rand.nextInt(10) + 1;

    System.out.println("Priority level is: " + priorityLevel);
  }
    
    

    
  }

  public static int turnAroundTime(int currentTime, int arrivalTime)
  {
      int turnAround = currentTime - arrivalTime;
      return turnAround;
  }

  public static int totalCPUTime(int n)
  {
      return 0;
  }

  public static void FIFO (PriorityQueue <Integer> a)
  {
      a.remove();
      System.out.println(a.peek());
  }
}
