import java.util.Scanner;

final class ThreeWayQuicksort{
    private ThreeWayQuicksort(){}

    //3-way partition, does the comparing of items
    public static Comparable[] partition(Comparable[] a, int lo, int hi){
        int pivot = getPivot(a, lo, hi);
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
        return a;
    }

    //recursive
    public static void sort(Comparable[] a){

    }
    private Comparable[] sort(Comparable[] a, int lo, int hi){
        return a;
    }

    public static int getPivot(Comparable[] a, int lo, int hi){
        int pivot = 0;
        int median = lo + ((hi - lo)/2);
        int pivotChoice;
        Scanner keyboard = new Scanner(System.in);
        System.out.println("For first element pivot, enter 1");
        System.out.println("For median element pivot enter 2");
        System.out.println("For left sub array median enter 3");
        System.out.println("For right sub array median enter 4");
        System.out.println("\n");
        pivotChoice = keyboard.nextInt();
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
        System.out.println("pivot element: "+a[pivot]);
        return pivot;
    }

    public static Comparable[] insertionSort(Comparable[] a, int lo, int hi){
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

    //todo just made everything static was that a good idea I don't know?????

    public static void main(String[] args) {
        String string = "ILOVEALGORITHMS";
        Character[] array = new Character[15];
        for (int i = 0; i < string.length(); i++) {
            array[i] = string.charAt(i);
        }

        Comparable[] partitionedArray = ThreeWayQuicksort.partition(array, 0, 14);
        for (int i = 0; i < string.length(); i++) {
            System.out.println(partitionedArray[i]);
        }
    }
}
