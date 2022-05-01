package com.example.oscarapp.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.oscarapp.R;
import com.example.oscarapp.helpers.ImageAsyncTask;
import com.example.oscarapp.models.AppInfo;
import com.example.oscarapp.models.Movie;

public class DetailMovieActivity extends AppCompatActivity {
    ImageView imageView;
    TextView nomeText, generoText;
    ProgressBar progressBar;
    Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        Intent it = getIntent();
        movie = (Movie) it.getSerializableExtra("movie");

        imageView = findViewById(R.id.detailImage);
        nomeText = findViewById(R.id.detailNameText);
        generoText = findViewById(R.id.detailGenderText);
        progressBar = findViewById(R.id.progressBarDetail);
        nomeText.setText(movie.getNome());
        generoText.setText(movie.getGenero());

        ImageAsyncTask task = new ImageAsyncTask(progressBar, imageView);
        task.execute(movie.getFoto());
    }

    public void choiceMovie(View view) {
        AppInfo.selectedMovie = movie;
        Intent it = new Intent(getApplicationContext(), WelcomeActivity.class);
        startActivity(it);
        finish();
    }
}
