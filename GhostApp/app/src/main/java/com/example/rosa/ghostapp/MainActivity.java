package com.example.rosa.ghostapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends Activity {
    SharedPreferences languagePreferences;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        languagePreferences = getSharedPreferences("LanguagePreferences", Context.MODE_PRIVATE);

    }


    // Open the SelectUser Activity
    public void playGameListener(View v){

        Intent intent = new Intent(MainActivity.this, SelectUser.class);
        startActivity(intent);

    }


    // Open the Highscores Activity
    public void viewHighscoresListener(View v){

        Intent intent = new Intent(MainActivity.this, Highscores.class);
        startActivity(intent);

    }


    // Set language preferences to english
    public void setToEnglishListener(View v){

        SharedPreferences.Editor editor = languagePreferences.edit();
        editor.putString("Language", "english.txt");
        editor.commit();
        Toast.makeText(getApplicationContext(), "You chose: " + "English", Toast.LENGTH_LONG).show();

    }


    // Set language preferences to dutch
    public void setToDutchListener(View v) {

        SharedPreferences.Editor editor = languagePreferences.edit();
        editor.putString("Language", "dutch.txt");
        editor.commit();
        Toast.makeText(getApplicationContext(), "You chose: " + "Dutch", Toast.LENGTH_LONG).show();

    }


}

