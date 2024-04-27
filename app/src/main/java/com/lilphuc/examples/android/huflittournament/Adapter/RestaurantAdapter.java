package com.lilphuc.examples.android.huflittournament.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.lilphuc.examples.android.huflittournament.Model.Restaurant;
import com.lilphuc.examples.android.huflittournament.R;
import com.lilphuc.examples.android.huflittournament.Utils.Utils;

import java.util.ArrayList;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {
    private final Context context;
    private final ArrayList<Restaurant> restaurants;


    public RestaurantAdapter(Context context, ArrayList<Restaurant> restaurants) {
        this.context = context;
        this.restaurants = restaurants;
    }


    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new RestaurantViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_restaurant_fragment, viewGroup, false));
    }


    @Override
    public void onBindViewHolder(@NonNull final RestaurantViewHolder restaurantViewHolder, int position) {

        //Storing the stable ID for the item at position
        final Restaurant restaurant = restaurants.get(position);

        //Based on item position, storing the data accordingly
        restaurantViewHolder.restoImg.setImageResource(restaurant.getRestaurantImageId());
        restaurantViewHolder.restoTitle.setText(restaurant.getRestaurantTitle());
        restaurantViewHolder.restoType.setText(restaurant.getRestaurantType());
        restaurantViewHolder.restoRating.setText(String.valueOf(restaurant.getRestaurantRating()));
        restaurantViewHolder.restoRatingBar.setRating(restaurant.getRestaurantRating());

        //Click Listener to open a detail intent, displaying more info about restaurants
        restaurantViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.detailIntent(context, restaurant, restaurantViewHolder.restoImg);
            }
        });
    }


    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    public static class RestaurantViewHolder extends RecyclerView.ViewHolder {

        // Variable declaration for views available on screen
        final ImageView restoImg;
        final TextView restoTitle;
        final TextView restoType;
        final TextView restoRating;
        final RatingBar restoRatingBar;
        final CardView cardView;

        RestaurantViewHolder(@NonNull View itemView) {
            super(itemView);

            // Fetching view IDs for view elements from resource
            restoImg = itemView.findViewById(R.id.restaurant_image);
            restoTitle = itemView.findViewById(R.id.restaurant_name);
            restoType = itemView.findViewById(R.id.restaurant_type);
            restoRating = itemView.findViewById(R.id.rating);
            restoRatingBar = itemView.findViewById(R.id.ratingBar);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}