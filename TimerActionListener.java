import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerActionListener implements ActionListener {

    private SudokuTimer sudokuTimer; 

    // Constructeur prenant une instance de SudokuTimer en paramètre
    public TimerActionListener(SudokuTimer sudokuTimer) {
        this.sudokuTimer = sudokuTimer; // Initialisation de la référence
    }

    // Méthode invoquée à chaque fois que l'événement du timer se produit
    @Override
    public void actionPerformed(ActionEvent e) {
        long elapsedTime = System.currentTimeMillis() - sudokuTimer.getStartTime(); 
        sudokuTimer.updateTimerLabel(elapsedTime); 
    }
}
