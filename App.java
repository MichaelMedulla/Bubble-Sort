import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class App 
{
    public static int[] generateRandomArray(int size)
    {
        int[] arr = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++)
        {
            arr[i] = random.nextInt(100);
        }
        return arr;
    }

    public static void swap(int[] arr, int i, int j)
    {
        if (arr == null || i < 0 || j < 0 || i >= arr.length || j >= arr.length)
        {
            return;
        }
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void bubbleSort(int[] arr, int n)
    {
        if (arr == null || n <= 1)
        {
            return;
        }
        for(int i = 0; i < n; i++)
        {
            if (arr[i] > arr[i+1])
            {
                swap(arr, i, i+1);
            }
        }
        bubbleSort(arr, n-1);
    }

    /*public static boolean isArraySorted(int[] arr)
    {
        if(arr == null || arr.length <= 1)
        {
            return true;
        }
        for (int i = 0; i < arr.length-1; i++)
        {
            if (arr[i] > arr [i+1])
            {
                return false;
            }
        }
        return true;
    }*/

    public static void writeArrayToFile(int[] arr, String fp) throws IOException
    {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fp));

        for(int i = 0; i < arr.length; i++)
        {
            writer.write(Integer.toString(arr[i]));
            writer.newLine();
        }
        writer.close();
    }

    public static int[] readFileToArray(String fp) throws IOException
    {
        int[] arr = null;
        int count = 0;

        BufferedReader reader = new BufferedReader(new FileReader(fp));
        while(reader.readLine() != null)
        {
            count++;
        }
        reader.close();

        reader = new BufferedReader(new FileReader(fp));
        arr = new int[count];
        
        String line;
        int i = 0;

        while ((line = reader.readLine()) != null)
        {
            int num = Integer.parseInt(line.trim());
            arr[i++] = num;
        }
        reader.close();
        return arr;
    }

    public static void main(String[] args) throws Exception 
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Would you like to generate a random array(1) or read from a file(2)");
        Integer input = scan.nextInt();

        if (input == 1)
        {
            int[] arr = generateRandomArray(10);

            System.out.println("Array before sorting:");
            System.out.println(Arrays.toString(arr));

            bubbleSort(arr, arr.length-1);
            System.out.println("Array after sorting: ");
            System.out.println(Arrays.toString(arr));

            String fp = "array.txt";
            writeArrayToFile(arr, fp);
        }
        else if (input == 2)
        {
            String fp = "inputArray.txt";
            int[] arr = readFileToArray(fp);
            for(int num : arr)
            {
                System.out.println(num);
            }
            bubbleSort(arr, arr.length-1);
            System.out.println("Array after sorting: ");
            System.out.println(Arrays.toString(arr));

            String fp2 = "array2.txt";
            writeArrayToFile(arr, fp2);
        }
        else if(input != 1 && input != 2)
        {
            System.out.println("Invalid input, please try again");
        }
       
        /*int[] arr = generateRandomArray(10);

        System.out.println("Array before sorting:");
        System.out.println(Arrays.toString(arr));

        bubbleSort(arr, arr.length-1);
        System.out.println("Array after sorting: ");
        System.out.println(Arrays.toString(arr));

        String fp = "array.txt";
        writeArrayToFile(arr, fp);

        int[] array = readFileToArray(fp);
        for(int num : array)
        {
            System.out.println(num);
        }*/
        scan.close();
    }
}
