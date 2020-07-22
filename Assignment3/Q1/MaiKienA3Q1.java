import java.util.*;
import java.io.*;

public class MaiKienA3Q1 {

    public static void main (String[] args) {
        Scanner keyboardInput = new Scanner(System.in);

        System.out.println("Please enter the input file name (.txt only):");
        String fileName = keyboardInput.nextLine();

        Maze maze = new Maze(fileName);

        System.out.println("Processing " + fileName + "...");

        System.out.println("The initial maze is:");

        System.out.println(maze.mazeToString());

        maze.stackSearch();

        System.out.println("The path found using a stack is:");

        System.out.println(maze.mazeToString());

        System.out.println("Path from start to finish: " + maze.pathToString());

        maze.resetMaze();

        maze.queueSearch();

        System.out.println("The path found using a queue is:");

        System.out.println(maze.mazeToString());

        System.out.println("Path from start to finish: " + maze.pathToString());

        System.out.println("Processing terminated normally.");

    }

}

class Maze { 
    public static final int ROW_INDEX = 0;
    public static final int COL_INDEX = 1;

    public static final int MOVE_UP_ROW = -1;
    public static final int MOVE_UP_COL = 0;

    public static final int MOVE_DOWN_ROW = 1;
    public static final int MOVE_DOWN_COL = 0;

    public static final int MOVE_LEFT_ROW = 0;
    public static final int MOVE_LEFT_COL = -1;

    public static final int MOVE_RIGHT_ROW = 0;
    public static final int MOVE_RIGHT_COL = 1;

    private Position[][] mazeMap;

    private int rowNum;
    private int colNum;

    private int startRow;
    private int startCol;

    private Stack posStack;
    private Queue posQueue;

    public Maze (String fileName) {
        readFile(fileName);
        posStack = new Stack();
        posQueue = new Queue();

    }

