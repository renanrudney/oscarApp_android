package com.example.oscarapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oscarapp.api.RetrofitConfig;
import com.example.oscarapp.models.Token;
import com.example.oscarapp.models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    EditText loginInput;
    EditText passwordInput;
    String login, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getData(View view) {
        loginInput = findViewById(R.id.editLoginInput);
        passwordInput = findViewById(R.id.editPasswordInput);

        if (loginInput.length() != 0 && passwordInput.length() != 0) {
            login = loginInput.getText().toString();
            password = passwordInput.getText().toString();

            User user = new User(login, password);

            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Consultando token");
            progressDialog.show();

            Call<Token> call = new RetrofitConfig().getUserService().getToken(user);
            call.enqueue(new Callback<Token>() {
                @Override
                public void onResponse(Call<Token> call, Response<Token> response) {
                    if (response.isSuccessful()) {
                        Token token = response.body();
                        progressDialog.dismiss();
//                        String filteredToken = String.valueOf(token.getToken());

                    } else if(response.code() == 403) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Credenciais inválidas!", Toast.LENGTH_SHORT).show();
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Ocorreu um erro no servidor, tente novamente mais tarde!", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<Token> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Ocorreu um erro inesperado, tente novamente mais tarde!", Toast.LENGTH_LONG).show();
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), "Preencha todos os campos \uD83D\uDE21", Toast.LENGTH_LONG).show();
        }
    }
}
