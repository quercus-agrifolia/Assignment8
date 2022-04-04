import java.util.Random;
import java.util.Scanner;

final class ThreeWayQuicksort{
    private ThreeWayQuicksort(){}

    public static void sort(Comparable[]a, int pivotChoice){
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1, pivotChoice);
    }
    //recursive
    private static void sort(Comparable[] a, int lo, int hi, int pivotChoice){
        if (hi <= lo) return;   //base case: return if partitioned sub array has one element (it's sorted)
        int pivot = partition(a, lo, hi, pivotChoice);
        //TODO need to switch to insertion sort once elements get less than 30 elements
        sort(a, lo, pivot - 1, pivotChoice); //sort left
        sort(a, pivot + 1, hi, pivotChoice); //sort right
    }

    //3-way partition, does the comparing of items
    private static int partition(Comparable[] a, int lo, int hi, int pivotChoice){
        int pivot = getPivot(a, lo, hi,pivotChoice);
        int leftIndex = lo;
        int rightIndex = hi + 1;
        //make sure pivot is the first element
        if(pivot != lo) {
            swap(a, pivot, lo);
            pivot = lo;
        }
        while (true) {
            while (a[++leftIndex].compareTo(a[pivot]) < 0) if(leftIndex == hi) break;//scan l-r, compare to pivot, if  sorted, break
            while (a[--rightIndex].compareTo(a[pivot]) > 0) if (rightIndex == lo) break; //scan r-l, compare to pivot, if sorted, break
            if (leftIndex >= rightIndex) break;  //if left and right indices cross exit loop
            swap(a, leftIndex, rightIndex);
        }
        swap(a, pivot, rightIndex); //put pivot in its true place
        return rightIndex;
    }

    private static int getPivot(Comparable[] a, int lo, int hi, int pivotChoice){
        int pivot = 0;
        int median = lo + ((hi - lo)/2);
        //use switch case?
        switch (pivotChoice) {
            //i. pivot is first item or any random item
            case 1: pivot = lo;
                System.out.println("pivot is first element of sub array");
                break;
                //pivot is median of sub array
            case 2: pivot = median;
                System.out.println("pivot is median:");
                break;
            //median of a[left] and sub array median
            case 3: pivot = lo + ((median - lo) / 2);
                System.out.println("pivot is left median");
                break;
            //a[right] of the sub array
            case 4: pivot = median + ((hi - median) / 2);
                System.out.println("pivot is right median");
                break;
        }

        return pivot;
    }

    private static Comparable[] insertionSort(Comparable[] a, int lo, int hi){
        for(int i = 1; i < hi; i++){
            for(int j = i; j > 0 && (a[j].compareTo(a[j - 1]) < 0); j--){
                    swap(a, j, j - 1);
            }
        }
        return a;
    }
    private static void swap(Comparable[] a, int index1, int index2){
        Comparable temp1 = a[index1];
        a[index1] = a[index2];
        a[index2] = temp1;
        System.out.println(a[index1]+" swapped with "+a[index2]);
    }

    public static void main(String[] args) {
        Random rng = new Random();
        Double[] array1 = new Double[(int) Math.pow(10, 3)];
        Double[] array2 = new Double[(int) Math.pow(10, 4)];
        Double[] array3 = new Double[(int) Math.pow(10, 5)];
        Double[] array4 = new Double[(int) Math.pow(10, 6)];

        Double[][] arrays = new Double[4][];
        arrays[0] = array1;
        arrays[1] = array2;
        arrays[3] = array3;
        arrays[4] = array4;

        for(int i = 0; i < 4; i++){

        }

        int[] pivots = new int[4];
        Scanner keyboard = new Scanner(System.in);
        for(int i = 0; i < 4; i++){
            System.out.println("For first element pivot, enter 1");
            System.out.println("For median element pivot enter 2");
            System.out.println("For left sub array median enter 3");
            System.out.println("For right sub array median enter 4");
            pivots[i] = keyboard.nextInt();
        }

        for(int j = 0; j < 4; j++){
            long start = System.nanoTime();
            ThreeWayQuicksort.sort(array1, pivots[j]);
            long end = System.nanoTime();
            System.out.println("Time to sort 10^" + (j+3) + "elements" + (end - start) + " nanoseconds");
        }


    }
}
