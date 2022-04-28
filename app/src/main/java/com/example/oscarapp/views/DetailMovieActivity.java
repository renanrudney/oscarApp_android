package com.example.oscarapp.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.oscarapp.R;
import com.example.oscarapp.helpers.ImageAsyncTask;

public class DetailMovieActivity extends AppCompatActivity {
    ImageView imageView;
    TextView nomeText, generoText;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        Intent it = getIntent();
        Bundle params = it.getExtras();
        String name = params.getString("name");
        String gender = params.getString("gender");
        String photo_url = params.getString("photo_url");

        imageView = findViewById(R.id.detailImage);
        nomeText = findViewById(R.id.detailNameText);
        generoText = findViewById(R.id.detailGenderText);
        progressBar = findViewById(R.id.progressBarDetail);
        nomeText.setText(name);
        generoText.setText(gender);

        ImageAsyncTask task = new ImageAsyncTask(progressBar, imageView);
        task.execute(photo_url);
    }
}