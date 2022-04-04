public class ThreeWayQuicksort<Item extends Comparable>{

    //3-way partition, does the comparing of items
    private int partition(){

    }

    //recursive
    public Item[] sort(Item[] a){

    }

    public int getPivot(Item[] array){
        //i. pivot is first item or any random item
        //ii. pivot is median of a[left], a[center], and a[right] of the sub array (or any three random elements in the sub array)
        int pivot;
        return pivot;
    }

    public Item[] insertionSort(Item[] a, int lo, int hi){
        int length = hi - lo;
        for(int i = 1; i < length; i++){
            for(int j = i; j > 0; j--){
                
            }
        }

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
        for(int i = 0; i < string.length(); i++){
            array[0] = string.charAt(i);
        }
        QS.insertionSort(array, 0, 15);
    }
}
