package com.example.rosa.ghostapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class GameEnded extends Activity {
    Game game;
    TextView displayWinner;
    String winner, word, player1Preferences, player2Preferences;
    Boolean firstplayer;
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
        player1Preferences = playerPreferences.getString("Player1", "Player1");
        player2Preferences = playerPreferences.getString("Player2", "Player2");

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
        word = wordPreferences.getString("Word", "");

        highScoresPreferences = getSharedPreferences("Highscores", Context.MODE_PRIVATE);

        if(!word.equals("")){

            if(!highScoresPreferences.contains(word)){
                SharedPreferences.Editor editor = highScoresPreferences.edit();
                editor.putString(word, winner);
                editor.commit();
            }

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
