package com.example.lehiteixeira.banco_neon.Contract;

import com.example.lehiteixeira.banco_neon.BasePresenterContract;
import com.example.lehiteixeira.banco_neon.BaseViewContract;
import com.example.lehiteixeira.banco_neon.Data.Model.Person;

import java.util.ArrayList;

/**
 * Created by Lehiteixeira on 06/09/17.
 */

public interface TransferLogContract {

    interface View extends BaseViewContract<Presenter> {

        void setPersons(ArrayList<Person> persons);

        void showFail();

    }

    interface Presenter extends BasePresenterContract {

        void getTranferLog();

    }
}

