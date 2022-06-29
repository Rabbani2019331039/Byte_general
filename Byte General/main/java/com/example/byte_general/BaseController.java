package com.example.byte_general;

import javafx.scene.Parent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class BaseController {

    private double x = 0, y = 0;
    public Stage stage;
    public Parent root;
    public ViewFactory viewFactory;
    public String fxmlName;

    public ViewFactory getViewFactory() {
        return viewFactory;
    }

    public String getFxmlName() {
        return fxmlName;
    }

    public BaseController(ViewFactory viewFactory, String fxmlName) {
        this.viewFactory = viewFactory;
        this.fxmlName = fxmlName;
    }

    public void init() {

        this.stage = ViewFactory.windows.get(this.getClass());

        this.root = stage.getScene().getRoot();
        this.root.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });

        this.root.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX() - x);
            stage.setY(mouseEvent.getScreenY() - y);
        });
    }
}
