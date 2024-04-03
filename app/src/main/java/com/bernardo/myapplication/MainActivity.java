package com.vincius.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;

    int attempts = 0;
    List<String> errorUsernameList = new ArrayList<>();
    List<String> errorPasswordList = new ArrayList<>();

    String username = "admin";
    String password = "admin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUsername = findViewById(R.id.textUsername);
        editTextPassword = findViewById(R.id.textPassword);
        retrieveData();
    }

    public void retrieveData() {
        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        boolean firstRun = sharedPref.getBoolean("firstRun", true);

        if (firstRun) {

            username = "admin";
            password = "admin";


            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putBoolean("firstRun", false);
            editor.apply();
        } else {

            username = sharedPref.getString("newUsername", "admin");
            password = sharedPref.getString("newPassword", "admin");
        }
    }


    public void loginButton(View view) {
        String textUsername = editTextUsername.getText().toString();
        String textPassword = editTextPassword.getText().toString();



        if (attempts < 3) {
            if (username.equals(textUsername) && password.equals(textPassword)) {
                attempts = 0;
                errorUsernameList.clear();
                errorPasswordList.clear();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            } else {
                attempts++;
                errorUsernameList.add(textUsername);
                errorPasswordList.add(textPassword);
                Toast.makeText(this, "Username/Password incorrect", Toast.LENGTH_SHORT).show();

            }
        } else {
            Toast.makeText(this, "Maximum attempts exceeded", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    public void cancelButton(View view){
        finish();
    }

}


