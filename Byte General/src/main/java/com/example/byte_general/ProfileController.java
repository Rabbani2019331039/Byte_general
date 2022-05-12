package com.example.byte_general;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.image.ImageView;

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
    private ImageView profileImage;

    public ProfileController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }

    public void init() {
        super.init();
        profileImage = ImageUtilities.clipCircleImage(profileImage);
    }


    @FXML
    private void logout() {

        //problem
        // after logout text fields are not cleared
        viewFactory.closeWindow(ProfileController.class);
        viewFactory.showStartWindow();
    }

    @FXML
    void closeProgram() {
        viewFactory.closeWindow(ProfileController.class);
    }
}
