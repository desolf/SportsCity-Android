package com.n1.city.base;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.n1.city.R;
import com.n1.city.application.N1Application;
import com.n1.city.model.User;

public abstract class N1ActivityBase extends AppCompatActivity {

    protected String TAG = "N1-City";

    private View maskView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    protected void onResume() {
        super.onResume();
        makeAllButtonsForAlphaPressed((ViewGroup) findViewById(R.id.rootLayout));
    }

    public N1Application getN1Application() {
        return (N1Application) getApplicationContext();
    }

    public void onClick_Back(View view) {
        onBackPressed();
    }

    private View.OnTouchListener viewTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN: {
                    view.setAlpha(0.5f);
                    break;
                }
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL: {
                    view.setAlpha(1f);
                    break;
                }
            }
            return false;
        }
    };

    protected void makeAllButtonsForAlphaPressed(ViewGroup viewGroup) {
        for (int i = 0; viewGroup != null && i < viewGroup.getChildCount(); i++) {
            View childView = viewGroup.getChildAt(i);
            if (childView instanceof Button)
                makeViewForAlphaPressed(childView);
            if (childView instanceof ImageButton)
                makeViewForAlphaPressed(childView);
            if (childView instanceof ViewGroup)
                makeAllButtonsForAlphaPressed((ViewGroup) childView);
        }
    }

    protected void makeViewForAlphaPressed(View v) {
        v.setOnTouchListener(viewTouchListener);
    }

    protected void makeViewForAlphaPressed(int res_id) {
        findViewById(res_id).setOnTouchListener(viewTouchListener);
    }

    protected void showLoadingDialog() {
        ConstraintLayout rootLayout = findViewById(R.id.rootLayout);

        if (maskView == null) {
            maskView = new View(this);

            maskView.setBackgroundColor(Color.argb(100, 0, 0, 0));
            ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.MATCH_PARENT);

            rootLayout.addView(maskView, layoutParams);

            maskView.setVisibility(View.GONE);

        }
        if (progressBar == null) {
            progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleLarge);

            ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(100, 100);
            layoutParams.topToTop = R.id.rootLayout;
            layoutParams.bottomToBottom = R.id.rootLayout;
            layoutParams.leftToLeft = R.id.rootLayout;
            layoutParams.rightToRight = R.id.rootLayout;
            layoutParams.matchConstraintPercentHeight = 50;
            layoutParams.matchConstraintPercentWidth = 50;

            rootLayout.addView(progressBar, layoutParams);

            progressBar.setVisibility(View.GONE);
        }

        maskView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);

    }

    protected void dismissLoadingDialog() {
        maskView.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
    }

    protected User getCurrentUser() {
        return getN1Application().getCurrentUser();
    }

    protected void enterFullscreen(boolean fullscreen) {
        View decorView = getWindow().getDecorView();

        if (fullscreen) {

            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_IMMERSIVE
                            // Set the content to appear under the system bars so that the
                            // content doesn't resize when the system bars hide and show.
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            // Hide the nav bar and status bar
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN);
        } else {
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
    }

    protected void setText(int res_id, String text) {
        View view = findViewById(res_id);

        if (view.getClass().equals(AppCompatTextView.class))
            ((AppCompatTextView) view).setText(text);
        else if (view.getClass().equals(AppCompatEditText.class))
            ((AppCompatEditText) view).setText(text);

    }

    protected void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(this);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
