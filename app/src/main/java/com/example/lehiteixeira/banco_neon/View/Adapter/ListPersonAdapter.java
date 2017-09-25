package com.example.lehiteixeira.banco_neon.View.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lehiteixeira.banco_neon.Custom.CustomDialogSendMoney;
import com.example.lehiteixeira.banco_neon.Data.Model.Person;
import com.example.lehiteixeira.banco_neon.R;
import com.example.lehiteixeira.banco_neon.Util.Util;

import java.text.NumberFormat;
import java.util.ArrayList;


public class ListPersonAdapter extends RecyclerView.Adapter<ListPersonAdapter.MyViewHolder>  {

    private ArrayList<Person> list_person;

    public Context mcontext;
    public  Activity mactivity;
    public static Bitmap b;

    private NumberFormat nf = NumberFormat.getCurrencyInstance();

    public ListPersonAdapter(ArrayList<Person> persons, Context context, Activity activity) {
        list_person = persons;
        mcontext = context;
        mactivity = activity;
    }

    // Called when RecyclerView needs a new RecyclerView.ViewHolder of the given type to represent an item.
    @Override
    public ListPersonAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                               int viewType) {
        // create a layout
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.custom_list_person_item, null);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    // Called by RecyclerView to display the data at the specified position.
    @Override
    public void onBindViewHolder(final MyViewHolder viewHolder, final int position ) {

        String str = nf.format(Double.parseDouble(list_person.get(position).getValue().toString()));
        //Poupulate views
        viewHolder.initials.setText(list_person.get(position).getInitials(list_person.get(position).getName().toString()));
        viewHolder.name.setText(list_person.get(position).getName().toString());
        viewHolder.phone.setText(list_person.get(position).getPhone().toString());
        viewHolder.value.setText(str);
        // set de rounded image or initials
        if (list_person.get(position).getUserimage() == 0){
             viewHolder.userimg.setVisibility(View.GONE);
        }else{
            viewHolder.userimg.setImageDrawable(Util.cutRooundedImage(mactivity,list_person.get(position).getUserimage()));
        }
        //Dialog send money it's call only in SendMoneyActivity screen
        if (mactivity.getLocalClassName().equals("View.Activity.SendMoneyActivity")){
            viewHolder.value.setVisibility(View.GONE);
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

            CustomDialogSendMoney cdd=new CustomDialogSendMoney(mactivity,list_person.get(position),mcontext);
            cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            cdd.getWindow().getAttributes().windowAnimations = R.style.animationdialog;
            cdd.show();

                }
            });
        }
    }

    // initializes some private fields to be used by RecyclerView.
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView initials;
        public TextView name;
        public TextView phone;
        public TextView value;
        public ImageView userimg;


        public MyViewHolder(View itemLayoutView) {
            super(itemLayoutView);

            initials = (TextView) itemLayoutView.findViewById(R.id.initials);
            name = (TextView) itemLayoutView.findViewById(R.id.name);
            phone = (TextView) itemLayoutView.findViewById(R.id.phone);
            value = (TextView) itemLayoutView.findViewById(R.id.value);
            userimg = (ImageView) itemLayoutView.findViewById(R.id.imgListPerson);

        }
    }

    //Returns the total number of items in the data set hold by the adapter.
    @Override
    public int getItemCount() {
        return list_person.size();
    }

}