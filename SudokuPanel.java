import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class SudokuPanel extends JPanel implements KeyListener, MouseListener {
    private Grille grille;
    private SudokuBoard sudokuBoard;
    private ChargerUneGrille chargerUneGrille;
    

    public SudokuPanel(SudokuBoard sudokuBoard) {
        this.sudokuBoard = sudokuBoard;
        setLayout(new GridLayout(9, 9));

        grille = new Grille(sudokuBoard);
        add(grille);

        chargerUneGrille = new ChargerUneGrille();
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
        ChargerUneGrille.chargerUneGrille(grille);

    }
    // Méthode pour sauvegarder une grille depuis un fichier
    public void saveToFile(File file) {
        SaveGridPanel.saveGridPanel(grille);
    }
}
