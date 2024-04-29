import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SudokuPanel extends JPanel implements KeyListener {
    private Sudoku sudoku;
    private JButton loadButton;

    public SudokuPanel(Sudoku sudoku) {
        this.sudoku = sudoku;
        setMinimumSize(new Dimension(200, 200));
        setPreferredSize(new Dimension(400, 400));

       

        // Ajout du panneau lui-même en tant qu'écouteur de clavier
        addKeyListener(this);
        // Rendre le panneau focusable pour recevoir les entrées au clavier
        setFocusable(true);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        // Gérer les touches de direction pour la navigation
        if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT) {
            // Implémenter la logique de navigation ici
        } else {
            // Gérer les autres pressions de touche
            char keyChar = e.getKeyChar();
            if (Character.isDigit(keyChar)) {
                int number = Character.getNumericValue(keyChar);
                // Implémenter la logique pour définir le numéro dans la grille Sudoku
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

    // Gérer les clics de souris sur le panneau
    public void handleMouseClick(int x, int y) {
        // Implémenter la logique pour déterminer la cellule sélectionnée en fonction des coordonnées du clic de souris
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Implémenter la logique de dessin pour la grille Sudoku et les nombres
    }

    private void chargerGrilleDepuisFichier(String chemin) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(chemin));
            String line;
            int row = 0;
            while ((line = reader.readLine()) != null && row < 9) {
                String[] values = line.split("");
                for (int col = 0; col < 9; col++) {
                    sudoku.setCell(row, col, Integer.parseInt(values[col]));
                }
                row++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
