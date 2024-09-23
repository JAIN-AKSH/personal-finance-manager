import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.json.*;

public class TransactionSystemWithMenu extends JFrame implements ActionListener {

    // Declare UI components for the menu
    JPanel contentPanel;
    CardLayout cardLayout;
    JPanel enterTransactionPanel, updateTransactionPanel, deleteTransactionPanel, showTransactionPanel;

    JButton btnEnterTransaction, btnUpdateTransaction, btnDeleteTransaction, btnShowTransactions;

    // UI elements for transaction fields
    JTextField textTransactionID, textAmount, textDate;
    JTextArea textDescription;

    public TransactionSystemWithMenu() {
        // Set up the frame
        setTitle("Transaction System with Menu");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Call method to create the left menu bar
        createLeftMenuBar();

        // Create the content area that will switch between different panels
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        // Initialize different panels for different menus
        createEnterTransactionPanel();
        createUpdateTransactionPanel();
        createDeleteTransactionPanel();
        createShowTransactionPanel();

        // Add these panels to the contentPanel with CardLayout
        contentPanel.add(enterTransactionPanel, "EnterTransactions");
        contentPanel.add(updateTransactionPanel, "UpdateTransactions");
        contentPanel.add(deleteTransactionPanel, "DeleteTransactions");
        contentPanel.add(showTransactionPanel, "ShowTransactions");

        // Add content panel to the center
        add(contentPanel, BorderLayout.CENTER);
    }

    // Method to create the left menu bar
    private void createLeftMenuBar() {
        // Create a new panel for the menu
        JPanel menuPanel = new JPanel();
        menuPanel.setBackground(new Color(153, 51, 255));  // Purple background color
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));  // Vertical alignment

        // Add menu items (buttons)
        btnEnterTransaction = new JButton("Enter Transactions");
        btnUpdateTransaction = new JButton("Update Transactions");
        btnDeleteTransaction = new JButton("Delete Transactions");
        btnShowTransactions = new JButton("Show Transactions");

        // Set styles for menu buttons
        configureMenuButton(btnEnterTransaction);
        configureMenuButton(btnUpdateTransaction);
        configureMenuButton(btnDeleteTransaction);
        configureMenuButton(btnShowTransactions);

        // Add action listeners for each button
        btnEnterTransaction.addActionListener(this);
        btnUpdateTransaction.addActionListener(this);
        btnDeleteTransaction.addActionListener(this);
        btnShowTransactions.addActionListener(this);

        // Add buttons to the menu panel
        menuPanel.add(btnEnterTransaction);
        menuPanel.add(Box.createVerticalStrut(10));  // Space between buttons
        menuPanel.add(btnUpdateTransaction);
        menuPanel.add(Box.createVerticalStrut(10));
        menuPanel.add(btnDeleteTransaction);
        menuPanel.add(Box.createVerticalStrut(10));
        menuPanel.add(btnShowTransactions);

        // Add the panel to the left side of the frame
        add(menuPanel, BorderLayout.WEST);
    }

    // Helper method to configure the menu buttons
    private void configureMenuButton(JButton button) {
        button.setMaximumSize(new Dimension(200, 40));
        button.setAlignmentX(Component.LEFT_ALIGNMENT);  // Align to left
        button.setForeground(Color.BLACK);  // Black text color
        button.setBackground(Color.WHITE);  // White background for the button
        button.setFocusPainted(false);  // Remove focus paint
        button.setBorderPainted(false);  // No borders
        button.setHorizontalAlignment(SwingConstants.LEFT);  // Align text to the left

        // Add hover effect (optional)
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(230, 230, 230));  // Slightly darker white on hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.WHITE);  // Reset background color
            }
        });
    }

    // Create a panel for Enter Transactions
    private void createEnterTransactionPanel() {
        enterTransactionPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel labelTransactionID = new JLabel("Transaction ID:");
        JLabel labelAmount = new JLabel("Amount:");
        JLabel labelDate = new JLabel("Date (YYYY-MM-DD):");
        JLabel labelDescription = new JLabel("Description:");

        textTransactionID = new JTextField(20);
        textAmount = new JTextField(20);
        textDate = new JTextField(20);
        textDescription = new JTextArea(3, 20);

        JButton submitButton = new JButton("Submit");

        // Add components to the form panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        enterTransactionPanel.add(labelTransactionID, gbc);

        gbc.gridx = 1;
        enterTransactionPanel.add(textTransactionID, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        enterTransactionPanel.add(labelAmount, gbc);

        gbc.gridx = 1;
        enterTransactionPanel.add(textAmount, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        enterTransactionPanel.add(labelDate, gbc);

        gbc.gridx = 1;
        enterTransactionPanel.add(textDate, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        enterTransactionPanel.add(labelDescription, gbc);

        gbc.gridx = 1;
        enterTransactionPanel.add(new JScrollPane(textDescription), gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        enterTransactionPanel.add(submitButton, gbc);

        // Add submit button action listener
        submitButton.addActionListener(e -> insertTransaction());
    }

    // Create a panel for Update Transactions
    private void createUpdateTransactionPanel() {
        updateTransactionPanel = new JPanel();
        updateTransactionPanel.add(new JLabel("Update Transaction Feature - Coming Soon!"));
    }

    // Create a panel for Delete Transactions
    private void createDeleteTransactionPanel() {
        deleteTransactionPanel = new JPanel();
        deleteTransactionPanel.add(new JLabel("Delete Transaction Feature - Coming Soon!"));
    }

    // Create a panel for Show Transactions
    private void createShowTransactionPanel() {
        showTransactionPanel = new JPanel();
        JTextArea textArea = new JTextArea(20, 50);
        JScrollPane scrollPane = new JScrollPane(textArea);
        showTransactionPanel.add(scrollPane);

        // Show transactions using API call
        showTransactions(textArea);
    }

    // Insert transaction using API
    private void insertTransaction() {
        String transactionID = textTransactionID.getText();
        String amount = textAmount.getText();
        String date = textDate.getText();
        String description = textDescription.getText();

        try {
            URL url = new URL("http://localhost:8080/api/transactions");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setDoOutput(true);

            // Create JSON object
            JsonObject json = Json.createObjectBuilder()
                    .add("transaction_id", transactionID)
                    .add("amount", Double.parseDouble(amount))
                    .add("date", date)
                    .add("description", description)
                    .build();

            // Send JSON payload
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = json.toString().getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Read response
            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                JOptionPane.showMessageDialog(this, "Transaction Added: " + response.toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Show transactions using GET API
    private void showTransactions(JTextArea textArea) {
        try {
            URL url = new URL("http://localhost:8080/api/transactions");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }

                // Display transactions
                textArea.setText(response.toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle menu button clicks and switch between panels
        if (e.getSource() == btnEnterTransaction) {
            cardLayout.show(contentPanel, "EnterTransactions");
        } else if (e.getSource() == btnUpdateTransaction) {
            cardLayout.show(contentPanel, "UpdateTransactions");
        } else if (e.getSource() == btnDeleteTransaction) {
            cardLayout.show(contentPanel, "DeleteTransactions");
        } else if (e.getSource() == btnShowTransactions) {
            cardLayout.show(contentPanel, "ShowTransactions");
        }
    }

    public static void main(String[] args) {
        // Create the form and display it
        TransactionSystemWithMenu form = new TransactionSystemWithMenu();
        form.setVisible(true);
    }
}
