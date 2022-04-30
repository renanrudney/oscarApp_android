package com.example.oscarapp.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.oscarapp.MainActivity;
import com.example.oscarapp.R;
import com.example.oscarapp.models.Choice;

public class WelcomeActivity extends AppCompatActivity {
    TextView tokenView, userView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        tokenView = findViewById(R.id.tokenView);
        userView = findViewById(R.id.userView);

        String token = Choice.token;
        String user = Choice.user;
        tokenView.setText(token);
        userView.setText(user);
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
            case R.id.movie_choice:
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
            case R.id.exit:
                it = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(it);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}