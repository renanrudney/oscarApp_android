package com.example.oscarapp.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.welcome_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent it;
        switch (item.getItemId()) {
            case R.id.film_choice:
                it = new Intent(getApplicationContext(), MovieActivity.class);
                startActivity(it);
                break;
            case R.id.director_choice:
                it = new Intent(getApplicationContext(), DirectorActivity.class);
                startActivity(it);
                break;
            case R.id.confirm_choice:
                it = new Intent(getApplicationContext(), ConfirmActivity.class);
                startActivity(it);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}