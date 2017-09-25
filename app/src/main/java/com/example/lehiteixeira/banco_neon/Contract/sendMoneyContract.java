package com.example.lehiteixeira.banco_neon.Contract;

import com.example.lehiteixeira.banco_neon.BasePresenterContract;
import com.example.lehiteixeira.banco_neon.BaseViewContract;

/**
 * Created by Lehiteixeira on 06/09/17.
 */

public interface sendMoneyContract {

    interface View extends BaseViewContract<Presenter> {

        void showoSuccessFail(Boolean sucessFail);

        void LoadingOn();

        void LoadingOff();

    }

    interface Presenter extends BasePresenterContract {

        void sendMoney(String clientId, Double valor);

    }
}

