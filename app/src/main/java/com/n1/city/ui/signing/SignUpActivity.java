package com.n1.city.ui.signing;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.n1.city.R;
import com.n1.city.base.N1ActivityBase;
import com.n1.city.ui.profile.ProfileEditActivity;

public class SignUpActivity extends N1ActivityBase {

    boolean isFromSignUp = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        setupUI();
    }

    private void setupUI() {

    }

    public void onClick_Submit(View view) {
        // TODO
        Intent intent = new Intent(SignUpActivity.this, ProfileEditActivity.class);
        intent.putExtra("sign_up_processing", true);
        startActivity(intent);
    }
}
