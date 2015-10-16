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
    Boolean firstplayer, contains = false;
    SharedPreferences playerPreferences, wordPreferences, highScoresPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_ended);

        setWinnerText();
        newHighscore();

    }


    // Get winning player (this is the current player)
    public String getWinner(){

        playerPreferences = getSharedPreferences("PlayerPreferences", Context.MODE_PRIVATE);
        player1Preferences = playerPreferences.getString("Player1", player1Preferences);
        player2Preferences = playerPreferences.getString("Player2", player2Preferences);

        firstplayer = game.winner();

        if (firstplayer){
            winner = player1Preferences;
        }

        else{
            winner = player2Preferences;
        }

        return winner;

    }


    // Set winner TextView to output from getWinner() method
    public void setWinnerText(){

        displayWinner = (TextView)findViewById(R.id.winner);
        displayWinner.setText("Congrats, " + getWinner() + ", you won!");

    }


    // Add new highscore to highScorePreferences
    public void newHighscore(){

        winner = getWinner();

        wordPreferences = getSharedPreferences("WordPreferences", Context.MODE_PRIVATE);
        word = wordPreferences.getString("Word", word);

        highScoresPreferences = getSharedPreferences("Highscores", Context.MODE_PRIVATE);

        if(!highScoresPreferences.contains(word)){
            SharedPreferences.Editor editor = highScoresPreferences.edit();
            editor.putString(word, winner);
            editor.commit();
        }

    }


    // Open PlayGame Activity
    public void playAgainListener (View v){

        Intent intent = new Intent(GameEnded.this, PlayGame.class);
        startActivity(intent);

    }


    // Open Highscores Activity
    public void viewHighscoresListener(View v){

        Intent intent = new Intent(GameEnded.this, Highscores.class);
        startActivity(intent);

    }


}
