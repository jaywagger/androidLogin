package com.example.finalproject.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finalproject.R;

public class TemperatureControllActivity extends AppCompatActivity {
    Button btn; // 현재온도 up 버튼
    Button btn2; // 현재온도 down버튼
    TextView txt; //현재온도
    TextView txt2; //설정온도

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature_controll);
        btn = findViewById(R.id.temperature_btnup);
        btn2 = findViewById(R.id.temperature_btndown);
        txt = findViewById(R.id.now_temperature);
        txt.setText("27도");
        txt2 = findViewById(R.id.update_temperature);
        txt2.setText(txt.getText().toString());
        Intent intent = getIntent();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt2.setText(plus(txt2.getText().toString()));
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt2.setText(minus(txt2.getText().toString()));

            }
        });
    }
    public String plus (String cel){
        double tmp =Double.parseDouble(cel.split("도")[0])+0.5;

        return Double.toString(tmp) +"도";
    }
    public String minus (String cel){
        double tmp =Double.parseDouble(cel.split("도")[0])-0.5;

        return Double.toString(tmp) +"도";
    }
}
