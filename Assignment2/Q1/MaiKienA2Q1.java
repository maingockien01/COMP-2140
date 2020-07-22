/**
* MaiKienA2Q1
*
* COMP 2140 SECTION D01
* ASSIGNMENT    Assignment 2, question 1
* @author       Kien Mai - 7876083
* @version      June 12
*
* PURPOSE: 	Implemt a number of sorting algorithm 
*/

import java.util.*;
import java.io.*;


public class MaiKienA2Q1 {

    // Control the testing
    private static final int ARRAY_SIZE = 10000;
    private static final int SAMPLE_SIZE = 300; // The number of trials in each experiment.

    // Control the randomness
    private static final int NUM_SWAPS = ARRAY_SIZE / 2;
    private static Random generator = new Random( System.nanoTime() );

    // Control the base cases for hybrid quick sort:
    private static final int BREAKPOINT = 50;

    // Controls which sort is tried.
    private static final int INSERTION_SORT = 0;
    private static final int BUBBLE_SORT = 1;
    private static final int SELECTION_SORT = 2;
    private static final int MERGE_SORT = 3;
    private static final int QUICK_SORT = 4;
    private static final int HYBRID_QUICK_SORT = 5;
    private static final int SHELL_SORT = 6;

/*********** main and the method it calls *************************/

    /*******************************************************************
     * main
     *
     * Purpose: Print out "bookend" messages and call the method that
     *          does all the testing of the sorting algorithms.
     *
     ******************************************************************/
    public static void main( String[] args ) {
		System.out.println( "\n\nCOMP 2140 A2Q1 Sorting Test --- Summer 2020\n" );

		testSorts();

		System.out.println( "\nProcessing ends normally\n" );
    } // end main


    /*******************************************************************
     * testSorts
     *
     * Purpose: Run each sorting algorithm SAMPLE_SIZE times,
     *          on an array of size ARRAY_SIZE with NUM_SWAPS
     *          random swaps performed on it.
     *          Compute the arithmetic mean of the timings for each sorting algorithm.
     *
     *          Print the results.
     *
     ******************************************************************/
    private static void testSorts() {

		// Arrays used in timing experiments (create these arrays once)
		int[] array = new int[ARRAY_SIZE]; // array to be sorted
		long[] sortTime = new long[ SAMPLE_SIZE ]; // store timings for multiple runs
	                                           // of a single sorting method
		// Fill array to be sorted with the numbers 0 to ARRAY_SIZE.
		// (The numbers will be randomly swapped before each sort.)
		fillArray( array );

		// Now run the experiments on all the sorts
		System.out.println("Array size: " + ARRAY_SIZE + "\nNumber of swaps: " + NUM_SWAPS);
		System.out.println("Number of trials of each sort: " + SAMPLE_SIZE );

		// Stats for each run
		System.out.println("\nInsertion sort mean: "
			   + tryOneSort( array, sortTime, INSERTION_SORT )
			   + " ns" );
		System.out.println("Bubble sort mean: "
			   + tryOneSort( array, sortTime, BUBBLE_SORT )
			   + " ns" );
		System.out.println("Selection sort mean: "
			   + tryOneSort( array, sortTime, SELECTION_SORT )
			   + " ns" );
		System.out.println("Merge sort mean: "
			   + tryOneSort( array, sortTime, MERGE_SORT )
			   + " ns" );
		System.out.println("Quick sort mean: "
			   + tryOneSort( array, sortTime, QUICK_SORT )
			   + " ns" );
		System.out.println("Hybrid quick sort mean: "
			   + tryOneSort( array, sortTime, HYBRID_QUICK_SORT )
			   + " ns" );
		System.out.println("Shell sort mean: "
			   + tryOneSort( array, sortTime, SHELL_SORT )
			   + " ns" );

    } // end testSorts

/*********** methods called by testSorts *************************/

