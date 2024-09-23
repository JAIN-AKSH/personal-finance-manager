import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {

    public Dashboard() {
        // Setting up the main frame
        setTitle("Financial Dashboard");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Creating the main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Overview Section
        JPanel overviewPanel = createOverviewSection();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 10, 10);
        mainPanel.add(overviewPanel, gbc);

        // Analytics Section
        JPanel analyticsPanel = createAnalyticsSection();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        mainPanel.add(analyticsPanel, gbc);

        // Transactions Section
        JPanel transactionsPanel = createTransactionsSection();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        mainPanel.add(transactionsPanel, gbc);

        // Action Section
        JPanel actionPanel = createActionSection();
        gbc.gridx = 1;
        gbc.gridy = 1;
        mainPanel.add(actionPanel, gbc);

        // Stock Portfolio Section
        JPanel stockPanel = createStockSection();
        gbc.gridx = 2;
        gbc.gridy = 1;
        mainPanel.add(stockPanel, gbc);

        // Adding the main panel to the frame
        add(mainPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    private JPanel createOverviewSection() {
        JPanel overviewPanel = new JPanel();
        overviewPanel.setLayout(new GridLayout(1, 3, 10, 10));

        // Create individual cards for Balance, Income, Expenses
        JPanel balanceCard = createCard("Balance", "$1,655", "+12%", "balance_icon.png");
        JPanel incomeCard = createCard("Income", "$435", "+4%", "income_icon.png");
        JPanel expensesCard = createCard("Expenses", "$842", "-2%", "expenses_icon.png");

        overviewPanel.add(balanceCard);
        overviewPanel.add(incomeCard);
        overviewPanel.add(expensesCard);

        return overviewPanel;
    }

    private JPanel createAnalyticsSection() {
        JPanel analyticsPanel = new JPanel();
        analyticsPanel.setLayout(new BorderLayout());

        JLabel analyticsTitle = new JLabel("Analytics");
        analyticsTitle.setFont(new Font("Arial", Font.BOLD, 18));
        analyticsPanel.add(analyticsTitle, BorderLayout.NORTH);

        // Bar chart using JFreeChart
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(500, "Income", "Jan");
        dataset.addValue(1000, "Income", "Feb");
        dataset.addValue(1500, "Income", "Mar");
        dataset.addValue(1753, "Income", "May");
        JFreeChart barChart = ChartFactory.createBarChart("", "Months", "Amount", dataset, PlotOrientation.VERTICAL, false, true, false);
        ChartPanel chartPanel = new ChartPanel(barChart);
        analyticsPanel.add(chartPanel, BorderLayout.CENTER);

        // Add buttons for week, month, etc.
        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new GridLayout(1, 4));
        filterPanel.add(new JButton("Week"));
        filterPanel.add(new JButton("Month"));
        filterPanel.add(new JButton("6 months"));
        filterPanel.add(new JButton("Year"));

        analyticsPanel.add(filterPanel, BorderLayout.SOUTH);

        return analyticsPanel;
    }

    private JPanel createTransactionsSection() {
        JPanel transactionsPanel = new JPanel();
        transactionsPanel.setLayout(new BorderLayout());

        JLabel transactionsTitle = new JLabel("Transactions");
        transactionsTitle.setFont(new Font("Arial", Font.BOLD, 18));
        transactionsPanel.add(transactionsTitle, BorderLayout.NORTH);

        // Transaction items list
        DefaultListModel<String> transactionList = new DefaultListModel<>();
        transactionList.addElement("Stoneblack -$23");
        transactionList.addElement("WorldTok +$45");
        transactionList.addElement("Niko +$30");
        transactionList.addElement("Viky +$23");
        transactionList.addElement("GreenCo -$140");

        JList<String> transactionJList = new JList<>(transactionList);
        transactionsPanel.add(new JScrollPane(transactionJList), BorderLayout.CENTER);

        return transactionsPanel;
    }

    private JPanel createActionSection() {
        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new GridLayout(2, 1, 10, 10));

        JButton transferButton = new JButton("Transfer");
        JButton receiveButton = new JButton("Receive");

        actionPanel.add(transferButton);
        actionPanel.add(receiveButton);

        return actionPanel;
    }

    private JPanel createStockSection() {
        JPanel stockPanel = new JPanel();
        stockPanel.setLayout(new BorderLayout());

        JLabel stockTitle = new JLabel("Stock Portfolio");
        stockTitle.setFont(new Font("Arial", Font.BOLD, 18));
        stockPanel.add(stockTitle, BorderLayout.NORTH);

        // Line chart using JFreeChart
        XYSeries series = new XYSeries("Stock Prices");
        series.add(1, 4240);
        series.add(2, 4310);
        series.add(3, 4420);
        series.add(4, 4462);
        XYSeriesCollection dataset = new XYSeriesCollection(series);
        JFreeChart lineChart = ChartFactory.createXYLineChart("", "Time", "Price", dataset, PlotOrientation.VERTICAL, true, true, false);
        ChartPanel lineChartPanel = new ChartPanel(lineChart);

        stockPanel.add(lineChartPanel, BorderLayout.CENTER);

        return stockPanel;
    }

    // Helper method to create a card-like panel with icon and values
    private JPanel createCard(String title, String value, String change, String iconPath) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        card.setBackground(new Color(245, 245, 245));

        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel valueLabel = new JLabel(value, SwingConstants.CENTER);
        valueLabel.setFont(new Font("Arial", Font.PLAIN, 24));

        JLabel changeLabel = new JLabel(change, SwingConstants.CENTER);
        changeLabel.setFont(new Font("Arial", Font.ITALIC, 14));

        // Adding the icon
        ImageIcon icon = new ImageIcon(iconPath);
        JLabel iconLabel = new JLabel(icon, SwingConstants.CENTER);

        card.add(iconLabel, BorderLayout.NORTH);
        card.add(valueLabel, BorderLayout.CENTER);
        card.add(changeLabel, BorderLayout.SOUTH);

        return card;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Dashboard());
    }
}

