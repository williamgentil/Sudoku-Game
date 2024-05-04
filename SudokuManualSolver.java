import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SudokuManualSolver {
    private SudokuBoard sudokuBoard;
    private SudokuPanel manualPanel;

    public SudokuManualSolver(SudokuBoard sudokuBoard) {
        this.sudokuBoard = sudokuBoard;
        manualPanel = new SudokuPanel(sudokuBoard);
    }

    public void solveManually() {
        manualPanel.createAndShowGUI();
    }

    public static void main(String[] args) {
        SudokuBoard sudokuBoard = loadFromFile("exemple.gri");
        if (sudokuBoard != null) {
            SudokuManualSolver manualSolver = new SudokuManualSolver(sudokuBoard);
            manualSolver.solveManually();
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
