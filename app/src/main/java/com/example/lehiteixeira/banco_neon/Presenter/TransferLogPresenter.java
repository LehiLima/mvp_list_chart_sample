package com.example.lehiteixeira.banco_neon.Presenter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.lehiteixeira.banco_neon.Contract.TransferLogContract;
import com.example.lehiteixeira.banco_neon.Contract.sendMoneyContract;
import com.example.lehiteixeira.banco_neon.Data.DataManager;
import com.example.lehiteixeira.banco_neon.Data.Model.Person;
import com.example.lehiteixeira.banco_neon.Data.Model.Transfer;
import com.example.lehiteixeira.banco_neon.Data.network.RemoteCallback;
import com.example.lehiteixeira.banco_neon.NeonApplication;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Lehiteixeira on 05/09/17.
 */

public class TransferLogPresenter implements TransferLogContract.Presenter{

    private final DataManager mDataManager;
    private Activity mActivity;
    @NonNull
    private Context mContext;
    @NonNull
    private TransferLogContract.View view;
    private List<Transfer> mResponse;


    public TransferLogPresenter(DataManager mDataManager, Context mContext, @NonNull TransferLogContract.View TransferLogView,Activity activity) {
        this.mDataManager = mDataManager;
        this.mContext = mContext;
        this.mActivity = activity;
        view = TransferLogView;
        view.setPresenter(this);
    }

    @Override
    public void start() {
        getTranferLog();
    }

    @Override
    public void getTranferLog() {
        mDataManager.getTransfer(new RemoteCallback<List<Transfer>>() {
            @Override
            public void onSuccess(List<Transfer> response) {
                setSumtransfer(response);
            }

            @Override
            public void onUnauthorized() {

            }

            @Override
            public void onFailed(Throwable throwable) {

            }
        });
    }

    private void setSumtransfer(List<Transfer> transfers){
        ArrayList<Person> persons = ((NeonApplication) mActivity.getApplication()).getPersons();

        for (int i  =0; i < persons.size(); i++){
            Double sumValue = sumValue = 0.00;
            for (Transfer t: transfers){
                if (Integer.parseInt(persons.get(i).getId())==t.getClienteId()){
                    sumValue += t.getValor();
                    persons.get(i).setValue(sumValue);
                }
            }
        }
        //order by value
        Collections.sort(persons, new orderbyvalueComparator());
        view.setPersons(persons);
    }

   //order by vvlue
    class orderbyvalueComparator implements Comparator<Person>
    {
        public int compare(Person left, Person right) {
            return right.getValue().compareTo(left.getValue());
        }
    }


}
