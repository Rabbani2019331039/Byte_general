package com.example.byte_general;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.HashMap;
import java.util.Map;

public class ViewFactory {
    public static Map<Class<?>, Stage> windows = new HashMap<>();



    private boolean isWindowOpenedBefore(Class<?> controllerClass){
        if(windows.containsKey(controllerClass)){
//            windows.get(controllerClass).show();
            return true;
        }
        return false;
    }

    //initialize the window
    private void initWindow(String fxmlTitle, BaseController controller){
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxmlTitle));
        fxmlLoader.setController(controller);

        Parent parent;
        try{
            parent = fxmlLoader.load();
        }
        catch(Exception e){
            e.printStackTrace();
            return;
        }

        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle(fxmlTitle);
        stage.setWidth(900);
        stage.setHeight(600);
        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);

        stage.setOnCloseRequest(event -> {
            windows.remove(controller.getClass());
        });
        windows.put(controller.getClass(), stage);
        controller.init();
        stage.show();
    }


    public void showStartWindow(){
        if(!isWindowOpenedBefore(StartWindowController.class)){
            StartWindowController controller = new StartWindowController(this, "LoginView.fxml");
            initWindow("LoginView.fxml", controller);
        }
        else{
            windows.get(StartWindowController.class).show();
        }
    }

    public void showProfileWindow(){
        if(!isWindowOpenedBefore(ProfileController.class)){
            ProfileController controller = new ProfileController(this, "profileView.fxml");
            initWindow("profileView.fxml", controller);
        }
        else{
            windows.get(ProfileController.class).show();
        }
    }

    public void showSettingWindow(){
        if(!isWindowOpenedBefore(SettingsController.class)){
            SettingsController controller = new SettingsController(this, "settingsView.fxml");
            initWindow("settingsView.fxml", controller);
        }
        else{
            windows.get(SettingsController.class).show();
        }
    }


    public void closeWindow(Class<?> controllerClass){
        windows.get(controllerClass).close();
    }

}
