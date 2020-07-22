public class BubbleSort<E> {

    public void sort(E[] array, int length) {

        for (int i = 0; i < length-1; i ++) {
            for (int j = 0; j < length-1-i; j ++) {
                if(array[j] > array[j+1]) {
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            } // end for j
        } // end for i

    }

}
