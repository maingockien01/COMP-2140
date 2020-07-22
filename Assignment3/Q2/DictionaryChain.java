class DictionaryChain implements Dictionary {

    public static final double RESIZE_THRESHOLD = 2;
    public static final int BASE = 27;
    public static final int CASTING_VALUE = 96;

    private Chain[] wordList;
    private int size;
    private int capacity;

    public DictionaryChain (int initCapacity) {
        this.capacity = initCapacity;
        this.size = 0;
        wordList = new Chain[this.capacity];
    }

    @Override
    public void insert (String newWord) {
        if (isWordNotValid(newWord)) {
            return;
        };
        //Insert new word to current word list
        this.size = insert(newWord, this.wordList, this.size);
        //Resize if neccessary
        resize ();

    }

    //Return new size after insert 
    private int insert (String newWord, Chain[] wordList, int size) {
        int capacity = wordList.length;
        //Search if new word already exits in the dictionary 
        boolean isWordExist = search(newWord, wordList);
        //if yes stop and return current size
        if (isWordExist) {
            return size;
        };
        //if no:
        //Calculate hash value of the new word
        int hashValue = hashHorner(newWord, capacity);
        //chech if there is collision 
        boolean isThereNoCollision = wordList[hashValue] == null;
        //if no -> create new chain with new word
        if (isThereNoCollision) {
            wordList[hashValue] = new Chain(newWord);
        } else {
        //if yes -> insert word into current chain 
            Chain chain = wordList[hashValue];
            chain.insert(newWord);
        };

        return size + 1;

    }

    @Override
    public boolean search (String wordToFind) {
        return search(wordToFind, this.wordList);
    }

    private boolean search (String wordToFind, Chain[] wordList) {
        if (isWordNotValid(wordToFind)) {
            return false;
        };
        int capacity = wordList.length;
        // Get hash value 
        int hashValue = hashHorner(wordToFind, capacity);
        // if the position of hash avlue is null -> not found 
        boolean isChainNotExist = wordList[hashValue] == null;
        if (isChainNotExist) {
            return false;
        } else {
        // else search in the chain 
            Chain chain = wordList[hashValue];
            return chain.search(wordToFind);
        }

    }

    @Override
    public int getSize () {
        return this.size;
    }
    //Hash Horner's method
    private int hashHorner (String word, int capacity) {
        int hashValue = 0;
        
        for (int i = 0; i < word.length(); i ++) {
            int letterValue = convert(word.charAt(i));
            hashValue = (hashValue * BASE + letterValue) % capacity;
        };
        return hashValue;
    }

    private boolean isWordNotValid (String word) {
        boolean isNotValid = word == null || word.compareTo(" ") == 0 || word.compareTo("") == 0 || word.compareTo("\n") == 0;
        return isNotValid;
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

    //Resize 
    private void resize () {
        //Calculate load factor 
        double loadFactor = size / capacity;
        //if load factor is larger than threshold 
        //yes -> resize 
        if (loadFactor >= RESIZE_THRESHOLD) {
        //  get new capacity (next prime)
            int newCapacity = getNextPrime(capacity * 2);
        //  create new list
            Chain[] newWordList = new Chain[newCapacity];
        //  insert all existing word to new list 
            for (int i = 0; i < capacity; i ++) {
                if (wordList[i] != null) {
                    moveChain (wordList[i], newWordList);
                };
            }
        //  update all number for the instance
            this.capacity = newCapacity;
            this.wordList = newWordList;
        //  Size is still same

        };
        //no -> do nothing 
    }

    //(Chain, Chain[]) -> void
    //insert all words from given chain to the given array of chain (Chain[])
    private void moveChain (Chain chain, Chain[] wordList) {
        Node curr = chain.getTop();
        while (curr != null) {
            String word = curr.getWord();
            insert(word, wordList, 0);
            curr = curr.getNext();
        };
    }

    private int getNextPrime (int number) {
        int nextPrime = number+1;
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
