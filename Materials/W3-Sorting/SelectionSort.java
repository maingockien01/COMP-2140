public class SelectionSort<E> { 

    public void sort(E[] array, int length) {

        for (int i = 0; i < length-1; i ++) {
            int smallestIndex = i;
            for(int j = i+1; j < length; j ++) {
                if (array[smallestIndex] < array[j]) {
                    smallestIndex = j;
                }
            }; //end for j
            int temp = array[i];
            array[i] = array[smallestIndex];
            array[smallestIndex] = temp;

        } //end for i
    }
}
