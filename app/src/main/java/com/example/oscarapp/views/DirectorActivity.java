package com.example.oscarapp.views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.oscarapp.R;
import com.example.oscarapp.api.ExternalConfig;
import com.example.oscarapp.models.Director;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DirectorActivity extends AppCompatActivity {
    RadioGroup directorGroup;
    private List<Director> listDirector = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_director);
        setTitle("OscarApp - Diretores");
        directorGroup = findViewById(R.id.radioGroupDirector);

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Baixando diretores...");
        progressDialog.show();

        Call<List<Director>> call = new ExternalConfig().getDirectorService().getAllDirectors();
        call.enqueue(new Callback<List<Director>>() {
            @Override
            public void onResponse(Call<List<Director>> call, Response<List<Director>> response) {
                if(response.isSuccessful()) {
                    listDirector = response.body();
                    RadioButton btn;
                    directorGroup.setOrientation(LinearLayout.VERTICAL);
                    for (final Director director : listDirector) {
                        btn = new RadioButton(DirectorActivity.this);
                        btn.setId(View.generateViewId());
                        btn.setText(director.getNome());
                        directorGroup.addView(btn);
                    }
                    progressDialog.dismiss();
                } else {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Ocorreu um erro no servidor, tente novamente mais tarde!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Director>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Ocorreu um erro inesperado, tente novamente mais tarde!", Toast.LENGTH_LONG).show();
            }
        });

    }
}