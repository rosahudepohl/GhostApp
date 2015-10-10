package com.example.rosa.ghostapp;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.rosa.ghostapp.R;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Highscore extends Activity {
    private ArrayAdapter<String> itemsAdapter;
    private ListView highscoresList;
    SharedPreferences sharedpreferences;
    List<String> highscores;
    public static final String highScores = "Highscores";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        sharedpreferences = getSharedPreferences(highScores, Context.MODE_PRIVATE);
        /*highscores = sharedpreferences.getString("Highscores", highscores);*/
        highscores = new ArrayList<>();
        highscores.add("Player1, word, 10 points");
        highscores.add("Player2, word, 10 points");
        highscores.add("Player3, word, 10 points");

        highscoresList = (ListView) findViewById(R.id.listView);
        itemsAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, highscores);
        highscoresList.setAdapter(itemsAdapter);
    }

}