    /*******************************************************************
     * tryOneSort:
     *
     * Purpose: Get an average run time for a sorting algorithm.
     *
     * Methodology: Run the chosen sorting algorithm SAMPLE_SIZE times,
     *          on an array of size ARRAY_SIZE with NUM_SWAPS
     *          random swaps performed on it.
     *          Return the arithmetic mean of the timings.
     *
     ******************************************************************/
    private static double tryOneSort( int[] array, long[] sortTime, int whichSort ) {

		long start, stop, elapsedTime;  // Time how long each sort takes.

		start = stop = 0; // because the compiler complains that they might
		                  // not have been initialized inside the for-loop

		for ( int i = 0; i < SAMPLE_SIZE; i++ ) {
		    randomizeArray( array, NUM_SWAPS );
		    if ( whichSort == INSERTION_SORT ) {
				start = System.nanoTime();
				insertionSort( array );
				stop = System.nanoTime();
				checkArray(array, "Insertion sort");
		    } else if ( whichSort == BUBBLE_SORT ) {
				start = System.nanoTime();
				bubbleSort( array );
				stop = System.nanoTime();
				checkArray(array, "Bubble sort");
		    } else if ( whichSort == SELECTION_SORT ) {
				start = System.nanoTime();
				selectionSort( array );
				stop = System.nanoTime();
				checkArray(array, "Selection sort");
		    } else if ( whichSort == MERGE_SORT ) {
				start = System.nanoTime();
				mergeSort( array );
				stop = System.nanoTime();
				checkArray(array, "Merge sort");
		    } else if ( whichSort == QUICK_SORT ) {
				start = System.nanoTime();
				quickSort( array );
				stop = System.nanoTime();
				checkArray(array, "Quick sort");
		    } else if ( whichSort == HYBRID_QUICK_SORT ) {
				start = System.nanoTime();
				hybridQuickSort( array );
				stop = System.nanoTime();
				checkArray(array, "Hybrid quick sort");
		    } else if ( whichSort == SHELL_SORT ) {
				start = System.nanoTime();
				shellSort( array );
				stop = System.nanoTime();
				checkArray(array, "Shell sort");
		    }
		    elapsedTime = stop - start;
		    sortTime[i] = elapsedTime;
		} // end for

		return arithmeticMean( sortTime );
    } // end tryOneSort


/********** Add sort methods here ********************/


    // Non-recursive Insertion sort
    private static void insertionSort (int[] array, int start, int end) {
        int temp = 0;
        for(int i = start+1; i < end; i ++) {
            int j = i;
            while(j > start && array[j] < array[j-1]) {
                swap(array, j, j-1);
                j --;
            }  
        };
    } //End insertionSort helper

    public static void insertionSort (int[] array) {
        insertionSort(array, 0, array.length);
    } //End insertionSort

    //Non-recursive bubble sort
    public static void bubbleSort (int[] array) {
        for (int i = 0; i < array.length-1; i ++) {
            for (int j = 0; j < array.length-1-i; j ++) {
                if (array[j] > array[j+1]) {
                    swap(array, j, j +1);
                }
            }
        }
    } //End bubbleSort

    //Non-recursive selection sort
    public static void selectionSort (int[] array) {
        for (int i = 0; i< array.length-1; i ++) {
            int smallestIndex = i;
            for (int j = i +1; j < array.length; j ++) {
                if (array[j] < array[smallestIndex]) { 
                    smallestIndex = j;
                }
            };
            swap(array, smallestIndex, i);
        }
    } //End selectionSort

    //Recursive mergeSort 
    public static void mergeSort (int[] array) {
        int[] temp = new int[array.length];
        mergeSort(array, 0, array.length, temp);
    } //End mergeSort

    private static void mergeSort (int[] array, int start, int end, int[] temp) {
        //Base cases
        if(end <= start) {
            return;
        };
        if(end - start == 1) {
            return;
        };
        if(end - start == 2) {
            if (array[start] > array[start+1]) {
                swap(array, start, start+1);
            };
        };
        //Recursive case
        int mid = (start+end)/2;
        mergeSort(array, start, mid, temp);
        mergeSort(array, mid, end, temp);
        merge(array, start, mid, end, temp);
    } //End mergeSort helper

