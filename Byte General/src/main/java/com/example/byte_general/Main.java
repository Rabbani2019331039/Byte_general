package com.example.byte_general;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {

    public static ViewFactory viewFactory;
    @Override
    public void start(Stage stage) throws IOException {

        Main.viewFactory = new ViewFactory();
        Main.viewFactory.showStartWindow();

//        Main.viewFactory.showProfileWindow();
    }

    public static void main(String[] args) {
        launch();
    }
}