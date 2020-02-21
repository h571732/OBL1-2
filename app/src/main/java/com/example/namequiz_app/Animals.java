package com.example.namequiz_app;

import android.widget.ImageView;

import java.io.Serializable;

public class Animals implements Serializable { //Bruker serializable for å sende en ArrayList som inneholder objekter, hvis ikke kræsj
     int image = 0;
     String name="";



    public Animals(int image, String name){
        this.image = image;
        this.name = name;

    }
    public int getImage() {
        return image;

    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public  String getName() {
        return  name;

    }
}
