package com.n1.city.ui.signing;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.n1.city.R;
import com.n1.city.base.N1ActivityBase;
import com.n1.city.ui.HomeActivity;
import com.n1.city.utils.UIHelper;

public class LoginActivity extends N1ActivityBase {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setupUI();
    }

    void setupUI() {
        UIHelper.setUnderlineFor((Button) findViewById(R.id.btn_forgot_password));
    }

    public void onClick_SignUp(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    public void onClick_Login(View view) {
        // TODO
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void onClick_ForgotPassword(View view) {
        Intent intent = new Intent(this, ForgotPasswordActivity.class);
        startActivity(intent);
    }
}
