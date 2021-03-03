import java.util.Comparator;
import java.util.Arrays;

public class SortingArray
{
    public static double[][] sortArr(double[][] arr, int n)
    {
        Arrays.sort(arr, new Comparator<double[]>()
        {
            @Override
            public int compare(double[] o1, double[] o2)
            {
                double diff = o1[n] - o2[n];
                return diff < 0 ? - 1 : diff == 0 ? 0 : 1;
            }
            
        });
        return arr;
    }
}