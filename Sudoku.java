import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class Sudoku {
    private int[][] grid;
    private JTextField[][] fields;

    public Sudoku() {
        grid = new int[9][9];
        fields = new JTextField[9][9];
        createUI();
    }

    private void createUI() {
        JFrame frame = new JFrame("Sudoku");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLayout(new GridLayout(9, 9));

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                fields[i][j] = new JTextField();
                fields[i][j].setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
                frame.add(fields[i][j]);
            }
        }

        frame.setVisible(true);
    }

    public void setCell(int row, int col, int value) {
        grid[row][col] = value;
        fields[row][col].setText(String.valueOf(value));
    }

    public static void main(String[] args) {
        Sudoku sudoku = new Sudoku();
        // Remplissez les valeurs de la grille ici
        // par exemple: sudoku.setCell(0, 0, 5);
    }
}