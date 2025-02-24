package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class NewCellRenderer extends DefaultTableCellRenderer {
    public NewCellRenderer() {
        setHorizontalAlignment(JLabel.CENTER);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (value != null) {
            char blockSymbol = value.toString().charAt(0);
            Color color = getColorForBlock(blockSymbol);
            cell.setBackground(color);
            cell.setForeground(Color.BLACK);
        } else {
            cell.setBackground(Color.WHITE);
            cell.setForeground(Color.BLACK);
        }

        return cell;
    }

    private Color getColorForBlock(char blockSymbol) {
        return switch (blockSymbol) {
            case 'A' -> Color.RED;
            case 'B' -> Color.BLUE;
            case 'C' -> Color.GREEN;
            case 'D' -> Color.YELLOW;
            case 'E' -> Color.ORANGE;
            case 'F' -> Color.PINK;
            case 'G' -> Color.CYAN;
            case 'H' -> Color.MAGENTA;
            case 'I' -> new Color(128, 0, 128);
            case 'J' -> new Color(0, 128, 128);
            case 'K' -> new Color(128, 128, 0);
            case 'L' -> new Color(128, 128, 128);
            case 'M' -> new Color(255, 0, 0);
            case 'N' -> new Color(0, 255, 0);
            case 'O' -> new Color(0, 0, 255);
            case 'P' -> new Color(255, 255, 0);
            case 'Q' -> new Color(255, 0, 255);
            case 'R' -> new Color(0, 255, 255);
            case 'S' -> new Color(128, 0, 0);
            case 'T' -> new Color(0, 128, 0);
            case 'U' -> new Color(0, 0, 128);
            case 'V' -> new Color(128, 128, 0);
            case 'W' -> new Color(128, 0, 128);
            case 'X' -> new Color(0, 128, 128);
            case 'Y' -> new Color(192, 192, 192);
            case 'Z' -> new Color(64, 64, 64);
            default -> Color.WHITE;
        };
    }
}