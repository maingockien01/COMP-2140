public class ShellSort<E> {

    public void sort(E[] array, int length, int gapValues) {

        for each (gapValue in gapValues) {
            for (int startIndex = 0; startIndex < gapValue; startIndex ++) {
                sortInterleavedList(array, length, startIndex, gapValue);
            }
        }
    }

    private void sortInterleavedList(E[] array, int length, int startIndex, int gapValue) {

        for(int i = startIndex + gap; i < length; i += gap) {
            j = i;
            while( (j-gap)>=startIndex && array[j] < array[j-gap] ) {
                E temp = array[j];
                array[j] = array[j-gap];
                array[j-gap] = temp;

                j -= gap;
            }; // end for swap - j
        } //end for i
    }
}
