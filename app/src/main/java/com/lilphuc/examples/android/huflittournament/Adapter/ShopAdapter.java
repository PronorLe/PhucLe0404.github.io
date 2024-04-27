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

import com.lilphuc.examples.android.huflittournament.Model.Shop;
import com.lilphuc.examples.android.huflittournament.R;
import com.lilphuc.examples.android.huflittournament.Utils.Utils;

import java.util.ArrayList;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ShopViewHolder> {
    private final Context context;
    private final ArrayList<Shop> shops;


    public ShopAdapter(Context context, ArrayList<Shop> shops) {
        this.context = context;
        this.shops = shops;
    }


    @NonNull
    @Override
    public ShopViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new ShopViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_shopping_fragment, viewGroup, false));
    }


    @Override
    public void onBindViewHolder(@NonNull final ShopViewHolder shopViewHolder, int position) {

        //Storing the stable ID for the item at position
        final Shop shop = shops.get(position);

        //Based on item position, storing the data accordingly
        shopViewHolder.shopImg.setImageResource(shop.getShopImageId());
        shopViewHolder.shopTitle.setText(shop.getShopTitle());
        shopViewHolder.shopPlace.setText(shop.getShopPlace());
        shopViewHolder.shopRating.setText(String.valueOf(shop.getShopRating()));
        shopViewHolder.shopRatingBar.setRating(shop.getShopRating());

        //Click Listener to open a detail intent, displaying more info about shops
        shopViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.detailIntent(context, shop, shopViewHolder.shopImg);
            }
        });
    }


    @Override
    public int getItemCount() {
        return shops.size();
    }

    public static class ShopViewHolder extends RecyclerView.ViewHolder {

        // Variable declaration for views available on screen
        final ImageView shopImg;
        final TextView shopTitle;
        final TextView shopRating;
        final RatingBar shopRatingBar;
        final TextView shopPlace;
        final CardView cardView;

        ShopViewHolder(@NonNull View itemView) {
            super(itemView);

            // Fetching view IDs for view elements from resource
            shopImg = itemView.findViewById(R.id.shop_image);
            shopTitle = itemView.findViewById(R.id.shop_name);
            shopRating = itemView.findViewById(R.id.rating);
            shopRatingBar = itemView.findViewById(R.id.ratingBar);
            shopPlace = itemView.findViewById(R.id.shop_address);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}