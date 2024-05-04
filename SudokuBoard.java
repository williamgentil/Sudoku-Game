import javax.swing.*;

public class SudokuBoard {
    private int[][] board;
    private final int size;
    private final int regionSize;

    public SudokuBoard(int size) {
        this.size = size;
        this.regionSize = (int) Math.sqrt(size);
        this.board = new int[size][size];
    }

    public void setNumber(int row, int col, int number) {
        if (isValidPlacement(row, col, number)) {
            board[row][col] = number;
        } else {
            JOptionPane.showMessageDialog(null, "Placement invalide ! Veuillez respecter les règles du Sudoku.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean isValidPlacement(int row, int col, int number) {
        return isRowValid(row, number) && isColumnValid(col, number) && isRegionValid(row, col, number);
    }

    private boolean isRowValid(int row, int number) {
        for (int col = 0; col < size; col++) {
            if (board[row][col] == number) {
                return false;
            }
        }
        return true;
    }

    private boolean isColumnValid(int col, int number) {
        for (int row = 0; row < size; row++) {
            if (board[row][col] == number) {
                return false;
            }
        }
        return true;
    }

    private boolean isRegionValid(int row, int col, int number) {
        int regionRowStart = row - row % regionSize;
        int regionColStart = col - col % regionSize;

        for (int i = regionRowStart; i < regionRowStart + regionSize; i++) {
            for (int j = regionColStart; j < regionColStart + regionSize; j++) {
                if (board[i][j] == number) {
                    return false;
                }
            }
        }
        return true;
    }

    public int[][] getGrid() {
        return board;
    }
}
