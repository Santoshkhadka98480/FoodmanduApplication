package com.example.foodmandu.model;

import java.util.ArrayList;
import java.util.List;

public class Super7 {
    static List<Super7> superlist=new ArrayList<>();
    private int image;

    public Super7(int image) {
        this.image = image;
    }

    public static List<Super7> getSuperlist() {
        return superlist;
    }

    public static void setSuperlist(List<Super7> superlist) {
        Super7.superlist = superlist;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
