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
        System.out.println("new sort call====================");
        if ((hi - lo) <= 5) {  //base case: return if partitioned sub array has 30 elements or less
            insertionSort(a, lo, hi);
            return;
        }
        if(hi <= lo) return;

        int pivot = partition(a, lo, hi, pivotChoice);

        System.out.println("pivot = " + pivot);
        System.out.println("next call1: lo: "+lo + " hi: " + (pivot-1));
        sort(a, lo, pivot - 1, pivotChoice); //sort left
        System.out.println("next call2: lo: "+(pivot+1) + " hi: " + hi);
        sort(a, pivot + 1, hi, pivotChoice); //sort right
    }

    //3-way partition, does the comparing of items
    private static int partition(Comparable[] a, int lo, int hi, int pivotChoice){
        int pivot = getPivot(a, lo, hi,pivotChoice);
        System.out.println("inside partition() pivot: "+pivot);

        //make sure pivot is the first element
        if(pivot != lo) {
            swap(a, pivot, lo);
            pivot = lo;
        }

        for(int i = 0; i < 10; i++){
            System.out.println(a[i]);
        }

        int leftIndex = lo + 1;
        int rightIndex = hi;

        while (true) {
            while (a[leftIndex].compareTo(a[pivot]) < 0) {
                leftIndex++;
                if(leftIndex == hi) break;//scan l-r, compare to pivot, if  sorted, break
            }
            while (a[rightIndex].compareTo(a[pivot]) > 0){
                rightIndex--;
                if (rightIndex == lo) break; //scan r-l, compare to pivot, if sorted, break
            }
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
            case 1:
                pivot = lo;
                System.out.println("pivot is first element of sub array");
                break;
                //pivot is median of sub array
            case 2:
                pivot = median;
                System.out.println("pivot is median:");
                break;
            //median of a[lo], a[median], and a[hi] (median of 3)
            case 3:
                Comparable[] b = new Comparable[3];
                b[0] = a[lo];
                b[1] = a[median];
                b[2] = a[hi];
                Comparable[] medianOfThree = insertionSort(b, 0, 3);
                if (medianOfThree[1] == a[lo]) pivot = lo;
                else if (medianOfThree[1] == a[median]) pivot = median;
                else pivot = hi;
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
        //TODO make this whole thing 3-way quicksort
        Random rng = new Random(100);

        Integer[] array = new Integer[10];
        for(int i = 0; i < 10; i++){
            array[i] = rng.nextInt(10);
        }
        for(int i = 0; i < 10; i++){
            System.out.println(array[i]);
        }

        ThreeWayQuicksort.sort(array, 0, 9, 3);

        for(int i = 0; i < 10; i++){
            System.out.println(array[i]);
        }
//
//
//        Double[][] arrays = new Double[4][];
//
//        for(int j = 0; j < 4; j++) {
//            arrays[j] = new Double[(int) Math.pow(10, j+3)];
//            int length = (int) Math.pow(10, j + 3);
//            for (int i = 0; i < length - 1; i++) {
//                arrays[j][i] = rng.nextDouble();
//                System.out.println(arrays[j][i]);
//            }
//        }
//
//        int[] pivots = new int[4];
//        Scanner keyboard = new Scanner(System.in);
//        for(int i = 0; i < 4; i++){
//            System.out.println("For first element pivot, enter 1");
//            System.out.println("For median element pivot enter 2");
//            System.out.println("For left sub array median enter 3");
//            System.out.println("For right sub array median enter 4");
//            pivots[i] = keyboard.nextInt();
//        }
//
//        for(int j = 0; j < 4; j++){
//            long start = System.nanoTime();
//            ThreeWayQuicksort.sort(arrays[j], pivots[j]);
//            long end = System.nanoTime();
//            System.out.println("Time to sort 10^" + (j+3) + "elements" + (end - start) + " nanoseconds");
//        }


    }
}
