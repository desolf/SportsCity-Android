package com.n1.city.ui.profile;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.n1.city.R;
import com.n1.city.base.N1ActivityBase;
import com.n1.city.model.Profile;
import com.n1.city.ui.HomeActivity;

public class ProfileEditActivity extends N1ActivityBase {

    Profile profile;

    boolean isFromSignUp = false;

    ImageView imgAvatar;

    EditText edtRealName;
    EditText edtPhoneNumber;
    EditText edtHeight;
    EditText edtWeight;
    EditText edtPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);

        isFromSignUp = getIntent().getBooleanExtra("sign_up_processing", false);
        profile = new Profile();

        setupUI();
    }

    private void setupUI() {
        imgAvatar = findViewById(R.id.img_profile_avatar);

        edtRealName = findViewById(R.id.edt_profile_realname);
        edtPhoneNumber = findViewById(R.id.edt_profile_phone);
        edtHeight = findViewById(R.id.edt_profile_height);
        edtWeight = findViewById(R.id.edt_profile_weight);
        edtPosition = findViewById(R.id.edt_profile_position);

        edtPosition.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                    pickPosition();
            }
        });
        edtPosition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickPosition();
            }
        });
    }

    void pickPosition() {

        String currentPosition = edtPosition.getText().toString();

        String[] positionNames = {"控球後衛（PG）", "得分後衛（SG）", "小前鋒（SF）", "大前鋒（PF）", "中鋒(C)"};
        final String[] positionShortNames = {"PG", "SG", "SF", "PF", "C"};
        final boolean[] checkedItems = {
                currentPosition.contains("PG"),
                currentPosition.contains("SG"),
                currentPosition.contains("SF"),
                currentPosition.contains("PF"),
                currentPosition.contains("C")
        };

        new AlertDialog.Builder(ProfileEditActivity.this)
                .setTitle("選擇位置")
                .setMultiChoiceItems(positionNames, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        checkedItems[which] = isChecked;
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("確認", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String newPosition = "";
                        for(int i=0;i<checkedItems.length;i++) {
                            if (checkedItems[i]) {
                                newPosition += newPosition.isEmpty() ? positionShortNames[i] : ","+positionShortNames[i];
                            }
                        }

                        edtPosition.setText(newPosition);

                        dialog.dismiss();
                    }
                })
                .show();

    }


    @Override
    public void onClick_Back(View view) {
        if (isFromSignUp) {
            Intent intent = new Intent(ProfileEditActivity.this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

            finish();

        } else {
            super.onBackPressed();
        }
    }

    public void onClick_Submit(View view) {
        //TODO
        Intent intent = new Intent(ProfileEditActivity.this, ProfileViewActivity.class);
        intent.putExtra("sign_up_processing", isFromSignUp);
        startActivity(intent);

        finish();
    }
}
