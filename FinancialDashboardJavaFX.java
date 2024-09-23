import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class FinancialDashboardJavaFX extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Main Layout
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(20);
        grid.setVgap(20);

        // Section 1: Overview - Balance, Income, Expenses
        HBox overviewBox = new HBox(20);
        overviewBox.getChildren().addAll(
                createStatBox("Balance", "$1,655", "+12%", "#b2c8df"),
                createStatBox("Income", "$435", "+4%", "#d3b2df"),
                createStatBox("Expenses", "$842", "-2%", "#c7dfb2")
        );
        grid.add(overviewBox, 0, 0, 3, 1); // Adding to GridPane

        // Section 2: Analytics (Placeholder with Bar Chart)
        VBox analyticsBox = new VBox(10);
        Label analyticsTitle = new Label("Analytics");
        analyticsTitle.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        BarChart<String, Number> barChart = createBarChart();
        analyticsBox.getChildren().addAll(analyticsTitle, barChart);
        grid.add(analyticsBox, 3, 0, 2, 1); // Adding to GridPane

        // Section 3: Transactions
        VBox transactionsBox = createTransactionsBox();
        grid.add(transactionsBox, 0, 1); // Adding to GridPane

        // Section 4: Actions (Transfer, Receive buttons)
        VBox actionBox = createActionButtons();
        grid.add(actionBox, 1, 1); // Adding to GridPane

        // Section 5: Stock Portfolio (Placeholder with Line Chart)
        VBox stockPortfolioBox = new VBox(10);
        Label stockTitle = new Label("Stock Portfolio");
        stockTitle.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        LineChart<Number, Number> lineChart = createLineChart();
        stockPortfolioBox.getChildren().addAll(stockTitle, lineChart);
        grid.add(stockPortfolioBox, 2, 1, 2, 1); // Adding to GridPane

        // Section 6: Investments, Finances, Piggy Bank (with placeholder icons)
        VBox investmentsBox = createImageBox("Investments", "#f8d9b2");
        VBox financesBox = createImageBox("Your Finances", "#f8e8b2");
        VBox piggyBankBox = createImageBox("Piggy Bank", "#f2b2b2");
        VBox rightBox = new VBox(20, investmentsBox, financesBox, piggyBankBox);
        grid.add(rightBox, 4, 1); // Adding to GridPane

        // Scene setup
        Scene scene = new Scene(grid, 1000, 600);
        primaryStage.setTitle("Financial Dashboard");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to create statistic boxes (Balance, Income, Expenses)
    private VBox createStatBox(String title, String value, String change, String color) {
        VBox box = new VBox(10);
        box.setPadding(new Insets(10));
        box.setStyle("-fx-background-color: " + color + "; -fx-border-radius: 10; -fx-background-radius: 10;");
        Label titleLabel = new Label(title);
        titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        Label valueLabel = new Label(value);
        valueLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        Label changeLabel = new Label(change);
        box.getChildren().addAll(titleLabel, valueLabel, changeLabel);
        return box;
    }

    // Method to create bar chart for Analytics
    private BarChart<String, Number> createBarChart() {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Month");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Amount");
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>("Jan", 1000));
        series.getData().add(new XYChart.Data<>("Feb", 1200));
        series.getData().add(new XYChart.Data<>("Mar", 900));
        series.getData().add(new XYChart.Data<>("Apr", 1500));
        series.getData().add(new XYChart.Data<>("May", 1753));
        barChart.getData().add(series);

        return barChart;
    }

    // Method to create line chart for Stock Portfolio
    private LineChart<Number, Number> createLineChart() {
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Time");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Value");
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);

        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>(0, 4200));
        series.getData().add(new XYChart.Data<>(1, 4240));
        series.getData().add(new XYChart.Data<>(2, 4300));
        lineChart.getData().add(series);

        return lineChart;
    }

    // Method to create a transaction section
    private VBox createTransactionsBox() {
        VBox transactionsBox = new VBox(10);
        Label transactionTitle = new Label("Transactions");
        transactionTitle.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        transactionsBox.getChildren().addAll(
                transactionTitle,
                new Label("Stoneblack    -$23"),
                new Label("WorldTok      +$45"),
                new Label("Niko          +$30"),
                new Label("Viky          +$23"),
                new Label("GreenCo       -$140")
        );
        return transactionsBox;
    }

    // Method to create action buttons (Transfer, Receive)
    private VBox createActionButtons() {
        VBox actionBox = new VBox(10);
        actionBox.getChildren().addAll(
                new Label("Transfer"),
                new Label("Receive")
        );
        return actionBox;
    }

    // Method to create sections with placeholder images
    private VBox createImageBox(String title, String color) {
        VBox box = new VBox(10);
        box.setPadding(new Insets(10));
        box.setStyle("-fx-background-color: " + color + "; -fx-border-radius: 10; -fx-background-radius: 10;");
        Label titleLabel = new Label(title);
        titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        ImageView icon = new ImageView(new Image("https://firebasestorage.googleapis.com/v0/b/bazaari-finance-ffa31.appspot.com/o/Company_image%2Fbazaari-finnance.png?alt=media&token=16d2bac9-e1eb-492f-9c2c-eb91d4db62c1"));
        icon.setFitWidth(50);
        icon.setFitHeight(50);
        box.getChildren().addAll(titleLabel, icon);
        return box;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
