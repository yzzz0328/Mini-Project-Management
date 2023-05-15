package View;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

// Lam Ting Le
public class LineWrapCellRenderer extends JTextArea implements TableCellRenderer {
    int rowHeight = 0;  // current max row height for this scan

    // Lam Ting Le
    // Method to allow change of row and width dynamically with line wrap
    @Override
    public Component getTableCellRendererComponent(
            JTable table,
            Object value,
            boolean isSelected,
            boolean hasFocus,
            int row,
            int column) {
        setText((String) value);
        setWrapStyleWord(true);
        setLineWrap(true);

        // current table column width in pixels
        int colWidth = table.getColumnModel().getColumn(column).getWidth();

        // set the text area width (height doesn't matter here)
        setSize(new Dimension(colWidth, 1));

        // get the text area preferred height and add the row margin
        int height = getPreferredSize().height + table.getRowMargin();

        // ensure the row height fits the cell with most lines
        if (column == 0 || height > rowHeight) {
            table.setRowHeight(row, height);
            rowHeight = height;
        }
        return this;
    }
}
