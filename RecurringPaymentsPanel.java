import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class RecurringPaymentsPanel extends JPanel {
    public RecurringPaymentsPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 255));

        // Create a table for recurring payments
        String[] columnNames = {"Payment Name", "Amount", "Frequency", "Next Payment Date"};
        Object[][] data = {
                {"Rent", "$1,200", "Monthly", "2023-09-30"},
                {"Netflix", "$15", "Monthly", "2023-09-20"}
        };
        JTable table = new JTable(new DefaultTableModel(data, columnNames));
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }
}

