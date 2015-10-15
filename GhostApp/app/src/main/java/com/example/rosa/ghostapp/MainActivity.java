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
    SharedPreferences languagePreferences1;
    public static final String languagePreferences = "LanguagePreferences" ;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        languagePreferences1 = getSharedPreferences(languagePreferences, Context.MODE_PRIVATE);

        Button playGame = (Button) findViewById(R.id.playGameButton);
        playGame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, SelectUser.class);
                startActivity(intent);

            }
        });

        Button highscores = (Button) findViewById(R.id.highscoresButton);
        highscores.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, Highscores.class);
                startActivity(intent);

            }
        });

        Button setLanguageEnglish = (Button) findViewById(R.id.englishButton);
        setLanguageEnglish.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                SharedPreferences.Editor editor = languagePreferences1.edit();
                editor.putString("Language", "english.txt");
                editor.commit();
                Toast.makeText(getApplicationContext(), "You chose: " + "English", Toast.LENGTH_LONG).show();

            }
        });

        Button setLanguageDutch = (Button) findViewById(R.id.dutchButton);
        setLanguageDutch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                SharedPreferences.Editor editor = languagePreferences1.edit();
                editor.putString("Language", "dutch.txt");
                editor.commit();
                Toast.makeText(getApplicationContext(), "You chose: " + "Dutch", Toast.LENGTH_LONG).show();

            }
        });

    }

}
