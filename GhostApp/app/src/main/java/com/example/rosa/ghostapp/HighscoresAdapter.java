package com.example.rosa.ghostapp;

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

    public HighscoresAdapter(Map<String, ?> map) {
        highscoresList = new ArrayList();
        highscoresList.addAll(map.entrySet());
    }

    @Override
    public int getCount() {
        return highscoresList.size();
    }

    @Override
    public Map.Entry<String, String> getItem(int position) {
        return (Map.Entry) highscoresList.get(position);
    }

    @Override
    public long getItemId(int position) {
        //niet nodig
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View result;

        if (convertView == null) {
            result = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_highscore, parent, false);
        } else {
            result = convertView;
        }

        Map.Entry<String, String> item = getItem(position);

        // TODO replace findViewById by ViewHolder
        ((TextView) result.findViewById(android.R.id.text1)).setText(item.getKey());
        ((TextView) result.findViewById(android.R.id.text2)).setText(item.getValue());

        return result;
    }
}