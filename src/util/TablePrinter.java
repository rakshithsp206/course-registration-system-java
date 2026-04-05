package util;
import java.sql.*;
import java.util.*;

public class TablePrinter {

    public static void printResultSet(ResultSet rs) throws SQLException {

        ResultSetMetaData meta = rs.getMetaData();
        int cols = meta.getColumnCount();

        // Store column widths
        int[] colWidth = new int[cols];

        // Initialize with column names
        for (int i = 0; i < cols; i++) {
            colWidth[i] = meta.getColumnName(i + 1).length();
        }

        List<String[]> rows = new ArrayList<>();

        // Read all rows
        while (rs.next()) {
            String[] row = new String[cols];

            for (int i = 0; i < cols; i++) {
                String value = String.valueOf(rs.getObject(i + 1));
                row[i] = value;
                colWidth[i] = Math.max(colWidth[i], value.length());
            }
            rows.add(row);
        }

        // Print top border
        printBorder(colWidth);

        // Print header
        System.out.print("| ");
        for (int i = 0; i < cols; i++) {
            System.out.printf("%-" + colWidth[i] + "s | ", meta.getColumnName(i + 1));
        }
        System.out.println();

        // Print separator
        printBorder(colWidth);

        // Print rows
        for (String[] row : rows) {
            System.out.print("| ");
            for (int i = 0; i < cols; i++) {
                System.out.printf("%-" + colWidth[i] + "s | ", row[i]);
            }
            System.out.println();
        }

        // Print bottom border
        printBorder(colWidth);
    }

    private static void printBorder(int[] colWidth) {
        System.out.print("+");
        for (int width : colWidth) {
            System.out.print("-".repeat(width + 2) + "+");
        }
        System.out.println();
    }
}