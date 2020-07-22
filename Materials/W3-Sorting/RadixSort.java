public class RadixSort { 

    public void sort(int[] array, int arraySize) {
        //Create 10 buckets
        //For-loop 
        //  subdivide list to 10 list 
        //  rebuild list
        //  clear all buckets
    }

    //Inner class: Bucket
    private class Bucket {
        private int[] numbers;
        private int size;
        public Bucket(int arrayLength) {
            numbers = new int[arrayLength];
            size = 0;
        }

        public void addNumber(int number) {
            numbers[size] = number;
            size ++;
        }

        //Move all numbers to other array
        //Clear numbers after transfering
        public int transferNumbers(int[] otherNumbers, int startIndex) {
            int endIndex = startIndex + size;
            for (int i = 0; i < size; i ++) {
                otherNumbers[startIndex+i] = numbers[i];
                numbers[i] = 0;
            };
            size = 0;
            return endIndex;
        }
    }

    //Find the max length in array of int 
    private int getMaxLength(int[] array, int arraySize) {
        int maxLength = 0;
        for (int i = 0; i < arraySize; i ++) {
            int length = getLength(array[i]);
            if (length > maxLength) {
                maxLength = length;
            };
        };
        return maxLength;
    }

    //Find the number of digits of numbers
    private int getLength(int number) {
        if (number == 0) {
            return 1;
        }; 

        int digits = 0;
        while (number != 0) {
            number = number / 10;
            digits ++;
        };
        return digits;
    };

    //Subdivide list to 10 buckets
    private void subdivideList(Bucket[] buckets, int[] array, int arraySize, int digitIndex) { 
        if (buckets.length != 10) {
            //Throw exception here but later, not neccessary for now!
            return;
        };
        for (int i = 0; i < arraySize; i ++) {

        }
    } 

    //Rebuild the list
    private void rebuildList(Bucket[] buckets, int[] array) {
    }

}
