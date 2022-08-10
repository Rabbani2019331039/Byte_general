package com.example.byte_general;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import org.kordamp.ikonli.javafx.FontIcon;
import org.w3c.dom.Text;

import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    private String checkPassword;


    private DBHandler dbHandler= new DBHandler();
    public SettingsController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }

    public void init() {
        super.init();
        LoggedInUser loggedInUser = LoggedInUser.getInstance();
        setInfo(loggedInUser.getUsername());
        profileImage = ImageUtilities.clipCircleImage(profileImage);
        settingsProfileImage = ImageUtilities.clipCircleImage(settingsProfileImage);

    }

    private void setInfo(String username){
        String query  = "SELECT * FROM users WHERE username = ?";

        try{
            PreparedStatement ps = dbHandler.getConnection().prepareStatement(query);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                setUsername(rs.getString("username"));
                setEmail(rs.getString("email"));
                checkPassword = rs.getString("password");
                cfhandles.add(rs.getString("CFHandle1"));
                cfhandles.add(rs.getString("CFHandle2"));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

        setOptionCF();
    }

    public void setEmail(String emailText) {
        this.email.setText(emailText);
    }

    public void setProfileImage(ImageView profileImage) {
        this.profileImage = profileImage;
    }

    public void setSettingsProfileImage(ImageView settingsProfileImage) {
        this.settingsProfileImage = settingsProfileImage;
    }

    public void setUsername(String usernameText) {
        this.username.setText(usernameText);
    }

    @FXML
    private void showProfile() {
        viewFactory.closeWindow(SettingsController.class);
        viewFactory.showProfileWindow();
    }

    @FXML
    void showAdmin() {
        viewFactory.closeWindow(SettingsController.class);
        viewFactory.showAdminWindow();
    }

    @FXML
    void showPractice() {

    }

    @FXML
    private void saveChanges()  {
        try {
            String query = "UPDATE users SET email = ?, CFHandle1 = ?, CFHandle2 = ?  WHERE username =?";
            PreparedStatement ps = dbHandler.getConnection().prepareStatement(query);
            ps.setString(1, email.getText());

            HBox hbox = (HBox) (handlesDrawerCF.getChildren().get(0));
            TextField tf = (TextField) hbox.getChildren().get(0);
            ps.setString(2, tf.getText());

            hbox = (HBox) (handlesDrawerCF.getChildren().get(1));
            tf = (TextField) hbox.getChildren().get(0);
            ps.setString(3, tf.getText());

            ps.setString(4, username.getText());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(oldPassword.getText().equals(checkPassword)){
            if(!newPassword.getText().equals("")){
                try {
                    String query = "UPDATE users SET password = ?  WHERE username =?";
                    PreparedStatement ps = dbHandler.getConnection().prepareStatement(query);
                    ps.setString(1, newPassword.getText());
                    ps.setString(2, username.getText());

                    ps.executeUpdate();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }

    }

    int cfIndex = 6;
    int cfHandleCount = 0;
    int atcIndex = 7;
    int atcHandleCount = 0;
    private List<String> cfhandles = new ArrayList<>();


    ArrayList<VBox> handlesCF = new ArrayList<VBox>();
    @FXML
    void giveOptionCF() {
        try{
            HandlesController controller = new HandlesController();
            FXMLLoader multipleCFComponent = new FXMLLoader(getClass().getResource("handleOptionsComponent.fxml"));
            multipleCFComponent.setController(controller);
            controller.setParent(handlesDrawerCF);

            Node comp = multipleCFComponent.load();
            controller.setMe(comp);
            handlesDrawerCF.getChildren().add(comp);
            cfHandleCount++;
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    void setOptionCF(){
        for(int i=0;i<2;i++){
            giveOptionCF();
            HBox hbox = (HBox) handlesDrawerCF.getChildren().get(i);
            TextField tf  = (TextField) hbox.getChildren().get(0);
            tf.setText(cfhandles.get(i));
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
