package com.example.rosa.ghostapp;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Highscores extends Activity {
    private HighscoresAdapter itemsAdapter;
    private ListView highscoresList;
    SharedPreferences highScoresPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        highScoresPreferences = getSharedPreferences("Highscores", Context.MODE_PRIVATE);

        Map<String, ?> highscores = highScoresPreferences.getAll();

        Log.d("test", "highscores = "+ highscores);

        highscoresList = (ListView) findViewById(R.id.listView);

        showHighscores(highscores);

    }

    public void showHighscores(Map<String, ?> highscores) {
        itemsAdapter  = new HighscoresAdapter(highscores);
        highscoresList.setAdapter(itemsAdapter);
    }

}

