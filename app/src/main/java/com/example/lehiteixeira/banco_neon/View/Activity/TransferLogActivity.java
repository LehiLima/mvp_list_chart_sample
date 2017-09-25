package com.example.lehiteixeira.banco_neon.View.Activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.example.lehiteixeira.banco_neon.Contract.TransferLogContract;
import com.example.lehiteixeira.banco_neon.Custom.CustomChartBarNeon;
import com.example.lehiteixeira.banco_neon.Custom.CustomDialogSucessFail;
import com.example.lehiteixeira.banco_neon.Data.DataManager;
import com.example.lehiteixeira.banco_neon.Presenter.TransferLogPresenter;
import com.example.lehiteixeira.banco_neon.Util.Util;
import com.example.lehiteixeira.banco_neon.View.Adapter.ChartPersonAdapter;
import com.example.lehiteixeira.banco_neon.View.Adapter.ListPersonAdapter;
import com.example.lehiteixeira.banco_neon.Data.Model.Person;
import com.example.lehiteixeira.banco_neon.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


public class TransferLogActivity extends AppCompatActivity implements TransferLogContract.View{

    private TransferLogContract.Presenter mPresenter;

    @Bind(R.id.loading_screen)
    LinearLayout loading_screen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Custom action bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_transfer_log);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_chevron_left_white_24dp);
        getSupportActionBar().setTitle(getResources().getString(R.string.transfer_log));
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        // End
        overridePendingTransition(R.anim.alpha_out_right ,R.anim.alpha_in_left);

        loading_screen.setVisibility(View.VISIBLE);
        ckeckConnection();

        mPresenter = new TransferLogPresenter(DataManager.getInstance(this),this,this,this);
        mPresenter.start();


    }

    private void poulateListPersons(ArrayList<Person> persons) {
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.list_persons);
        ListPersonAdapter mAdapter;
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ListPersonAdapter(persons,this,this);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void populateChart(ArrayList<Person> persons) {
        CustomChartBarNeon mCustomChartBarNeon = (CustomChartBarNeon) findViewById(R.id.chart_persons);
        ChartPersonAdapter mAdapterChart;
        mCustomChartBarNeon.setHasFixedSize(true);
        mCustomChartBarNeon.setLayoutManager(new LinearLayoutManager(this));
        mAdapterChart = new ChartPersonAdapter(persons,this,this);
        mCustomChartBarNeon.setAdapter(mAdapterChart);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mCustomChartBarNeon.setLayoutManager(layoutManager);
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    @Override
    public void setPersons(ArrayList<Person> persons) {
        loading_screen.setVisibility(View.GONE);
        populateChart(persons);
        poulateListPersons(persons);
    }

    @Override
    public void showFail() {
        loading_screen.setVisibility(View.GONE);
        CustomDialogSucessFail cdd=new CustomDialogSucessFail(this,this,getResources().getString(R.string.send_error),R.drawable.warning);
        cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        cdd.getWindow().getAttributes().windowAnimations = R.style.animationdialog;
        cdd.show();
    }

    @Override
    public void setPresenter(TransferLogContract.Presenter presenter) {
    }

    private void ckeckConnection() {
        if (!Util.isOnline(this)){
            CustomDialogSucessFail cdd=new CustomDialogSucessFail(this,this, getResources().getString(R.string.connection_error_transfer_log),R.drawable.warning);
            cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            cdd.getWindow().getAttributes().windowAnimations = R.style.animationdialog;
            cdd.show();
        }
    }

}
