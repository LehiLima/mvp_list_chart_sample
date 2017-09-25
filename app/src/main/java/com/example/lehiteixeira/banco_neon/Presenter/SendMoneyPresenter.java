package com.example.lehiteixeira.banco_neon.Presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.lehiteixeira.banco_neon.Contract.sendMoneyContract;
import com.example.lehiteixeira.banco_neon.Data.DataManager;
import com.example.lehiteixeira.banco_neon.Data.network.RemoteCallback;
import com.example.lehiteixeira.banco_neon.R;

/**
 * Created by Lehiteixeira on 05/09/17.
 */

public class SendMoneyPresenter implements sendMoneyContract.Presenter{

    private final DataManager mDataManager;
    @NonNull
    private sendMoneyContract.View view;
    @NonNull
    private Context mContext;

    public SendMoneyPresenter(DataManager mDataManager, Context mContext, @NonNull sendMoneyContract.View sendMoneyView) {
        this.mDataManager = mDataManager;
        this.mContext = mContext;
        view = sendMoneyView;
        view.setPresenter(this);
    }

    @Override
    public void sendMoney(String clientId, Double valor) {
        mDataManager.postSendMoney(clientId, valor, new RemoteCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean response) {
               view.showoSuccessFail(response);
            }

            @Override
            public void onUnauthorized() {
                view.showoSuccessFail(false);
            }

            @Override
            public void onFailed(Throwable throwable) {
                view.showoSuccessFail(false);
            }
        });
    }

    public void LoadingOn(){
        view.LoadingOn();
    }

    public void LoadingOff(){
        view.LoadingOff();
    }


    @Override
    public void start() {

    }
}
