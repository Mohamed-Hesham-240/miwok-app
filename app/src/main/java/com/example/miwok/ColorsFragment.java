package com.example.miwok;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;


public class ColorsFragment extends Fragment {
    MediaPlayer mp;
    //new helper method
    public void releaseMediaPlayer(MediaPlayer mp){
        if (mp!=null){
            mp.release();
            mp=null;
        }
    }
    //overriding the onStop method
    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer(mp);
    }


    public ColorsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =inflater.inflate(R.layout.words_list, container, false);
        ArrayList<Word> words =new ArrayList<Word>();
        Collections.addAll(words ,new Word("weṭeṭṭi","red",R.drawable.color_red,R.raw.color_red)
                ,new Word("chokokki","green",R.drawable.color_green,R.raw.color_green),
                new Word("ṭakaakki","brown",R.drawable.color_brown,R.raw.color_brown),
                new Word("ṭopoppi","gray",R.drawable.color_gray,R.raw.color_gray),
                new Word("kululli","black",R.drawable.color_black,R.raw.color_black),
                new Word("kelelli","white",R.drawable.color_white,R.raw.color_white),
                new Word("ṭopiisә","dusty yellow",R.drawable.color_dusty_yellow,R.raw.color_dusty_yellow),
                new Word("chiwiiṭә","mustard yellow",R.drawable.color_mustard_yellow,R.raw.color_mustard_yellow) );
        //We need to create a custom word adapter class
        WordAdapter itemsAdapter = new WordAdapter (getActivity(), words,R.color.category_colors);

        ListView listView = (ListView) rootView.findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                releaseMediaPlayer(mp);
                mp=MediaPlayer.create(getActivity(),words.get(i).getAudioResouceID());
                mp.start();
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        releaseMediaPlayer(mp);
                    }
                });

            }
        });

        return rootView;
    }
}