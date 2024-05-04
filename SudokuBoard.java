import java.util.Random;

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

  
  public int getSize() {
    return size;
  }

  // Génère un nouveau tableau
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

     // Check si le tableau est complètement rempli
    for (int row = 0; row < size; row++) {
      for (int col = 0; col < size; col++) {
        originalBoard[row][col] = board[row][col];
      }
    }
  }

  // Efface le tableau
  private void clearBoard() {
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        board[i][j] = 0;
        solution[i][j] = 0;
      }
    }
  }

  // Check si le tableau est complètement rempli
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

  
  public boolean checkBoard() {
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
    // Check les colonnes pour voir si dupliqué
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
    // Check chaque région pour voir si dupliqué
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

  
  public boolean solve() {
    return solveHelper(0, 0);
  }

  // Méthode d'aide au solver en utilisant la méthode de backtracking
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

  // Check si la case est modifiable ou si elle fait partie de la grille de base
  public boolean isEditable(int row, int col) {
    return originalBoard[row][col] == 0;
  }

  // Check si placer un chiffre à une position précise est possible
  public boolean isValid(int row, int col, int num) {
    for (int c = 0; c < size; c++) {
      if (board[row][c] == num) {
        return false;
      }
    }
    // Check si le chiffre existe déjà dans la ligne
    for (int r = 0; r < size; r++) {
      if (board[r][col] == num) {
        return false;
      }
    }
    // Check si le chiffre existe déjà dans la région
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

  // Place le chiffre sur le tableau si c'est possible
  public void placeNumber(int row, int col, int num) {
    if (isValid(row, col, num)) {
      board[row][col] = num;
    }
  }

  // Place le chiffre sur le tableau même si ce n'est pas possible
  public void setNumber(int row, int col, int num) {
    board[row][col] = num;
  }

  // Prend le chiffre à une poistion exacte du tableau
  public int getNumber(int row, int col) {
    if (isValidPosition(row, col)) {
      return board[row][col];
    } else {
      throw new IllegalArgumentException("Invalid position: (" + row + ", " + col + ")");
    }
  }

  // Check si la position est déjà dans le tableau
  private boolean isValidPosition(int row, int col) {
    return row >= 0 && row < size && col >= 0 && col < size;
  }
}
