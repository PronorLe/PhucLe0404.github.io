package com.lilphuc.examples.android.huflittournament.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.lilphuc.examples.android.huflittournament.Fragment.HotelDetailFragment;
import com.lilphuc.examples.android.huflittournament.Fragment.PlaceDetailFragment;
import com.lilphuc.examples.android.huflittournament.Fragment.RestaurantDetailFragment;
import com.lilphuc.examples.android.huflittournament.Fragment.ShopDetailFragment;
import com.lilphuc.examples.android.huflittournament.Model.Hotel;
import com.lilphuc.examples.android.huflittournament.Model.Place;
import com.lilphuc.examples.android.huflittournament.Model.Restaurant;
import com.lilphuc.examples.android.huflittournament.Model.Shop;
import com.lilphuc.examples.android.huflittournament.R;

public class
DetailActivity extends AppCompatActivity {


    public static final String INTENT_EXTRA = "extras";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        Object object = getIntent().getSerializableExtra(INTENT_EXTRA);
        Fragment fragment = null;


        if (object instanceof Hotel) {
            fragment = HotelDetailFragment.newInstance((Hotel) object);
        } else if (object instanceof Place) {
            fragment = PlaceDetailFragment.newInstance((Place) object);
        } else if (object instanceof Shop) {
            fragment = ShopDetailFragment.newInstance((Shop) object);
        } else if (object instanceof Restaurant) {
            fragment = RestaurantDetailFragment.newInstance((Restaurant) object);
        }


        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
        }
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}