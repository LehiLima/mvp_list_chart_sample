package com.example.lehiteixeira.banco_neon.Custom;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.lehiteixeira.banco_neon.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Lehiteixeira on 06/09/17.
 */

public class CustomDialogSucessFail extends Dialog  {

    private Activity activity;
    private String mMessage;
    private int resId;
    private Context mContext;


    @Bind(R.id.imagefucessfail)
    ImageView imageSucessFail;

    @Bind(R.id.massege)
    TextView massege;

    @OnClick(R.id.btn_ok)
    public void btnOk() {
        dismiss();
    }

    @OnClick(R.id.btn_close)
    public void close() {
        dismiss();
    }


    public CustomDialogSucessFail(Activity activity, Context context, String message, int resId) {
        super(activity);
        this.activity = activity;
        this.mContext = context;
        this.mMessage = message;
        this.resId = resId;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog_sucess_fail);
        ButterKnife.bind(this);

        setCancelable(false);

        populateDialog(mMessage,resId);

    }

    private void populateDialog(String message, int resId) {
        imageSucessFail.setImageResource(resId);
        massege.setText(message);
    }


}
