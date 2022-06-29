package com.example.byte_general;

public class Authenticator {
    public static boolean authenticate(String username, String password) {
        return username.equals("admin") && password.equals("admin");
    }
}
