public class Stack {
    public static final int INITIAL_SIZE = 4;
    public static final int RESIZE_FACTOR = 2;
    public static final double ENLARGE_THRESHOLD = 0.8;
    public static final double REDUCE_THRESHOLD = 0.5;
    private Position[] list;

    private int length;
    private int capacity;

    public Stack () {
        capacity = INITIAL_SIZE;
        list = new Position[capacity];

        length = 0;

    }

    public void push (Position position) {
        //Push
        int newTopIndex = length;

        list[newTopIndex] = position;
        length ++;
        //Resize
        resizeList();
    }

    public Position pop () {
        //Empty case
        if (isEmpty()) {
            return null;
        };
        //Normal case
        int topIndex = getTopIndex();
        Position top = list[topIndex];

        list[topIndex] = null;
        length --;

        //Resize
        resizeList();

        return top;
    }

    public Position top () {
        int topIndex = getTopIndex();
        return list[topIndex];
    } 
    
    private int getTopIndex () {
        return length-1;
    }

    public boolean isEmpty () {
        return this.length == 0;
    }

    public int getLength () {
        return this.length;
    }

    private void resizeList () {
        double loadFactor = (double) length / capacity;

        if (loadFactor >= ENLARGE_THRESHOLD) {
            enlargeList();
            return;
        };

        if (loadFactor < REDUCE_THRESHOLD && capacity > INITIAL_SIZE) {
            reduceList();
            return;
        }
    }

    private void enlargeList () {
        int newCapacity = capacity * RESIZE_FACTOR;
        Position[] newList = new Position[newCapacity];

        for (int i = 0; i < capacity; i ++) {
            newList[i] = list[i];
        };

        list = newList;
        capacity = newCapacity;
    }

    private void reduceList () {
        int newCapacity = capacity / RESIZE_FACTOR;
        Position[] newList = new Position[newCapacity];

        for (int i = 0; i < newCapacity; i ++) {
            newList[i] = list[i];
        };

        list = newList;
        capacity = newCapacity;
    }
}
