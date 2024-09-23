import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;

public class DashboardPanel extends JPanel {

    public DashboardPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 255));

        // Create a container for the entire dashboard
        JPanel dashboardContainer = new JPanel(new GridLayout(2, 1, 10, 10));
        dashboardContainer.setBackground(new Color(240, 240, 255));

        // Add stat cards (Income, Expenses, Savings) at the top
        dashboardContainer.add(createStatCards());

        // Create a chart panel container for visual insights
        JPanel chartContainer = new JPanel(new GridLayout(1, 2, 10, 10));
        chartContainer.setBackground(new Color(240, 240, 255));

        // Add a line chart and a pie chart side by side
        chartContainer.add(createLineChartPanel());
        chartContainer.add(createPieChartPanel());

        dashboardContainer.add(chartContainer);

        // Add dashboard container to the panel
        add(dashboardContainer, BorderLayout.CENTER);
    }

    // Method to create stat cards for financial summary
    private JPanel createStatCards() {
        JPanel statPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        statPanel.setBackground(new Color(240, 240, 255));

        // Example stats: Total Income, Total Expenses, Savings
        statPanel.add(createStatCard("Total Income", "$12,000", new Color(0, 153, 0)));
        statPanel.add(createStatCard("Total Expenses", "$8,500", new Color(204, 0, 0)));
        statPanel.add(createStatCard("Net Savings", "$3,500", new Color(0, 102, 204)));

        return statPanel;
    }

    // Helper method to create individual stat cards
    private JPanel createStatCard(String title, String value, Color backgroundColor) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBackground(backgroundColor);
        card.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel valueLabel = new JLabel(value);
        valueLabel.setForeground(Color.WHITE);
        valueLabel.setFont(new Font("Arial", Font.BOLD, 24));

        card.add(titleLabel, BorderLayout.NORTH);
        card.add(valueLabel, BorderLayout.CENTER);

        return card;
    }

    // Method to create a line chart for financial trends
    private ChartPanel createLineChartPanel() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(5000, "Income", "January");
        dataset.addValue(5500, "Income", "February");
        dataset.addValue(6000, "Income", "March");

        dataset.addValue(4000, "Expenses", "January");
        dataset.addValue(4200, "Expenses", "February");
        dataset.addValue(4300, "Expenses", "March");

        JFreeChart lineChart = ChartFactory.createLineChart(
                "Financial Trend", "Month", "Amount", dataset);

        return new ChartPanel(lineChart);
    }

    // Method to create a pie chart for expenses distribution
    private ChartPanel createPieChartPanel() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Rent", 1200);
        dataset.setValue("Groceries", 600);
        dataset.setValue("Utilities", 300);
        dataset.setValue("Entertainment", 200);

        JFreeChart pieChart = ChartFactory.createPieChart(
                "Expense Distribution", dataset, true, true, false);

        return new ChartPanel(pieChart);
    }
}
