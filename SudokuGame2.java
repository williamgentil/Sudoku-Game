import javax.swing.*;
import java.awt.*;

public class SudokuGame2 {

    // Variables d'instance

    private JFrame frame;
    private SudokuPanel2 sudokuPanel2;
    private SudokuTimer sudokuTimer;
    /**
    * La classe <code>SudokuGame2</code> représente le jeu Sudoku dans une interface graphique Swing.
    * Elle gère l'affichage du jeu, les interactions avec l'utilisateur et les fonctionnalités du jeu.
    * @version 1.1
    * @author ETOILE Fabio
    */
    public SudokuGame2() {

        // Constructeur

        frame = new JFrame("Sudoku Game");
        SudokuBoard2 board = new SudokuBoard2(9); // Création d'un nouveau tableau Sudoku
        sudokuPanel2 = new SudokuPanel2(board); // Création du panneau Sudoku avec le tableau Sudoku
        sudokuTimer = new SudokuTimer(); // Création d'un nouveau minuteur
    }
     /**
     * Démarre le jeu en démarrant le minuteur.
     */
    public void startGame() {
        sudokuTimer.startTimer(); // Démarrage du minuteur
    }
      /**
     * Initialise le jeu en configurant la fenêtre principale, les boutons et les écouteurs d'événements.
     */
    public void initializeGame() {
        // Configuration de la fenêtre principale
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(sudokuTimer, BorderLayout.EAST);

        // Création des boutons
        JButton checkButton = new JButton("Vérifier");
        JButton solveButton = new JButton("Résoudre");
        JButton generateButton = new JButton("Générer Grille");
        JButton loadGrid2Button = new JButton("Charger Grille"); // Nouveau bouton

        // Ajout des écouteurs d'événements aux boutons

        // Bouton de vérification
        checkButton.addActionListener(e -> {
            boolean isCorrect = sudokuPanel2.getBoard().checkBoard();
            if (isCorrect) {
                JOptionPane.showMessageDialog(frame, "Toutes nos félicitations! Le tableau Sudoku est correct!", "Vérifcations",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "Aie, Le Sudoku contient des erreurs..", "Vérifcations",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        // Bouton de résolution
        
    // Bouton de résolution

solveButton.addActionListener(e -> {
    long startTime = System.nanoTime(); // Enregistrement du temps de début de résolution

    // Attendre un délai aléatoire entre 5 et 10 secondes
    int delay = (int) (Math.random() * 6 + 5); // entre 5 et 10 inclus
    try {
        Thread.sleep(delay * 1000); // Convertir le délai en millisecondes
    } catch (InterruptedException ex) {
        Thread.currentThread().interrupt();
    }

    // Exécuter la résolution
    boolean solved = sudokuPanel2.getBoard().solve();

    long endTime = System.nanoTime(); // Enregistrement du temps de fin de résolution

    // Calculer le temps écoulé en secondes
    long elapsedTime = (endTime - startTime) / 1_000_000_000;

    // Afficher le message de résolution avec le temps écoulé
    if (solved) {
        String message = String.format("Sudoku résolu en %d secondes", elapsedTime);
        JOptionPane.showMessageDialog(frame, message, "Résolutions", JOptionPane.INFORMATION_MESSAGE);
        // Mettre à jour l'affichage après la résolution
        updateBoardView();
    } else {
        JOptionPane.showMessageDialog(frame, "Impossible de résoudre le Sudoku", "Erreur", JOptionPane.ERROR_MESSAGE);
    }
});







        // Bouton de génération d'une nouvelle grille
        generateButton.addActionListener(e -> {
            SudokuBoard2 board = new SudokuBoard2(9);
            board.generateBoard();
            sudokuPanel2.setBoard(board);
            sudokuPanel2.repaint();
        });

        // Bouton de chargement d'une grille
        loadGrid2Button.addActionListener(e -> {
            LoadGrid2.LoadGrid2(sudokuPanel2);
        });

        // Création d'un panneau pour les boutons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(checkButton);
        buttonPanel.add(solveButton);
        buttonPanel.add(generateButton);
        buttonPanel.add(loadGrid2Button);

        // Ajout du panneau Sudoku et du panneau de boutons à la fenêtre principale
        frame.add(sudokuPanel2, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Configuration de la taille de la fenêtre et affichage
        frame.setSize(500, 500);
        frame.pack();
        frame.setVisible(true);
        startGame(); // Début du jeu en démarrant le minuteur
    }

    // Méthode pour mettre à jour la vue du tableau Sudoku
    private void updateBoardView() {
        sudokuPanel2.repaint();
    }
}
