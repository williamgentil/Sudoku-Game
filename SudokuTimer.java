import javax.swing.*;
import java.awt.*;

/**
 * La classe <code>SudokuTimer</code> est un composant graphique qui affiche un chronomètre pour le jeu Sudoku.
 * @version 1.1
 * @author ETOILE Fabio
 */
public class SudokuTimer extends JPanel {

    // Variables d'instance
    private Timer timer; // Le timer pour mettre à jour l'heure
    private JLabel timerLabel; // L'étiquette affichant le temps
    private long startTime; // Le temps de début du chronomètre
    private boolean isRunning; // Indique si le chronomètre est en cours d'exécution

    // Constructeur
    public SudokuTimer() {
        setLayout(new FlowLayout(FlowLayout.RIGHT)); // Définit le layout pour placer l'étiquette à droite

        timerLabel = new JLabel("00:00:00"); // Crée une nouvelle étiquette avec l'heure initiale
        timerLabel.setFont(new Font("Arial", Font.PLAIN, 16)); // Définit la police de l'étiquette
        add(timerLabel); // Ajoute l'étiquette au panneau

        // Crée un timer qui déclenchera l'événement à intervalles réguliers pour mettre à jour l'heure
        timer = new Timer(1000, new TimerActionListener(this));
    }

    // Méthode pour obtenir le temps de début du chronomètre
    public long getStartTime() {
        return startTime;
    }

    // Méthode pour mettre à jour l'étiquette de l'heure avec le temps écoulé
    public void updateTimerLabel(long elapsedTime) {
        long seconds = (elapsedTime / 1000) % 60; // Calcule les secondes écoulées
        long minutes = (elapsedTime / (1000 * 60)) % 60; // Calcule les minutes écoulées
        long hours = (elapsedTime / (1000 * 60 * 60)) % 24; // Calcule les heures écoulées

        // Formatte le temps écoulé en format HH:MM:SS
        String timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        timerLabel.setText(timeString); // Met à jour le texte de l'étiquette avec le temps écoulé
    }

    // Méthode pour démarrer le chronomètre
    public void startTimer() {
        if (!isRunning) { // Vérifie si le chronomètre n'est pas déjà en cours d'exécution
            startTime = System.currentTimeMillis(); // Enregistre le temps de début
            timer.start(); // Démarre le timer pour mettre à jour l'heure
            isRunning = true; // Met à jour le statut du chronomètre
        }
    }

    // Méthode pour arrêter le chronomètre
    public void stopTimer() {
        timer.stop(); // Arrête le timer
        isRunning = false; // Met à jour le statut du chronomètre
    }

    // Méthode pour réinitialiser le chronomètre
    public void resetTimer() {
        timer.stop(); // Arrête le timer
        isRunning = false; // Met à jour le statut du chronomètre
        timerLabel.setText("00:00:00"); // Réinitialise le texte de l'étiquette
    }
}
