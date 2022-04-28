package com.example.oscarapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.oscarapp.R;
import com.example.oscarapp.adapters.AdapterMovie;
import com.example.oscarapp.api.ExternalConfig;
import com.example.oscarapp.api.RetrofitConfig;
import com.example.oscarapp.helpers.RecyclerItemClickListener;
import com.example.oscarapp.models.Movie;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieActivity extends AppCompatActivity {
    private RecyclerView recyclerViewMovie;
    private List<Movie> listMovie = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        setTitle("OscarApp - Filmes indicados");
        recyclerViewMovie = findViewById(R.id.recyclerViewMovie);

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Baixando filmes...");
        progressDialog.show();

        Call<List<Movie>> call = new ExternalConfig().getMovieService().getAllMovies();
        call.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                if(response.isSuccessful()) {
                    listMovie = response.body();
                    AdapterMovie adapter = new AdapterMovie(listMovie);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerViewMovie.setLayoutManager(layoutManager);
                    recyclerViewMovie.setHasFixedSize(true);
                    recyclerViewMovie.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
                    recyclerViewMovie.setAdapter(adapter);
                    recyclerViewMovie.addOnItemTouchListener(
                            new RecyclerItemClickListener(
                                    getApplicationContext(),
                                    recyclerViewMovie,
                                    new RecyclerItemClickListener.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(View view, int position) {
                                            Movie obj = listMovie.get(position);
                                            Intent it = new Intent(getApplicationContext(), DetailMovieActivity.class);
                                            Bundle params = new Bundle();
                                            params.putString("name", obj.getNome());
                                            params.putString("gender", obj.getGenero());
                                            params.putString("photo_url", obj.getFoto());
                                            it.putExtras(params);
                                            startActivity(it);
                                        }
                                        @Override
                                        public void onLongItemClick(View view, int position) {
                                        }
                                        @Override
                                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                        }
                                    }
                            )
                    );
                    progressDialog.dismiss();
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Ocorreu um erro no servidor, tente novamente mais tarde!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Ocorreu um erro inesperado, tente novamente mais tarde!", Toast.LENGTH_LONG).show();
            }
        });
    }
}