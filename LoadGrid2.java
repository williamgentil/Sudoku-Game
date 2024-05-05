
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.DataInputStream;

/**
 * La classe <code>LoadGrid2</code> est utilisée pour charger une deuxième grille de Sudoku à partir d'un fichier.
 * @version 1.1
 * @author ETOILE Fabio
 */
public class LoadGrid2 {

    /**
     * Méthode principale pour charger une grille à partir d'un fichier.
     *
     * @param sudokuPanel2 Le panneau de Sudoku où afficher la grille chargée.
     */
    public static void LoadGrid2(SudokuPanel2 sudokuPanel2) {
        // Création d'une fenêtre de dialogue pour choisir le fichier
        JFrame frame = new JFrame();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Sélectionnez le fichier grille");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Uniquement(*.gri)", "gri"));
        int returnValue = fileChooser.showOpenDialog(frame);
        // Si l'utilisateur sélectionne un fichier
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            loadGrid2(sudokuPanel2, file); 
        }
    }

    /**
     * Méthode pour charger la grille à partir d'un fichier.
     *
     * @param sudokuPanel2 Le panneau de Sudoku où afficher la grille chargée.
     * @param file Le fichier à partir duquel charger la grille.
     */
    private static void loadGrid2(SudokuPanel2 sudokuPanel2, File file) {
        try {
            FileInputStream fileInput = new FileInputStream(file);
            DataInputStream dataInput = new DataInputStream(fileInput);

            SudokuBoard2 board = sudokuPanel2.getBoard();

            // Parcours des lignes du fichier
            for (int row = 0; row < 9; row++) {
                int num = dataInput.readInt();
                String str = Integer.toString(num);

                // Remplissage de la ligne dans le tableau Sudoku
                while (str.length() < 9) {
                    str = "0" + str;  
                }
                for (int col = 0; col < 9; col++) {
                    char cr = str.charAt(col);
                    if (cr == '0') {
                        board.setNumber(row, col, 0);
                    } else {
                        int value = Character.getNumericValue(cr);
                        board.setNumber(row, col, value);
                    }
                }
            }

            dataInput.close(); 
            fileInput.close(); 

            sudokuPanel2.repaint(); 

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
