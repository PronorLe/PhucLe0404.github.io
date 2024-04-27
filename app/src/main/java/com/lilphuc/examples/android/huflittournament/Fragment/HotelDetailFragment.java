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

import com.lilphuc.examples.android.huflittournament.Activity.DetailActivity;
import com.lilphuc.examples.android.huflittournament.Activity.PaymentActivity;
import com.lilphuc.examples.android.huflittournament.Activity.RetrigActivity;
import com.lilphuc.examples.android.huflittournament.Model.Hotel;
import com.lilphuc.examples.android.huflittournament.R;
import com.lilphuc.examples.android.huflittournament.Utils.Utils;

import static com.lilphuc.examples.android.huflittournament.Activity.DetailActivity.INTENT_EXTRA;

public class HotelDetailFragment extends Fragment implements View.OnClickListener {

    private Hotel hotel;

    public static HotelDetailFragment newInstance(Hotel hotel) {
        HotelDetailFragment hotelDetailFragment = new HotelDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(INTENT_EXTRA, hotel);
        hotelDetailFragment.setArguments(bundle);
        return hotelDetailFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Hotel hotel = (Hotel) getArguments().getSerializable(INTENT_EXTRA);

            if (hotel != null) {
                this.hotel = hotel;
            }
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_hotel_fg_detail, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Fetching view IDs for elements from resource
        ImageView hotelImg = view.findViewById(R.id.hotelImage);
        TextView hotelTitle = view.findViewById(R.id.title);
        TextView hotelRating = view.findViewById(R.id.rating);
        RatingBar hotelRatingBar = view.findViewById(R.id.ratingBar);
        TextView hotelPhone = view.findViewById(R.id.phone);
        TextView hotelDirections = view.findViewById(R.id.directions);
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
        Utils.setUpToolbar(getActivity(), toolbar, hotel.getHotelTitle());

        //Click listener to handle view click events
        hotelPhone.setOnClickListener(this);
        hotelDirections.setOnClickListener(this);

        //Setting the data to appropriate item position
        hotelImg.setImageResource(hotel.getHotelImageId());
        hotelTitle.setText(hotel.getHotelTitle());
        hotelRating.setText(String.valueOf(hotel.getHotelRating()));
        hotelRatingBar.setRating(hotel.getHotelRating());
        hotelPhone.setText(hotel.getHotelPhone());
        hotelDirections.setText(hotel.getHotelLocation());
    }


    @Override
    public void onClick(View view) {
        if (getContext() != null) {
            switch (view.getId()) {
                case R.id.phone:
                    Utils.phoneIntent(getContext(), hotel.getHotelPhone());
                    break;
                case R.id.directions:
                    Utils.directionsIntent(getContext(), hotel.getHotelLocation());
                    break;
            }
        }
    }
}