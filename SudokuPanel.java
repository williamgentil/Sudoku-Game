import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * La classe <code>SudokuPanel</code> est un panneau qui affiche le plateau du jeu Sudoku et gère les interactions avec l'utilisateur.
 * @version 1.1
 * @author ETOILE Fabio
 * Il permet de sélectionner une case, d'entrer des chiffres avec le clavier et d'afficher visuellement le Sudoku.
 */
public class SudokuPanel extends JPanel implements KeyListener {
  // Variables d'instance
  private SudokuBoard board;
  private int selectedNumber;
  private int selectedRow;
  private int selectedCol;

  /**
   * Constructeur de la classe <code>SudokuPanel</code>.
   * Initialise le panneau Sudoku avec un plateau de jeu donné et ajoute des écouteurs d'événements pour les clics souris et les entrées clavier.
   * @param board Le plateau de jeu Sudoku associé à ce panneau.
   */
  public SudokuPanel(SudokuBoard board) {
    this.board = board;
    selectedNumber = 0; 
    selectedRow = -1; 
    selectedCol = -1; 
    setMinimumSize(new Dimension(200, 200));
    setPreferredSize(new Dimension(400,400));

    SudokuPanelMouseListener mouseListener = new SudokuPanelMouseListener(this);
    addMouseListener(mouseListener);
    addKeyListener(this);
    // Rend le paneau focusable pour recevoir de l'input du clavier
    setFocusable(true);
  }

  @Override
  public void keyPressed(KeyEvent e) {
    int keyCode = e.getKeyCode();
    int boardSize = board.getSize();

    // Met à jour la case sélectionnée si une flèche directionnelle est appuyée 
    if (keyCode == KeyEvent.VK_UP && selectedRow > 0) {
      selectedRow--;
    } else if (keyCode == KeyEvent.VK_DOWN && selectedRow < boardSize - 1) {
      selectedRow++;
    } else if (keyCode == KeyEvent.VK_LEFT && selectedCol > 0) {
      selectedCol--;
    } else if (keyCode == KeyEvent.VK_RIGHT && selectedCol < boardSize - 1) {
      selectedCol++;
      // Si aucune flèche directionnelle appuyée passer directement à l'input d'un chiffre
    } else {
      handleKeyPress(e.getKeyChar());
    }
    // Repaint pour rafraichir le IU
    repaint();
  }

  @Override
  public void keyReleased(KeyEvent e) {
    
  }

  @Override
  public void keyTyped(KeyEvent e) {
    
  }

  /**
   * Définit le plateau de jeu Sudoku associé à ce panneau.
   * @param board Le nouveau plateau de jeu Sudoku.
   */
  public void setBoard(SudokuBoard board) {
    this.board = board;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    int boardSize = board.getSize();
    int blockSize = (int) Math.sqrt(boardSize);
    int cellSize = Math.min(getWidth(), getHeight()) / boardSize; // Calculate the cell size

    // Dessine le plateau du Sudoku
    g.setColor(Color.BLACK);
    for (int i = 0; i <= boardSize; i++) {
      if (i % blockSize == 0) {
        
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2)); 

        
        int x = i * cellSize;
        g2.drawLine(x, 0, x, boardSize * cellSize);

        
        int y = i * cellSize;
        g2.drawLine(0, y, boardSize * cellSize, y);

        
        g2.setStroke(new BasicStroke(1));
      } else {
       
        g.drawLine(i * cellSize, 0, i * cellSize, boardSize * cellSize);
        g.drawLine(0, i * cellSize, boardSize * cellSize, i * cellSize);
      }
    }

   // Dessine les chiffres sur le plateau du Sudoku
    Font numberFont = new Font("Arial", Font.BOLD, cellSize / 2);
    g.setFont(numberFont);
    for (int row = 0; row < boardSize; row++) {
      for (int col = 0; col < boardSize; col++) {
        int number = board.getNumber(row, col);
        if (number != 0) {
          int x = col * cellSize + cellSize / 4;
          int y = row * cellSize + cellSize / 2;
          Color numberColor = board.isEditable(row, col) ? Color.BLACK : Color.BLACK;
          g.setColor(numberColor);
          g.drawString(Integer.toString(number), x, y);
        }
      }
    }
    // Surligne la case sélectionnée avec un contour BLEU
    if (selectedRow >= 0 && selectedCol >= 0) {
      int x = selectedCol * cellSize;
      int y = selectedRow * cellSize;

      g.setColor(Color.BLUE);
      g.drawRect(x, y, cellSize - 1, cellSize - 1);

      // Repaint pour rafraichir l'IU
      repaint();
    }
  }

  /**
   * Récupère le plateau de jeu Sudoku associé à ce panneau.
   * @return Le plateau de jeu Sudoku associé à ce panneau.
   */
  public SudokuBoard getBoard() {
    return board;
  }

  // Gère les clicks souris dans le panneau
  public void handleMouseClick(int x, int y) {
    int boardSize = board.getSize();
    int cellSize = Math.min(getWidth(), getHeight()) / boardSize;

     // Détermine la ligne et la colonne en fonction des coordonnées de la position de la souris au moment du clic
    int row = y / cellSize;
    int col = x / cellSize;

    
    selectedRow = row;
    selectedCol = col;

    
    requestFocus();

    
    repaint();
  }

  // Gère les input clavier autres que les flèches directionnelles 
  public void handleKeyPress(char key) {
    
    if (selectedRow >= 0 && selectedCol >= 0) {
      
      if (Character.isDigit(key) && board.isEditable(selectedRow, selectedCol)) {
        int number = Character.getNumericValue(key);
        board.setNumber(selectedRow, selectedCol, number);
      }
      
      repaint();
    }
  }

  
  public int getSelectedNumber() {
    return selectedNumber;
  }

  
  public void setNumber(int row, int col, int number) {
    if (board.isEditable(row, col)) {
      board.setNumber(row, col, number);
    }
  }
}
