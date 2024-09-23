import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginPage extends JFrame {

    private Connection conn;  // Database connection object

    public LoginPage() {
        // Call method to connect to database
        connectToDatabase();

        // Frame settings
        setTitle("Spendly Login");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel with box layout (vertical)
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(17, 20, 24));  // Dark background like your HTML
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Title section
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(17, 20, 24));
        JLabel titleLabel = new JLabel("Welcome back to Spendly");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Inter", Font.BOLD, 24));
        titlePanel.add(titleLabel);

        // Subtitle
        JLabel subtitleLabel = new JLabel("Sign in to your account using your email and password.");
        subtitleLabel.setForeground(Color.WHITE);
        subtitleLabel.setFont(new Font("Inter", Font.PLAIN, 14));
        titlePanel.add(subtitleLabel);

        mainPanel.add(titlePanel);

        // Email field
        JPanel emailPanel = new JPanel();
        emailPanel.setBackground(new Color(17, 20, 24));
        emailPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setForeground(Color.WHITE);
        JTextField emailField = new JTextField(20);
        emailPanel.add(emailLabel);
        emailPanel.add(emailField);

        // Password field
        JPanel passwordPanel = new JPanel();
        passwordPanel.setBackground(new Color(17, 20, 24));
        passwordPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.WHITE);
        JPasswordField passwordField = new JPasswordField(20);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);

        // Remember me checkbox
        JPanel checkboxPanel = new JPanel();
        checkboxPanel.setBackground(new Color(17, 20, 24));
        JCheckBox rememberMe = new JCheckBox("Remember me");
        rememberMe.setForeground(Color.WHITE);
        rememberMe.setBackground(new Color(17, 20, 24));
        checkboxPanel.add(rememberMe);

        // Sign in button
        JButton signInButton = new JButton("Sign in");
        signInButton.setBackground(new Color(25, 128, 230));
        signInButton.setForeground(Color.WHITE);
        signInButton.setFocusPainted(false);
        signInButton.setPreferredSize(new Dimension(100, 30));

        // Button action listener
        signInButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText().trim();  // Trim spaces
                String password = new String(passwordField.getPassword()).trim();  // Trim spaces

                // Call method to validate login credentials
                if (validateLogin(email, password)) {
                    JOptionPane.showMessageDialog(null, "Login successful!");
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid login. Try again.");
                }
            }
        });

        // Add elements to main panel
        mainPanel.add(emailPanel);
        mainPanel.add(passwordPanel);
        mainPanel.add(checkboxPanel);
        mainPanel.add(signInButton);

        // Add main panel to frame
        add(mainPanel);
    }

    // Method to connect to PostgreSQL database
    private void connectToDatabase() {
        try {
            // Connection details
            String url = "jdbc:postgresql://localhost:5432/login_system";  // Replace with your DB name
            String user = "postgres";  // Your PostgreSQL username
            String password = "aksh";  // Your PostgreSQL password

            // Establish the connection
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the database");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to validate login using the database
    private boolean validateLogin(String email, String password) {
        String query = "SELECT * FROM users WHERE email = ? AND password = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, email);
            pstmt.setString(2, password);

            System.out.println("Attempting login with Email: [" + email + "] and Password: [" + password + "]");  // Debugging

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                System.out.println("Login successful!");  // Debugging
                return true;  // User found, login is successful
            } else {
                System.out.println("No matching user found");  // Debugging
                return false;  // User not found, login failed
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LoginPage().setVisible(true);
            }
        });
    }
}
