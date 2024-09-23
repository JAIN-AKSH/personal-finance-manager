import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FinancialManagementSystem extends JFrame {

    private JPanel navigationPanel;
    private JPanel mainPanel;
    private CardLayout cardLayout;

    public FinancialManagementSystem() {
        setTitle("Financial Management System");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        setLayout(new BorderLayout());

        // Color and Font customization
        Color navBgColor = new Color(102, 51, 153);  // Dark purple for navigation background
        Color mainBgColor = new Color(240, 240, 255); // Very light purple/grey for main panel
        Color buttonColor = new Color(230, 230, 250); // Lavender for button background
        Color buttonHoverColor = new Color(153, 102, 204); // Lighter purple for hover effect
        Font buttonFont = new Font("Arial", Font.BOLD, 16);

        // Initialize Navigation Panel
        navigationPanel = new JPanel();
        navigationPanel.setLayout(new GridLayout(7, 1, 10, 10)); // Add space between buttons
        navigationPanel.setBackground(navBgColor);
        navigationPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding around the panel

        // Add navigation buttons
        String[] menuOptions = {"Dashboard", "Accounts", "Income", "Expenses", "Transactions", "Recurring Payments", "Goals"};
        for (String option : menuOptions) {
            JButton button = new JButton(option);
            button.setFocusPainted(false); // Removes focus outline
            button.setFont(buttonFont);
            button.setForeground(Color.BLACK); // Set button text to black
            button.setBackground(buttonColor);
            button.setOpaque(true);
            button.setBorderPainted(false);
            button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            button.addActionListener(new NavigationListener());
            button.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    button.setBackground(buttonHoverColor); // Hover effect
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    button.setBackground(buttonColor); // Default color
                }
            });
            navigationPanel.add(button);
        }

        // Initialize Main Panel with CardLayout
        mainPanel = new JPanel();
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);
        mainPanel.setBackground(mainBgColor);

        // Add different panels for each section
        mainPanel.add(createSectionPanel("Dashboard View", mainBgColor), "Dashboard");
        mainPanel.add(createSectionPanel("Accounts View", mainBgColor), "Accounts");
        mainPanel.add(createSectionPanel("Income View", mainBgColor), "Income");
        mainPanel.add(createSectionPanel("Expenses View", mainBgColor), "Expenses");
        mainPanel.add(createSectionPanel("Transfer View", mainBgColor), "Transactions");
        mainPanel.add(createSectionPanel("Recurring Payments View", mainBgColor), "Recurring Payments");
        mainPanel.add(createSectionPanel("Goals View", mainBgColor), "Goals");
// Add these panels to your mainPanel
        mainPanel.add(new DashboardPanel(), "Dashboard");
        mainPanel.add(new AccountsPanel(), "Accounts");
        mainPanel.add(new IncomePanel(), "Income");
        mainPanel.add(new ExpensesPanel(), "Expenses");
        mainPanel.add(new TransactionsPanel(), "Transactions");
        mainPanel.add(new RecurringPaymentsPanel(), "Recurring Payments");
        mainPanel.add(new GoalsPanel(), "Goals");

        // Add Panels to Frame
        add(navigationPanel, BorderLayout.WEST);
        add(mainPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    // Method to create a panel for each section with styling
    private JPanel createSectionPanel(String labelText, Color bgColor) {
        JPanel panel = new JPanel();
        panel.setBackground(bgColor);
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        label.setForeground(Color.BLACK); // Set section title text to black
        panel.add(label);
        return panel;
    }

    // ActionListener to switch between different views
    private class NavigationListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            String command = button.getText();
            cardLayout.show(mainPanel, command);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FinancialManagementSystem());
    }
}
