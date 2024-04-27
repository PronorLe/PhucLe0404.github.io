package com.lilphuc.examples.android.huflittournament.Data;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;

import com.lilphuc.examples.android.huflittournament.Model.Hotel;
import com.lilphuc.examples.android.huflittournament.R;

import java.util.ArrayList;

public class HotelData {


    public static ArrayList<Hotel> fetchHotelData(Context context) {

        ArrayList<Hotel> hotels = new ArrayList<>();
        Resources resources = context.getResources();


        TypedArray typedArray = resources.obtainTypedArray(R.array.hotelImgID);
        int[] hotelImgId = new int[typedArray.length()];
        for (int index = 0; index < hotelImgId.length; index++) {
            hotelImgId[index] = typedArray.getResourceId(index, 0);
        }


        typedArray = resources.obtainTypedArray(R.array.hotelRating);
        float[] rating = new float[typedArray.length()];
        for (int index = 0; index < rating.length; index++) {
            rating[index] = typedArray.getFloat(index, 0);
        }


        typedArray.recycle();


        String[] name = resources.getStringArray(R.array.hotelName);
        String[] type = resources.getStringArray(R.array.hotelType);
        String[] phone = resources.getStringArray(R.array.hotelPhone);
        String[] address = resources.getStringArray(R.array.hotelAddress);


        for (int i = 0; i < hotelImgId.length; i++) {
            Hotel hotel = new Hotel(hotelImgId[i], name[i], rating[i], phone[i], type[i], address[i]);
            hotels.add(hotel);
        }
        return hotels;
    }
}