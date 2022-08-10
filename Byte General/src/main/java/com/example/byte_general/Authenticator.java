package com.example.byte_general;

import com.mysql.cj.protocol.Resultset;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Authenticator {
    private DBHandler loginHandler = new DBHandler();
    public boolean authenticate(String username, String password) {
        String query  = "SELECT * FROM users WHERE username = ? AND password = ? ";
        try{
            PreparedStatement ps = loginHandler.getConnection().prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                return true;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public void authenticateNewUser(String username, String password, String email){
        String query  = "SELECT * FROM users WHERE username = ?";
        try {
            PreparedStatement ps = loginHandler.getConnection().prepareStatement(query);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if(rs.isBeforeFirst()){
                System.out.println("user already exist");
            }
            else{
                query = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
                ps = loginHandler.getConnection().prepareStatement(query);
                ps.setString(1, username);
                ps.setString(2, password);
                ps.setString(3, email);

                ps.executeUpdate();
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }


}
