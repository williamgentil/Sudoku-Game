import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * La classe <code>SudokuPanel2MouseListener</code> est un écouteur d'événements de souris qui gère les clics souris sur le panneau Sudoku.
 * @version 1.1
 * @author ETOILE Fabio
 */
public class SudokuPanel2MouseListener extends MouseAdapter {
    private SudokuPanel2 panel;

    /**
     * Constructeur de la classe <code>SudokuPanel2MouseListener</code>.
     * Initialise l'écouteur d'événements de souris avec le panneau Sudoku spécifié.
     * @param panel Le panneau Sudoku associé à cet écouteur d'événements de souris.
     */
    public SudokuPanel2MouseListener(SudokuPanel2 panel) {
        this.panel = panel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Appelle la méthode handleMouseClick du panneau Sudoku en transmettant les coordonnées du clic souris.
        panel.handleMouseClick(e.getX(), e.getY());
    }
}
