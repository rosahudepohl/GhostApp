package com.example.rosa.ghostapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class GameEnded extends Activity {
    Game game;
    String winner;
    Button PlayAgain;
    Button HighScores;
    Boolean firstplayer;
    Boolean contains = false;
    List<Object> highscore = new ArrayList();
    String player1preferences;
    String player2preferences;
    SharedPreferences sharedpreferences;
    public static final String player1Preferences = "Player1Preferences" ;
    public static final String player2Preferences = "Player2Preferences" ;
    List<List> highscores = new ArrayList<List>();
    public static final String highScores = "Highscores";
    public static final String currentWord = "Word";
    String word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_ended);

        sharedpreferences = getSharedPreferences(player1Preferences, Context.MODE_PRIVATE);
        sharedpreferences = getSharedPreferences(player2Preferences, Context.MODE_PRIVATE);
        player1preferences = sharedpreferences.getString("Player1", player1preferences);
        player2preferences = sharedpreferences.getString("Player2", player2preferences);

        TextView winner = (TextView)findViewById(R.id.winner);
        firstplayer = game.winner();
        winner.setText("Congrats, "+ getWinner()+ ", you won!");

        sharedpreferences = getSharedPreferences(currentWord, Context.MODE_PRIVATE);
        word = sharedpreferences.getString("Word", word);
        newHighscore();

        PlayAgain = (Button) findViewById(R.id.playAgainButton);

        PlayAgain.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(GameEnded.this, PlayGame.class);
                startActivity(intent);
            }
        });

        HighScores = (Button) findViewById(R.id.highscoresButton);

        HighScores.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(GameEnded.this, Highscore.class);
                startActivity(intent);
            }
        });
    }

    public String getWinner(){
        if (firstplayer){
            winner = player1preferences;
        }
        else{
            winner = player2preferences;
        }
        return winner;
    }

    public void newHighscore(){

        if(!highscores.isEmpty()){
            for(List highscore : highscores) {
                if (highscore.contains(word)) {
                    contains = true;
                }
            }
        }

        if(!contains){
            highscore.add(word);
            highscore.add(word.length());
            highscore.add(getWinner());
            highscores.add(highscore);
        }


        Log.d("test", "Highscores: "+ highscores);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game_ended, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
