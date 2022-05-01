package com.example.byte_general;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("LogInView.fxml"));
        StartWindowController controller = new StartWindowController();
        fxmlLoader.setController(controller);
        controller.setStage(stage);


        Scene scene = new Scene(fxmlLoader.load());

        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);


        stage.setTitle("Hello!");
        stage.setScene(scene);
        controller.init();

        stage.show();

//        BaseController startWindowController = new StartWindowController();
//        ViewFactory view = new ViewFactory();
//        view.initWindow("start-window.fxml", startWindowController);
    }

    public static void main(String[] args) {
        launch();
    }
}