    private static void merge (int[] array, int start, int mid, int end, int[] temp) {
        int firstArrayIndex = start;
        int secondArrayIndex = mid;
        
        for(int i = start ; i < end; i ++) {
            if(firstArrayIndex < mid && (secondArrayIndex >= end || array[firstArrayIndex] < array[secondArrayIndex])) {
                temp[i] = array[firstArrayIndex];
                firstArrayIndex ++;
            } else {
                temp[i] = array[secondArrayIndex];
                secondArrayIndex ++;
            }
        }

        for (int i = start; i < end; i ++) {
            array[i] = temp[i];
        };
    }  //End merge

    //Recursive quick sort
    public static void quickSort(int[] array) {
        quickSort(array, 0, array.length);
    } //End quickSort

    private static void quickSort(int[] array, int start, int end) {
        //Base cases
        if (start >= end) {
            return;
        };
        if (start - end == 1) {
            return;
        };
        if (start - end == 2) {
            if (array[start] > array[start+1]) {
                swap(array, start, start+1);
            }
        };
        //Recursive case
        int pivotIndex = choosePivot(array, start, end);
        pivotIndex = partitionArray(array, start, end, pivotIndex);
        quickSort(array, start, pivotIndex);
        quickSort(array, pivotIndex+1,end);
    } //End quickSort helper

    //Median-of-three
    private static int choosePivot(int[] array, int start, int end) {
        int mid = (start + end) /2;

        int startNum = array[start];
        int midNum = array[mid];
        int endNum = array[end-1];

        if( (startNum-midNum)*(startNum-endNum) < 0 ) {
            return start;
        } else if ( (midNum-startNum) * (midNum-endNum) < 0 ) {
            return mid;
        } else {
            return end-1;
        }
    } //End choosePivot

    // Given the index of pivot to partition the array
    // return the final position of pivot
    private static int partitionArray(int[] array, int start, int end, int pivotIndex) {
        // Swap pivot to start 
        swap(array, start,pivotIndex);
        pivotIndex = start;
        int pivot = array[pivotIndex];
        // Partitioning
        int leftLastIndex = pivotIndex;
         for (int i = pivotIndex +1; i < end; i ++) {
             if(array[i] < pivot) {
                 leftLastIndex ++;
                 swap(array, i, leftLastIndex);
             };
         };
         // Swap pivot to the last position of left array
         swap(array, pivotIndex, leftLastIndex);
         pivotIndex = leftLastIndex;
         return pivotIndex;
    } //End partitionArray

    //Hybrid recursive quick sort 
    private static void hybridQuickSort (int[] array, int start, int end) {
        //Base case
        if (start >= end) {
            return;
        };
        if (end - start == 1) {
            return;
        };
        if (end - start == 2 && array[start] > array[start+1]) {
            swap(array, start, start + 1);
        };
        if (end-start <= BREAKPOINT) {
            insertionSort (array, start, end);
        };
        //Recursive case
        int pivotIndex = choosePivot(array, start, end);
        pivotIndex = partitionArray(array, start, end, pivotIndex);
        hybridQuickSort(array, start, pivotIndex);
        hybridQuickSort(array, pivotIndex, end);
    } //End hybridQuickSort helper

    public static void hybridQuickSort (int[] array) {
        hybridQuickSort(array, 0, array.length);
    } //End hybridQuickSort

    //Non-recursive shell sort 
    public static void shellSort (int[] array) {
        int[] gapValues = initKnuthSequence(array.length);

        for (int gap : gapValues) { 
            shellSort(array, 0, array.length, gap);
        };
    } //End shellSort

    private static int[] initKnuthSequence (int arrayLength) {
        int count = 0;
        for (int i = 1; i < arrayLength; i = i * 3 + 1) {
            count ++;
        };
        int[] consequence = new int[count];

        for (int i = 1; i < arrayLength; i = i * 3 + 1) {
            count--;
            consequence[count] = i;
        };

        return consequence;
    } //End initKnuthSequence

    private static void shellSort (int[] array, int start, int end, int gap) {
        for (int i = 0; i < gap; i ++) {
            sortLeavedList(array, start+i, end, gap);
        }
    } //End shellSort

    private static void sortLeavedList (int[] array, int start, int end, int gap) {
        for (int i = start + gap; i < end; i += gap) {
            int j = i;
            while (j >= start + gap && array[j] < array[j-gap]) {
                swap(array, j, j-gap);
                j -= gap;
            }
        }
    } //End sortLeavedList



/****************** Other miscellaneous methods ********************/

