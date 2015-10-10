package com.example.rosa.ghostapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by Rosa on 29-9-2015.
 */
public class Game {

    Lexicon lexicon;
    static Boolean firstplayer;
    Boolean ended;
    Boolean validword;
    String word;
    static PlayGame playgame;
    SharedPreferences sharedpreferences;
    public static final String currentWord = "Word";



    public Game(Lexicon lexicon1){
        lexicon = lexicon1;
        firstplayer = true;
        ended = false;
        validword = true;
        word = "";
    }

    public void guess(String letter){

        word = word + letter;
        lexicon.filter(word);


        if (lexicon.count() == 0) {
            ended = true;
            switchPlayer();
            validword = false;
        }

        else if (lexicon.count() == 1){
            if (lexicon.filteredset.contains(word) && word.length() > 3) {
                ended = true;
            }
            switchPlayer();
        }

        else{
            switchPlayer();

        }

    }

    public boolean turn(){
       return firstplayer;
    }

    public boolean validword(){
        return validword;
    }

    public boolean ended(){
        return ended;
    }

    public static boolean winner(){

    return firstplayer;
    }

    public void switchPlayer(){
        if(firstplayer){
            firstplayer = false;
        }
        else{
            firstplayer = true;
        }
    }
}
