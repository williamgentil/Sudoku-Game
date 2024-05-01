import javax.swing.JButton;
import javax.swing.JPanel;

public class GrilleJoueur extends JPanel {
    private JButton createEmptyGridButton;

    public GrilleJoueur(SudokuPanel sudokuPanel) {
        // Créer le bouton pour créer une grille vide
        createEmptyGridButton = new JButton("Créer une Grille Vide");
        createEmptyGridButton.addActionListener(e -> {
            // Appeler la méthode pour créer une grille vide dans le SudokuPanel
            sudokuPanel.createEmptyGrid();
        });

        // Ajouter le bouton au panneau
        add(createEmptyGridButton);
    }
}