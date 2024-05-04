import javax.swing.*;
import java.awt.*;

public class SudokuGame {

    // Variables d'instance

    private JFrame frame;
    private SudokuPanel sudokuPanel;
    private SudokuTimer sudokuTimer;

    public SudokuGame() {

        // Constructeur

        frame = new JFrame("Sudoku Game");
        SudokuBoard board = new SudokuBoard(9); // Création d'un nouveau tableau Sudoku
        sudokuPanel = new SudokuPanel(board); // Création du panneau Sudoku avec le tableau Sudoku
        sudokuTimer = new SudokuTimer(); // Création d'un nouveau minuteur
    }

    public void startGame() {
        sudokuTimer.startTimer(); // Démarrage du minuteur
    }

    public void initializeGame() {
        // Configuration de la fenêtre principale
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(sudokuTimer, BorderLayout.EAST);

        // Création des boutons
        JButton checkButton = new JButton("Vérifier");
        JButton solveButton = new JButton("Résoudre");
        JButton generateButton = new JButton("Générer Grille");
        JButton loadGridButton = new JButton("Charger Grille"); // Nouveau bouton
        JButton saveButton = new JButton("Sauvegarder Grille");

        // Ajout des écouteurs d'événements aux boutons

        // Bouton de vérification
        checkButton.addActionListener(e -> {
            boolean isCorrect = sudokuPanel.getBoard().checkBoard();
            if (isCorrect) {
                JOptionPane.showMessageDialog(frame, "Toutes nos félicitations! Le tableau Sudoku est correct!", "Vérifcations",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "Aie, Le Sudoku contient des erreurs..", "Vérifcations",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        // Bouton de résolution
        solveButton.addActionListener(e -> {
            boolean solved = sudokuPanel.getBoard().solve();
            if (solved) {
                updateBoardView();
                JOptionPane.showMessageDialog(frame, "Sudoku résolu!", "Résolutions", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "Impossible de résoudre le Sudoku", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Bouton de génération d'une nouvelle grille
        generateButton.addActionListener(e -> {
            SudokuBoard board = new SudokuBoard(9);
            board.generateBoard();
            sudokuPanel.setBoard(board);
            sudokuPanel.repaint();
        });

        // Bouton de chargement d'une grille
        loadGridButton.addActionListener(e -> {
            ChargerUneGrille.chargerUneGrille(sudokuPanel);
        });

        // Bouton de sauvegarde de la grille
        saveButton.addActionListener(e -> {
            SaveGrid.saveGrid(sudokuPanel);
        });

        // Création d'un panneau pour les boutons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(checkButton);
        buttonPanel.add(solveButton);
        buttonPanel.add(generateButton);
        buttonPanel.add(loadGridButton);
        buttonPanel.add(saveButton);

        // Ajout du panneau Sudoku et du panneau de boutons à la fenêtre principale
        frame.add(sudokuPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Configuration de la taille de la fenêtre et affichage
        frame.setSize(500, 500);
        frame.pack();
        frame.setVisible(true);
        startGame(); // Début du jeu en démarrant le minuteur
    }

    // Méthode pour mettre à jour la vue du tableau Sudoku
    private void updateBoardView() {
        sudokuPanel.repaint();
    }
}
