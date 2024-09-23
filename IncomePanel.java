import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.*;
import java.awt.*;

public class IncomePanel extends JPanel {
    public IncomePanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 255));

        // Create a bar chart for income over months
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(5000, "Income", "January");
        dataset.addValue(5500, "Income", "February");
        dataset.addValue(6000, "Income", "March");

        JFreeChart barChart = ChartFactory.createBarChart(
                "Monthly Income", "Month", "Amount", dataset);

        ChartPanel chartPanel = new ChartPanel(barChart);
        add(chartPanel, BorderLayout.CENTER);
    }
}
