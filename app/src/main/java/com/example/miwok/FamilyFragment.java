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


public class FamilyFragment extends Fragment {
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


    public FamilyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.words_list, container, false);
        ArrayList<Word> words =new ArrayList<Word>();
        Collections.addAll(words ,  new Word("әpә","father",R.drawable.family_father,R.raw.family_father),
                new Word("әṭa","mother",R.drawable.family_mother,R.raw.family_mother),
                new Word("angsi","son",R.drawable.family_son,R.raw.family_son),
                new Word("tune","daughter",R.drawable.family_daughter,R.raw.family_daughter),
                new Word("taachi","older brother",R.drawable.family_older_brother,R.raw.family_older_brother),
                new Word("chalitti","younger brother",R.drawable.family_younger_brother,R.raw.family_younger_brother),
                new Word("teṭe","older sister",R.drawable.family_older_sister,R.raw.family_older_sister),
                new Word("kolliti","younger sister",R.drawable.family_younger_sister,R.raw.family_younger_sister),
                new Word("ama","grandmother",R.drawable.family_grandmother,R.raw.family_grandmother),
                new Word("paapa","grandfather",R.drawable.family_grandfather,R.raw.family_grandfather) );
        //We need to create a custom word adapter class
        WordAdapter itemsAdapter = new WordAdapter (getActivity(), words,R.color.category_family);

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