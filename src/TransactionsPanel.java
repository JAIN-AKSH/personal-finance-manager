import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TransactionsPanel extends JPanel {
    public TransactionsPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 255));

        // Create a table for displaying transactions
        String[] columnNames = {"Date", "Description", "Amount", "Type"};
        Object[][] data = {
                {"2023-09-01", "Salary", "$5,000", "Income"},
                {"2023-09-03", "Groceries", "$150", "Expense"},
                {"2023-09-10", "Utility Bill", "$100", "Expense"}
        };
        JTable table = new JTable(new DefaultTableModel(data, columnNames));
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }
}

