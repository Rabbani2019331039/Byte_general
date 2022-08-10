package com.example.byte_general;

public final class LoggedInUser {
    private String username;
    private static LoggedInUser INSTANCE = null;

    private LoggedInUser(){}

    public static LoggedInUser getInstance(){
        if(INSTANCE==null){
            INSTANCE = new LoggedInUser();
        }
        return INSTANCE;
    }

    public LoggedInUser(String username){
        this.username = username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
