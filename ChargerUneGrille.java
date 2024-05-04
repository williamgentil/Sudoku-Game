import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.DataInputStream;


public class ChargerUneGrille {
    public static void chargerUneGrille(Grille grille) {
        JFrame frame = new JFrame();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Séléctionnez le fichier grille");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Uniquement(*.gri)", "gri"));
        int returnValue = fileChooser.showOpenDialog(frame);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            chargerGrille(grille, file);
        }
    }

    private static void chargerGrille(Grille grille, File file) {
        try {
            FileInputStream fileInput = new FileInputStream(file);
            DataInputStream dataInput = new DataInputStream(fileInput);

            JTextField[][] fields = grille.getFields();

            for (int row = 0; row < 9; row++) {
                int num = dataInput.readInt();
                String str = Integer.toString(num);

                while (str.length() < 9) {
                    str = "0" + str;  
                }

                for (int col = 0; col < 9; col++) {
                    char cr = str.charAt(col);
                    if (cr == '0') {
                        fields[row][col].setText("");
                    } else {
                        fields[row][col].setText(Character.toString(cr));
                    }
                }
            }

            dataInput.close(); 
            fileInput.close(); 

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
