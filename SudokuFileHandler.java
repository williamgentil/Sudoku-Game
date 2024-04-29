import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class SudokuFileHandler {
    public static SudokuGrid loadFromFile(String fileName) {
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            int size = scanner.nextLine().length();
            SudokuGrid grid = new SudokuGrid(size);
            int row = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                for (int col = 0; col < size; col++) {
                    int number = Character.getNumericValue(line.charAt(col));
                    grid.setNumber(row, col, number);
                }
                row++;
            }
            scanner.close();
            return grid;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void saveToFile(SudokuGrid grid, String fileName) {
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
