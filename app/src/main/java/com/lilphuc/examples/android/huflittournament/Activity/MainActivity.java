package com.lilphuc.examples.android.huflittournament.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.lilphuc.examples.android.huflittournament.Adapter.TabAdapter;

import com.lilphuc.examples.android.huflittournament.Fragment.HotelFragment;
import com.lilphuc.examples.android.huflittournament.Fragment.PlaceFragment;
import com.lilphuc.examples.android.huflittournament.Fragment.RestaurantFragment;
import com.lilphuc.examples.android.huflittournament.Fragment.ShopFragment;
import com.lilphuc.examples.android.huflittournament.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static String userName;
    public static String passWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            String name = bundle.getString("userName");
            
            userName = bundle.getString("userName");
            passWord  = bundle.getString("passWord");
        }

        ViewPager viewPager = findViewById(R.id.viewPager);
        TabLayout tabLayout = findViewById(R.id.tabs);


        TabAdapter tabAdapter = new TabAdapter(getSupportFragmentManager());


        tabAdapter.addFragment(new HotelFragment(), getString(R.string.hotels));
        tabAdapter.addFragment(new PlaceFragment(), getString(R.string.places));
        tabAdapter.addFragment(new RestaurantFragment(), getString(R.string.restaurants));
        tabAdapter.addFragment(new ShopFragment(), getString(R.string.shoppings));



        viewPager.setAdapter(tabAdapter);


        tabLayout.setupWithViewPager(viewPager);
    }

    private void initView() {

    }
}