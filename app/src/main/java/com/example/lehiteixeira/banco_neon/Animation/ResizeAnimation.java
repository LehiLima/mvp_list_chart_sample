package com.example.lehiteixeira.banco_neon.Animation;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;

/**
 * Created by Lehiteixeira on 05/09/17.
 */

public class ResizeAnimation extends Animation {
    final int startHight;
    final Float targetHight;
    ImageView view;

    public ResizeAnimation(ImageView view, Float targetHight) {
        this.view = view;
        this.targetHight = targetHight;
        startHight = view.getWidth();
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        int newHight = (int) (startHight + (targetHight - startHight) * interpolatedTime);
        view.getLayoutParams().height = newHight;
        view.requestLayout();
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
    }

    @Override
    public boolean willChangeBounds() {
        return true;
    }
}