public class IntroSortTest {
    
	private static int[] arraySizes = new int[]{10, 100, 1000, 10000, 100000, 1000000};

    public static void main(String[] args) {
	//run tests for ArraySort
	for(int testnum = 0; testnum < 6; testnum++)
	    test(testnum);
    }

    private static void test(int testNum) {
		System.out.println("\n***** BEGIN TEST " + (testNum+1) + "*****");
		int[] baseArray1 = new int[arraySizes[testNum]];
		int[] baseArray2 = new int[arraySizes[testNum]];
		for (int i=0; i<arraySizes[testNum]; i++){
			int val = (int) (Math.random()*1000);
			baseArray1[i] = val;
			baseArray2[i] = val;
		}

		Array array1 = new Array(baseArray1);
		Array array2 = new Array(baseArray2);

		System.out.println(array2.length());

		long introStart = System.currentTimeMillis();
		Introsort.introsort(array1);
		long introEnd = System.currentTimeMillis();
		Quicksort.quicksort(array2, 0, array2.length()-1);
		long quickEnd = System.currentTimeMillis();

		long c1 = array1.getAccessCount();
		long c2 = array2.getAccessCount();

		boolean isSorted = true;

		for (int i=0; i<array1.length()-1; i++){
			if (array1.getVal(i) > array1.getVal(i+1)){
				isSorted = false;
			}
			if (array2.getVal(i) > array2.getVal(i+1)){
				isSorted = false;
			}
		}

		if(isSorted) {
			System.out.println("Array is sorted!");
		} else {
			System.out.println("Array is not sorted.");
			System.out.println(array1);
		}
		System.out.println("Introsort access count: " + c1);
		System.out.println("Quicksort access count: " + c2);
		System.out.println("Introsort time taken: " + (introEnd-introStart));
		System.out.println("Quicksort time taken: " + (quickEnd-introEnd));
		return;
    }
}