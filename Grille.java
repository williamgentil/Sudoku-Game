import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Grille extends JPanel {
    private SudokuBoard sudokuBoard;
    private JTextField[][] fields;

    public Grille(SudokuBoard sudokuBoard) {
        this.sudokuBoard = sudokuBoard;
        this.fields = new JTextField[9][9];
        createUI();
    }

    private void createUI() {
        JFrame frame = new JFrame("Sudoku");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);

        // Utilisation d'un BorderLayout pour le JFrame
        frame.setLayout(new BorderLayout());

        // Création de la grille Sudoku
        JPanel sudokuPanel = new JPanel(new GridLayout(9, 9));
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                fields[i][j] = new JTextField();
                ((AbstractDocument) fields[i][j].getDocument()).setDocumentFilter(new NumberDocumentFilter(1));

                int top = (i % 3 == 0 || i == 0) ? 2 : 1;
                int left = (j % 3 == 0 || j == 0) ? 2 : 1;
                int bottom = (i % 3 == 2 || i == 8) ? 2 : 1;
                int right = (j % 3 == 2 || j == 8) ? 2 : 1;
                fields[i][j].setBorder(new MatteBorder(top, left, bottom, right, Color.BLACK));
                fields[i][j].setHorizontalAlignment(JTextField.CENTER);
                fields[i][j].setFont(new Font("Arial", Font.BOLD, 20));
                fields[i][j].setPreferredSize(new Dimension(50, 50)); // Taille personnalisée pour chaque case
                sudokuPanel.add(fields[i][j]);
            }
        }

        // Ajout de la grille Sudoku au centre du BorderLayout
        frame.add(sudokuPanel, BorderLayout.CENTER);

        // Création du bouton "Charger Grille"
        JButton loadGridButton = new JButton("Charger Grille");
        loadGridButton.setPreferredSize(new Dimension(180, 60));
        loadGridButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChargerUneGrille.chargerUneGrille(Grille.this);
            }
        });

        // Création du bouton "Sauvegarder Grille" en utilisant la méthode createSaveGridButton()
        JButton saveGridButton = createSaveGridButton();

        // Création d'un JPanel pour les boutons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(loadGridButton);
        buttonPanel.add(saveGridButton); // Ajout du bouton "Sauvegarder Grille"

        // Ajout du JPanel contenant les boutons au bas du BorderLayout
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Rendre la fenêtre visible
        frame.setVisible(true);
    }

    // Méthode pour créer le bouton "Sauvegarder Grille"
    private JButton createSaveGridButton() {
        JButton saveGridButton = new JButton("Sauvegarder Grille");
        saveGridButton.setPreferredSize(new Dimension(180, 60));
        saveGridButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SaveGridPanel.saveGridPanel(Grille.this);
            }
        });
        return saveGridButton;
    }

    public void setCell(int row, int col, int value) {
        if (sudokuBoard.isValidPlacement(row, col, value)) {
            sudokuBoard.setNumber(row, col, value);
            fields[row][col].setText(String.valueOf(value));
        } else {
            JOptionPane.showMessageDialog(this, "Placement invalide ! Veuillez respecter les règles du Sudoku.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
    

    public JTextField[][] getFields() {
        return fields;
    }

    public int[][] getGrid() {
        return sudokuBoard.getGrid();
    }
}
