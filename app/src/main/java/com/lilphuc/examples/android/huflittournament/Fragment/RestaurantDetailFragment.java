package com.lilphuc.examples.android.huflittournament.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.lilphuc.examples.android.huflittournament.Activity.PaymentActivity;
import com.lilphuc.examples.android.huflittournament.Activity.RetrigActivity;
import com.lilphuc.examples.android.huflittournament.Model.Restaurant;
import com.lilphuc.examples.android.huflittournament.R;
import com.lilphuc.examples.android.huflittournament.Utils.Utils;

import static com.lilphuc.examples.android.huflittournament.Activity.DetailActivity.INTENT_EXTRA;

public class RestaurantDetailFragment extends Fragment implements View.OnClickListener {

    private Restaurant restaurant;


    public static RestaurantDetailFragment newInstance(Restaurant restaurant) {
        RestaurantDetailFragment fragment = new RestaurantDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(INTENT_EXTRA, restaurant);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Restaurant restaurant = (Restaurant) getArguments().getSerializable(INTENT_EXTRA);
            if (restaurant != null) {
                this.restaurant = restaurant;
            }
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_restaurant_fg_detail, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Fetching view IDs for elements from resource
        ImageView restoImg = view.findViewById(R.id.restaurantImage);
        TextView restoTitle = view.findViewById(R.id.title);
        TextView restoRating = view.findViewById(R.id.rating);
        RatingBar restoRatingBar = view.findViewById(R.id.ratingBar);
        TextView restoDirections = view.findViewById(R.id.directions);
        TextView restoPhone = view.findViewById(R.id.phone);
        TextView restoHours = view.findViewById(R.id.hours);
        TextView restoUrl = view.findViewById(R.id.website);
        Toolbar toolbar = view.findViewById(R.id.toolbar);

        Button btn_retrig = view.findViewById(R.id.btn_retrig);
        btn_retrig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PaymentActivity.class);
                startActivity(intent);
            }
        });

        //Setting up custom toolbar to show the clicked item title
        Utils.setUpToolbar(getActivity(), toolbar, restaurant.getRestaurantTitle());

        //Click listener to handle view click events
        restoPhone.setOnClickListener(this);
        restoDirections.setOnClickListener(this);
        restoUrl.setOnClickListener(this);

        //Setting the data to appropriate item position
        restoImg.setImageResource(restaurant.getRestaurantImageId());
        restoTitle.setText(restaurant.getRestaurantTitle());
        restoRating.setText(String.valueOf(restaurant.getRestaurantRating()));
        restoRatingBar.setRating(restaurant.getRestaurantRating());
        restoPhone.setText(restaurant.getRestaurantPhone());
        restoHours.setText(restaurant.getRestaurantTime());
        restoDirections.setText(restaurant.getRestaurantLocation());
        restoUrl.setText(restaurant.getRestaurantWebsite());
    }


    @Override
    public void onClick(View view) {
        if (getContext() != null) {
            switch (view.getId()) {
                case R.id.directions:
                    Utils.directionsIntent(getContext(), restaurant.getRestaurantLocation());
                    break;
                case R.id.phone:
                    Utils.phoneIntent(getContext(), restaurant.getRestaurantPhone());
                    break;
                case R.id.website:
                    Utils.websiteIntent(getContext(), restaurant.getRestaurantWebsite());
                    break;
            }
        }
    }
}