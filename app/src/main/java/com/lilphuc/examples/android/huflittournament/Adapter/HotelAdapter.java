package com.lilphuc.examples.android.huflittournament.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.lilphuc.examples.android.huflittournament.Model.Hotel;
import com.lilphuc.examples.android.huflittournament.R;
import com.lilphuc.examples.android.huflittournament.Utils.Utils;

import java.util.ArrayList;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.HotelViewHolder> {

    private final ArrayList<Hotel> hotels;
    private final Context context;


    public HotelAdapter(ArrayList<Hotel> hotels, Context context) {
        this.hotels = hotels;
        this.context = context;
    }


    @NonNull
    @Override
    public HotelViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new HotelViewHolder(LayoutInflater
                .from(context)
                .inflate(R.layout.layout_hotel_fragment, viewGroup, false));
    }


    @Override
    public void onBindViewHolder(@NonNull final HotelAdapter.HotelViewHolder hotelViewHolder, int position) {
        //Storing the stable ID for the item at position
        final Hotel hotel = hotels.get(position);

        //Based on item position, storing the data accordingly
        hotelViewHolder.hotelImage.setImageResource(hotel.getHotelImageId());
        hotelViewHolder.hotelTitle.setText(hotel.getHotelTitle());
        hotelViewHolder.hotelRating.setText(String.valueOf(hotel.getHotelRating()));
        hotelViewHolder.hotelRatingBar.setRating(hotel.getHotelRating());
        hotelViewHolder.hotelType.setText(hotel.getHotelType());

        //Click Listener to open a detail intent, displaying more info about hotels
        hotelViewHolder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.detailIntent(context, hotel, hotelViewHolder.hotelImage);
            }
        });
    }


    @Override
    public int getItemCount() {
        return hotels.size();
    }

    public static class HotelViewHolder extends RecyclerView.ViewHolder {

        // Variable declaration for views available on screen
        final ImageView hotelImage;
        final TextView hotelTitle;
        final TextView hotelRating;
        final RatingBar hotelRatingBar;
        final TextView hotelType;
        final LinearLayout constraintLayout;

        HotelViewHolder(@NonNull View itemView) {
            super(itemView);

            // Fetching view IDs for view elements from resource
            hotelImage = itemView.findViewById(R.id.hotel_image);
            hotelTitle = itemView.findViewById(R.id.hotel_name);
            hotelRating = itemView.findViewById(R.id.rating);
            hotelRatingBar = itemView.findViewById(R.id.ratingBar);
            hotelType = itemView.findViewById(R.id.hotel_type);
            constraintLayout = itemView.findViewById(R.id.parent);
        }
    }
}