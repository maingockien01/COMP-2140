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
