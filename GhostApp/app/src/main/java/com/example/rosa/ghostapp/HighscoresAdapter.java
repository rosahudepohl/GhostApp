package com.example.rosa.ghostapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.rosa.ghostapp.R;

import java.util.ArrayList;
import java.util.Map;

public class HighscoresAdapter extends BaseAdapter {
    private final ArrayList highscoresList;


    // Put key,value from map in highscoresList
    public HighscoresAdapter(Map<String, ?> map) {

        highscoresList = new ArrayList();
        highscoresList.addAll(map.entrySet());

    }


    // Get number of key,value pairs in highscoresList
    @Override
    public int getCount() {

        return highscoresList.size();

    }


    // Get key,value pair from position
    @Override
    public Map.Entry<String, String> getItem(int position) {

        return (Map.Entry) highscoresList.get(position);

    }


    // Not needed
    public long getItemId(int position) {

        return 0;

    }


    // Set highscoreWord and highscorePlayer to key,value from map
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final View result;

        if (convertView == null) {
            result = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_highscore, parent, false);
        } else {
            result = convertView;
        }

        Map.Entry<String, String> item = getItem(position);

        ((TextView) result.findViewById(R.id.highscoreWord)).setText(item.getKey());
        ((TextView) result.findViewById(R.id.highscorePlayer)).setText(item.getValue());

        return result;

    }


}