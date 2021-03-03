//AUTHORS:
// Joseph Bermudez   PID: 6052768
// Aiman Kayad       PID: 5975108
import java.util.Random;
import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.Queue;
import java.text.NumberFormat;
import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.Arrays;
import java.util.LinkedList; 
import java.util.Queue; 
import java.util.Scanner;

class Main
{
  public static void main(String[] args)
  {
    run();
  }
  static NumberFormat numberFormatter = new DecimalFormat("#0.00");
  static SortingArray sort = new SortingArray();

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

    process obj = new process(n, 1);
    double[][] mainArray = new double[n][7];
    double[][] mLArray = new double[n][7];
    obj.setProcess(mainArray);
    obj.setActive(mainArray);
    obj.setTimeInterval(k);
    obj.setArrivalTime(mainArray);
    obj.setCPUTime(d, v, mainArray);
    obj.setPriorityLevel(mainArray);

    Scanner keyboard = new Scanner(System.in);
    System.out.println("Would you like to see the table of processes?(Y/N)");
    String choice = keyboard.nextLine();

    if(choice.equalsIgnoreCase("y"))
    {
      System.out.println("\n~~~~~~~~~~TABLE OF PROCESSES:~~~~~~~~~~\n");
      obj.outPut(mainArray);
    }
    else if(!choice.equalsIgnoreCase("n") && !choice.equalsIgnoreCase("y"))
    {
      System.out.println("Invalid input... Now displaying Turn Around Times for every Algorithm:\n");
    }

    //mLArray = mainArray;
    for(int row = 0; row < mLArray.length; row++)
    {
      for(int column = 0; column < mLArray[row].length; column++)
      {
        mLArray[row][column] = mainArray[row][column];
      }
    }

    double currentTime = 0; // current time of the whole assignment
    double currentTimeFIFO = 0;
    double currentTimeSJF = 0;
    double currentTimeSRT = 0;
    double currentTimeML = 0;
    double TT = 0;
    double TTFIFO = 0;
    double TTSJF = 0;
    double TTSRT = 0;
    double TTML = 0;
    for (int row = 0; row < n; row++)
    {
        // check if each process is active, while no process is active, increment t
        if (row == mainArray.length - 1 && mainArray[row][obj.indActive] == 0)
        {
          currentTimeFIFO++;
          currentTimeSJF++;
          currentTimeSRT++;
          currentTimeML++;
        }
    }
        //FIFO
        for(int i = 0; i < mainArray.length; i++)
        {
          FIFO fifo = new FIFO();
          double rT = fifo.algo(mainArray, i);
          while(rT > 0)
          {
            rT--;
            currentTime++;
            currentTimeFIFO++;
            TT++;
          }
          TTFIFO += (currentTimeFIFO - mainArray[i][obj.indArrivalTime]);
        }
        System.out.println("\n\nTurn Around Time for FIFO:\t" + TTFIFO);
        //SJF
        for(int i = 0; i < mainArray.length; i++)
        {
          SJF shortestJobFirst = new SJF();
          double rT = shortestJobFirst.algo(mainArray, i);
          while(rT > 0)
          {
            rT--;
            currentTime++;
            currentTimeSJF++;
            TT++;
          }
          TTSJF += (currentTimeSJF - mainArray[i][obj.indArrivalTime]);
        }
        System.out.println("Turn Around Time for SJF:\t" + numberFormatter.format(TTSJF));
        //SRT
        for(int i = 0; i < mainArray.length; i++)
        {
          SRT shortestRemainingTime = new SRT();
          double rT = shortestRemainingTime.algoPrep(mainArray, i);
          double rTtemp = rT;
          //shortestRemainingTime.algo(mainArray, rT, i);
          mainArray = sort.sortArr(mainArray, 2);
          do
          {
            rT--;
            currentTime++;
            currentTimeSRT++;
            TT++;
            if((i + 1) >= mainArray.length)
            {
              break;
            }
          }while(rT > 0 && (mainArray[i + 1][obj.indRemainingCPUTime] > rT));
          if((i + 1) < mainArray.length)
          {
            if(mainArray[i + 1][obj.indRemainingCPUTime] < rTtemp)
            {
              while(mainArray[i + 1][obj.indRemainingCPUTime] < rTtemp && mainArray[i+1][obj.indRemainingCPUTime] > 0)
              {
                mainArray[i + 1][obj.indRemainingCPUTime]--;
                currentTime++;
                currentTimeSRT++;
                TT++;
              }
            }
          }
          double num = Double.parseDouble(numberFormatter.format(rT));
          if(num < 0)
          {
            mainArray[i][obj.indRemainingCPUTime] = 0;
          }
          if(i == mainArray.length - 1)
          {
            for(int check = 0; check < mainArray.length; check++)
            {
              while(mainArray[check][obj.indRemainingCPUTime] > 0)
              {
                mainArray[check][obj.indRemainingCPUTime]--;
                if(mainArray[check][obj.indRemainingCPUTime] < 0);
                {
                  mainArray[check][obj.indRemainingCPUTime] = 0;
                }
                currentTime++;
                currentTimeSRT++;
                TT++;
              }
            }
          }
          TTSRT += (currentTimeSRT - mainArray[i][obj.indArrivalTime]);
        }
        System.out.println("Turn Around Time for SRT:\t" + numberFormatter.format(TTSRT));
        //Preemptive Multi-level Priority Scheduling


