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

}
