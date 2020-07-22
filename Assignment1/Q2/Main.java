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
};
