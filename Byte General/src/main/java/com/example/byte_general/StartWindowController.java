package com.example.byte_general;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.w3c.dom.events.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class StartWindowController extends BaseController{


    @FXML
    private HBox topbar;

    @FXML
    void closeProgram(){
        stage.close();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//
//        topbar.setOnMousePressed(mouseEvent -> {
//            x = mouseEvent.getSceneX();
//            y = mouseEvent.getSceneY();
//        });
//
//        topbar.setOnMouseDragged(mouseEvent -> {
//            stage.setX(mouseEvent.getScreenX() - x);
//            stage.setY(mouseEvent.getScreenY() - y);
//        });
//    }
}
