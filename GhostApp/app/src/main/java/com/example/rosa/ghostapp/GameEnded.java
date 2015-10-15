package com.example.rosa.ghostapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class GameEnded extends Activity {
    Game game;
    TextView displayWinner;
    String winner, word, player1Preferences, player2Preferences;
    Button PlayAgain, HighScores;
    Boolean firstplayer, contains = false;
    SharedPreferences sharedpreferences, wordPreferences;
    public static final String playerPreferences = "PlayerPreferences";
    public static final String currentWord = "Word";
    public static final String highScores = "Highscores";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_ended);

        setWinnerText();
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
                Intent intent = new Intent(GameEnded.this, Highscores.class);
                startActivity(intent);
            }
        });
    }



    public void setWinnerText(){

        displayWinner = (TextView)findViewById(R.id.winner);
        firstplayer = game.winner();
        displayWinner.setText("Congrats, "+ getWinner()+ ", you won!");

    }



    public String getWinner(){

        sharedpreferences = getSharedPreferences(playerPreferences, Context.MODE_PRIVATE);
        player1Preferences = sharedpreferences.getString("Player1", player1Preferences);
        player2Preferences = sharedpreferences.getString("Player2", player2Preferences);

        if (firstplayer){
            winner = player1Preferences;
        }

        else{
            winner = player2Preferences;
        }

        return winner;

    }



    public void newHighscore(){

        winner = getWinner();

        sharedpreferences = getSharedPreferences(currentWord, Context.MODE_PRIVATE);
        word = sharedpreferences.getString("Word", word);

        wordPreferences = getSharedPreferences(highScores, Context.MODE_PRIVATE);

        if(!wordPreferences.contains(word)){
            SharedPreferences.Editor editor = wordPreferences.edit();
            editor.putString(word, winner);
            editor.commit();
        }

    }
}
