

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * La classe <code>TimerActionListener</code> est un écouteur d'événements qui réagit aux événements déclenchés
 * @version 1.1
 * @author ETOILE Fabio
 * par un timer. Il est utilisé pour mettre à jour l'heure affichée dans un composant {@link SudokuTimer}.
 */
public class TimerActionListener implements ActionListener {

    private SudokuTimer sudokuTimer; // Référence vers le composant SudokuTimer associé à cet écouteur

    /**
     * Constructeur de la classe.
     * @param sudokuTimer Le composant SudokuTimer associé à cet écouteur.
     */
    public TimerActionListener(SudokuTimer sudokuTimer) {
        this.sudokuTimer = sudokuTimer; // Initialise la référence au SudokuTimer fourni en paramètre
    }

    /**
     * Méthode invoquée à chaque fois que l'événement du timer se produit.
     * Elle calcule le temps écoulé depuis le début du chronomètre et met à jour l'étiquette de l'heure
     * dans le composant SudokuTimer associé.
     * @param e L'événement ActionEvent associé à cet appel.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Calcule le temps écoulé depuis le début du chronomètre
        long elapsedTime = System.currentTimeMillis() - sudokuTimer.getStartTime();
        // Met à jour l'étiquette de l'heure dans le composant SudokuTimer associé
        sudokuTimer.updateTimerLabel(elapsedTime);
    }
}
