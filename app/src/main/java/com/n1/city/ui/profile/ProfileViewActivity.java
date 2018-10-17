package com.n1.city.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.n1.city.R;
import com.n1.city.base.N1ActivityBase;
import com.n1.city.ui.HomeActivity;
import com.n1.city.utils.UIHelper;

public class ProfileViewActivity extends N1ActivityBase {

    boolean isFromSignUp = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);

        isFromSignUp = getIntent().getBooleanExtra("sign_up_processing", false);

        setupUI();
    }

    private void setupUI() {
        UIHelper.setUnderlineFor((Button) findViewById(R.id.btn_change_password));
    }

    @Override
    public void onBackPressed() {
        if (isFromSignUp) {
            Intent intent = new Intent(ProfileViewActivity.this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

            finish();

        } else {
            super.onBackPressed();
        }
    }

    public void onClick_EditProfile(View view) {
        Intent intent = new Intent(ProfileViewActivity.this, ProfileEditActivity.class);
        startActivity(intent);
    }

    public void onClick_ChangePassword(View view) {
        // TODO
        Intent intent = new Intent(ProfileViewActivity.this, ChangePasswordActivity.class);
        startActivity(intent);
    }
}
