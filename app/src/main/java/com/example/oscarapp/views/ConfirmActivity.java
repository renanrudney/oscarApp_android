package com.example.oscarapp.views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oscarapp.R;
import com.example.oscarapp.api.RetrofitConfig;
import com.example.oscarapp.models.AppInfo;
import com.example.oscarapp.models.Choice;
import com.example.oscarapp.models.Token;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConfirmActivity extends AppCompatActivity {
    TextView confirmUserText, confirmDirectorText, confirmMovieText;
    String directorMessage, movieMessage;
    Button confirmButton;
    EditText editConfirmToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
        setTitle("OscarApp - Seus votos");

        confirmUserText = findViewById(R.id.confirmUserText);
        confirmDirectorText = findViewById(R.id.confirmDirectorText);
        confirmMovieText = findViewById(R.id.confirmMovieText);
        confirmButton = findViewById(R.id.confirmButton);
        editConfirmToken = findViewById(R.id.editConfirmToken);

        confirmUserText.setText(AppInfo.user);

        if (AppInfo.remoteDirectorName != null)
            directorMessage = AppInfo.remoteDirectorName;
        else if (AppInfo.selectedDirector != null)
            directorMessage = AppInfo.selectedDirector.getNome();
        else
            directorMessage = "Você ainda nâo votou em um Diretor.";

        if (AppInfo.remoteMovieName != null)
            movieMessage = AppInfo.remoteMovieName;
        else if (AppInfo.selectedMovie != null)
            movieMessage = AppInfo.selectedMovie.getNome();
        else
            movieMessage = "Você ainda nâo votou em um Filme.";

        if (AppInfo.remoteDirectorID != 0 && AppInfo.remoteMovieID != 0) {
            editConfirmToken.setVisibility(View.INVISIBLE);
            confirmButton.setVisibility(View.INVISIBLE);
        } else if (AppInfo.selectedMovie != null && AppInfo.selectedDirector != null)
            confirmButton.setEnabled(true);
        else
            confirmButton.setEnabled(false);

        confirmDirectorText.setText(directorMessage);
        confirmMovieText.setText(movieMessage);
    }

    public void sendChoices(View view) {
        String token = editConfirmToken.getText().toString();
        if (token.length() == 0)
            Toast.makeText(this, "Insira um token!", Toast.LENGTH_SHORT).show();
        else {
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Enviando votos...");
            progressDialog.show();
            String login = AppInfo.user;
            int selectedDirectorID = AppInfo.selectedDirector.getId();
            int selectedMovieID = AppInfo.selectedMovie.getId();
            String selectedDirectorName = AppInfo.selectedDirector.getNome();
            String selectedMovieName = AppInfo.selectedMovie.getNome();
            Choice choice = new Choice(selectedDirectorID, selectedMovieID, selectedDirectorName, selectedMovieName);
            Call<Choice> call = new RetrofitConfig().getChoiceService().sendChoice(login, token, choice);
            call.enqueue(new Callback<Choice>() {
                @Override
                public void onResponse(Call<Choice> call, Response<Choice> response) {
                    progressDialog.dismiss();
                    if (response.isSuccessful()) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(ConfirmActivity.this);
                        builder.setMessage("Seu voto foi registrado com sucesso!");

                        builder.setPositiveButton(
                                "OK",
                                (dialog, id) -> {
                                    dialog.cancel();
                                    Intent welcomeIt = new Intent(getApplicationContext(), WelcomeActivity.class);
                                    startActivity(welcomeIt);
                                    finish();
                                });

                        AlertDialog alert = builder.create();
                        alert.show();

                    } else if(response.code() == 401) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(ConfirmActivity.this);
                        builder.setMessage("Seu token está inválido!");

                        builder.setPositiveButton(
                                "OK",
                                (dialog, id) -> {
                                    dialog.cancel();
                                });

                        AlertDialog alert = builder.create();
                        alert.show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Ocorreu um erro no servidor, tente novamente mais tarde!", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<Choice> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Ocorreu um erro inesperado, tente novamente mais tarde!", Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}