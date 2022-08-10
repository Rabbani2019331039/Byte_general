package com.example.byte_general;

public class Member {

    String username;
    String CFhandle1;
    String CFhandle2;

    double performance;

    boolean selected;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public double getPerformance() {
        return performance;
    }

    public void setPerformance(double performance) {
        this.performance = performance;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCFhandle1() {
        return CFhandle1;
    }

    public void setCFhandle1(String CFhandle1) {
        this.CFhandle1 = CFhandle1;
    }

    public String getCFhandle2() {
        return CFhandle2;
    }

    public void setCFhandle2(String CFhandle2) {
        this.CFhandle2 = CFhandle2;
    }
}
