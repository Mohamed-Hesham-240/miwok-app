package com.example.miwok;

import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {
    int colorID;
    MediaPlayer mediaPlayer;
    //We pass the context and the custom array list created
    //the second argument (item list layout) is passed when we have a single text view

    public WordAdapter (Context context , ArrayList<Word>list, int colorID){
        super(context,0,list);
        this.colorID=colorID;

    }
    //new helper method

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //getting the next object to be displayed on the screen
        Word currentWord = getItem(position);
        View listItemView = convertView;
        //if there is now scrap views, inflate a new view according to list_item layout
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        //setting the background color
        LinearLayout linearLayout=listItemView.findViewById(R.id.layout);
        linearLayout.setBackgroundColor(ContextCompat.getColor(getContext(), colorID));
        ImageView playButton = listItemView.findViewById(R.id.ic_play);
        playButton.setBackgroundColor(ContextCompat.getColor(getContext(), colorID));
        //first text view
        TextView miowkWord = listItemView.findViewById(R.id.text1);
        miowkWord.setText(currentWord.getMiowkWord());
        //second text view
        TextView defaultWord = listItemView.findViewById(R.id.text2);
        defaultWord.setText(currentWord.getDefaultWord());
        // creating the multiplayer for the pron of the word
        //image view
        ImageView img = listItemView.findViewById(R.id.img1);
        if (!currentWord.hasImage()) {
            img.setVisibility(View.GONE);
        }
        else {
            img.setVisibility(View.VISIBLE);
            img.setImageResource(currentWord.getImgResourceID());
        }
        //setting the onClicks


        return listItemView;
    }

}
