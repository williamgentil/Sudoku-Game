import javax.swing.*;
import java.awt.*;

public class SudokuTimer extends JPanel {

    // Variables d'instance
    private Timer timer; 
    private JLabel timerLabel; 
    private long startTime; 
    private boolean isRunning; 

    // Constructeur
    public SudokuTimer() {
        setLayout(new FlowLayout(FlowLayout.RIGHT)); 

        timerLabel = new JLabel("00:00:00"); 
        timerLabel.setFont(new Font("Arial", Font.PLAIN, 16)); 
        add(timerLabel); 

        
        // et un écouteur d'événements TimerActionListener pour mettre à jour l'heure
        timer = new Timer(1000, new TimerActionListener(this));
    }

    // Méthode pour obtenir le temps de début du chronomètre
    public long getStartTime() {
        return startTime;
    }

    // Méthode pour mettre à jour l'étiquette de l'heure avec le temps écoulé
    public void updateTimerLabel(long elapsedTime) {
        long seconds = (elapsedTime / 1000) % 60; 
        long minutes = (elapsedTime / (1000 * 60)) % 60; 
        long hours = (elapsedTime / (1000 * 60 * 60)) % 24; 

        
        String timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        timerLabel.setText(timeString); 
    }

    // Méthode pour démarrer le chronomètre
    public void startTimer() {
        if (!isRunning) { 
            startTime = System.currentTimeMillis(); 
            timer.start(); 
            isRunning = true; 
        }
    }

    // Méthode pour arrêter le chronomètre
    public void stopTimer() {
        timer.stop(); 
        isRunning = false;
    }

    // Méthode pour réinitialiser le chronomètre
    public void resetTimer() {
        timer.stop(); 
        isRunning = false; 
        timerLabel.setText("00:00:00"); 
    }
}
