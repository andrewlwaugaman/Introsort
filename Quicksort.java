public class Quicksort {

    /*Parameter array: The array to sort.
    Parameter start: The start of the subarray to be sorted.
    Parameter end: The end of the subarray to be sorted.
    Returns: The position that the pivot element ended up in.
    This function separates every element above the pivot from every element less than or
    equal to the pivot and then puts the pivot in the middle and returns its new position.*/
    private static int pivot(Array array, int start, int end){
        int pivotIndex = end;
        int pivotValue = array.getVal(pivotIndex);
        int beforeStart = start-1;
        for (int i=start; i<end; i++){
            if (array.getVal(i) <= pivotValue){
                beforeStart++;
                array.swap(beforeStart, i);
            }
        }
        array.swap(beforeStart+1, end);
        return beforeStart+1;
    }

    /*Parameter array: The array to sort.
    Parameter start: The start of the subarray to be sorted.
    Parameter end: The end of the subarray to be sorted.
    Returns: Nothing.
    This function starts by finding the median of the first, middle, and last elements in the
    subarray and it then pivots the subarray around that. It then calls itself twice, once on
    the subarray before the pivot element and once on the subarray after the pivot element.*/
    public static void quicksort(Array array, int start, int end){
        if (start >= end || start < 0 || end > array.length()){
            return;
        }
        int firstVal = array.getVal(start);
        int lastVal = array.getVal(end);
        int midVal = array.getVal(start+(end-start+1)/2);
        int max = Math.max(Math.max(firstVal, midVal), lastVal);
        int min = Math.min(Math.min(firstVal, midVal), lastVal);
        int median = max ^ min ^ firstVal ^ lastVal ^ midVal;
        int pivotIndex;
        if (median == firstVal){
            pivotIndex = start;
        } else if (median == midVal){
            pivotIndex = start+(end-start+1)/2;
        } else {
            pivotIndex = end;
        }
        array.swap(pivotIndex, end);
        int p = pivot(array, start, end);
        quicksort(array, start, p-1);
        quicksort(array, p+1, end);
    }

}