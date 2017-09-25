package com.example.lehiteixeira.banco_neon.Presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.example.lehiteixeira.banco_neon.BasePresenterContract;
import com.example.lehiteixeira.banco_neon.Data.DataManager;
import com.example.lehiteixeira.banco_neon.Data.network.RemoteCallback;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Lehiteixeira on 05/09/17.
 */

public class BasePresenter implements BasePresenterContract{

    private final DataManager mDataManager;
    private String mName;
    private String mEmail;
    private Context mContext;
    private static final String SHARED_PREFERENCE_NAME = "NEON_SHARED_PREFERENCES";


    public BasePresenter(@NonNull DataManager dataManager, @NonNull String name, String email, @NonNull Context context) {
        mDataManager = dataManager;
        mName = name;
        mEmail = email;
        mContext = context;
    }

    @Override
    public void start() {
        mDataManager.getToken(mName, mEmail, new RemoteCallback<String>() {
            @Override
            public void onSuccess(String response) {
                SharedPreferences.Editor editor =  mContext.getSharedPreferences(SHARED_PREFERENCE_NAME, MODE_PRIVATE).edit();
                editor.putString("token", response);
                editor.apply();
            }

            @Override
            public void onUnauthorized() {

            }

            @Override
            public void onFailed(Throwable throwable) {

            }
        });
    }

}
