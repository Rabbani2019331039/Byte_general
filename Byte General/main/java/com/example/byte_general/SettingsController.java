package com.example.byte_general;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import org.kordamp.ikonli.javafx.FontIcon;

public class SettingsController extends BaseController {

    @FXML
    private JFXButton adminButton;

    @FXML
    private JFXButton closeButton;

    @FXML
    private TextField email;

    @FXML
    private JFXButton logoutButton;

    @FXML
    private FontIcon mulATChand;

    @FXML
    private FontIcon mulCFhand;

    @FXML
    private TextField newPassword;

    @FXML
    private TextField oldPassword;

    @FXML
    private JFXButton practiceButton;

    @FXML
    private JFXButton profileButton;

    @FXML
    private ImageView profileImage;

    @FXML
    private ImageView settingsProfileImage;

    @FXML
    private TextField username;

    public SettingsController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }

    public void init() {
        super.init();
        profileImage = ImageUtilities.clipCircleImage(profileImage);
        settingsProfileImage = ImageUtilities.clipCircleImage(settingsProfileImage);
    }

    @FXML
    private void showProfile() {
        viewFactory.closeWindow(SettingsController.class);
        viewFactory.showProfileWindow();
    }

    @FXML
    void showAdmin() {

    }

    @FXML
    void showPractice() {

    }



    @FXML
    private void logout() {

        //problem
        // after logout text fields are not cleared
        viewFactory.closeWindow(SettingsController.class);
        viewFactory.showStartWindow();
    }

    @FXML
    void closeProgram() {
        viewFactory.closeWindow(SettingsController.class);
    }

}
