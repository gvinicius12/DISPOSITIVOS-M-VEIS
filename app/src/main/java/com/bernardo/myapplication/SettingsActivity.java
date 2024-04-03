package com.vinicius.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    private EditText editTextNewUser;
    private EditText editTextNewPassword;
    private EditText editTextConfirmNewPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        editTextNewUser = findViewById(R.id.newUser);
        editTextNewPassword = findViewById(R.id.newPassword);
        editTextConfirmNewPassword = findViewById(R.id.confirmPassword);
    }

    public void confirmButton(View view){
        String newUser = editTextNewUser.getText().toString();
        String newPassword = editTextNewPassword.getText().toString();
        String confirmPassword = editTextConfirmNewPassword.getText().toString();

        if(newPassword.equals(confirmPassword)){
            SharedPreferences sharedPref = getSharedPreferences(
                    getString(R.string.preference_file_key), Context.MODE_PRIVATE);


            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("newUser", newUser);
            editor.putString("newPassword", newPassword);
            editor.apply();

            startActivity(new Intent(this, MainActivity.class));

        }else{
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
        }
    }

    public void cancelButton(View view){
        finish();
    }
}
