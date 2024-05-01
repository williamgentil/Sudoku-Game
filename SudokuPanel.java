import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SudokuPanel extends JPanel implements KeyListener, MouseListener {
    private Grille grille;
    private SudokuBoard sudokuBoard;
    private LoadFromFilePanel loadFromFilePanel;

    public SudokuPanel(SudokuBoard sudokuBoard) {
        this.sudokuBoard = sudokuBoard;
        setLayout(new GridLayout(9, 9));

        grille = new Grille();
        add(grille);

        loadFromFilePanel = new LoadFromFilePanel(this);
        add(loadFromFilePanel);

        addMouseListener(this);
        addKeyListener(this);
        setFocusable(true);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT) {
            // Gérer la navigation avec les touches directionnelles
        } else {
            char keyChar = e.getKeyChar();
            if (Character.isDigit(keyChar)) {
                int number = Character.getNumericValue(keyChar);
                // Placer le numéro dans la grille Sudoku
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Ne rien faire
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Ne rien faire
    }

    public void createEmptyGrid() {
        // Réinitialiser la grille en supprimant tous les chiffres qu'elle contient
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudokuBoard.setNumber(i, j,0 ); 
            }
        }
        // Mettre à jour l'interface utilisateur pour refléter la grille vide
        repaint();
    }
    


    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        // Déterminer la cellule sélectionnée en fonction des coordonnées du clic de souris
    } 

    @Override
    public void mousePressed(MouseEvent e) {
        // Ne rien faire
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Ne rien faire
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Ne rien faire
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Ne rien faire
    }

    // Méthode pour gérer le clic de souris sur la grille
    public void handleMouseClick(int x, int y) {
        // Implémentez la logique pour déterminer la cellule sélectionnée en fonction des coordonnées du clic de souris
    }


    // Méthode pour charger une grille depuis un fichier
    public void loadFromFile(File file) {
        try {
            Scanner scanner = new Scanner(file);
            int row = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                for (int col = 0; col < line.length(); col++) {
                    char c = line.charAt(col);
                    if (c != '0') {
                        int number = Character.getNumericValue(c);
                        sudokuBoard.setNumber(row, col, number);
                    }
                }
                row++;
            }
            scanner.close();
            // Mettez à jour l'interface utilisateur pour refléter la nouvelle grille chargée
            repaint();  
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}