import java.util.*;
import java.io.*;

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
