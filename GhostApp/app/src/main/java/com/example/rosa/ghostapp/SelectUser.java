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
import android.widget.AutoCompleteTextView;
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
    AutoCompleteTextView Player1Input, Player2Input;
    Button PlayGame;
    SharedPreferences sharedpreferences;
    String player1preferences, player2preferences;
    public static final String playerPreferences = "PlayerPreferences";
    Spinner player1spinner, player2spinner;
    ArrayAdapter<String> adapter;
    List<String> players = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_user);

        sharedpreferences = getSharedPreferences(playerPreferences, Context.MODE_PRIVATE);
        PlayGame = (Button) findViewById(R.id.button4);
        players = getPlayers();
        setAdapter();

        PlayGame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                readTextView();
                addPlayerPreferences();
                addNewPlayers();

                Intent intent = new Intent(SelectUser.this, PlayGame.class);
                startActivity(intent);
            }
        });

    }



    public void readTextView(){

        player1preferences = Player1Input.getText().toString().replaceAll("\\s","");
        player2preferences = Player2Input.getText().toString().replaceAll("\\s","");

    }



    public void setAdapter(){

        Player1Input = (AutoCompleteTextView) findViewById(R.id.player1);
        Player2Input = (AutoCompleteTextView) findViewById(R.id.player2);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, players);
        Player1Input.setAdapter(adapter);
        Player2Input.setAdapter(adapter);

    }



    public void addNewPlayers(){

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
    }



    public void addPlayerPreferences(){

        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("Player1", player1preferences);
        editor.putString("Player2", player2preferences);
        editor.commit();

    }



    public List getPlayers(){

        try {
            players = readFromFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return players;
    }



    public void writetoFile(List<String> players) throws IOException {

        File file = new File("players.txt");
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput("players.txt", Context.MODE_PRIVATE));

        for (String player : players) {
            outputStreamWriter.write(player+ " ");
        }
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


}
