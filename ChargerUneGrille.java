import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.DataInputStream;

public class ChargerUneGrille {
    // Méthode principale pour charger une grille à partir d'un fichier
    public static void chargerUneGrille(SudokuPanel sudokuPanel) {
        // Création d'une fenêtre de dialogue pour choisir le fichier
        JFrame frame = new JFrame();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Sélectionnez le fichier grille");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Uniquement(*.gri)", "gri"));
        int returnValue = fileChooser.showOpenDialog(frame);
        // Si l'utilisateur sélectionne un fichier
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            chargerGrille(sudokuPanel, file); 
        }
    }

    // Méthode pour charger la grille à partir d'un fichier
    private static void chargerGrille(SudokuPanel sudokuPanel, File file) {
        try {
            FileInputStream fileInput = new FileInputStream(file);
            DataInputStream dataInput = new DataInputStream(fileInput);

            SudokuBoard board = sudokuPanel.getBoard();

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

            sudokuPanel.repaint(); 

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
