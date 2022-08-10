package com.example.byte_general;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class ProfileController extends BaseController{
    @FXML
    private JFXButton adminButton;

    @FXML
    private JFXButton allOjButton;

    @FXML
    private JFXButton atcOjButton;

    @FXML
    private JFXButton cfOjButton;

    @FXML
    private JFXButton closeButton;

    @FXML
    private JFXButton logoutButton;

    @FXML
    private JFXButton practiceButton;

    @FXML
    private JFXButton profileButton;

    @FXML
    private LineChart<?, ?> ratingChart;

    @FXML
    private JFXButton settingsButton;

    @FXML
    private PieChart solvedProblemTags;

    @FXML
    private PieChart verdictPieChart;

    @FXML
    private ImageView profileImage;

    @FXML
    private VBox pieChartContainer;

    public ProfileController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }

    public void init() {
        super.init();
        initLineChart();
        initTagsPieChart();
        initVerdictPieChart();
        profileImage = ImageUtilities.clipCircleImage(profileImage);
    }


    private void initLineChart(){
        XYChart.Series series = new XYChart.Series();
        series.getData().add(new XYChart.Data("jan", 1200));
        series.getData().add(new XYChart.Data("feb", 1230));
        series.getData().add(new XYChart.Data("mar", 1212));
        series.getData().add(new XYChart.Data("apr", 1240));
        series.getData().add(new XYChart.Data("may", 1300));
        series.getData().add(new XYChart.Data("jun", 1315));
        ratingChart.getData().addAll(series);
//        ratingChart.setHorizontalGridLinesVisible(false);
        ratingChart.setVerticalGridLinesVisible(false);
        ratingChart.setLegendVisible(false);
        ratingChart.setAlternativeRowFillVisible(false);
    }

    private void initTagsPieChart(){
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Binary Search", 10),
                new PieChart.Data("Implementation", 30),
                new PieChart.Data("DP", 10),
                new PieChart.Data("Adhoc", 15),
                new PieChart.Data("Math", 30),
                new PieChart.Data("Number Theory", 20),
                new PieChart.Data("Graph", 20),
                new PieChart.Data("Greedy", 10),
                new PieChart.Data("String", 20)
        );
        solvedProblemTags.setData(pieChartData);
    }

    private void initVerdictPieChart(){
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("AC", 100),
                new PieChart.Data("WA", 30),
                new PieChart.Data("TLE", 10),
                new PieChart.Data("Others", 15)
        );
        verdictPieChart.setData(pieChartData);
    }



    @FXML
    private void logout() {

        //problem
        // after logout text fields are not cleared
        viewFactory.closeWindow(ProfileController.class);
        viewFactory.showStartWindow();
    }

    @FXML
    private void showSettings(){
        viewFactory.closeWindow(ProfileController.class);
        viewFactory.showSettingWindow();
    }

    @FXML
    void showAdmin() {
        viewFactory.closeWindow(ProfileController.class);
        viewFactory.showAdminWindow();
    }

    @FXML
    void showPractice() {

    }

    @FXML
    void closeProgram() {
        viewFactory.closeWindow(ProfileController.class);
    }
}
