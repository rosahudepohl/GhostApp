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
    Button Restart, Resign, SwitchLanguage;
    EditText letterInput;
    Lexicon lexicon;
    Game game;
    TextView wordView, playersTurnView, showPlayer1, showPlayer2;
    SharedPreferences sharedpreferences, languagePreferences1;
    String languagepreferences, player1Preferences, player2Preferences;
    public static final String languagePreferences = "LanguagePreferences" ;
    public static final String playerPreferences = "PlayerPreferences";
    public static final String currentWord = "Word";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);

        sharedpreferences = getSharedPreferences(playerPreferences, Context.MODE_PRIVATE);
        languagePreferences1 = getSharedPreferences(languagePreferences, Context.MODE_PRIVATE);
        playersTurnView = (TextView)findViewById(R.id.playersTurn);
        setPlayerView();
        createGame();
        checkGameStatus();

        Restart = (Button) findViewById(R.id.restartButton);
        Restart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(PlayGame.this, PlayGame.class);
                startActivity(intent);
            }
        });

        Resign = (Button) findViewById(R.id.resignButton);
        Resign.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                game.switchPlayer();
                Intent intent = new Intent(PlayGame.this, GameEnded.class);
                startActivity(intent);
            }
        });

        SwitchLanguage = (Button) findViewById(R.id.switchLanguageButton);
        SwitchLanguage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                if(languagepreferences.equals("dutch.txt")) {
                    languagepreferences = "english.txt";
                }
                else {
                    languagepreferences = "dutch.txt";
                }
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("Language", languagepreferences);
                Log.d("test", "languagepreferences after change = " + languagepreferences);

                editor.commit();

                Intent intent = new Intent(PlayGame.this, PlayGame.class);
                startActivity(intent);
            }

        });


        letterInput = (EditText)findViewById(R.id.letterInputField);
        letterInput.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    switch (keyCode) {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:

                            handleInput(letterInput);
                            checkGameStatus();

                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });

    }



    public void setPlayerView(){

        showPlayer1 = (TextView)findViewById(R.id.playerView1);
        showPlayer2 = (TextView)findViewById(R.id.playerView2);
        player1Preferences = sharedpreferences.getString("Player1", player1Preferences);
        player2Preferences = sharedpreferences.getString("Player2", player2Preferences);
        showPlayer1.setText(player1Preferences);
        showPlayer2.setText(player2Preferences);

    }



    public void createGame(){

        sharedpreferences = getSharedPreferences(languagePreferences, Context.MODE_PRIVATE);
        languagepreferences = sharedpreferences.getString("Language", "dutch.txt");
        Log.d("test", "languagepreferences in createGame = "+languagepreferences);

        lexicon = new Lexicon(languagepreferences, this);
        game = new Game(lexicon);

    }



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



    public void checkGameStatus(){

        sharedpreferences = getSharedPreferences(currentWord, Context.MODE_PRIVATE);

        if (game.ended() == true) {
            if (game.validword()) {
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("Word", game.word);
                editor.commit();
            }
            Intent intent = new Intent(PlayGame.this, GameEnded.class);
            startActivity(intent);
        }

        else{
            if (game.turn()){
                Log.d("turn", "player "+ player1Preferences);
                playersTurnView.setText("Your turn, " + player1Preferences + "!");
            }
            else{
                Log.d("turn", "player "+ player2Preferences);
                playersTurnView.setText("Your turn, "+ player2Preferences+ "!");
            }
        }
    }



}
