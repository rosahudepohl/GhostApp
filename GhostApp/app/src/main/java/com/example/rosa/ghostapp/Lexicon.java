package com.example.rosa.ghostapp;

import android.content.Context;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import static android.system.Os.read;

/**
 * Created by Rosa on 29-9-2015.
 */
class Lexicon {
    HashSet<String> filteredset = new HashSet<>();
    HashSet<String> wordset = new HashSet<>();

    Lexicon(String filename, Context context){

        try {
            InputStream inputstream = context.getAssets().open(filename);
            Scanner in = new Scanner(inputstream);
            Log.d("test","found file");

            while(in.hasNextLine()){
                Log.d("test", "found line");
                wordset.add(in.nextLine().trim().toLowerCase());
            }

            Log.d("test", ""+wordset.size());
            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public HashSet<String> filter(String string) {

        for (String word : wordset){
            if (word.startsWith(string))
                filteredset.add(word);
        }

        return filteredset;
    }


    public int count() {
        int wordcount;
        wordcount = filteredset.size();
        return wordcount;
    }

    public String result() {
        String remainingword = wordset.iterator().next();
        return remainingword;
    }

    public HashSet<String> reset() {
        filteredset.clear();
        return filteredset;
    }

}

