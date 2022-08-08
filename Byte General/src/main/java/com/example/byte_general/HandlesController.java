package com.example.byte_general;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

public class HandlesController {
    public static int cnt = 0;
    public int number = 0;

    public HandlesController(){
        super();
        this.number = cnt;
        cnt++;
    }

    public Parent node = null;
    public Node me = null;
    public void setParent(Parent node){
        this.node = node;
    }

    public void setMe(Node me){
        this.me = me;
    }



    public void removeHandle(){
        ((VBox) node).getChildren().remove(me);
//        cnt--;
        System.out.println("removed");
    }
}
