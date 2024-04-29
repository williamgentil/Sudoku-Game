import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseClickListener extends MouseAdapter {
    private SudokuPanel sudokuPanel;

    public MouseClickListener(SudokuPanel sudokuPanel) {
        this.sudokuPanel = sudokuPanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        sudokuPanel.handleMouseClick(e.getX(), e.getY());
    }
}
