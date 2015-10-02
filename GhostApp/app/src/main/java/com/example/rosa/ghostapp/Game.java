package com.example.rosa.ghostapp;

/**
 * Created by Rosa on 29-9-2015.
 */
public class Game {

    Lexicon lexicon;
    Boolean firstplayer;
    Boolean ended;
    String word;

    public Game(Lexicon lexicon1){
        lexicon = lexicon1;
        firstplayer = true;
        ended = false;
    }

    public void guess(String letter){

        word = word + letter;
        lexicon.filter(word);

        if (lexicon.count() == 0){
            ended = true;
            switchPlayer();
        }

        if (lexicon.count() == 1){
            if (lexicon.filteredset.contains(word) && word.length() > 3){
                ended = true;
                switchPlayer();
            }
            else {
                switchPlayer();
            }
        }

    }

    public boolean turn(){
       return firstplayer;
    }

    public boolean ended(){
        return ended;
    }

    public boolean winner(){
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
