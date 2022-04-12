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
        if ((hi - lo) <= 30) {  //base case: return if partitioned sub array has 30 elements or less
            insertionSort(a, lo, hi);
            return;
        }
        if(hi <= lo) return;    //if hi and lo pointers cross, the sub array has been scanned
        int pivot = partition(a, lo, hi, pivotChoice);
        sort(a, lo, pivot - 1, pivotChoice); //sort left sub array
        sort(a, pivot + 1, hi, pivotChoice); //sort right sub array
    }

    //3-way partition, does the comparing of items
    private static int partition(Comparable[] a, int lo, int hi, int pivotChoice){
        int pivotIndex = getPivot(a, lo, hi,pivotChoice);
        Comparable pivot;

        //make sure pivot is the first element
        if(pivotIndex != lo) swap(a, pivotIndex, lo);
        pivot = a[lo];

        int lt = lo;
        int gt = hi;
        int i = lt + 1;

        while(i <= gt){
            int comparison = a[i].compareTo(pivot);
            if(comparison < 0){ //if a[i]<a[pivot], swap i with pivot, and keep scanning down sub array
                swap(a, lt++, i++);
            }
            else if(comparison > 0){ //if a[i]>a[pivot], swap i with gt and decrement gt
                swap(a, i, gt--);
            }
            else i++;   //else a[i] and a[pivot] are equal, so just increment i
        }
        return gt;

    }

    private static int getPivot(Comparable[] a, int lo, int hi, int pivotChoice){
        int pivot = 0;
        int median = lo + ((hi - lo)/2);
        //use switch case?
        switch (pivotChoice) {
            //i. pivot is first item or any random item
            case 1:
                pivot = lo;
                break;
                //pivot is median of sub array
            case 2:
                pivot = median;
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
    }

    public static void main(String[] args) {
        Random rng = new Random(33);

        int length = (int) Math.pow(10, 3);
        Double[] thirdPowerArray = new Double[length];
        for(int i = 0; i < length; i ++){
            thirdPowerArray[i] = rng.nextDouble();
        }
        long start = System.nanoTime();
        ThreeWayQuicksort.sort(thirdPowerArray, 0, length - 1, 1);
        long end = System.nanoTime();
        System.out.println("Time to sort 10^3 elements: " + (end - start) + " nanoseconds");


        length = (int) Math.pow(10, 4);
        Double[] fourthPowerArray = new Double[length];
        for(int i = 0; i < length; i ++){
            fourthPowerArray[i] = rng.nextDouble();
        }
        start = System.nanoTime();
        ThreeWayQuicksort.sort(fourthPowerArray, 0, length - 1, 1);
        end = System.nanoTime();
        System.out.println("Time to sort 10^4 elements: " + (end - start) + " nanoseconds");


        length = (int) Math.pow(10, 5);
        Double[] fifthPowerArray = new Double[length];
        for(int i = 0; i < length; i ++){
            fifthPowerArray[i] = rng.nextDouble();
        }
        start = System.nanoTime();
        ThreeWayQuicksort.sort(fifthPowerArray, 0, length - 1, 1);
        end = System.nanoTime();
        System.out.println("Time to sort 10^5 elements: " + (end - start) + " nanoseconds");


        length = (int) Math.pow(10, 6);
        Double[] sixthPowerArray = new Double[length];
        for(int i = 0; i < length; i ++){
            sixthPowerArray[i] = rng.nextDouble();
        }
        start = System.nanoTime();
        ThreeWayQuicksort.sort(sixthPowerArray, 0, length - 1, 1);
        end = System.nanoTime();
        System.out.println("Time to sort 10^6 elements: " + (end - start) + " nanoseconds");


    }
}
