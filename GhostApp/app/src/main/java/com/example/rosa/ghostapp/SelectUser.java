package com.example.rosa.ghostapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SelectUser extends Activity {
    EditText Player1input;
    EditText Player2input;
    Button PlayGame;
    String player1preferences;
    String player2preferences;
    SharedPreferences sharedpreferences;
    public static final String player1Preferences = "Player1Preferences" ;
    public static final String player2Preferences = "Player2Preferences" ;
    Spinner player1spinner;
    Spinner player2spinner;
    ArrayAdapter<String> adapter;
    List<String> players = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_user);

        try {
            players = readFromFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.d("test", "mislukt");

        }

        PlayGame = (Button) findViewById(R.id.button4);
        Player1input = (EditText) findViewById(R.id.player1);
        Player2input = (EditText) findViewById(R.id.player2);

        sharedpreferences = getSharedPreferences(player1Preferences, Context.MODE_PRIVATE);
        sharedpreferences = getSharedPreferences(player2Preferences, Context.MODE_PRIVATE);

        player1spinner = (Spinner) findViewById(R.id.spinner2);
        player2spinner = (Spinner) findViewById(R.id.spinner);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, players);
        player1spinner.setAdapter(adapter);
        player2spinner.setAdapter(adapter);

        PlayGame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                player1preferences = Player1input.getText().toString().replaceAll("\\s","");
                player2preferences = Player2input.getText().toString().replaceAll("\\s","");

                if (!players.contains(player1preferences)) {
                    players.add(player1preferences);
                    Log.d("test", "player added= " + player1preferences);
                    try {
                        writetoFile(players);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (!players.contains(player2preferences)) {
                    players.add(player2preferences);
                    Log.d("test", "player added= " + player2preferences);
                    try {
                        writetoFile(players);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("Player1", player1preferences);
                editor.putString("Player2", player2preferences);
                editor.commit();

                Intent intent = new Intent(SelectUser.this, PlayGame.class);
                startActivity(intent);
            }
        });
    }


    public void writetoFile(List<String> players) throws IOException {
        File file = new File("players.txt");
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput("players.txt", Context.MODE_PRIVATE));

        outputStreamWriter.write("Rosa");

        /*for (String player : players) {
            outputStreamWriter.write(player+ " ");
        }*/
        outputStreamWriter.close();

    }

    private List readFromFile() throws FileNotFoundException {
        FileInputStream fileinput = this.openFileInput("players.txt");

        Scanner s = new Scanner(fileinput);
        while (s.hasNext()){
            players.add(s.next());
        }
        s.close();

        try {
            fileinput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.d("test", "players: " + players);

        return players;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_select_user, menu);
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
