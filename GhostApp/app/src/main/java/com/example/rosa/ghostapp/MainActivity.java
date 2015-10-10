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
    String languagepreferences = "dutch.txt";
    SharedPreferences sharedpreferences;
    public static final String languagePreferences = "SavedPreferences" ;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedpreferences = getSharedPreferences(languagePreferences, Context.MODE_PRIVATE);

        Button playgame = (Button) findViewById(R.id.playGameButton);
        playgame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SelectUser.class);
                startActivity(intent);
            }
        });

        Button highscores = (Button) findViewById(R.id.highscoresButton);
        highscores.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Highscore.class);
                startActivity(intent);
            }
        });

        Button setLanguageEnglish = (Button) findViewById(R.id.englishButton);
        setLanguageEnglish.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("Language", "english.txt");
                editor.commit();
                Toast.makeText(getApplicationContext(), "You chose: " + "English", Toast.LENGTH_LONG).show();
            }
        });

        Button setLanguageDutch = (Button) findViewById(R.id.dutchButton);
        setLanguageDutch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("Language", "dutch.txt");
                editor.commit();
                Toast.makeText(getApplicationContext(), "You chose: " + "Dutch", Toast.LENGTH_LONG).show();
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
