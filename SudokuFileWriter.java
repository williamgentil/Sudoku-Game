import java.io.FileWriter;
import java.io.IOException;

public class SudokuFileWriter {
    public static void saveToFile(SudokuBoard grid, String fileName) {
        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write(grid.toString());
            writer.close();
            System.out.println("Grille sauvegardée avec succès !");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
