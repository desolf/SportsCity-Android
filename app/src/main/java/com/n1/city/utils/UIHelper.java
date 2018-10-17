package com.n1.city.utils;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.n1.city.R;


public class UIHelper {

    public static void setUnderlineFor(Button button) {

        button.setPaintFlags(button.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }


    public static void shakeView(Context context, View view) {
        final Animation animShake = AnimationUtils.loadAnimation(context, R.anim.shake);

        view.startAnimation(animShake);
    }

    public static void shakeViewById(Context context, int resource_id) {
        shakeView(context, ((AppCompatActivity)context).findViewById(resource_id));
    }

    public static void focusView(Context context, View view) {
        view.setFocusable(true);
        view.requestFocus();
    }

    public static void focusViewById(Context context, int resource_id) {
        focusView(context, ((AppCompatActivity)context).findViewById(resource_id));
    }

    public static void shakeAndFocusView(Context context, View view) {
        shakeView(context, view);
        focusView(context, view);
    }

    public static void shakeAndFocusViewById(Context context, int resource_id) {
        shakeViewById(context, resource_id);
        focusViewById(context, resource_id);
    }
}
