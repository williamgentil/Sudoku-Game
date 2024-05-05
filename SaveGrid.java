import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * La classe <code>SaveGrid</code> est utilisée pour sauvegarder la grille Sudoku dans un fichier.
 * @version 1.1
 * @author ETOILE Fabio
 */
public class SaveGrid {

    /**
     * Méthode pour sauvegarder la grille dans un fichier.
     *
     * @param sudokuPanel Le panneau de Sudoku contenant la grille à sauvegarder.
     */
    public static void saveGrid(SudokuPanel sudokuPanel) {

        // Création d'une fenêtre de dialogue pour choisir l'emplacement de sauvegarde
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Sauvegarder la grille");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Fichiers .gri", "gri"));
        int select = fileChooser.showSaveDialog(null);

        // Si l'utilisateur choisit un emplacement pour sauvegarder
        if (select == JFileChooser.APPROVE_OPTION) {
            try {
                // Obtention du chemin du fichier sélectionné
                String selectedFilePath = fileChooser.getSelectedFile().getAbsolutePath();
                // Vérification de l'extension .gri et ajout si nécessaire
                if (!selectedFilePath.toLowerCase().endsWith(".gri")) {
                    selectedFilePath += ".gri"; 
                }
                // Création d'un flux de sortie vers le fichier
                FileOutputStream file = new FileOutputStream(selectedFilePath);
                DataOutputStream fichier = new DataOutputStream(file);

                SudokuBoard board = sudokuPanel.getBoard();
               
                // Parcours des lignes de la grille
                for (int ligne = 0; ligne < 9; ligne++) {
                    StringBuilder sbuild = new StringBuilder();
                    
                    // Parcours des colonnes de la grille
                    for (int colonne = 0; colonne < 9; colonne++) {
                        int number = board.getNumber(ligne, colonne);
                        // Conversion du nombre en texte pour l'écriture dans le fichier
                        String text = (number == 0) ? "0" : Integer.toString(number);
                        sbuild.append(text);
                    }
                    String numCh = sbuild.toString();
                    // Écriture de la ligne dans le fichier sous forme d'un entier
                    fichier.writeInt(Integer.parseInt(numCh));
                }
                fichier.close(); // Fermeture du fichier
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
