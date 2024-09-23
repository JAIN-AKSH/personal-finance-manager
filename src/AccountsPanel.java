import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AccountsPanel extends JPanel {
    public AccountsPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 255));

        // Create a table for displaying account details
        String[] columnNames = {"Account", "Balance"};
        Object[][] data = {
                {"Checking", "$2,500"},
                {"Savings", "$5,000"},
                {"Credit Card", "$1,000"}
        };
        JTable table = new JTable(new DefaultTableModel(data, columnNames));
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }
}
