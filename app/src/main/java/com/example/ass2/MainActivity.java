package com.example.ass2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText edt1,edt2;
    Button btn1,btn2;
    CheckBox chk;
    static final String NAME="NAME";
    static final String PASSWORD="PASSWORD";
    static final String FLAG ="FLAG";
    boolean flag=false;
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt1=findViewById(R.id.username);
        edt2=findViewById(R.id.password);
        btn1=findViewById(R.id.loginBtn);
        btn2=findViewById(R.id.signbtn);
        chk=findViewById(R.id.ckRememberMe);
        setupSharedPrefs();
        checkPref();
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=edt1.getText().toString();
                String  password=edt2.getText().toString();


                if(chk.isChecked()){
                    if(flag){
                        editor.putString(NAME,name);
                        editor.putString(PASSWORD,password);
                        editor.putBoolean(FLAG,true);
                        editor.commit();
                    }
                }
                else {
                    editor.remove(NAME);
                    editor.remove(PASSWORD);
                    editor.remove(FLAG);
                    editor.apply();
                }
                Intent appsIntent =new Intent(MainActivity.this, Apps.class);
                startActivity(appsIntent);

            }

        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regIntent =new Intent(MainActivity.this,regstraion.class);

                finish();
                startActivity(regIntent);
            }
        });

    }
    public void setupSharedPrefs() {
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        editor = pref.edit();
    }
        public void checkPref(){
        flag=pref.getBoolean(FLAG,false);
        if(flag){
            String name = pref.getString(NAME, "");
            String password = pref.getString(PASSWORD, "");
            edt1.setText(name);
            edt2.setText(password);
            chk.setChecked(true);
        }
    }
}




