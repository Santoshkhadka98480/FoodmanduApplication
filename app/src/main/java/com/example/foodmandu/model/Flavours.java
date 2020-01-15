package com.example.foodmandu.model;

import java.util.ArrayList;
import java.util.List;

public class Flavours {
    static List<Flavours> flavoursList =new ArrayList<>();
    private int img;

    public Flavours(int img) {
        this.img = img;
    }

    public static List<Flavours> getFlavoursList() {
        return flavoursList;
    }

    public static void setFlavoursList(List<Flavours> flavoursList) {
        Flavours.flavoursList = flavoursList;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
