import java.util.Random;
import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    
    int process;
    boolean active;
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

    int d = scan.nextInt();

 
 //The standard deviation of total CPU time, v
    System.out.println("Enter The Standard Deviation CPU Time: ");

    int v = scan.nextInt();

    
  //The quantum q, for the last scheduling algorithm.
    System.out.println("Enter a Time Quantum: ");

    int q = scan.nextInt();




    System.out.println("Number of Processes Entered: " + n);

    System.out.println("Time Interval: " + k);

    System.out.println("Mean CPU Time: " + d);

    System.out.println("Standard Deviation CPU Time: " + v);

    System.out.println("Time Quantum is: " + q);



    //Using a random number generator, derive m arrival times, Ai, for all processes, distributed uniformly within the interval [0:k].

    Random rand = new Random();

    for (int i = 0; i < n;i++) 
    {
      
      int m = rand.nextInt(k + 1);

       System.out.println("Random arrival time generated is: " + m);

    }

  }
}