class DictionaryOrdered implements Dictionary {
    public static final int NOT_EXIST = -1;

    private String[] wordList; 
    private int size;
    private int capacity;

    //Constructor
    public DictionaryOrdered (int initCapacity) { //Receive intial capacity
        this.capacity = initCapacity;
        wordList = new String[this.capacity];
        this.size = 0;
    }

    @Override
    public int getSize () {
        return this.size;
    }

    @Override
    public void insert (String newWord) {
        if (isWordNotValid(newWord)) {
            return;
        };

        newWord = newWord.toLowerCase();
        
        //Check if word exists?
        if (search(newWord)) {
            return;
        };
        //Special case: first word
        if (size == 0) {
            wordList[size] = newWord;
            size ++;
            resize();
            return;
        };
        //Normal case
        //Searching for a word having same first letter
        char firstLetter = newWord.charAt(0);
        int indexWord = search (firstLetter, this.wordList, 0, size);

        //There is no word has same first letter
        if(indexWord == NOT_EXIST) {
            if (firstLetter > wordList[size-1].charAt(0)) {
                insert(newWord, size);
            } else {
                for (int i = 0; i < size; i ++) {
                    if (firstLetter < wordList[i].charAt(0)) {
                        insert(newWord, i);
                        break;
                    };
                }
            }
        } else { //There is a word has same first letter
            //If word is on the right side of the found word
            if (newWord.compareTo(wordList[indexWord]) > 0) {
                int rightIndex = indexWord + 1;
                while (rightIndex < size && wordList[rightIndex].charAt(0) == firstLetter) {
                    if (newWord.compareTo(wordList[rightIndex]) < 0) {
                        break;
                    };
                    rightIndex ++;
                };
                insert(newWord, rightIndex);
            } else {
            //If word is on the left side
                int leftIndex = indexWord;
                while (leftIndex > 0 && wordList[leftIndex-1].charAt(0) == firstLetter) {
                    if (newWord.compareTo(wordList[leftIndex-1]) > 0) {
                        break;
                    };
                    leftIndex --;
                };
                if (leftIndex < 0 ) {
                    leftIndex = 0;
                };
                insert(newWord, leftIndex);
            };                    
        };
        size ++;
        resize();
    }
    
    private void insert(String newWord, int index) {
        for (int i = size - 1; i >= index; i --) {
            wordList[i+1] = wordList[i];
        };
        wordList[index] = newWord;
    }

    private boolean isWordNotValid (String word) {
        boolean isNotValid = word == null || word.equals(" ") || word.equals("") || word.equals("\n") ;
        return isNotValid;
    }
    
    private void resize() {
        boolean isArrayFull = size >= capacity;
        if (isArrayFull) {
            int newCapacity = capacity * 2;
            String[] tempList = new String[newCapacity];
            capacity = newCapacity;
            for (int i = 0; i < size; i ++) {
                tempList[i] = wordList[i];
            }
            wordList = tempList;
        };
    }

    @Override
    public boolean search(String wordToFind) {
        if (isWordNotValid(wordToFind)) {
            return false;
        };
        wordToFind = wordToFind.toLowerCase();
        
        //Search for word has same first letter
        char firstLetter = wordToFind.charAt(0);

        int indexWord = search(firstLetter, this.wordList, 0, size);

        //If there is no word has same first letter
        if (indexWord == NOT_EXIST) {
            return false;
        };

        //If there is
        //If the word is the one to find?
        if (wordToFind.equals(wordList[indexWord])) {
            return true;
        };

        int leftIndex = indexWord - 1;
        int rightIndex = indexWord + 1;

        //Search left side
        while ( leftIndex >= 0 && wordList[leftIndex].charAt(0) == firstLetter) {
            if (wordToFind.equals(wordList[leftIndex])) {
                return true;
            };
            leftIndex --;
        };

        //Search right side
        while (rightIndex < size && wordList[rightIndex].charAt(0) == firstLetter) {
            if (wordToFind.equals(wordList[rightIndex])) {
                return true;
            };
            rightIndex ++;
        };

        //Default case: not found
        return false;
    }
    
    //Recursive function
    //Search for the word having same first letter 
    //Return index of the word
    public int search(char firstLetter, String[] wordList, int start, int end) {
        if (start >= end) {
            return -1;
        };
        int mid = (start + end) /2;

        String word = wordList[mid];

        char firstLetterWord = word.charAt(0);

        if (firstLetter == firstLetterWord) {
            return mid;
        } else if (firstLetter < firstLetterWord) {
            return search(firstLetter, wordList, start, mid);
        } else {
            return search(firstLetter, wordList, mid + 1, end);
        }
    }

}
