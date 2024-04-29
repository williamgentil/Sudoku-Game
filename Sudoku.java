import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Sudoku {
    private int[][] grid;
    private JTextField[][] fields;

    public Sudoku() {
        grid = new int[9][9];
        fields = new JTextField[9][9];
        createUI();
        loadFromFile("grille.txt"); // Charger la grille à partir du fichier "grille.txt"
    }
// creer la grille 
    private void createUI() {
        JFrame frame = new JFrame("Sudoku");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLayout(new GridLayout(9, 9));

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                fields[i][j] = new JTextField();
                int top = (i % 3 == 0 || i == 0) ? 2 : 1;
                int left = (j % 3 == 0 || j == 0) ? 2 : 1;
                int bottom = (i % 3 == 2 || i == 8) ? 2 : 1;
                int right = (j % 3 == 2 || j == 8) ? 2 : 1;
                fields[i][j].setBorder(new MatteBorder(top, left, bottom, right, Color.BLACK));
                fields[i][j].setHorizontalAlignment(JTextField.CENTER);
                fields[i][j].setFont(new Font("Arial", Font.BOLD, 20));
                fields[i][j].setPreferredSize(new Dimension(50, 50)); // Taille personnalisée pour chaque case
                frame.add(fields[i][j]);
            }
        }

        frame.setVisible(true);
    }

// Méthode pour charger la grille à partir d'un fichier texte
private void loadFromFile(String fileName) {
    try {
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        int row = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            for (int col = 0; col < line.length(); col++) {
                char c = line.charAt(col);
                if (c != '0') {
                    int number = Character.getNumericValue(c);
                    setCell(row, col, number);
                }
            }
            row++;
        }
        scanner.close();
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }
}

    public void setCell(int row, int col, int value) {
        grid[row][col] = value;
        fields[row][col].setText(String.valueOf(value));
    }
}
