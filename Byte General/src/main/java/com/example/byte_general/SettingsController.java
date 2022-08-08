package com.example.byte_general;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
    private VBox profileSettingsLeftbar;

    @FXML
    private VBox handlesDrawerCF;

    @FXML
    private VBox handlesDrawerATC;

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

    int cfIndex = 6;
    int cfHandleCount = 0;
    int atcIndex = 7;
    int atcHandleCount = 0;

    ArrayList<VBox> handlesCF = new ArrayList<VBox>();
    @FXML
    void giveOptionCF() {

        try{
            HandlesController controller = new HandlesController();
            FXMLLoader multipleCFComponent = new FXMLLoader(getClass().getResource("handleOptionsComponent.fxml"));
            multipleCFComponent.setController(controller);
            controller.setParent(handlesDrawerCF);

//            handlesCF.add(multipleCFComponent.load());

//            handlesCF.get(handlesCF.size()-1).getChildren().get(0);
            Node comp = multipleCFComponent.load();
            controller.setMe(comp);
            handlesDrawerCF.getChildren().add(comp);
            cfHandleCount++;
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void giveOptionATC(){
        try{
            HandlesController controller = new HandlesController();
            FXMLLoader multipleCFComponent = new FXMLLoader(getClass().getResource("handleOptionsComponent.fxml"));
            multipleCFComponent.setController(controller);
            controller.setParent(handlesDrawerATC);

            Node comp = multipleCFComponent.load();
            controller.setMe(comp);

            handlesDrawerATC.getChildren().add(comp);
            atcHandleCount++;

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void removeHandle(){
//        handlesDrawerCF.getChildren().remove();
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

    public void UploadImageActionPerformed() {
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG
                = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
        FileChooser.ExtensionFilter extFilterjpg
                = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extFilterPNG
                = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
        FileChooser.ExtensionFilter extFilterpng
                = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        FileChooser.ExtensionFilter extensionFilterAll = new FileChooser.ExtensionFilter("All Files", "*.*");
        fileChooser.getExtensionFilters()
                .addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng,extensionFilterAll);
        //Show open file dialog
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            byte[] data_image = null;

            try {
                FileInputStream fin = new FileInputStream(file);
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                byte[] buf = new byte[1024];
                for (int readNum; (readNum = fin.read(buf)) != -1; ) {
                    bos.write(buf, 0, readNum);
                }
                data_image = bos.toByteArray();
                String fileName = file.getName();
                String extension = file.getName().substring(file.getName().lastIndexOf(".") + 1);
                System.out.println(extension);

                settingsProfileImage.setImage(new Image(new ByteArrayInputStream(data_image)));
                settingsProfileImage = ImageUtilities.clipCircleImage(settingsProfileImage);
                profileImage.setImage(new Image(new ByteArrayInputStream(data_image)));
                profileImage = ImageUtilities.clipCircleImage(profileImage);

                fin.close();
                bos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }

}
