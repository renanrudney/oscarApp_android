package com.example.oscarapp.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.oscarapp.MainActivity;
import com.example.oscarapp.R;
import com.example.oscarapp.api.RetrofitConfig;
import com.example.oscarapp.models.AppInfo;
import com.example.oscarapp.models.Choice;
import com.example.oscarapp.models.Token;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WelcomeActivity extends AppCompatActivity {
    TextView tokenView, userView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        getChoice();
        tokenView = findViewById(R.id.tokenView);
        userView = findViewById(R.id.userView);

        String token = AppInfo.token;
        String user = AppInfo.user;
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
                if (AppInfo.remoteMovieID != 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("Você já confirmou seu voto para filme!");

                    builder.setPositiveButton(
                            "Ok",
                            (dialog, id) -> dialog.cancel());

                    AlertDialog alert = builder.create();
                    alert.show();
                } else {
                    it = new Intent(getApplicationContext(), MovieActivity.class);
                    startActivity(it);
                }
                break;
            case R.id.director_choice:
                if (AppInfo.remoteDirectorID != 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("Você já confirmou seu voto para diretor!!");

                    builder.setPositiveButton(
                            "Ok",
                            (dialog, id) -> dialog.cancel());

                    AlertDialog alert = builder.create();
                    alert.show();
                } else {
                    it = new Intent(getApplicationContext(), DirectorActivity.class);
                    startActivity(it);
                }
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

    public void getChoice() {
        Call<Choice> call = new RetrofitConfig().getChoiceService().retrieveChoice(AppInfo.user, AppInfo.token);
        call.enqueue(new Callback<Choice>(

        ) {
            @Override
            public void onResponse(Call<Choice> call, Response<Choice> response) {
                if (response.isSuccessful()) {
                    Choice choiceBody = response.body();
                    if (choiceBody != null) {
                        AppInfo.remoteMovieID = choiceBody.getRemote_movie_id();
                        AppInfo.remoteDirectorID = choiceBody.getRemote_director_id();
                        AppInfo.remoteMovieName = choiceBody.getRemote_movie_name();
                        AppInfo.remoteDirectorName = choiceBody.getRemote_director_name();
                    } else {
                        AppInfo.remoteMovieID = 0;
                        AppInfo.remoteDirectorID = 0;
                        AppInfo.remoteMovieName = null;
                        AppInfo.remoteDirectorName = null;
                    }
                }
            }

            @Override
            public void onFailure(Call<Choice> call, Throwable t) {

            }
        });
    }
}
