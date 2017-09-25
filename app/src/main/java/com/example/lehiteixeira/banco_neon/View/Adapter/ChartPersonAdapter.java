package com.example.lehiteixeira.banco_neon.View.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lehiteixeira.banco_neon.Animation.ResizeAnimation;
import com.example.lehiteixeira.banco_neon.Data.Model.Person;
import com.example.lehiteixeira.banco_neon.R;
import com.example.lehiteixeira.banco_neon.Util.Util;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;


public class ChartPersonAdapter extends RecyclerView.Adapter<ChartPersonAdapter.MyViewHolder>  {

    private ArrayList<Person> list_person;
    // Pega a formatacao do sistema, se for brasil R$ se EUA US$
    private NumberFormat nf = NumberFormat.getCurrencyInstance();

    public Context mcontext;
    public  Activity mactivity;
    public static Bitmap b;

    public ChartPersonAdapter(ArrayList<Person> persons, Context context, Activity activity) {
        list_person = persons;
        mcontext = context;
        mactivity = activity;
    }

    // Called when RecyclerView needs a new RecyclerView.ViewHolder of the given type to represent an item.
    @Override
    public ChartPersonAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                              int viewType) {
        // create a layout
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.custom_bar_chart_item, null);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    // Called by RecyclerView to display the data at the specified position.
    @Override
    public void onBindViewHolder(final MyViewHolder viewHolder, final int position ) {
        //format currency
        String str = nf.format(Double.parseDouble(list_person.get(position).getValue().toString()));
        String strCurency = str.replace("R$","");
        //set values
        viewHolder.initials.setText(list_person.get(position).getInitials(list_person.get(position).getName().toString()));
        viewHolder.value.setText(strCurency);
        // set de rounded image
        viewHolder.userimg.setImageDrawable(Util.cutRooundedImage(mactivity,list_person.get(position).getUserimage()));
        //find progress
        float progress = 550 * (Float.parseFloat(list_person.get(position).getValue().toString()) / getMaxvalue(list_person)) ;
        viewHolder.progress.getLayoutParams().height = 0;
        //scale and progress animation
        ChartAnimation(viewHolder.relativeLayoutImage,viewHolder.progress, progress,0f,1f,0f,1f);
    }

    // initializes some private fields to be used by RecyclerView.
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView initials;
        public TextView value;
        public ImageView userimg;
        public ImageView progress;
        public RelativeLayout relativeLayoutImage;

        public MyViewHolder(View itemLayoutView) {
            super(itemLayoutView);

            initials = (TextView) itemLayoutView.findViewById(R.id.initials);
            value = (TextView) itemLayoutView.findViewById(R.id.value);
            userimg = (ImageView) itemLayoutView.findViewById(R.id.imgListPerson);
            progress = (ImageView) itemLayoutView.findViewById(R.id.chartprogress);
            relativeLayoutImage = (RelativeLayout) itemLayoutView.findViewById(R.id.relative);
        }
    }

    //Returns the total number of items in the data set hold by the adapter.
    @Override
    public int getItemCount() {
        return list_person.size();
    }


    private Float getMaxvalue(ArrayList<Person> list_person){

        ArrayList<Float> maxvalue = new ArrayList<Float>();

        for (Person p: list_person){
            maxvalue.add(Float.parseFloat(p.getValue().toString()));
        }

        return Collections.max(maxvalue);
    }

    public void ChartAnimation(RelativeLayout v, final ImageView barprogress, final Float progress, float startScaleX, float endScaleX, float startScaleY, float endScaleY) {
        Animation anim = new ScaleAnimation(
                startScaleX, endScaleX, // Start and end values for the X axis scaling
                startScaleY, endScaleY, // Start and end values for the Y axis scaling
                Animation.RELATIVE_TO_SELF, 0.5f, // Pivot point of X scaling
                Animation.RELATIVE_TO_SELF, 0.5f); // Pivot point of Y scaling
        anim.setFillAfter(true); // Needed to keep the result of the animation
        anim.setDuration(200);

        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // bar growing animation
                ResizeAnimation resizeAnimation = new ResizeAnimation(barprogress, progress);
                resizeAnimation.setDuration(200);
                barprogress.startAnimation(resizeAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                Toast.makeText(mactivity.getBaseContext() , " Repeat ",Toast.LENGTH_LONG);
            }
        });
        v.startAnimation(anim);
    }


}