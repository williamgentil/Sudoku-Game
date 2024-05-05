import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * La classe <code>SudokuPanelMouseListener</code> est un écouteur d'événements de souris qui gère les clics souris sur le panneau Sudoku.
 * @version 1.1
 * @author ETOILE Fabio
 */
public class SudokuPanelMouseListener extends MouseAdapter {
    private SudokuPanel panel;

    /**
     * Constructeur de la classe <code>SudokuPanelMouseListener</code>.
     * Initialise l'écouteur d'événements de souris avec le panneau Sudoku spécifié.
     * @param panel Le panneau Sudoku associé à cet écouteur d'événements de souris.
     */
    public SudokuPanelMouseListener(SudokuPanel panel) {
        this.panel = panel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Appelle la méthode handleMouseClick du panneau Sudoku en transmettant les coordonnées du clic souris.
        panel.handleMouseClick(e.getX(), e.getY());
    }
}
