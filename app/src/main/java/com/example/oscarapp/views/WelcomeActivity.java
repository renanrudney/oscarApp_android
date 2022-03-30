package com.example.oscarapp.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.oscarapp.R;

public class WelcomeActivity extends AppCompatActivity {
    TextView tokenView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        tokenView = findViewById(R.id.tokenView);

        Intent it = getIntent();
        String token = it.getStringExtra("token");
        tokenView.setText(token);
    }
}