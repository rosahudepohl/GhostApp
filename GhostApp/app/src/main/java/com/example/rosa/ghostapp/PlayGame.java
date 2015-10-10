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

public class PlayGame extends Activity {
    Button Restart;
    Button Resign;
    Button SwitchLanguage;
    String letter;
    String player1;
    String player2;
    EditText letterInput;
    Lexicon lexicon;
    Game game;
    TextView textView;
    String winner;
    SharedPreferences sharedpreferences;
    String languagepreferences = "dutch.txt";
    public static final String languagePreferences = "SavedPreferences" ;
    String player1preferences;
    String player2preferences;
    public static final String player1Preferences = "Player1Preferences" ;
    public static final String player2Preferences = "Player2Preferences" ;
    public static final String currentWord = "Word";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);

        sharedpreferences = getSharedPreferences(player1Preferences, Context.MODE_PRIVATE);
        sharedpreferences = getSharedPreferences(player2Preferences, Context.MODE_PRIVATE);

        TextView showPlayer1 = (TextView)findViewById(R.id.showPlayer1);
        TextView showPlayer2 = (TextView)findViewById(R.id.showPlayer2);
        player1preferences = sharedpreferences.getString("Player1", player1preferences);
        player2preferences = sharedpreferences.getString("Player2", player2preferences);
        showPlayer1.setText(player1preferences);
        showPlayer2.setText(player2preferences);

        sharedpreferences = getSharedPreferences(languagePreferences, Context.MODE_PRIVATE);
        languagepreferences = sharedpreferences.getString("Language", languagepreferences);

        lexicon = new Lexicon(languagepreferences, this);
        game = new Game(lexicon);

        letterInput = (EditText)findViewById(R.id.letterInputField);
        textView = (TextView)findViewById(R.id.textViewField);

        sharedpreferences = getSharedPreferences(currentWord, Context.MODE_PRIVATE);

        letterInput.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    switch (keyCode) {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:

                            String letter = letterInput.getText().toString();

                            game.guess(letter);
                            textView.setText(game.word);
                            letterInput.getText().clear();

                            if (game.ended() == true){
                                if (game.validword()){
                                    SharedPreferences.Editor editor = sharedpreferences.edit();
                                    editor.putString("Word", game.word);
                                    editor.commit();
                                }
                                Intent intent = new Intent(PlayGame.this, GameEnded.class);
                                startActivity(intent);
                            }
                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }

        });

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
                editor.commit();

                Intent intent = new Intent(PlayGame.this, PlayGame.class);
                startActivity(intent);
            }

        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_play_game, menu);
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
