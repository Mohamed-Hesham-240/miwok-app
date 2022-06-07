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


public class PhrasesFragment extends Fragment {

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


    public PhrasesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View rootView = inflater.inflate(R.layout.words_list, container, false);

        ArrayList<Word> words =new ArrayList<Word>();
        Collections.addAll(words ,new Word("minto wuksus","Where are you going?",R.raw.phrase_where_are_you_going),
                new Word("tinnә oyaase'nә","What is your name?",R.raw.phrase_what_is_your_name),
                new Word("oyaaset...","My name is...",R.raw.phrase_my_name_is),
                new Word("michәksәs?","How are you feeling?",R.raw.phrase_how_are_you_feeling),
                new Word("kuchi achit","I’m feeling good.",R.raw.phrase_im_feeling_good),
                new Word("әәnәs'aa?","Are you coming?",R.raw.phrase_are_you_coming),
                new Word("hәә’ әәnәm","Yes, I’m coming.",R.raw.phrase_yes_im_coming),
                new Word("әәnәm","I’m coming.",R.raw.phrase_im_coming),
                new Word("yoowutis","Let’s go.",R.raw.phrase_lets_go),
                new Word("әnni'nem","Come here.",R.raw.phrase_come_here) );
        //We need to create a custom word adapter class
        WordAdapter itemsAdapter = new WordAdapter (getActivity(), words,R.color.category_phrases);

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