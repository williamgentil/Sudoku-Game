import java.util.Random;

/**
 * La classe <code>SudokuBoard</code> représente le tableau de jeu du Sudoku.
 * @version 1.1
 * @author ETOILE Fabio
 */
public class SudokuBoard {
  
  // Variables d'instances
  private int[][] board;
  private int[][] solution;
  private int size = 9;
  private int[][] originalBoard;

  // Constructeur
  public SudokuBoard(int size) {
    this.size = size;
    board = new int[size][size];
    solution = new int[size][size];
    originalBoard = new int[size][size];
  }

  /**
   * Retourne la taille du tableau de Sudoku.
   *
   * @return La taille du tableau de Sudoku.
   */
  public int getSize() {
    return size;
  }

  /**
   * Génère un nouveau tableau de Sudoku.
   */
  public void generateBoard() {
    clearBoard();
    solve(); 

    Random random = new Random();
    int numToRemove = 55; 

    for (int i = 0; i < numToRemove; i++) {
        int row = random.nextInt(size);
        int col = random.nextInt(size);

        if (board[row][col] != 0) {
            board[row][col] = 0;
        } else {
            i--;
        }
    }

    for (int row = 0; row < size; row++) {
      for (int col = 0; col < size; col++) {
        originalBoard[row][col] = board[row][col];
      }
    }
  }

  /**
   * Efface le tableau de Sudoku.
   */
  private void clearBoard() {
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        board[i][j] = 0;
        solution[i][j] = 0;
      }
    }
  }

  /**
   * Vérifie si le tableau de Sudoku est complètement rempli.
   *
   * @return true si le tableau est complètement rempli, sinon false.
   */
  public boolean isFilled() {
    for (int row = 0; row < size; row++) {
      for (int col = 0; col < size; col++) {
        if (board[row][col] == 0) {
          return false; 
        }
      }
    }
    return true; 
  }

  /**
   * Vérifie si le tableau de Sudoku est valide.
   *
   * @return true si le tableau est valide, sinon false.
   */
  public boolean checkBoard() {
    // Vérifie les lignes
    for (int row = 0; row < size; row++) {
      boolean[] used = new boolean[size + 1];
      for (int col = 0; col < size; col++) {
        int number = getNumber(row, col);
        if (number != 0) {
          if (used[number]) {
            return false; 
          }
          used[number] = true;
        }
      }
    }
    // Vérifie les colonnes
    for (int col = 0; col < size; col++) {
      boolean[] used = new boolean[size + 1];
      for (int row = 0; row < size; row++) {
        int number = getNumber(row, col);
        if (number != 0) {
          if (used[number]) {
            return false; 
          }
          used[number] = true;
        }
      }
    }
    // Vérifie chaque région
    int blockSize = (int) Math.sqrt(size);
    for (int blockRow = 0; blockRow < blockSize; blockRow++) {
      for (int blockCol = 0; blockCol < blockSize; blockCol++) {
        boolean[] used = new boolean[size + 1];
        for (int row = blockRow * blockSize; row < (blockRow + 1) * blockSize; row++) {
          for (int col = blockCol * blockSize; col < (blockCol + 1) * blockSize; col++) {
            int number = getNumber(row, col);
            if (number != 0) {
              if (used[number]) {
                return false; 
              }
              used[number] = true;
            }
          }
        }
      }
    }
    return true; 
  }

  /**
   * Résout le Sudoku.
   *
   * @return true si le Sudoku est résolu, sinon false.
   */
  public boolean solve() {
    return solveHelper(0, 0);
  }

  /**
   * Méthode d'aide pour résoudre le Sudoku en utilisant la méthode de backtracking.
   *
   * @param row La ligne actuelle.
   * @param col La colonne actuelle.
   * @return true si le Sudoku est résolu, sinon false.
   */
  private boolean solveHelper(int row, int col) {
    
    if (row == size) {
      return true;
    }
    
    int nextRow = col == size - 1 ? row + 1 : row;
    int nextCol = (col + 1) % size;

    if (board[row][col] != 0) {
      return solveHelper(nextRow, nextCol);
    }
    
    for (int num = 1; num <= 9; num++) {
      if (isValid(row, col, num)) {
        board[row][col] = num; 

        if (solveHelper(nextRow, nextCol)) {
          return true; 
        }
        board[row][col] = 0; 
      }
    }
    return false; 
  }

  /**
   * Vérifie si une case du Sudoku est modifiable.
   *
   * @param row La ligne de la case.
   * @param col La colonne de la case.
   * @return true si la case est modifiable, sinon false.
   */
  public boolean isEditable(int row, int col) {
    return originalBoard[row][col] == 0;
  }

  /**
   * Vérifie si un chiffre peut être placé dans une case du Sudoku.
   *
   * @param row La ligne de la case.
   * @param col La colonne de la case.
   * @param num Le chiffre à placer.
   * @return true si le chiffre peut être placé dans la case, sinon false.
   */
  public boolean isValid(int row, int col, int num) {
    for (int c = 0; c < size; c++) {
      if (board[row][c] == num) {
        return false;
      }
    }
    // Vérifie la colonne
    for (int r = 0; r < size; r++) {
      if (board[r][col] == num) {
        return false;
      }
    }
    // Vérifie la région
    int blockStartRow = (row / 3) * 3;
    int blockStartCol = (col / 3) * 3;
    for (int r = blockStartRow; r < blockStartRow + 3; r++) {
      for (int c = blockStartCol; c < blockStartCol + 3; c++) {
        if (board[r][c] == num) {
          return false;
        }
      }
    }
    return true; 
  }

  /**
   * Place un chiffre dans une case du Sudoku si c'est possible.
   *
   * @param row La ligne de la case.
   * @param col La colonne de la case.
   * @param num Le chiffre à placer.
   */
  public void placeNumber(int row, int col, int num) {
    if (isValid(row, col, num)) {
      board[row][col] = num;
    }
  }

  /**
   * Place un chiffre dans une case du Sudoku même si ce n'est pas possible.
   *
   * @param row La ligne de la case.
   * @param col La colonne de la case.
   * @param num Le chiffre à placer.
   */
  public void setNumber(int row, int col, int num) {
    board[row][col] = num;
  }

  /**
   * Récupère le chiffre dans une case du Sudoku.
   *
   * @param row La ligne de la case.
   * @param col La colonne de la case.
   * @return Le chiffre dans la case spécifiée.
   * @throws IllegalArgumentException si la position spécifiée est invalide.
   */
  public int getNumber(int row, int col) {
    if (isValidPosition(row, col)) {
      return board[row][col];
    } else {
      throw new IllegalArgumentException("Invalid position: (" + row + ", " + col + ")");
    }
  }

  /**
   * Vérifie si une position est valide dans le Sudoku.
   *
   * @param row La ligne de la position.
   * @param col La colonne de la position.
   * @return true si la position est valide, sinon false.
   */
  private boolean isValidPosition(int row, int col) {
    return row >= 0 && row < size && col >= 0 && col < size;
  }
}
