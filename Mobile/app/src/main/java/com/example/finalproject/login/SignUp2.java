package com.example.finalproject.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finalproject.R;
import com.example.finalproject.camera.FaceRec_reg;

public class SignUp2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2);
    }


    public void btn_yes(View view){
        Intent intent = new Intent(SignUp2.this, FaceRec_reg.class);
        startActivity(intent);
        finish();

    }

    public void btn_no(View view){
        Intent intent = new Intent(SignUp2.this, LoginActivity.class);
        startActivity(intent);
        finish();

    }

}
