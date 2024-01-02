package com.example.ass2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Apps extends AppCompatActivity {
    Button btn1,btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apps);
        btn1=findViewById(R.id.firstApp);
        btn2=findViewById(R.id.secondApp);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent first_app=new Intent(Apps.this,firstApp.class);
                startActivity(first_app);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent second_app=new Intent(Apps.this, secondApp.class);
                startActivity(second_app);
            }
        });

    }
}