import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SudokuPanel2MouseListener extends MouseAdapter {
    private SudokuPanel2 panel;

    public SudokuPanel2MouseListener(SudokuPanel2 panel) {
        this.panel = panel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        panel.handleMouseClick(e.getX(), e.getY());
    }
}
