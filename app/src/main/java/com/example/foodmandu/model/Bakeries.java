package com.example.foodmandu.model;

import java.util.ArrayList;
import java.util.List;

public class Bakeries {
    static List<Bakeries> bakerieslist=new ArrayList<>();
    private int image;

    public Bakeries(int image) {
        this.image = image;
    }

    public static List<Bakeries> getBakerieslist() {
        return bakerieslist;
    }

    public static void setBakerieslist(List<Bakeries> bakerieslist) {
        Bakeries.bakerieslist = bakerieslist;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
