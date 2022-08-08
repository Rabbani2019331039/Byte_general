package com.example.byte_general;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.jfoenix.controls.JFXButton;
import javafx.stage.Window;
import org.w3c.dom.events.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class StartWindowController extends BaseController{

    @FXML
    private HBox parent;

    @FXML
    private VBox signInBar;

    @FXML
    private VBox signUpBar;

    @FXML
    private VBox rightBar;

    @FXML
    private PasswordField password;

    @FXML
    private JFXButton signIn;

    @FXML
    private Label signInText;

    @FXML
    private Label signUpText;

    @FXML
    private JFXButton toggleSignIn;

    @FXML
    private JFXButton toggleSignUp;

    @FXML
    private TextField username;


    public StartWindowController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }

    @FXML
    void closeProgram(){
        stage.close();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

//    public void initialize(){
//        Scene scene = this.stage.getScene();
//
//    }

    public boolean isSignIn = true;
    @FXML
    void changeToSignIn() {
        if(!isSignIn) {

            FXMLLoader signInLoader = new FXMLLoader(getClass().getResource("signinComponent.fxml"));
            signInLoader.setController(this);
            rightBar.getChildren().remove(rightBar.getChildren().get(rightBar.getChildren().size() - 1));
            try {
                rightBar.getChildren().add(signInLoader.load());
            } catch (Exception e) {
                e.printStackTrace();
            }

            isSignIn = true;
        }
    }

    @FXML
    void changeToSignUp() {
        if(isSignIn) {
            rightBar.getChildren().remove(rightBar.getChildren().get(rightBar.getChildren().size() - 1));
            try {
                FXMLLoader signUpLoader = new FXMLLoader(getClass().getResource("signupComponent.fxml"));
                signUpLoader.setController(this);
                rightBar.getChildren().add(signUpLoader.load());
            } catch (Exception e) {
                e.printStackTrace();
            }

            isSignIn = false;
        }
    }

    @FXML
    public void login() {
        Authenticator authenticator = new Authenticator();
        if(authenticator.authenticate(username.getText(), password.getText())) {
            username.clear();
            password.clear();
            viewFactory.showProfileWindow();
            viewFactory.closeWindow(StartWindowController.class);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Login failed");
            alert.setContentText("Username or password is incorrect");
            alert.showAndWait();

        }
    }




}
