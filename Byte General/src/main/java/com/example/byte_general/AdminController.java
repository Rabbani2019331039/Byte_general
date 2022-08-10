package com.example.byte_general;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminController extends BaseController{
    @FXML
    private JFXButton adminButton;

    @FXML
    private JFXButton closeButton;

    @FXML
    private JFXButton logoutButton;

    @FXML
    private JFXButton membersSection;

    @FXML
    private JFXButton practiceButton;

    @FXML
    private JFXButton profileButton;

    @FXML
    private ImageView profileImage;

    @FXML
    private JFXButton querySection;

    @FXML
    private JFXButton settingsButton;

    @FXML
    private JFXButton teamFormSection;

    @FXML
    private Label memberItemCF1;

    @FXML
    private Label memberItemCF2;

    @FXML
    private Label memberItemUsername;

    @FXML
    private Label memberItemPerformance;

    @FXML
    private VBox memberItemLayout;




    private Map<HBox, Integer> selctedMembers = new HashMap<HBox, Integer>();

    public AdminController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }

    public void init() {
        super.init();
        fetchDataFromDB();
        initMemberSection();
        profileImage = ImageUtilities.clipCircleImage(profileImage);
    }



    private DBHandler dbHandler= new DBHandler();
    List<Member> allMembers = new ArrayList<>();

    private void fetchDataFromDB(){
        String query  = "SELECT * FROM users";
        try{
            PreparedStatement ps = dbHandler.getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Member member = new Member();
                member.setUsername(rs.getString("username"));
                member.setCFhandle1(rs.getString("CFHandle1"));
                member.setCFhandle2(rs.getString("CFHandle2"));
                member.setPerformance(rs.getDouble("performance"));
                member.setSelected(false);

                allMembers.add(member);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }


    private void initMemberSection(){
        for(int i=0;i<allMembers.size();i++){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("memberItemComponent.fxml"));
            fxmlLoader.setController(this);

            try{
                HBox item = fxmlLoader.load();
                setMemberItemData(allMembers.get(i));
                memberItemLayout.getChildren().add(item);
                selctedMembers.put(item,i);

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void setMemberItemData(Member member){
        memberItemUsername.setText(member.getUsername());
        memberItemCF1.setText(member.getCFhandle1());
        memberItemCF2.setText(member.getCFhandle2());
        memberItemPerformance.setText(""+member.getPerformance());
    }

    @FXML
    void closeProgram() {
        viewFactory.closeWindow(AdminController.class);
    }

    @FXML
    void logout() {
        viewFactory.closeWindow(ProfileController.class);
        viewFactory.showStartWindow();
    }

    @FXML
    void showProfile() {
        viewFactory.closeWindow(AdminController.class);
        viewFactory.showProfileWindow();
    }

    @FXML
    void showMembersSection(ActionEvent event) {

    }


    @FXML
    void showQuerySection(ActionEvent event) {

    }

    @FXML
    void showSettings(ActionEvent event) {
        viewFactory.closeWindow(AdminController.class);
        viewFactory.showSettingWindow();
    }

    @FXML
    void showTeamFormSection(ActionEvent event) {

    }

    @FXML
    void select(){

    }


}


