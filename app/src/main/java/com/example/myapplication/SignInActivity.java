package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.models.UserData;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

import com.example.myapplication.models.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignInActivity extends AppCompatActivity {

    TextView textView;
    EditText nameTxt;
    EditText surnameTxt;
    EditText mailTxt;
    EditText passTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        nameTxt = findViewById(R.id.editName);
        surnameTxt = findViewById(R.id.editSurname);
        mailTxt = findViewById(R.id.editMail);
        passTxt = findViewById(R.id.editPass);
    }

    public void buttonLOG(android.view.View v) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void buttonSIGN(android.view.View v) {
        if (nameTxt.getText().toString().isEmpty() || surnameTxt.getText().toString().isEmpty() || mailTxt.getText().toString().isEmpty() || passTxt.getText().toString().isEmpty()) {
            Toast.makeText(SignInActivity.this, "Please enter all the values", Toast.LENGTH_SHORT).show();

            return;
        }
        postData(nameTxt.getText().toString(), surnameTxt.getText().toString(), mailTxt.getText().toString(), passTxt.getText().toString());
    }

    private void postData(String name, String surname, String mail, String pass) {



        //Log.i("Grup3", postData);
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(RetrofitAPI.BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
        RetrofitAPI gerritAPI = retrofit.create(RetrofitAPI.class);
        Call<User> call = gerritAPI.add(new UserData(name, surname, mail, pass));
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                int variable = response.code();
                Log.i("REGISTER CODE", ":" + variable);
                if (response.isSuccessful()) {
                    User user = response.body();
                    String userNom = user.getUsername();
                    Log.i("Name", ":"+ userNom);
                    String userPsw = user.getPass();
                    Log.i("Psw", ":"+ userPsw);
                    Log.i("REGISTER", "OK" + user);

                    Toast.makeText(SignInActivity.this, "register okkk", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(SignInActivity.this, LoginActivity.class);
                    //intent.p
                    startActivity(intent);
                    SharedPreferences sharedPref = getSharedPreferences("myPref", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("User", userNom);
                    editor.putString("psw", userPsw);
                    //editor.putString("mail", mail);
                    editor.commit();
                } else {
                    Toast.makeText(SignInActivity.this, "EL NOM D'USUARI INTRODUIT JA EXISTEIX, PORVA AMB UN ALTRE DIFERENT", Toast.LENGTH_LONG).show();
                }
            }


            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("REGISTER", "ERROR", t);
                Toast.makeText(SignInActivity.this, "ERROR AL CREAR USUARI", Toast.LENGTH_LONG).show();
            }

        });
    }
    }

