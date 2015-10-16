package com.example.rosa.ghostapp;

/**
 * Created by Rosa on 29-9-2015.
 */
public class Game {

    Lexicon lexicon;
    static Boolean firstplayer;
    Boolean ended, validword;
    String word;



    public Game(Lexicon lexicon1){

        lexicon = lexicon1;
        firstplayer = true;
        ended = false;
        validword = true;
        word = "";

    }


    // Handle input from user: Filter filteredlist with new word and
    // Check how many words left in filteredset
    public void guess(String letter){

        word = word + letter;
        lexicon.filter(word);

        // If filteredset is empty, word is invalid, the game ends and
        // current player loses
        if (lexicon.count() == 0) {
            validword = false;
            ended = true;
            switchPlayer();
        }

        // If filteredset has 1 word left and if this word is aelready made and
        // longer than 3 characters, the game ends and current player loses
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
