//import java.util.Random;
import java.util.Scanner;
import java.util.PriorityQueue;

class Main {
  public static void main(String[] args)
  {
    run();
  }
  public static void run()
  {
    //Arrival time: random int uniform dist.
    //Total CPU Time: random int gaussian dist.
    //Remaining CPU Time: initialized to Ti.

    Scanner scan = new Scanner(System.in);
    
    //Number of processes n
    System.out.println("Enter The Number Of Processes: ");

    int n = scan.nextInt();

  //A value for k, which is the time interval during which processes may arrive
    System.out.println("Enter a Time Interval Value: ");
    int k = scan.nextInt();
    
  //The mean of total CPU time, d.
    System.out.println("Enter The Mean Total CPU Time: ");

    double d = scan.nextDouble();

 //The standard deviation of total CPU time, v
    System.out.println("Enter The Standard Deviation CPU Time: ");

    double v = scan.nextDouble();

  //The quantum q, for the last scheduling algorithm.
    System.out.println("Enter a Time Quantum: ");

    int q = scan.nextInt();

    System.out.println("Number of Processes Entered: " + n);

    System.out.println("Time Interval: " + k);

    System.out.println("Mean CPU Time: " + d);

    System.out.println("Standard Deviation CPU Time: " + v);

    System.out.println("Time Quantum is: " + q);
    
    process obj = new process(n, 1);
    obj.setActive();
    obj.setArrivalTime();
    obj.setCPUTime(d, v);
    obj.setPriorityLevel();
    obj.outPut();
  }

  /*public static void FIFO (PriorityQueue <Integer> a)
  {
      a.remove();
      System.out.println(a.peek());
  }*/
}