    private void readFile (String fileName) {
        try {
            File filePath = new File(fileName);
            Scanner fileReader = new Scanner(filePath);

            //Retreive sizes of the maze
            String sizeLine = fileReader.nextLine();
            String[] sizes = sizeLine.split(" ");
            rowNum = Integer.parseInt(sizes[ROW_INDEX]);
            colNum = Integer.parseInt(sizes[COL_INDEX]);
            //Create empty maze
            mazeMap = new Position[rowNum][colNum];
            //Fill up maze
            for (int row = 0; row < rowNum; row ++) {
                String rowLine = fileReader.nextLine();

                for (int col = 0; col < colNum; col ++) {
                    char positionChar = rowLine.charAt(col);

                    fillPosition(mazeMap, row, col, positionChar);

                    if (positionChar == PositionType.START.getPositionChar()) {
                        startRow = row;
                        startCol = col;
                    };

                } //End for col

            }; //End for row
            

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private void fillPosition(Position[][] mazeMap, int row, int col, char posChar) {
        Position pos = new Position(row, col, PositionType.getPositionType(posChar));
        
        mazeMap[row][col] = pos;
    }

    public void stackSearch () {
        Position start = mazeMap[startRow][startCol];
        start.setIsVisited(true);
        posStack.push(start);

        Position curr = posStack.top();

        while (!isFinished(curr) && isThereWay(posStack)) {
            Position nextCurr = move(curr);
            if (nextCurr == null) {
                posStack.pop();
                if (!posStack.isEmpty()) {
                    curr = posStack.top();
                };
            } else {
                nextCurr.setIsVisited(true);
                posStack.push(nextCurr);
                curr = nextCurr;
            };
        };
        
        if (isFinished(curr)) {
            Position finishPos = posStack.pop();
            posStack.top().setPrevPosition(finishPos);
        }

        //Change path to found path

        while (posStack.getLength() != 1) {
            Position pos = posStack.pop();
            posStack.top().setPrevPosition(pos);
            
            pos.setPositionType(PositionType.FOUND_PATH);
        }
        

    }

    private boolean isFinished(Position pos) {
        return pos.getPositionType() == PositionType.FINISH;
    }

    private boolean isThereWay(Stack posStack) {
        return !posStack.isEmpty();
    }

    //Find a neighbor position from given position
    private Position move (Position pos) {
        Position newPos = null;

        if ((newPos = move(pos, MOVE_UP_ROW, MOVE_UP_COL)) != null) {
            return newPos;
        };
        if ((newPos = move(pos, MOVE_DOWN_ROW, MOVE_DOWN_COL)) != null) {
            return newPos;
        };
        if ((newPos = move(pos, MOVE_LEFT_ROW, MOVE_LEFT_COL)) != null) {
            return newPos;
        };
        if ((newPos = move(pos, MOVE_RIGHT_ROW, MOVE_RIGHT_COL)) != null) {
            return newPos;
        };
        return newPos;
    }

    //Return new pos if it is an open path based on current pos and move direction 
    private Position move (Position pos, int moveRow, int moveCol) {
        int posRow = pos.getRow();
        int posCol = pos.getCol();

        int newRow = posRow + moveRow;
        int newCol = posCol + moveCol;

        if (newRow < 0 || newCol < 0 || newRow >= rowNum || newCol >= colNum) {
            return null;
        };

        Position newPos = mazeMap[newRow][newCol];

        if (newPos.isVisited() || newPos.getPositionType() == PositionType.WALL) {
            return null;
        };

        return newPos;
    }

    public void queueSearch() {
        Position start = mazeMap[startRow][startCol];
        Position curr = start;

        //Start searching
        while (!isFinished(curr)) {
            Position nextCurr = move (curr);
            if (nextCurr == null) {
                //start over
                //Empty queue and start from start
                while(!posQueue.isEmpty()) {
                    Position pos = posQueue.dequeuePos();
                    pos.setIsVisited(false);
                };

                //Mark the wrong way
                curr.setIsVisited(true);

                curr = start;
            } else {
                posQueue.enqueue(curr);
                curr.setIsVisited(true);

                curr = nextCurr;
            }
        }

        posQueue.enqueue(curr); //Enqueue finish position
        //End searching

        //Connect path
        while(posQueue.front() != null) {
            Position pos = posQueue.dequeuePos();

            pos.setPrevPosition(posQueue.frontPos());

            if (pos.getPositionType() == PositionType.PATH) {
                pos.setPositionType(PositionType.FOUND_PATH);
            };

        }
    }


    public String pathToString () {
        StringBuilder pathBuilder = new StringBuilder();
        Position pos = mazeMap[startRow][startCol];
        while(pos != null) {
            pathBuilder.append(pos.coordinateToString());
            pathBuilder.append(" ");
            pos = pos.getPrevPosition();
        };
        return pathBuilder.toString().trim();
    }

    public String mazeToString () {
        StringBuilder builder = new StringBuilder();

        for (int row = 0; row < rowNum; row ++) {
            for (int col = 0; col < colNum; col ++) {
                builder.append(mazeMap[row][col].getPositionChar());
            }; //end col
            builder.append("\n");

        }; //end row

        return builder.toString().trim();
    }   

    public void resetMaze () {
        for (int row = 0; row < rowNum; row ++) {
            for (int col = 0; col < colNum; col ++) {
                Position pos = mazeMap[row][col];
                pos.setIsVisited(false);

                if (pos.getPositionType() == PositionType.FOUND_PATH) {
                    pos.setPositionType(PositionType.PATH);
                }

            }; // end col

        }; // end row
    }
}

class Position { 
    public static final int DUMMY_POS_ROW = -1;
    public static final int DUMMY_POS_COL = -1;
    public static final PositionType DUMMY_POS_TYPE = PositionType.WALL;

    private static final Position DUMMY_POSITION = new Position(DUMMY_POS_ROW, DUMMY_POS_COL, DUMMY_POS_TYPE);

    public static Position getDummyPos () {
        return DUMMY_POSITION;
    }

    public static boolean isDummyPos (Position pos) {
        return pos.getRow() == DUMMY_POS_ROW && pos.getCol() == DUMMY_POS_COL;
    }

    private int row;
    private int col;
    
    private boolean isVisited;

    private Position prevPosition;

    private PositionType positionType;

    public Position (int row, int col, PositionType positionType) {
        this.row = row;
        this.col = col;

        this.positionType = positionType;

        prevPosition = null;
        isVisited = false;
    } 

    public void setRow (int row) {
        this.row = row;
    }

    public int getRow () {
        return this.row;
    }

    public void setCol (int col) {
        this.col = col;
    }

    public int getCol () {
        return this.col;
    }

    public void setIsVisited (boolean isVisited) {
        this.isVisited = isVisited;
    }

    public boolean isVisited () {
        return this.isVisited;
    }

    public void setPositionType (PositionType positionType) {
        this.positionType = positionType;
    }

    public PositionType getPositionType () {
        return this.positionType;
    }

    public char getPositionChar () {
        return this.positionType.getPositionChar();
    }

    public void setPrevPosition (Position prevPosition) {
        this.prevPosition = prevPosition;
    } 

    public Position getPrevPosition () {
        return this.prevPosition;
    }

    public String coordinateToString () {
        StringBuilder builder = new StringBuilder();
        builder.append("(");
        builder.append(row);
        builder.append(", ");
        builder.append(col);
        builder.append(")");
        return builder.toString();
    }

}

enum PositionType {
    START ('S'),
    PATH ('.'),
    WALL ('#'),
    FINISH ('F'),
    FOUND_PATH ('*');

    private char positionChar;

    private PositionType (char positionChar) {
        this.positionChar = positionChar;
    }

    public char getPositionChar () {
        return this.positionChar;
    }

    public static PositionType getPositionType (char posChar) {
        switch (posChar) {
            case 'S':
                return PositionType.START;
            case '.':
                return PositionType.PATH;
            case '#':
                return PositionType.WALL;
            case 'F':
                return PositionType.FINISH;
            case '*':
                return PositionType.FOUND_PATH;
            default:
                return null;
        }
    }

    public String toString () {
        StringBuilder builder = new StringBuilder(this.positionChar);
        return builder.toString();
    }
}

class Queue {
    private QueueNode front;
    private int length;

    public Queue () {
        front = QueueNode.getDummyNode();

        length = 0;
    }

    public void enqueue (QueueNode node) {
        QueueNode curr = front;
        while (curr.getNext() != null) {
            curr = curr.getNext();
        };

        curr.setNext(node);

        length ++;
    }

    public QueueNode dequeue () {
        if (isEmpty()) {
            return null;
        };
        QueueNode node = front.getNext();
        front.setNext(node.getNext());

        length --;

        return node;
    }

    public void enqueue (Position pos) {
        QueueNode node = new QueueNode(pos, null);
        enqueue(node);
    } 

    public Position dequeuePos () {
        QueueNode node = dequeue();
        if(node != null) {
            return node.getPos();
        } else {
            return null;
        }
    }

    public Position frontPos () {
        QueueNode node = front();
        if (node == null) {
            return null;
        } else {
            return node.getPos();
        }
    }

    public QueueNode front () {
        return front.getNext();
    }

    public boolean isEmpty () {
        return front.getNext() == null || length == 0;
    }

    public int getLength () {
        return this.length;
    }

}

class QueueNode {
    private static QueueNode DUMMY_NODE = new QueueNode (Position.getDummyPos(), null);

    public static QueueNode getDummyNode () {
        return DUMMY_NODE;
    }

    private Position pos;

    private QueueNode next;

    public QueueNode (Position pos, QueueNode next) {
        this.pos = pos;
        this.next = next;
    }

    public void setPos (Position pos) {
        this.pos = pos;
    } 

    public Position getPos () {
        return this.pos;
    }

    public void setNext (QueueNode next) {
        this.next = next;
    }

    public QueueNode getNext () {
        return this.next;
    }

}

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
