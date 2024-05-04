import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class NumberDocumentFilter extends DocumentFilter {
    private final int limit;

    public NumberDocumentFilter(int limit) {
        this.limit = limit;
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        if (string == null || string.isEmpty()) {
            return;
        }

        char c = string.charAt(0);
        if (Character.isDigit(c) && c >= '1' && c <= '9' && (fb.getDocument().getLength() + string.length()) <= limit) {
            super.insertString(fb, offset, string, attr);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        if (text == null || text.isEmpty()) {
            return;
        }

        char c = text.charAt(0);
        if (Character.isDigit(c) && c >= '1' && c <= '9' && (fb.getDocument().getLength() + text.length() - length) <= limit) {
            super.replace(fb, offset, length, text, attrs);
        }
    }
}
