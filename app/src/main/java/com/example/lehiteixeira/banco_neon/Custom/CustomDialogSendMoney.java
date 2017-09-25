package com.example.lehiteixeira.banco_neon.Custom;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lehiteixeira.banco_neon.Contract.sendMoneyContract;
import com.example.lehiteixeira.banco_neon.Data.DataManager;
import com.example.lehiteixeira.banco_neon.Data.Model.Person;
import com.example.lehiteixeira.banco_neon.Presenter.SendMoneyPresenter;
import com.example.lehiteixeira.banco_neon.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CustomDialogSendMoney extends Dialog  {

    private Activity activity;
    private Person person;
    private Context mContext;

    @Bind(R.id.initials)
    TextView initials;

    @Bind(R.id.txtsendvalue)
    EditText txtsendvalue;

    @Bind(R.id.name)
    TextView name;

    @Bind(R.id.phone)
    TextView phone;

    @Bind(R.id.imgPerson)
    ImageView ivImage;

    @Bind(R.id.warning_massage)
    TextView warning_massage;

    @OnClick(R.id.btn_send)
    public void sendMoney() {
        if (txtsendvalue.getText().toString().equals("") || txtsendvalue.getText().toString().isEmpty())  {
            warning_massage.setVisibility(View.VISIBLE);
        } else {
            SendMoneyPresenter sendMoneyPresenter;
            warning_massage.setVisibility(View.GONE);
            Double dValue = Double.parseDouble(txtsendvalue.getText().toString().replace("R$","").replace(",","."));
            sendMoneyPresenter = new SendMoneyPresenter(DataManager.getInstance(mContext),mContext, (sendMoneyContract.View) activity);
            sendMoneyPresenter.sendMoney(person.getId(),dValue);
            sendMoneyPresenter.LoadingOn();
            dismiss();
        }

    }

    @OnClick(R.id.btn_close)
    public void close() {
        dismiss();
    }


    public CustomDialogSendMoney(Activity activity, Person person, Context context) {
        super(activity);
        this.activity = activity;
        this.person = person;
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog_send_money);

        ButterKnife.bind(this);
        setCancelable(false);

        populateDialog();

        txtsendvalue.addTextChangedListener(new NumberFormatText(txtsendvalue));

    }

    private void populateDialog() {
        //Pulupate Dialog
        setUserImage(person.getUserimage());
        initials.setText(person.getInitials(person.getName()));
        name.setText(person.getName());
        phone.setText(person.getPhone());
    }


    private void setUserImage(int img){
        Bitmap bitmap = BitmapFactory.decodeResource(activity.getResources(), img);
        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(activity.getResources(), bitmap);
        roundedBitmapDrawable.setCircular(true);
        ivImage.setImageDrawable(roundedBitmapDrawable);
    }
}
