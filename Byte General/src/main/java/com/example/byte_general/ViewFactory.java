package com.example.byte_general;

import javafx.fxml.FXMLLoader;

public class ViewFactory {
    public void initWindow(String fxmlTitle, BaseController controller){
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxmlTitle));
        fxmlLoader.setController(controller);
//        controller.setStage(stage);
//
//
//        Scene scene = new Scene(fxmlLoader.load());
//
//        scene.setFill(Color.TRANSPARENT);
//        stage.initStyle(StageStyle.TRANSPARENT);
//
//
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        controller.init();
//
//        stage.show();
    }
}
