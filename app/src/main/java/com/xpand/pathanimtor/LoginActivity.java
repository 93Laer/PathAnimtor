package com.xpand.pathanimtor;

import android.annotation.TargetApi;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class LoginActivity extends AppCompatActivity implements View.OnFocusChangeListener, TextWatcher {

    private ImageView img1;
    private android.support.design.widget.TextInputEditText edit1;
    private android.support.design.widget.TextInputEditText edit2;
    private AnimatedVectorDrawable anim1;
    private AnimatedVectorDrawable anim2;
    private AnimatedVectorDrawable anim3;
    private AnimatedVectorDrawable anim3_back1;
    private AnimatedVectorDrawable anim_judge;
    private AnimatedVectorDrawable anim3_back1_no_pass;
    private AnimatedVectorDrawable anim3_no_pass;
    private boolean isGoBack;
    private boolean isShowTrue;
    private boolean isPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        img1 = ((ImageView) findViewById(R.id.img1));
        edit1 = ((android.support.design.widget.TextInputEditText) findViewById(R.id.edit1));
        edit2 = ((android.support.design.widget.TextInputEditText) findViewById(R.id.edit2));
        anim1 = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.anim1);
        anim2 = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.anim2);
        anim3 = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.anim3);
        anim3_back1 = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.anim3_back1);
        anim3_back1_no_pass = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.anim3_back1_no_pass);
        anim3_no_pass = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.anim3_no_pass);
        anim_judge = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.anim_judge);
        // 设置焦点变化的监听
        edit1.setOnFocusChangeListener(this);
        edit2.setOnFocusChangeListener(this);
        // 文本变化的监听
        edit1.addTextChangedListener(this);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case R.id.edit1:
                if (hasFocus) {
                    if (isGoBack) {
                        if (isPass) {
                            isGoBack = false;
                            img1.setImageDrawable(anim3_back1);
                            anim3_back1.start();
                        } else {
                            isGoBack = false;
                            img1.setImageDrawable(anim3_back1_no_pass);
                            anim3_back1_no_pass.start();
                        }
                    } else {
                        img1.setImageDrawable(anim1);
                        anim1.start();
                    }
                }

                break;
            case R.id.edit2:
                if (hasFocus) {
                    isGoBack = true;
                    if (isPass){
                        img1.setImageDrawable(anim3);
                        anim3.start();
                    }else {
                        img1.setImageDrawable(anim3_no_pass);
                        anim3_no_pass.start();
                    }

                }
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (!TextUtils.isEmpty(editable) && edit1.getText().toString().equals("123")) {
            isPass = true;
            img1.setImageDrawable(anim2);
            anim2.start();
            isShowTrue = true;
        } else if (isShowTrue) {
            isPass = false;
            img1.setImageDrawable(anim_judge);
            anim_judge.start();
            isShowTrue = false;
        }
    }
}
