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

import com.example.lehiteixeira.banco_neon.Contract.sendMoneyContract;
import com.example.lehiteixeira.banco_neon.Custom.CustomDialogSucessFail;
import com.example.lehiteixeira.banco_neon.NeonApplication;
import com.example.lehiteixeira.banco_neon.View.Adapter.ListPersonAdapter;
import com.example.lehiteixeira.banco_neon.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SendMoneyActivity extends AppCompatActivity implements sendMoneyContract.View{

    @Bind(R.id.loading_screen)
    LinearLayout loading_screen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_send_money);

        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_chevron_left_white_24dp);
        getSupportActionBar().setTitle(getResources().getString(R.string.send_money));
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        // End
        overridePendingTransition(R.anim.alpha_out_right ,R.anim.alpha_in_left);

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.list_persons);
        ListPersonAdapter mAdapter;
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ListPersonAdapter(((NeonApplication) this.getApplication()).getPersons() ,this,this);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
    @Override
    public void LoadingOn(){
        loading_screen.setVisibility(View.VISIBLE);
    }

    @Override
    public void LoadingOff(){
        loading_screen.setVisibility(View.GONE);
    }


    @Override
    public void showoSuccessFail(Boolean sucessFail) {
        String message;
        int resId;

        if (sucessFail){
            message = getResources().getString(R.string.send_sucess);
            resId = R.drawable.enviado_icon;
        }else{
            message = getResources().getString(R.string.send_error);
            resId = R.drawable.warning;
        }

        loading_screen.setVisibility(View.GONE);
        CustomDialogSucessFail cdd=new CustomDialogSucessFail(this,this,message,resId);
        cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        cdd.getWindow().getAttributes().windowAnimations = R.style.animationdialog;
        cdd.show();
    }

    @Override
    public void setPresenter(sendMoneyContract.Presenter presenter) {
    }
}
