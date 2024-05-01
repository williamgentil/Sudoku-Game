import java.io.*;

public class SudokuFileReader {
    public static SudokuBoard loadFromFile(String fileName) {
        try {
            File file = new File(fileName);
            FileInputStream inputStream = new FileInputStream(file);

            // Taille de la grille (racine carrée du nombre total d'octets dans le fichier)
            int size = (int) Math.sqrt(file.length());
            SudokuBoard grid = new SudokuBoard(size);

            int row = 0;
            int col = 0;

            // Lire les octets un par un et les utiliser pour remplir la grille
            int value;
            while ((value = inputStream.read()) != -1) {
                if (col == size) {
                    col = 0;
                    row++;
                }
                if (row == size) {
                    break; // Fin de la grille
                }
                if (value != 0) {
                    grid.setNumber(row, col, value);
                }
                col++;
            }

            inputStream.close();
            return grid;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
