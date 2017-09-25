package com.example.lehiteixeira.banco_neon.Custom;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by Lehiteixeira on 06/09/17.
 */


public class CustomChartBarNeon extends RecyclerView {

    Context context;

    public CustomChartBarNeon(Context context) {
        super(context);
        this.context = context;
    }

    public CustomChartBarNeon(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomChartBarNeon(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean fling(int velocityX, int velocityY) {

        velocityX *= 0.1;
        // velocityX *= 0.7; for Horizontal recycler view. comment velocityY line not require for Horizontal Mode.

        return super.fling(velocityX, velocityY);
    }

}