public class Introsort{

    
    /*Parameter array: The array to sort.
    Returns: Nothing.
    This function calls insertion sort if there's under 16 elements because
    insertion works better on small arrays and quicksort otherwise.*/
    public static void introsort(Array array){
        if (array.length() < 16){
            insertionsort(array, 0, array.length()-1);
        } else {
            quicksort(array, 0, array.length()-1, ((int) (Math.log(array.length())/Math.log(2)))*2);
        }
    }

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
    Parameter depthLimit: The number of levels of recursion remaining before heapsort is called.
    Returns: Nothing.
    This function starts by seeing if the subarray is small enough to use insertion sort or if
    it's deep enough in recursion to use heapsort instead of quicksort. If neither happen, it
    finds the median of the first, middle, and last elements in the subarray and pivots the
    subarray around that. It then calls itself twice, once on the subarray before the pivot
    element and once on the subarray after the pivot element.*/
    private static void quicksort(Array array, int start, int end, int depthLimit){
        if (start >= end || start < 0 || end > array.length()){
            return;
        } else if (end-start < 16){
            insertionsort(array, start, end);
        } else if (depthLimit == 0){
            heapsort(array, start, end);
        } else {
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
            quicksort(array, start, p-1, depthLimit-1);
            quicksort(array, p+1, end, depthLimit-1);
        }
    }

    /*Parameter array: The array to sort.
    Parameter start: The start of the subarray to be sorted.
    Parameter end: The end of the subarray to be sorted.
    Returns: Nothing.
    This function starts by heapifying the subarray and then uses heapsort
    on the subarray until it is small enough to call insertion sort.*/
    private static void heapsort(Array array, int start, int end){
        heapify(array, start, end);
        int curEnd = end;
        while (curEnd-start > 16){
            array.swap(curEnd, start);
            curEnd--;
            siftDown(array, start, start, curEnd);
        }
        insertionsort(array, start, curEnd+1);
    }

    /*Parameter array: The array to sort.
    Parameter start: The start of the subarray to be sorted.
    Parameter end: The end of the subarray to be sorted.
    Returns: Nothing.
    This function repeatedly calls siftdown on the first half of the subarray to heapify it.*/
    private static void heapify(Array array, int start, int end){
        int count = end-start;
        int middle = (count-1)/2+start;
        while (middle >= start){
            siftDown(array, start, middle, end);
            middle--;
        }
    }

    /*Parameter array: The array to sort.
    Parameter start: The start of the subarray to be sorted.
    Parameter end: The end of the subarray to be sorted.
    Returns: Nothing.
    This function swaps an element with its largest child element until it's in place for a heap.*/
    private static void siftDown(Array array, int root, int start, int end){
        int cur = start;
        while ((2*(cur-root))+1+root <= end){
            int child = (2*(cur-root))+1+root;
            int curVal = array.getVal(cur);
            int childVal = array.getVal(child);
            if (curVal < childVal){
                if (child+1 <= end && childVal < array.getVal(child+1)){
                    array.swap(cur, child+1);
                    cur = child+1;
                } else {
                    array.swap(cur, child);
                    cur = child;
                }
            } else if (child+1 <= end && curVal < array.getVal(child+1)){
                array.swap(cur, child+1);
                cur = child+1;
            } else {
                return;
            }
        }
    }

    /*Parameter array: The array to sort.
    Parameter start: The start of the subarray to be sorted.
    Parameter end: The end of the subarray to be sorted.
    Returns: Nothing.
    This function is a basic implementation of insertion sort that gets called on small subarrays.*/
    private static void insertionsort(Array array, int start, int end) {
        for (int i=start+1; i<=end; i++){
            int j = i;
            int cur = array.getVal(j);
            int last = array.getVal(j-1);
            while (j > start && cur < last){
                array.setVal(j, last);
                j--;
                if (j > start){
                    last = array.getVal(j-1);
                }
            }
            array.setVal(j, cur);
        }
    }

}