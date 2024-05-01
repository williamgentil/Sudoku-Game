import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Grille extends JPanel {
    private int[][] grid;
    private JTextField[][] fields;

    public Grille() {
        grid = new int[9][9];
        fields = new JTextField[9][9];
        createUI();
    }

    private void createUI() {
        JFrame frame = new JFrame("Grille");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);

        // Utilisation d'un BorderLayout pour le JFrame
        frame.setLayout(new BorderLayout());

        // Création de la grille Sudoku
        JPanel sudokuPanel = new JPanel(new GridLayout(9, 9));
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
                sudokuPanel.add(fields[i][j]);
            }
        }

        // Ajout de la grille Sudoku au centre du BorderLayout
        frame.add(sudokuPanel, BorderLayout.CENTER);

        // Création du bouton "Charger Grille"
        JButton loadGridButton = new JButton("Charger Grille");

        // Définir une taille préférée légèrement plus grande pour le bouton
        loadGridButton.setPreferredSize(new Dimension(180, 60));

        // Création d'un JPanel pour le bouton et définir un layout manager pour ce JPanel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(loadGridButton);

        // Ajout du JPanel contenant le bouton "Charger Grille" à droite du BorderLayout
        frame.add(buttonPanel, BorderLayout.SOUTH); // Déplacer le bouton vers le bas

        // Ajout d'un ActionListener au bouton pour charger la grille depuis un fichier .gri
        loadGridButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Utilisation de JFileChooser pour permettre à l'utilisateur de sélectionner un fichier
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter("Fichiers .gri", "gri"));
                int returnValue = fileChooser.showOpenDialog(frame);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    loadGridFromFile(file);
                }
            }
        });

        // Rendre la fenêtre visible
        frame.setVisible(true);
    }

    private void loadGridFromFile(File file) {
        try {
            Scanner scanner = new Scanner(file);
            int row = 0;
            while (scanner.hasNextLine() && row < 9) {
                String line = scanner.nextLine();
                for (int col = 0; col < line.length() && col < 9; col++) {
                    char c = line.charAt(col);
                    if (Character.isDigit(c)) {
                        int number = Character.getNumericValue(c);
                        setCell(row, col, number);
                    }
                }
                row++;
            }
            scanner.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public void setCell(int row, int col, int value) {
        grid[row][col] = value;
        fields[row][col].setText(String.valueOf(value));
    }
}
