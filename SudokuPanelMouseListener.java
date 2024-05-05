import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SudokuPanelMouseListener extends MouseAdapter {
    private SudokuPanel panel;

    public SudokuPanelMouseListener(SudokuPanel panel) {
        this.panel = panel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        panel.handleMouseClick(e.getX(), e.getY());
    }
}
