import java.util.Arrays;

public class SudokuBoard {
    private int[][] grid;
    private final int size;

    public SudokuBoard(int size) {
        this.size = size;
        this.grid = new int[size][size];
    }

    public void setNumber(int row, int col, int number) {
        if (isValidPlacement(row, col, number)) {
            grid[row][col] = number;
        } else {
            System.out.println("Placement invalide !");
        }
    }

    public void removeNumber(int row, int col) {
        grid[row][col] = 0;
    }

    private boolean isValidPlacement(int row, int col, int number) {
        return isRowValid(row, number) && isColumnValid(col, number) && isRegionValid(row, col, number);
    }

    private boolean isRowValid(int row, int number) {
        for (int col = 0; col < size; col++) {
            if (grid[row][col] == number) {
                return false;
            }
        }
        return true;
    }

    private boolean isColumnValid(int col, int number) {
        for (int row = 0; row < size; row++) {
            if (grid[row][col] == number) {
                return false;
            }
        }
        return true;
    }

    private boolean isRegionValid(int row, int col, int number) {
        int regionSize = (int) Math.sqrt(size);
        int regionRowStart = row - row % regionSize;
        int regionColStart = col - col % regionSize;

        for (int i = regionRowStart; i < regionRowStart + regionSize; i++) {
            for (int j = regionColStart; j < regionColStart + regionSize; j++) {
                if (grid[i][j] == number) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int[] row : grid) {
            sb.append(Arrays.toString(row)).append("\n");
        }
        return sb.toString();
    }
}