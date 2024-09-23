import javax.swing.*;
import java.awt.*;

public class GoalsPanel extends JPanel {

    public GoalsPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 255));

        // Add title for the goals section
        JLabel goalsTitle = new JLabel("Financial Goals");
        goalsTitle.setFont(new Font("Arial", Font.BOLD, 24));
        goalsTitle.setHorizontalAlignment(SwingConstants.CENTER);
        goalsTitle.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(goalsTitle, BorderLayout.NORTH);

        // Create containers for short-term and long-term goals
        JPanel goalContainer = new JPanel(new GridLayout(2, 1, 20, 20));
        goalContainer.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        goalContainer.setBackground(new Color(240, 240, 255));

        goalContainer.add(createGoalCategory("Short-Term Goals", new String[]{"Vacation Fund", "New Laptop"}, new int[]{70, 40}));
        goalContainer.add(createGoalCategory("Long-Term Goals", new String[]{"House Downpayment", "Retirement Fund"}, new int[]{50, 30}));

        add(goalContainer, BorderLayout.CENTER);
    }

    // Method to create a category of goals
    private JPanel createGoalCategory(String categoryTitle, String[] goalNames, int[] progressValues) {
        JPanel categoryPanel = new JPanel(new BorderLayout());
        categoryPanel.setBackground(new Color(240, 240, 255));

        JLabel categoryLabel = new JLabel(categoryTitle);
        categoryLabel.setFont(new Font("Arial", Font.BOLD, 20));
        categoryLabel.setForeground(Color.BLACK);
        categoryPanel.add(categoryLabel, BorderLayout.NORTH);

        JPanel goalsPanel = new JPanel(new GridLayout(goalNames.length, 1, 10, 10));
        goalsPanel.setBackground(new Color(240, 240, 255));

        for (int i = 0; i < goalNames.length; i++) {
            goalsPanel.add(createGoalPanel(goalNames[i], progressValues[i]));
        }

        categoryPanel.add(goalsPanel, BorderLayout.CENTER);

        return categoryPanel;
    }

    // Helper method to create individual goal panels with progress bars
    private JPanel createGoalPanel(String goalName, int progress) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(240, 240, 255));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel goalLabel = new JLabel(goalName);
        goalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        goalLabel.setForeground(new Color(0, 0, 102));

        JProgressBar progressBar = new JProgressBar(0, 100);
        progressBar.setValue(progress);
        progressBar.setString(progress + "%");
        progressBar.setStringPainted(true);
        progressBar.setForeground(new Color(0, 153, 76));  // Green for progress

        panel.add(goalLabel, BorderLayout.NORTH);
        panel.add(progressBar, BorderLayout.CENTER);

        return panel;
    }
}
