package com.example.foodmandu.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.foodmandu.LoginActivity;
import com.example.foodmandu.R;
import com.example.foodmandu.adaptter.BakeriesAdpater;
import com.example.foodmandu.adaptter.CategoriesViewPagerAdapter;
import com.example.foodmandu.adaptter.FlavourAdapter;
import com.example.foodmandu.adaptter.ImageSliderViewPagerAdapter;
import com.example.foodmandu.adaptter.Super7Adpater;
import com.example.foodmandu.model.Bakeries;
import com.example.foodmandu.model.Categories;
import com.example.foodmandu.model.Flavours;
import com.example.foodmandu.model.Super7;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    ViewPager vpImageSlider;
    private RecyclerView rvCategories;
    private RecyclerView rvBakeries;
    private RecyclerView rvSuper7;
    private RecyclerView rvFlavour;
    public static List<Categories> categoriesList = new ArrayList<>();
    public static List<Super7> superList=new ArrayList<>();
    public static List<Bakeries> bakeriesList = new ArrayList<>();
    public static List<Flavours> flavoursList=new ArrayList<>();

    private int position;
    private static final int PAGE_NUM=4;

    ImageView imageCategories;


    private Handler handler=new Handler();
    private Runnable runnable=new Runnable() {
        @Override
        public void run() {
            vpImageSlider.setCurrentItem(position,true);
            if(position>=PAGE_NUM) position=0;
            else position++;
            handler.postDelayed(runnable,3000);
        }
    };

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        vpImageSlider= view.findViewById(R.id.vpSliderImage);

        ImageSliderViewPagerAdapter adapter = new ImageSliderViewPagerAdapter(getActivity());
        vpImageSlider.setAdapter(adapter);
        runnable.run();

        rvCategories = view.findViewById(R.id.rvCategories);

        Categories categories = new Categories(R.drawable.slider1);
        categoriesList = categories.getListcategory();
        categoriesList.add(new Categories(R.drawable.restaurants));
        categoriesList.add(new Categories(R.drawable.liquors));
        categoriesList.add(new Categories(R.drawable.bakeries));
        categoriesList.add(new Categories(R.drawable.refreshment));
        categoriesList.add(new Categories(R.drawable.organic));

        CategoriesViewPagerAdapter adapter1 = new CategoriesViewPagerAdapter(getActivity(),categoriesList);
        rvCategories.setAdapter(adapter1);
        rvCategories.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));

        rvSuper7 = view.findViewById(R.id.rvSuper7);
        Super7 super7 = new Super7(R.drawable.super71);
        superList = super7.getSuperlist();

        superList.add(new Super7(R.drawable.super71));
        superList.add(new Super7(R.drawable.super72));
        superList.add(new Super7(R.drawable.super73));
        superList.add(new Super7(R.drawable.super74));
        superList.add(new Super7(R.drawable.super75));
        superList.add(new Super7(R.drawable.super76));
        superList.add(new Super7(R.drawable.super77));


        Super7Adpater super7Adpater = new Super7Adpater(getActivity(),superList);
        rvSuper7.setAdapter(super7Adpater);
        rvSuper7.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));

        rvBakeries = view.findViewById(R.id.rvBakeries);
        Bakeries bakeries = new Bakeries((R.drawable.bakeries1));
        bakeriesList = bakeries.getBakerieslist();

        bakeriesList.add(new Bakeries(R.drawable.bakeries1));
        bakeriesList.add(new Bakeries(R.drawable.bakeries2));
        bakeriesList.add(new Bakeries(R.drawable.bakeries3));
        bakeriesList.add(new Bakeries(R.drawable.bakeries4));
        bakeriesList.add(new Bakeries(R.drawable.bakeries5));

        BakeriesAdpater bakeriesAdpater = new BakeriesAdpater(getActivity(),bakeriesList);
        rvBakeries.setAdapter(bakeriesAdpater);
        rvBakeries.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));

        rvFlavour=view.findViewById(R.id.rvFlavours);
        Flavours flavoursViewModel=new Flavours(R.drawable.foodmandu);
        flavoursList=flavoursViewModel.getFlavoursList();

        flavoursList.add(new Flavours(R.drawable.flavour1));
        flavoursList.add(new Flavours(R.drawable.flavour2));
        flavoursList.add(new Flavours(R.drawable.flavour3));
        flavoursList.add(new Flavours(R.drawable.flavour4));
        flavoursList.add(new Flavours(R.drawable.flavour5));

        FlavourAdapter flavoursAdapter=new FlavourAdapter(getActivity(),flavoursList);
        rvFlavour.setAdapter(flavoursAdapter);
        rvFlavour.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));

        return view;
    }
}