package com.example.lehiteixeira.banco_neon.Data;

/**
 * Created by Lehiteixeira on 05/09/17.
 */

import android.content.Context;
import android.content.SharedPreferences;
import com.example.lehiteixeira.banco_neon.Data.Model.Transfer;
import com.example.lehiteixeira.banco_neon.Data.network.NeonService;
import com.example.lehiteixeira.banco_neon.Data.network.NeonServiceFactory;
import com.example.lehiteixeira.banco_neon.Data.network.RemoteCallback;
import java.util.HashMap;
import java.util.List;


import static android.content.Context.MODE_PRIVATE;

/**
 * Api abstraction
 */
public class DataManager {

    private static DataManager sInstance;
    private final NeonService mNeonService;
    private Context mContext;
    private String  mToken;

    private static final String SHARED_PREFERENCE_NAME = "NEON_SHARED_PREFERENCES";

    public static DataManager getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DataManager(context);
        }
        return sInstance;
    }

    private DataManager(Context context) {
        mNeonService = NeonServiceFactory.makeNeonService();
        this.mContext = context;
        getToken();
    }

    public void getToken(String name, String email, RemoteCallback<String> listener) {
        mNeonService.getGenareteToken(name, email).enqueue(listener);
    }

    public void postSendMoney(String clienteId, Double value, RemoteCallback<Boolean> listener) {
        HashMap<String, Object> jsonParams = new HashMap<String, Object>();
        jsonParams.put("ClienteId",clienteId);
        jsonParams.put("token",mToken);
        jsonParams.put("valor",value);
        mNeonService.postSendMoney(jsonParams).enqueue(listener);
    }

    public void getTransfer(RemoteCallback<List<Transfer>> listener) {
        mNeonService.getTranfers(mToken).enqueue(listener);
    }

    private void getToken(){
        SharedPreferences prefs = mContext.getSharedPreferences(SHARED_PREFERENCE_NAME, MODE_PRIVATE);
        String restoredToken = prefs.getString("token", null);
        if (restoredToken != null) {
            mToken = prefs.getString("token", "No token defined");//"No name defined" is the default value.
        }

    }
}
