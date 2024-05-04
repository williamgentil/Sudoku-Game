import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SudokuSolver {
    private SudokuBoard sudokuBoard;

    public SudokuSolver(SudokuBoard sudokuBoard) {
        this.sudokuBoard = sudokuBoard;
    }

    public void solve() {
        long startTime = System.nanoTime();
        if (solveSudoku(0, 0)) {
            long endTime = System.nanoTime();
            System.out.println("Grille résolue en " + (endTime - startTime) + " nanosecondes:");
            sudokuBoard.printBoard();
        } else {
            System.out.println("La grille ne peut pas être résolue.");
        }
    }

    private boolean solveSudoku(int row, int col) {
        if (row == 9) {
            row = 0;
            if (++col == 9)
                return true;
        }
        if (sudokuBoard.getNumber(row, col) != 0) {
            return solveSudoku(row + 1, col);
        }
        for (int num = 1; num <= 9; num++) {
            if (sudokuBoard.isValidPlacement(row, col, num)) {
                sudokuBoard.setNumber(row, col, num);
                if (solveSudoku(row + 1, col))
                    return true;
                sudokuBoard.setNumber(row, col, 0);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        SudokuBoard sudokuBoard = loadFromFile("exemple.gri");
        if (sudokuBoard != null) {
            SudokuSolver solver = new SudokuSolver(sudokuBoard);
            solver.solve();
        }
    }

    private static SudokuBoard loadFromFile(String fileName) {
        SudokuBoard sudokuBoard = new SudokuBoard(9);
        try {
            Scanner scanner = new Scanner(new File(fileName));
            for (int row = 0; row < 9; row++) {
                String line = scanner.nextLine();
                for (int col = 0; col < 9; col++) {
                    char c = line.charAt(col);
                    if (c != '0') {
                        int number = Character.getNumericValue(c);
                        sudokuBoard.setNumber(row, col, number);
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return sudokuBoard;
    }
}
