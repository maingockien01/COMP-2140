public class InsertionSort<E> {

    public void sort(E[] array, int length) {
        
        for(int i = 1; i < length; i ++) {
            int j = i;
            while(j>0 && array[j] < array[j-1]) {

                int temp = array[j];
                array[j] = array[j-1];
                array[j-1] = temp;

                --j;
            }; // end for j
        }; // end for i
    }
}
