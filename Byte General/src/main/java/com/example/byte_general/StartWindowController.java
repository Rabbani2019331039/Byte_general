package com.example.byte_general;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.jfoenix.controls.JFXButton;

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
    private JFXButton signUp;

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

    @FXML
    private TextField signUpUsername;

    @FXML
    private TextField signUpEmail;

    @FXML
    private PasswordField signUpPassword;


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
            viewFactory.showProfileWindow();
            LoggedInUser loggedInUser = LoggedInUser.getInstance();
            loggedInUser.setUsername(username.getText());
            username.clear();
            password.clear();
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

    @FXML
    void signup() {
        Authenticator authenticator = new Authenticator();
        if(authenticator.authenticate(signUpUsername.getText(), signUpPassword.getText())){
            System.out.println("account already exist");
        }
        else{
            authenticator.authenticateNewUser(signUpUsername.getText(), signUpPassword.getText(), signUpEmail.getText());
            LoggedInUser loggedInUser = LoggedInUser.getInstance();
            loggedInUser.setUsername(username.getText());
            viewFactory.showSettingWindow();
            viewFactory.closeWindow(StartWindowController.class);
        }
    }





}
