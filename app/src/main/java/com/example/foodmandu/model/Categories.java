package com.example.foodmandu.model;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class Categories extends ViewModel {

    static List<Categories> listcategory=new ArrayList<>();
    private int img;

    public Categories(int img) {
        this.img = img;
    }

    public static List<Categories> getListcategory() {
        return listcategory;
    }

    public static void setListcategory(List<Categories> listcategory) {
        Categories.listcategory = listcategory;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
