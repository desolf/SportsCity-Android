package com.n1.city.ui.signing;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;

import com.n1.city.R;
import com.n1.city.base.N1ActivityBase;

public class ForgotPasswordActivity extends N1ActivityBase {

    private enum CaptchaState { INIT, DOING, DONE}

    CaptchaState state = null;
    ImageButton robotButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        setupUI();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void setupUI() {
        robotButton = findViewById(R.id.btn_robot);

        updateRobotState();
    }

    public void onClick_Captcha(View view) {
        updateRobotState();
    }

    public void onClick_Submit(View view) {
        // TODO
        Intent intent = new Intent(ForgotPasswordActivity.this, ResetPasswordEmailSentActivity.class);
        startActivity(intent);
        finish();
    }

    private void updateRobotState() {
        if (state == null) {
            state = CaptchaState.INIT;
            robotButton.setImageResource(R.drawable.recaptcha_init);
            findViewById(R.id.progbar_waiting).setVisibility(View.INVISIBLE);
            findViewById(R.id.btn_reset_password).setEnabled(false);
            return;
        }

        if (state == CaptchaState.INIT) {
            state = CaptchaState.DOING;
            robotButton.setImageResource(R.drawable.recaptcha_doing);
            findViewById(R.id.progbar_waiting).setVisibility(View.VISIBLE);
            robotButton.setEnabled(false);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    updateRobotState();
                }
            }, 2000);
            return;
        }

        if (state == CaptchaState.DOING) {
            state = CaptchaState.DONE;
            robotButton.setImageResource(R.drawable.recaptcha_done);
            findViewById(R.id.progbar_waiting).setVisibility(View.INVISIBLE);
            findViewById(R.id.btn_reset_password).setEnabled(true);
            return;
        }
    }
}
