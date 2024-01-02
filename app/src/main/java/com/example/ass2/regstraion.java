package com.example.ass2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class regstraion extends AppCompatActivity {

    EditText userTxt;
    EditText emailTxt;
    EditText passTxt;
    RadioGroup genderG;
    Button register;
    Button goToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regstraion);

        userTxt = findViewById(R.id.username);
        emailTxt = findViewById(R.id.email);
        passTxt = findViewById(R.id.password);
        genderG = findViewById(R.id.genderGroup);
        register = findViewById(R.id.registerBtn);
        goToLogin = findViewById(R.id.goToLoginButton);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        goToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backLogin = new Intent(regstraion.this, MainActivity.class);
                startActivity(backLogin);
            }
        });
    }

    public void registerUser() {
        String username = userTxt.getText().toString();
        String email = emailTxt.getText().toString();
        String password = passTxt.getText().toString();
        int selectedId = genderG.getCheckedRadioButtonId();
        RadioButton selectedGender = findViewById(selectedId);
        String gender = selectedGender != null ? selectedGender.getText().toString() : "";

        if (validateInput(username, email, password, gender)) {
            Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show();
            goToLogin.setVisibility(View.VISIBLE);
        } else {
            Toast.makeText(this, "Please fill all fields correctly", Toast.LENGTH_SHORT).show();
        }
    }
    public boolean validateInput(String username, String email, String password, String gender) {
        return !(username.isEmpty() || email.isEmpty() || password.isEmpty() || gender.isEmpty());
    }
}
