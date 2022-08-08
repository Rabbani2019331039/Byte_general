package com.example.byte_general;
import java.sql.*;

public class DBHandler extends DBConfigs{
    public Connection connection;

    public Connection getConnection(){
        String connectionString = "jdbc:mysql://" + host + ":" + port + "/" + dbName + "?autoReconnect=true&useSSL=false";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(connectionString, username, password);
        }catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }


}
