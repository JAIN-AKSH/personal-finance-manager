import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import javax.swing.*;
import java.awt.*;

public class ExpensesPanel extends JPanel {
    public ExpensesPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 255));

        // Create a pie chart for expenses distribution
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Rent", 1200);
        dataset.setValue("Groceries", 600);
        dataset.setValue("Utilities", 300);
        dataset.setValue("Entertainment", 200);

        JFreeChart pieChart = ChartFactory.createPieChart(
                "Expenses Breakdown", dataset, true, true, false);

        ChartPanel chartPanel = new ChartPanel(pieChart);
        add(chartPanel, BorderLayout.CENTER);
    }
}

