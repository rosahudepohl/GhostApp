package com.example.rosa.ghostapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SelectUser extends Activity {
    AutoCompleteTextView Player1Input, Player2Input;
    SharedPreferences playerPreferences;
    String player1Preferences, player2Preferences;
    ArrayAdapter<String> playersAdapter;
    List<String> players = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_user);

        playerPreferences = getSharedPreferences("PlayerPreferences", Context.MODE_PRIVATE);
        players = getPlayers();
        setPlayersAdapter();

    }


    //  Call functions that handle textView input and
    //  Open the PlayGame Activity
    public void playGameListener(View v){

        readTextView();
        addPlayerPreferences();
        addNewPlayers();

        Intent intent = new Intent(SelectUser.this, PlayGame.class);
        startActivity(intent);

    }


    // Insert all the previously used playernames in the drop down menus
    public void setPlayersAdapter(){

        Player1Input = (AutoCompleteTextView) findViewById(R.id.player1);
        Player2Input = (AutoCompleteTextView) findViewById(R.id.player2);

        playersAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, players);
        Player1Input.setAdapter(playersAdapter);
        Player2Input.setAdapter(playersAdapter);

    }


    // Set player preferences to input from TextViews
    public void readTextView(){

        player1Preferences = Player1Input.getText().toString().replaceAll("\\s","");
        player2Preferences = Player2Input.getText().toString().replaceAll("\\s","");

    }


    // Set playerPreferences to the player preferences retrieved from TextViews
    public void addPlayerPreferences(){

        SharedPreferences.Editor editor = playerPreferences.edit();
        editor.putString("Player1", player1Preferences);
        editor.putString("Player2", player2Preferences);
        editor.commit();

    }


    // Add new players to the players list
    public void addNewPlayers(){

        if (!players.contains(player1Preferences)) {
            players.add(player1Preferences);
            try {
                writeToFile(players);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!players.contains(player2Preferences)) {
            players.add(player2Preferences);
            try {
                writeToFile(players);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    // Call readFromFile() method and set players to its output
    public List getPlayers(){

        try {
            players = readFromFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return players;
    }


    // Write all the players from the players List to players.txt
    public void writeToFile(List<String> players) throws IOException {

        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput("players.txt", Context.MODE_PRIVATE));

        for (String player : players) {
            outputStreamWriter.write(player+ " ");
        }
        outputStreamWriter.close();

    }


    // Add players from players.txt to the players List
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

        return players;

    }


}
