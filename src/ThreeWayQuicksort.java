import java.util.Scanner;

public class ThreeWayQuicksort<Item extends Comparable>{

    //3-way partition, does the comparing of items
    private int partition(){
        return 0;
    }

    //recursive
    public Item[] sort(Item[] a){
        return a;
    }

    public int getPivot(Item[] a, int lo, int hi){
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

    public Item[] insertionSort(Item[] a, int lo, int hi){
        for(int i = 1; i < hi; i++){
            for(int j = i; j > 0 && (a[j].compareTo(a[j - 1]) < 0); j--){
                    swap(a, j, j - 1);
            }
        }
        return a;
    }
    private void swap(Item[] a, int index1, int index2){
        Item temp1 = a[index1];
        a[index1] = a[index2];
        a[index2] = temp1;
    }

    public static void main(String[] args) {
        ThreeWayQuicksort<Character> QS = new ThreeWayQuicksort<>();
        String string = "QUICKSORTEXAMPLE";
        Character[] array = new Character[16];
        for (int i = 0; i < string.length(); i++) {
            array[i] = string.charAt(i);

        }
        Character[] sortedArray = QS.insertionSort(array, 1, 16);
        for (int i = 0; i < 16; i++) {
            System.out.println(sortedArray[i]);
        }
        QS.getPivot(array, 1, 16);
    }
}
