import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class SaveGridPanel {
    public static void saveGridPanel(Grille grille) {
        //System.out.println("ok");
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Sauvegarder la grille");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Fichiers .gri", "gri"));
        int select = fileChooser.showSaveDialog(null);

        if (select == JFileChooser.APPROVE_OPTION) {
            try {
                String selectedFilePath = fileChooser.getSelectedFile().getAbsolutePath();
                if (!selectedFilePath.toLowerCase().endsWith(".gri")) {
                    selectedFilePath += ".gri"; // Ajouter l'extension si elle n'est pas présente
                }
                FileOutputStream file = new FileOutputStream(selectedFilePath);
                DataOutputStream fichier = new DataOutputStream(file);

                JTextField[][] fields = grille.getFields();
                for (int ligne = 0; ligne < 9; ligne++) {
                    StringBuilder sbuild = new StringBuilder();
                    for (int colonne = 0; colonne < 9; colonne++) {
                        String text = fields[ligne][colonne].getText();
                        if (text.isEmpty()) {
                            sbuild.append("0");
                        } else {
                            sbuild.append(text);
                        }
                    }
                    String numCh = sbuild.toString();
                    System.out.println(numCh);
                    int a = Integer.parseInt(numCh);
                    System.out.println(a);
                    fichier.writeInt(a);
                }
                try {
                    file.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
