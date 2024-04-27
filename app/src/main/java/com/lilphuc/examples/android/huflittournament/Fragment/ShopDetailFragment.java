package com.lilphuc.examples.android.huflittournament.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.lilphuc.examples.android.huflittournament.Model.Shop;
import com.lilphuc.examples.android.huflittournament.R;
import com.lilphuc.examples.android.huflittournament.Utils.Utils;

import static com.lilphuc.examples.android.huflittournament.Activity.DetailActivity.INTENT_EXTRA;

public class ShopDetailFragment extends Fragment implements View.OnClickListener {

    private Shop shop;


    public static ShopDetailFragment newInstance(Shop shop) {
        ShopDetailFragment fragment = new ShopDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(INTENT_EXTRA, shop);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Shop shop = (Shop) getArguments().getSerializable(INTENT_EXTRA);
            if (shop != null) {
                this.shop = shop;
            }
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_shopping_fg_detail, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Fetching view IDs for elements from resource
        ImageView shopImg = view.findViewById(R.id.shopImage);
        TextView shopTitle = view.findViewById(R.id.title);
        TextView shopRating = view.findViewById(R.id.rating);
        RatingBar shopRatingBar = view.findViewById(R.id.ratingBar);
        TextView shopPhone = view.findViewById(R.id.phone);
        TextView shopHours = view.findViewById(R.id.hours);
        TextView shopDirections = view.findViewById(R.id.directions);
        Toolbar toolbar = view.findViewById(R.id.toolbar);

        //Setting up custom toolbar to show the clicked item title
        Utils.setUpToolbar(getActivity(), toolbar, shop.getShopTitle());

        //Click listener to handle view click events
        shopPhone.setOnClickListener(this);
        shopDirections.setOnClickListener(this);

        //Setting the data to appropriate item position
        shopImg.setImageResource(shop.getShopImageId());
        shopTitle.setText(shop.getShopTitle());
        shopRating.setText(String.valueOf(shop.getShopRating()));
        shopRatingBar.setRating(shop.getShopRating());
        shopPhone.setText(shop.getShopPhone());
        shopHours.setText(shop.getShopTime());
        shopDirections.setText(shop.getShopLocation());
    }


    @Override
    public void onClick(View view) {
        if (getContext() != null) {
            switch (view.getId()) {
                case R.id.phone:
                    Utils.phoneIntent(getContext(), shop.getShopPhone());
                    break;
                case R.id.directions:
                    Utils.directionsIntent(getContext(), shop.getShopLocation());
                    break;
            }
        }
    }
}