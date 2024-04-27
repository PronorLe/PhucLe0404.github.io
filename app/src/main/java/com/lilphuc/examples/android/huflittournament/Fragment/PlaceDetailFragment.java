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
import com.lilphuc.examples.android.huflittournament.Model.Place;
import com.lilphuc.examples.android.huflittournament.R;
import com.lilphuc.examples.android.huflittournament.Utils.Utils;

import static com.lilphuc.examples.android.huflittournament.Activity.DetailActivity.INTENT_EXTRA;

public class PlaceDetailFragment extends Fragment implements View.OnClickListener {

    private Place place;


    public static PlaceDetailFragment newInstance(Place place) {
        PlaceDetailFragment placeDetailFragment = new PlaceDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(INTENT_EXTRA, place);
        placeDetailFragment.setArguments(bundle);
        return placeDetailFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Place place = (Place) getArguments().getSerializable(INTENT_EXTRA);
            if (place != null) {
                this.place = place;
            }
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_place_fg_detail, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Fetching view IDs for elements from resource
        ImageView placeImg = view.findViewById(R.id.placeImage);
        TextView placeTitle = view.findViewById(R.id.title);
        TextView placeRating = view.findViewById(R.id.rating);
        RatingBar placeRatingBar = view.findViewById(R.id.ratingBar);
        TextView placePhone = view.findViewById(R.id.phone);
        TextView placeHours = view.findViewById(R.id.hours);
        TextView placeDirections = view.findViewById(R.id.directions);
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
        Utils.setUpToolbar(getActivity(), toolbar, place.getPlaceTitle());

        //Click listener to handle view click events
        placePhone.setOnClickListener(this);
        placeDirections.setOnClickListener(this);

        //Setting the data to appropriate item position
        placeImg.setImageResource(place.getPlaceImageId());
        placeTitle.setText(place.getPlaceTitle());
        placePhone.setText(place.getPlacePhone());
        placeHours.setText(place.getPlaceTime());
        placeRating.setText(String.valueOf(place.getPlaceRating()));
        placeRatingBar.setRating(place.getPlaceRating());
        placeDirections.setText(place.getPlaceLocation());
    }


    @Override
    public void onClick(View view) {
        if (getContext() != null) {
            switch (view.getId()) {
                case R.id.phone:
                    Utils.phoneIntent(getContext(), place.getPlacePhone());
                    break;
                case R.id.directions:
                    Utils.directionsIntent(getContext(), place.getPlaceLocation());
                    break;
            }
        }
    }
}