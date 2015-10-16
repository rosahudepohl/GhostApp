package com.example.rosa.ghostapp;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;
import java.util.Map;

public class Highscores extends Activity {
    private HighscoresAdapter itemsAdapter;
    private ListView highscoresList;
    SharedPreferences highScoresPreferences;
    Map<String, ?> highscores;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        highScoresPreferences = getSharedPreferences("Highscores", Context.MODE_PRIVATE);
        highscores = highScoresPreferences.getAll();
        showHighscores(highscores);

    }


    // Insert all the highscores in highscoresList
    // using the custom adapter HighscoresAdapter
    public void showHighscores(Map<String, ?> highscores) {

        highscoresList = (ListView) findViewById(R.id.listView);
        itemsAdapter  = new HighscoresAdapter(highscores);
        highscoresList.setAdapter(itemsAdapter);

    }

}

