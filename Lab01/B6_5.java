import java.util.Scanner;
public class B6_5 {
    public static void main (String[] args){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter n: ");
        int n = input.nextInt();
        int[]  arr = new int[n];
        System.out.print("Enter array: ");
        for (int i = 0; i < n; i++){
            arr[i] = input.nextInt();
        }
        QuickSort(arr, 0, n-1);
        System.out.print("Sorted array: ");
        for (int i = 0; i < n; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        int sum = 0;
        for (int i = 0; i < n; i++){
            sum += arr[i];
        }
        double average = (double) sum / n;
        System.out.print("Sum: " + sum + "\nAverage: " + average);
        input.close();
        System.exit(0);
    }
    private static void QuickSort(int[] a, int left, int right){
        int pivot;
        if (left < right){
            pivot = Partition(a,left,right);
            if (left < pivot)
                QuickSort(a,left,pivot-1);
            if (right > pivot)
                QuickSort(a,pivot+1, right);
        }
    }
    private static int Partition(int[] a, int left, int right){
        int pivot = a[left];
        int i = left; int j = right;
        while ( i < j ){
            i++;
            while (i < right && a[i] < pivot)
                i++;
            while (j > left && a[j] > pivot)
                j--;
            if (i < j) swap(a, i, j);
        }
        swap(a,left,j);
        return j;
    }
    private static void swap(int[] a, int i, int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}