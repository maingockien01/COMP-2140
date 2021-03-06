/**
* MaiKienA1Q2
*
* COMP 2140 SECTION D01
* ASSIGNMENT    Assignment 1, question 2
* @author       Kien Mai - 7876083
* @version      22 May
*
* PURPOSE: 	Sudoku game 
*/

import java.lang.Math;
import java.util.Scanner;

public class Main { 
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter a game board, row by row, with - to represent empty cells and spaces between each cell:");
        String input = scanner.nextLine();
        Sudoku sudoku = new Sudoku(input); 
        System.out.println("\nThe original board is:");
        sudoku.printBoard();
        sudoku.solveSudoku();
        System.out.println("\nThe solution is:");
        sudoku.printBoard();
        System.out.println("Program terminated normally.");

    }
}



public class Sudoku {
    private String input;
    private int blockSize;
    private int gridSize;
    private int filledCellsNum;
    private int[][] board;
    private boolean[][][] blocks;
    private boolean[][] rows;
    private boolean[][] cols;

    public Sudoku(String input) {
        this.input = input;
        initSudoku(input);

        
    };

    private void initSudoku(String input) {
        //Interpret input to board 
        board = interpreteInput(input);

        gridSize = board[1].length;


    };

    private int[][] interpreteInput(String input) {
        int[][] board;
        String[] charInput = input.split(" ");

        int inputLength = charInput.length;
        int gridSize = (int) Math.sqrt(inputLength);
        boolean isInputValid = gridSize * gridSize == inputLength;
        if (isInputValid) {
            board = new int[gridSize][gridSize];
            blockSize = (int) Math.sqrt(gridSize);

            blocks = new boolean[blockSize][blockSize][gridSize];
            rows = new boolean[gridSize][gridSize];
            cols = new boolean[gridSize][gridSize];
            //turn input string to board
            for(int row = 0; row < gridSize; row ++) {
                for (int col = 0; col < gridSize ; col ++) {
                    char inputChar = charInput[row*gridSize + col].charAt(0);
                    if(inputChar == '-') {
                        board[row][col] = 0;
                    } else {
                        int number = (int) inputChar - 48;
                        board[row][col] = number;
                        rows[row][number-1] = true;
                        cols[col][number-1] = true;
                        blocks[row/blockSize][col/blockSize][number-1] = true;
                    }
                };
            };
        } else {
            board = new int[9][9];
            gridSize = 9;
            blockSize = (int) Math.sqrt(gridSize);

            blocks = new boolean[blockSize][blockSize][gridSize];
            rows = new boolean[gridSize][gridSize];
            cols = new boolean[gridSize][gridSize];
        };
        return board;
    };

    /* 
     * (int, int, int) -> boolean
     * check if the number meets the rule of sudoku 
     * return true if it breaks any rule of sudoku
     *
     */
    private boolean checkNumber(int row, int col, int number) {
        boolean isRowExist = rows[row][number-1];
        boolean isColExist = cols[col][number-1];
        boolean isBlockExist = blocks[row/blockSize][col/blockSize][number-1];

        return isRowExist || isColExist || isBlockExist;
    };

    private void removeCell(int row, int col, int number) {
        board[row][col] = 0;
        rows[row][number-1] = false;
        cols[col][number-1] = false;
        blocks[row/blockSize][col/blockSize][number-1] = false;
    }

    public void solveSudoku () {
        solveSudokuRec(0,0);
    }

    private boolean solveSudokuRec(int row, int col) {
        if (row == gridSize) {
            return true;
        };

        if (board[row][col] != 0) {
            int nextRow, nextCol;
            if(col == gridSize -1) {
                nextRow = row +1;
            } else {
                nextRow = row;
            };

            nextCol = (col+1) % gridSize; 

            if(solveSudokuRec(nextRow, nextCol)) {
                return true;
            };
        } else {


            for (int i = 1; i <= gridSize ; i ++) {
                boolean isNumberContained = checkNumber(row, col, i);
                if (!isNumberContained) {
                    board[row][col] = i;
                    rows[row][i-1] = true;
                    cols[col][i-1] = true;
                    blocks[row/blockSize][col/blockSize][i-1] = true;

                    int nextRow, nextCol;
                    if(col == gridSize -1) {
                        nextRow = row +1;
                    } else {
                        nextRow = row;
                    };

                    nextCol = (col+1) % gridSize; 

                    if(solveSudokuRec(nextRow, nextCol)) {
                        return true;
                    } else {
                        removeCell(row, col, i);
                    }
                }
            }

        }

        return false;
    }

    public void printBoard() {
        String boardToString = boardToString(this.board);
        System.out.println(boardToString);
    }

    private String boardToString(int[][] board) {
        StringBuilder builder = new StringBuilder();
        for(int row = 0 ; row < gridSize; row ++){
            for (int col = 0; col < gridSize; col ++) {
                builder.append(board[row][col]);
                builder.append(" ");
            };
            builder.append("\n");
        };

        return builder.toString();

    }
}

/**
* 1: 
* I choose the grid 2D and int as it would be more convinient to access it by for-loop and easier to imagine.
* 2:
* I reject the approach of searching linearly if the number already existed in row or collumne or block because it would make algorithm slow down.
* I decide to use 3 other boolean arrays, each for row, col, and block, to check if the number existed. It would be faster and the complexity of this way of checking will be constant since accessing array is constant.
*/