    /*******************************************************************
     * swap
     *
     * Purpose: Swap the items stored in positions i and j in array.
     *
     ******************************************************************/
    private static void swap( int[] array, int i, int j ) {
		int temp = array[ i ];
		array[ i ] = array[ j ];
		array[ j ] = temp;
    } // end swap


    /*******************************************************************
     * isSorted
     *
     * Purpose: Return true if the input array is sorted into
     *          ascending order; return false otherwise.
     *
     * Idea: If every item is <= to the item immediately after it,
     *       then the whole list is sorted.
     *
     ******************************************************************/
    public static boolean isSorted( int[] array ) {
		boolean sorted = true;

		// Loop through all adjacent pairs in the
		// array and check if they are in proper order.
		// Stops at first problem found.
		for ( int i = 1; sorted && (i < array.length); i++ )
		    sorted = array[i-1] <=  array[i];
		return sorted;
    } // end method isSorted

    /*******************************************************************
     * checkArray
     *
     * Purpose: Print an error message if array is not
     *          correctly sorted into ascending order.
     *          (If the array is correctly sorted, checkArray does nothing.)
     *
     ******************************************************************/
    private static void checkArray(int[] array, String sortType) {
		if ( !isSorted( array ) )
		    System.out.println( sortType + " DID NOT SORT CORRECTLY *** ERROR!!" );
    }

    /*******************************************************************
     * fillArray
     *
     * Purpose: Fills the given array with the numbers 0 to array.length-1.
     *
     ******************************************************************/
    public static void fillArray( int[] array ) {

		for ( int i = 0; i < array.length; i++ ) {
		    array[i] = i;
		} // end for

    } // end fillArray

    /*******************************************************************
     * randomizeArray
     *
     * Purpose: Does numberOfSwaps swaps of randomly-chosen positions
     *          in the given array.
     *
     ******************************************************************/
    public static void randomizeArray( int[] array, int numberOfSwaps ) {
		for ( int count = 0; count < numberOfSwaps; count++ ) {
		    int i = generator.nextInt( array.length );
		    int j = generator.nextInt( array.length );
		    swap( array, i, j );
		}
    } // end randomizeArray


    /*******************************************************************
     * arithmeticMean
     *
     * Purpose: Compute the average of long values.
     *          To avoid long overflow, use type double in the computation.
     *
     ******************************************************************/
    public static double arithmeticMean(long data[]) {
		double sum = 0;
		for (int i = 0; i < data.length; i++)
		    sum += (double)data[i];
		return sum / (double)data.length;
    } // end arithmeticMean

} // end class MaiKienA2Q1

/***************** Output of the program && comments *****
/* COMP 2140 A2Q1 Sorting Test --- Summer 2020
/* 
/* Array size: 10000
/* Number of swaps: 5000
/* Number of trials of each sort: 300
/* 
/* Insertion sort mean: 1.130547295E7 ns
/* Bubble sort mean: 9.819589180333333E7 ns
/* Selection sort mean: 4.61795295E7 ns
/* Merge sort mean: 907223.6066666667 ns
/* Quick sort mean: 808792.3633333333 ns
/* Hybrid quick sort mean: 988048.2733333333 ns
/* Shell sort mean: 1112514.5633333332 ns
/* 
/* Processing ends normally
/* 
/****************** End output ******************
/****************** Comments ********************
/* 1. In the average, insertion sort was faster than selection sort. The reason could be the cost (time) of comparing is more expensive than the cost for swapping and there are much more comparisons in selection sort than insertion sort. 
/* 2. In the average, quick sort was faster than insertion sort. It is because the complexity or big-O notation of quick sort is n log n while the one for insertion sort is n^2 (n square).
/* 3. In the average, quick sort was slightly faster than hybrid sort. The reason is the insertion sort is implemented for some small cases in hybrid quick sort and insertion sort is most likely slower than quick sort.
/* 4. I would recommend Quick Sort because it has the fastest time of excutions.
/* 5. I would warn other people against using Bubble sort because it has the slowest time of excutions.
/****************** End comments ****************/ 
