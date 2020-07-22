class DictionaryOpen implements Dictionary {
    public static final int BASE = 27;
    public static final int CASTING_VALUE = 96;
    public static final int DOUBLE_HASH_CONSTANT = 41;
    public static final double RESIZE_THRESHOLD = 0.6;

    private String[] wordList;
    private int size;
    private int capacity;

    public DictionaryOpen (int initCapacity) {
        this.capacity = initCapacity;
        wordList = new String[this.capacity];
        size = 0;

    }

    @Override
    public void insert (String newWord) {
        size = insert(newWord, wordList, size);
        resize();
    }

    private int insert (String newWord, String[] wordList, int size) {
        newWord = newWord.toLowerCase();
        if (isWordNotValid(newWord)) {
            return size;
        };
        // Search if word already exists?
        if (search(newWord, wordList)) {
            return size;
        };
        //Get capacity of given word list (parameter)
        int capacity = wordList.length;
        //Get hash value
        int firstHash = hashHorner(newWord, capacity);
        //Check if there is collision 
        boolean isThereNoCollision = wordList[firstHash] == null;
        //if there is no collision -> insert
        if (isThereNoCollision) {
            wordList[firstHash] = newWord;
        } else {
        //if there is -> double hash
            int stepSize = hashStepSize(newWord);
            int doubleHash = firstHash;
            int i = 1;
            while (wordList[doubleHash] != null) {
                if (doubleHash == hashDouble(firstHash, stepSize, i, capacity)) {
                    System.out.println("Error: double hash was repeating one value. Cannot insert new word!");
                    return size;
                };
                doubleHash = hashDouble(firstHash, stepSize, i, capacity);
                i ++;
            };
            wordList[doubleHash] = newWord;
        };
        
        //Return new size
        int newSize = size + 1;
        return newSize;
    } 
    //
    //Resize function
    private void resize () {
        double loadFactor = (double) this.size / this.capacity;
        boolean isNeedToResize = loadFactor >= RESIZE_THRESHOLD;
        if (isNeedToResize) {
            int newCapacity = getNextPrime (this.capacity * 2);
            String[] newWordList = new String[newCapacity];

            //Move all words from current list to new list
            for (int i = 0; i < this.capacity; i ++) {
                if (wordList[i] != null) {
                    insert(wordList[i], newWordList, 0);
                }
            };

            this.capacity = newCapacity;
            this.wordList = newWordList;
            //Size is still same
        }
    }


    @Override
    public boolean search (String wordToFind) {
        return search(wordToFind, this.wordList);
    }

    private boolean search (String wordToFind, String[] wordList) {
        wordToFind = wordToFind.toLowerCase();
        if (isWordNotValid(wordToFind)) {
            return false;
        };
        //Get capacity of current word list
        int capacity = wordList.length;

        //Get hash value
        int firstHash = hashHorner(wordToFind, capacity);

        int stepSize = hashStepSize(wordToFind);
        int doubleHash = firstHash;

        //Iterate through the list untill hit the word or an empty cell
        for (int i = 0; i < capacity; i ++) {
            doubleHash = hashDouble(firstHash, stepSize, i, capacity);

            //Hit an empty cell -> word is not in the list
            if (wordList[doubleHash] == null) {
                return false;
            };

            //Hit the word -> found !!!
            if (wordToFind.compareTo(wordList[doubleHash]) == 0) {
                return true;
            };
        };

        //Default case: none exist!
        return false;
    }


    @Override
    public int getSize () {
        return this.size;
    }

    private boolean isWordNotValid (String word) {
        boolean isNotValid = word == null || word.compareTo(" ") == 0 || word.compareTo("") == 0 || word.compareTo("\n") == 0;
        return isNotValid;
    }

    //---Hasing functions

    //Hash Horner's method
    private int hashHorner (String word, int capacity) {
        int hashValue = 0;
        
        for (int i = 0; i < word.length(); i ++) {
            int letterValue = convert(word.charAt(i));

            hashValue = (hashValue * BASE + letterValue) % capacity;
        };

        return hashValue;
    }

    private int hashStepSize (String word) {
        int sumOfChar = sumOfChar(word);
        return DOUBLE_HASH_CONSTANT - (sumOfChar % DOUBLE_HASH_CONSTANT);
    }

    private int sumOfChar(String  word) {
        int sum = 0;
        for (int i =0; i < word.length(); i ++) {
            sum += convert(word.charAt(i));
        };
        return sum;
    }


    private int hashDouble (int firstHash, int secondHash, int i, int capacity) {
        return (firstHash + i * secondHash) % capacity;
    }

    //---End hasing functions

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < capacity; i ++) {
            if (wordList[i] == null) {
                builder.append(" empty ");
            } else {
                builder.append(" " + wordList[i] + " ");
            };
        }
        return builder.toString();
    }

    //Convert letter to corresponding number
    //a,b,c ... -> 0,1,2,... by subtracting letter ASCII code by 97 (ASCII code of 'a')
    private int convert (char letter) {
        if ((int) letter >= 97 && (int) letter <= 122) {
            return (int) letter - CASTING_VALUE;
        } else {
            return 0;
        }
    }

    private int getNextPrime (int number) {
        int nextPrime = number +1;
        while (!isPrime(nextPrime)) {
            nextPrime ++;
        };

        return nextPrime;
    } 

    private boolean isPrime (int number) {
        int i = 2;
        while (i*i <= number) {
            if (number % i == 0) {
                return false;
            }; 
            i ++;
        };

        return true;
    }
}
