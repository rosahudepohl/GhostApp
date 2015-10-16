package com.example.rosa.ghostapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PlayGame extends Activity {
    EditText letterInput;
    Lexicon lexicon;
    Game game;
    TextView wordView, playersTurnView, showPlayer1, showPlayer2;
    SharedPreferences playerPreferences, languagePreferences, wordPreferences;
    String languagepreferences, player1Preferences, player2Preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);

        playerPreferences = getSharedPreferences("PlayerPreferences", Context.MODE_PRIVATE);
        languagePreferences = getSharedPreferences("LanguagePreferences", Context.MODE_PRIVATE);

        setPlayerView();
        createGame();
        checkIfEnded();

        // Handle KeyEvents from letterInput
        letterInput = (EditText)findViewById(R.id.letterInputField);
        letterInput.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    switch (keyCode) {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:

                            handleInput(letterInput);
                            checkIfEnded();

                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });

    }


    // Create new Lexicon instance and
    // Create new Game instance using this lexicon
    public void createGame(){

        languagePreferences = getSharedPreferences("LanguagePreferences", Context.MODE_PRIVATE);
        languagepreferences = languagePreferences.getString("Language", "dutch.txt");

        lexicon = new Lexicon(languagepreferences, this);
        game = new Game(lexicon);

    }


    // Check if userinput is valid, if so, call guess() method and update wordView
    public void handleInput(EditText letterInput){

        wordView = (TextView)findViewById(R.id.wordView);

        String letter = letterInput.getText().toString();
        char character = letter.charAt(0);

        if(Character.isLetter(character)){
            game.guess(letter);
            wordView.setText(game.word);
        }

        else{
            Toast.makeText(getApplicationContext(), "Please choose a letter", Toast.LENGTH_SHORT).show();
        }

        letterInput.getText().clear();

    }


    // Check if game is ended, if so, save current word to wordPreferences and
    // Open GameEnded Activity
    public void checkIfEnded(){

        wordPreferences = getSharedPreferences("WordPreferences", Context.MODE_PRIVATE);
        playersTurnView = (TextView)findViewById(R.id.playersTurn);


        if (game.ended() == true) {

            if (game.validword()) {
                SharedPreferences.Editor editor = wordPreferences.edit();
                Log.d("test", "word added = "+ game.word);
                editor.putString("Word", game.word);
                editor.commit();
            }

            Intent intent = new Intent(PlayGame.this, GameEnded.class);
            startActivity(intent);
        }

        else{
            showPlayersTurn();
        }
    }


    // Display current player
    public void showPlayersTurn(){

        if (game.turn()){
            playersTurnView.setText("Your turn, " + player1Preferences + "!");
        }

        else{
            playersTurnView.setText("Your turn, " + player2Preferences + "!");
        }

    }


    // Set playerViews to current players from playerPreferences
    public void setPlayerView(){

        showPlayer1 = (TextView)findViewById(R.id.playerView1);
        showPlayer2 = (TextView)findViewById(R.id.playerView2);
        player1Preferences = playerPreferences.getString("Player1", player1Preferences);
        player2Preferences = playerPreferences.getString("Player2", player2Preferences);
        showPlayer1.setText(player1Preferences);
        showPlayer2.setText(player2Preferences);

    }


    // Reopen PlayGame Activity
    public void restartListener(View v){

        Intent intent = new Intent(PlayGame.this, PlayGame.class);
        startActivity(intent);

    }


    // Make other player the winner and
    // Open GameEnded Activity
    public void resignListener(View v){

        game.switchPlayer();
        Intent intent = new Intent(PlayGame.this, GameEnded.class);
        startActivity(intent);

    }


    // Set languagepreferences to other language and
    // Reopen PlayGame Activity.
    public void switchLanguageListener(View v){

        if(languagepreferences.equals("dutch.txt")) {
            languagepreferences = "english.txt";
        }

        else {
            languagepreferences = "dutch.txt";
        }

        SharedPreferences.Editor editor = languagePreferences.edit();
        editor.putString("Language", languagepreferences);

        editor.commit();

        Intent intent = new Intent(PlayGame.this, PlayGame.class);
        startActivity(intent);

    }


}
