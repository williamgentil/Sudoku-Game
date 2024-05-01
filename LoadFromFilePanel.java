import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LoadFromFilePanel extends JPanel {
    private JButton loadFromFileButton;
    private SudokuPanel sudokuPanel;

    public LoadFromFilePanel(SudokuPanel sudokuPanel) {
        this.sudokuPanel = sudokuPanel;
        // Créer le bouton pour charger une grille depuis un fichier
        loadFromFileButton = new JButton("Charger une Grille depuis un Fichier");
        loadFromFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Utiliser JFileChooser pour permettre à l'utilisateur de sélectionner un fichier
                JFileChooser fileChooser = new JFileChooser("");
                fileChooser.setMultiSelectionEnabled(false); // Permettre la sélection d'un seul fichier
                int returnValue = fileChooser.showDialog(LoadFromFilePanel.this, "Choisir");
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    // Récupérer le fichier sélectionné par l'utilisateur
                    File file = fileChooser.getSelectedFile();
                    // Appeler la méthode pour charger la grille depuis le fichier dans le SudokuPanel
                    sudokuPanel.loadFromFile(file);
                } else {
                    System.out.println("Chargement annulé.");
                }
            }
        });

        // Ajouter le bouton au panneau
        add(loadFromFileButton);
    }
}
