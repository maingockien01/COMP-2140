import java.util.*;

public class Lab6 {
    public static void main (String[] args) {
        Scanner keyboardInput = new Scanner(System.in);
        System.out.println("Please provide a word: ...");
        String word = keyboardInput.nextLine();
        Stack wordStack = new Stack(word);
        Queue wordQueue = new Queue(word);

        boolean isPandorime = isWordPandorime(wordStack, wordQueue, word.length());
        if (isPandorime) {
            System.out.println("The word is pandorime!");
        } else {
            System.out.println("The word is not pandorime!");
        };

    }

    private static boolean isWordPandorime (Stack wordStack, Queue wordQueue, int wordLength) {
        boolean isPandorime = true;

        for (int i = 0; i < wordLength && isPandorime; i ++) {
            char charStack = wordStack.pop();
            char charQueue = wordQueue.dequeue();
            isPandorime = charStack == charQueue;
        }
        
        return isPandorime;
    }

} //End lab6

class Stack {
    char[] charList;
    int capacity;
    int listLength;

    public Stack (String word) {
        capacity = word.length();
        charList = new char[capacity];
        listLength = 0;

        pushWord(word);
    }

    private void pushWord (String word) {
        for (int i = 0; i < capacity; i ++) {
            push(word.charAt(i));
        }
    }

    public void push (char c) {
        if (listLength >= capacity) {
            // Double size array here
        }; 
        charList[listLength] = c;
        listLength ++;
    }

    public char pop() {
        listLength --;
        char c = charList[listLength];
        if (listLength < capacity/2) {
            //DeDouble-size array here
        };
        return c;

    }

}

class Queue {
    char[] charList;
    int capacity;
    int listLength;

    int headIndex;
    int tailIndex;

    public Queue (String word) {
        capacity = word.length();
        charList = new char[capacity];

        headIndex = -1;
        tailIndex = -1;
        
        queueWord(word);

    }

    private void queueWord (String word) {
        for (int i = 0; i < capacity; i ++) {
            queue(word.charAt(i));
        }
    }

    public void queue (char c) {
        if (tailIndex >= capacity-1) {
            //Resize queue
        }; 
        if (tailIndex == -1) {
            charList[0] = c;

            headIndex = 0;
            tailIndex = 0;
        } else { //List is empty
            tailIndex ++;
            charList[tailIndex] = c;
        }
    }

    public char dequeue () {
        char c;
        if (headIndex >= tailIndex) {
            //There is only 1 element in the list
            c = charList[headIndex];

            //Resize 
            headIndex = -1;
            tailIndex = -1;
        } else {
            c = charList[headIndex];
            headIndex ++;
        };
        return c;
    }
}