    for (int row = 0; row < mainArray.length; row++)
    {
      if (mainArray[row][obj.indRemainingCPUTime] <= 0)
      {
        // set active flag of pi to 0
        mainArray[row][obj.indActive] = 0;
      }
    }

    //ML Scheduling Algo

    Queue<Double> P10 = new  LinkedList<>();
    Queue<Double> P9 = new  LinkedList<>();
    Queue<Double> P8 = new  LinkedList<>();
    Queue<Double> P7 = new  LinkedList<>();
    Queue<Double> P6 = new  LinkedList<>();
    Queue<Double> P5 = new  LinkedList<>();
    Queue<Double> P4 = new  LinkedList<>();
    Queue<Double> P3 = new  LinkedList<>();
    Queue<Double> P2 = new  LinkedList<>();
    Queue<Double> P1 = new  LinkedList<>();

    for(int row = 0; row < mLArray.length; row++)
    {


      if(mLArray[row][obj.indPriorityLevel] == 10)
      {
        P10.add(mLArray[row][0]);
      }
      else if(mLArray[row][obj.indPriorityLevel] == 9)
      {
        P9.add(mLArray[row][0]);
      }
      else if(mLArray[row][obj.indPriorityLevel] == 8)
      {
        P8.add(mLArray[row][0]);
      }
      else if(mLArray[row][obj.indPriorityLevel] == 7)
      {
        P7.add(mLArray[row][0]);
      }
      else if(mLArray[row][obj.indPriorityLevel] == 6)
      {
        P6.add(mLArray[row][0]);
      }
      else if(mLArray[row][obj.indPriorityLevel] == 5)
      {
        P5.add(mLArray[row][0]);
      }
      else if(mLArray[row][obj.indPriorityLevel] == 4)
      {
        P4.add(mLArray[row][0]);
      }
      else if(mLArray[row][obj.indPriorityLevel] == 3)
      {
        P3.add(mLArray[row][0]);
      }
      else if(mLArray[row][obj.indPriorityLevel] == 2)
      {
        P2.add(mLArray[row][0]);
      }
      else if(mLArray[row][obj.indPriorityLevel] == 1)
      {
        P1.add(mLArray[row][0]);
      }
    }
      boolean allZero = false;
      do
      {
        for(int i = 0; i < P10.size(); i++)
        {
          double temp = P10.peek();
          int timeQuantum = q;
          if(temp < 0 || temp >= mLArray.length)
          {
            break;
          }
          //System.out.println("P10 Temp: " + temp + "\trt index:" + obj.RemainingCPUTimes);
          while(mLArray[(int)temp][obj.indRemainingCPUTime] > 0)
          {
            if(timeQuantum == 0)
            {
              break;
            }
            mLArray[(int)temp][obj.indRemainingCPUTime]--;
            timeQuantum--;
            if(mLArray[(int)temp][obj.indRemainingCPUTime] < 0)
            {
              mLArray[(int)temp][obj.indRemainingCPUTime] = 0;
              currentTime++;
              currentTimeML++;
              TT++;
              break;
            }
            currentTime++;
            currentTimeML++;
            TT++;
          }
          P10.remove();
          P10.add(temp);

          TTML += (currentTimeML - mLArray[i][obj.indArrivalTime]);
        }
        for(int i = 0; i < P9.size(); i++) // p9
        {
          double temp = P9.peek();
          int timeQuantum = q;
          if(temp < 0 || temp >= mLArray.length)
          {
            break;
          }
          //System.out.println("P9 Temp: " + temp + "\trt index:" + obj.RemainingCPUTimes);
          while(mLArray[(int)temp][obj.indRemainingCPUTime] > 0)
          {
            if(timeQuantum == 0)
            {
              break;
            }
            mLArray[(int)temp][obj.indRemainingCPUTime]--;
            timeQuantum--;
            if(mLArray[(int)temp][obj.indRemainingCPUTime] < 0)
            {
              mLArray[(int)temp][obj.indRemainingCPUTime] = 0;
              currentTime++;
              currentTimeML++;
              TT++;
              break;
            }
            currentTime++;
            currentTimeML++;
            TT++;
          }
          P9.remove();
          P9.add(temp);

          TTML += (currentTimeML - mLArray[i][obj.indArrivalTime]);
        }
        for(int i = 0; i < P8.size(); i++) //p8
        {
          double temp = P8.peek();
          int timeQuantum = q;
          if(temp < 0 || temp >= mLArray.length)
          {
            break;
          }
         // System.out.println("P8 Temp: " + temp + "\trt index:" + obj.RemainingCPUTimes);
          while(mLArray[(int)temp][obj.indRemainingCPUTime] > 0)
          {
            if(timeQuantum == 0)
            {
              break;
            }
            mLArray[(int)temp][obj.indRemainingCPUTime]--;
            timeQuantum--;
            if(mLArray[(int)temp][obj.indRemainingCPUTime] < 0)
            {
              mLArray[(int)temp][obj.indRemainingCPUTime] = 0;
              currentTime++;
              currentTimeML++;
              TT++;
              break;
            }
            currentTime++;
            currentTimeML++;
            TT++;
          }
          P8.remove();
          P8.add(temp);

          TTML += (currentTimeML - mLArray[i][obj.indArrivalTime]);
        }
        for(int i = 0; i < P7.size(); i++) //p7
        {
          double temp = P7.peek();
          int timeQuantum = q;
          if(temp < 0 || temp >= mLArray.length)
          {
            break;
          }
         // System.out.println("P7 Temp: " + temp + "\trt index:" + obj.RemainingCPUTimes);
          while(mLArray[(int)temp][obj.indRemainingCPUTime] > 0)
          {
            if(timeQuantum == 0)
            {
              break;
            }
            mLArray[(int)temp][obj.indRemainingCPUTime]--;
            timeQuantum--;
            if(mLArray[(int)temp][obj.indRemainingCPUTime] < 0)
            {
              mLArray[(int)temp][obj.indRemainingCPUTime] = 0;
              currentTime++;
              currentTimeML++;
              TT++;
              break;
            }
            currentTime++;
            currentTimeML++;
            TT++;
          }
          P7.remove();
          P7.add(temp);

          TTML += (currentTimeML - mLArray[i][obj.indArrivalTime]);
        }
        for(int i = 0; i < P6.size(); i++) //p6
        {
          double temp = P6.peek();
          int timeQuantum = q;
          if(temp < 0 || temp >= mLArray.length)
          {
            break;
          }
          //System.out.println("P6 Temp: " + temp + "\trt index:" + obj.RemainingCPUTimes);
          while(mLArray[(int)temp][obj.indRemainingCPUTime] > 0)
          {
            if(timeQuantum == 0)
            {
              break;
            }
            mLArray[(int)temp][obj.indRemainingCPUTime]--;
            timeQuantum--;
            if(mLArray[(int)temp][obj.indRemainingCPUTime] < 0)
            {
              mLArray[(int)temp][obj.indRemainingCPUTime] = 0;
              currentTime++;
              currentTimeML++;
              TT++;
              break;
            }
            currentTime++;
            currentTimeML++;
            TT++;
          }
          P6.remove();
          P6.add(temp);

          TTML += (currentTimeML - mLArray[i][obj.indArrivalTime]);
        }
        for(int i = 0; i < P5.size(); i++) //p5
        {
          double temp = P5.peek();
          int timeQuantum = q;
          if(temp < 0 || temp >= mLArray.length)
          {
            break;
          }
        //  System.out.println("P5 Temp: " + temp + "\trt index:" + obj.RemainingCPUTimes);
          while(mLArray[(int)temp][obj.indRemainingCPUTime] > 0)
          {
            if(timeQuantum == 0)
            {
              break;
            }
            mLArray[(int)temp][obj.indRemainingCPUTime]--;
            timeQuantum--;
            if(mLArray[(int)temp][obj.indRemainingCPUTime] < 0)
            {
              mLArray[(int)temp][obj.indRemainingCPUTime] = 0;
              currentTime++;
              currentTimeML++;
              TT++;
              break;
            }
            currentTime++;
            currentTimeML++;
            TT++;
          }
          P5.remove();
          P5.add(temp);

          TTML += (currentTimeML - mLArray[i][obj.indArrivalTime]);
        }
        for(int i = 0; i < P4.size(); i++) //p4
        {
          double temp = P4.peek();
          int timeQuantum = q;
          if(temp < 0 || temp >= mLArray.length)
          {
            break;
          }
        //  System.out.println("P4 Temp: " + temp + "\trt index:" + obj.RemainingCPUTimes);
          while(mLArray[(int)temp][obj.indRemainingCPUTime] > 0)
          {
            if(timeQuantum == 0)
            {
              break;
            }
            mLArray[(int)temp][obj.indRemainingCPUTime]--;
            timeQuantum--;
            if(mLArray[(int)temp][obj.indRemainingCPUTime] < 0)
            {
              mLArray[(int)temp][obj.indRemainingCPUTime] = 0;
              currentTime++;
              currentTimeML++;
              TT++;
              break;
            }
            currentTime++;
            currentTimeML++;
            TT++;
          }
          P4.remove();
          P4.add(temp);

          TTML += (currentTimeML - mLArray[i][obj.indArrivalTime]);
        }
        for(int i = 0; i < P3.size(); i++) //p3
        {
          double temp = P3.peek();
          int timeQuantum = q;
          if(temp < 0 || temp >= mLArray.length)
          {
            break;
          }
        //  System.out.println("P3 Temp: " + temp + "\trt index:" + obj.RemainingCPUTimes);
          while(mLArray[(int)temp][obj.indRemainingCPUTime] > 0)
          {
            if(timeQuantum == 0)
            {
              break;
            }
            mLArray[(int)temp][obj.indRemainingCPUTime]--;
            timeQuantum--;
            if(mLArray[(int)temp][obj.indRemainingCPUTime] < 0)
            {
              mLArray[(int)temp][obj.indRemainingCPUTime] = 0;
              currentTime++;
              currentTimeML++;
              TT++;
              break;
            }
            currentTime++;
            currentTimeML++;
            TT++;
          }
          P3.remove();
          P3.add(temp);

          TTML += (currentTimeML - mLArray[i][obj.indArrivalTime]);
        }
        for(int i = 0; i < P2.size(); i++) //p2
        {
          double temp = P2.peek();
          int timeQuantum = q;
          if(temp < 0 || temp >= mLArray.length)
          {
            break;
          }
       //   System.out.println("P2 Temp: " + temp + "\trt index:" + obj.RemainingCPUTimes);
          while(mLArray[(int)temp][obj.indRemainingCPUTime] > 0)
          {
            if(timeQuantum == 0)
            {
              break;
            }
            mLArray[(int)temp][obj.indRemainingCPUTime]--;
            timeQuantum--;
            if(mLArray[(int)temp][obj.indRemainingCPUTime] < 0)
            {
              mLArray[(int)temp][obj.indRemainingCPUTime] = 0;
              currentTime++;
              currentTimeML++;
              TT++;
              break;
            }
            currentTime++;
            currentTimeML++;
            TT++;
          }
          P2.remove();
          P2.add(temp);

          TTML += (currentTimeML - mLArray[i][obj.indArrivalTime]);
        }
        for(int i = 0; i < P1.size(); i++) //p1
        {
          double temp = P1.peek();
          int timeQuantum = q;
          if(temp < 0 || temp >= mLArray.length)
          {
            break;
          }
         // System.out.println("P1 Temp: " + temp + "\trt index:" + obj.RemainingCPUTimes);
          while(mLArray[(int)temp][obj.indRemainingCPUTime] > 0)
          {
            if(timeQuantum == 0)
            {
              break;
            }
            mLArray[(int)temp][obj.indRemainingCPUTime]--;
            timeQuantum--;
            if(mLArray[(int)temp][obj.indRemainingCPUTime] < 0)
            {
              mLArray[(int)temp][obj.indRemainingCPUTime] = 0;
              currentTime++;
              currentTimeML++;
              TT++;
              break;
            }
            currentTime++;
            currentTimeML++;
            TT++;
          }
          P1.remove();
          P1.add(temp);

          TTML += (currentTimeML - mLArray[i][obj.indArrivalTime]);

        }
        for(int i = 0; i < mLArray.length; i++)
        {
          if(i == mLArray.length - 1)
            {
              for(int check = 0; check < mLArray.length; check++)
              {
                while(mLArray[check][obj.indRemainingCPUTime] > 0)
                {
                  mLArray[check][obj.indRemainingCPUTime]--;
                  if(mLArray[check][obj.indRemainingCPUTime] < 0);
                  {
                    mLArray[check][obj.indRemainingCPUTime] = 0;
                  }
                  currentTime++;
                  currentTimeML++;
                  TT++;
                }
              }
            }
        }

        //checking to see if all RemainingTimes are equal to zero, if not, redo all this until all equal zero
        //obj.outPut(mLArray);
        for(int currentRow = 0; currentRow < mLArray.length; currentRow++)
        {
          if(currentRow == (mLArray.length - 1) && mLArray[currentRow][obj.indRemainingCPUTime] == 0)
          {
            allZero = true;
            break;
          }
          if(mLArray[currentRow][obj.indRemainingCPUTime] != 0)
          {
            allZero = false;
            break;
          }
        }
      } while(allZero != true );
      
      System.out.println("Turn Around Time for ML:\t" + numberFormatter.format(TTML));
      double ATT = TT / n;
      System.out.println("Average Turn Around Time:\t" + numberFormatter.format(ATT));
      System.out.println("D / (Average Turn Around Time):\t" + numberFormatter.format(d/ATT));
      System.out.println("Would you like to see the table of processes after all the sceduling?(Y/N)");
      String choice2 = keyboard.nextLine();
  
      if(choice2.equalsIgnoreCase("y"))
      {
        System.out.println("\n~~~~~~~~~~TABLE OF PROCESSES:~~~~~~~~~~\n");
        mainArray = sort.sortArr(mainArray, 0);
        obj.outPut(mainArray);
      }
      else if(!choice2.equalsIgnoreCase("n") && !choice2.equalsIgnoreCase("y"))
      {
        System.out.println("Invalid input... Concluding Program\n");
      }
  }
}