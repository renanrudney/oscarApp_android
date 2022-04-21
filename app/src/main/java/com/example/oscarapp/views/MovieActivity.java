package com.example.oscarapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.oscarapp.R;
import com.example.oscarapp.adapters.AdapterMovie;
import com.example.oscarapp.models.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieActivity extends AppCompatActivity {
    private RecyclerView recyclerViewMovie;
    private List<Movie> listMovie = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        recyclerViewMovie = findViewById(R.id.recyclerViewMovie);

        Movie obj = new Movie(1, "example", "example", "example");
        listMovie.add(obj);

        AdapterMovie adapter = new AdapterMovie(listMovie);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewMovie.setLayoutManager(layoutManager);
        recyclerViewMovie.setHasFixedSize(true);
        recyclerViewMovie.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerViewMovie.setAdapter(adapter);
    }
}