package com.example.lehiteixeira.banco_neon.Util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;

import com.example.lehiteixeira.banco_neon.Custom.CustomDialogSucessFail;
import com.example.lehiteixeira.banco_neon.R;

/**
 * Created by Lehiteixeira on 06/09/17.
 */

public abstract class Util {
    private Context context;

    public Util(Context context) {
        this.context = context;
    }

    public static boolean isOnline(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public static RoundedBitmapDrawable cutRooundedImage(Context context,int resId) {
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resId);
        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), bitmap);
        roundedBitmapDrawable.setCircular(true);
        return roundedBitmapDrawable;
    }

}
