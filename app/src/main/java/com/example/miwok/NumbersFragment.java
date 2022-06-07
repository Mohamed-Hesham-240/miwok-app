package com.example.miwok;

import android.content.Context;
import android.media.AudioManager;
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


public class NumbersFragment extends Fragment {
    MediaPlayer mp;
    AudioManager manager ;
    AudioManager.OnAudioFocusChangeListener afChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        releaseMediaPlayer(mp);
                    }
                    else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {
                        mp.pause();
                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        mp.pause();
                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        mp.start(); //it starts from where we left off
                    }
                }
            };

    //new helper method
    public void releaseMediaPlayer(MediaPlayer mp){
        if (mp!=null){
            mp.release();
            mp=null;
            manager.abandonAudioFocus(afChangeListener);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer(mp);
    }

    public NumbersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.words_list, container, false);
        //logic
        ArrayList<Word> words =new ArrayList<Word>();
        Collections.addAll(words ,new Word("lutti","one",R.drawable.number_one,R.raw.number_one),
                new Word("otiiko","two",R.drawable.number_two,R.raw.number_two),
                new Word("tolookosu","three",R.drawable.number_three,R.raw.number_three),
                new Word("oyyisa","four",R.drawable.number_four,R.raw.number_four),
                new Word("massokka","five",R.drawable.number_five,R.raw.number_five),
                new Word("temmokka","six",R.drawable.number_six,R.raw.number_six),
                new Word("kenekaku","seven",R.drawable.number_seven,R.raw.number_seven),
                new Word("kawinta","eight",R.drawable.number_eight,R.raw.number_eight),
                new Word("wo’e","nine",R.drawable.number_nine,R.raw.number_nine),
                new Word("na’aacha","ten",R.drawable.number_ten,R.raw.number_ten) );
        //We need to create a custom word adapter class
        WordAdapter itemsAdapter = new WordAdapter (getActivity(), words,R.color.category_numbers);
        ListView listView = (ListView) rootView.findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);
        //audio focus segment
        manager = (AudioManager)getActivity().getSystemService(Context.AUDIO_SERVICE);// creating the audio manager

        //creating the click listner call back
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                releaseMediaPlayer(mp);
                //reuesting focus
                int result = manager.requestAudioFocus(afChangeListener,
                        AudioManager.STREAM_MUSIC, //the stream type is music
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT); //request focus for a short period
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // gained audio focus successfully
                    mp=MediaPlayer.create(getActivity(),words.get(i).getAudioResouceID());
                    mp.start();
                    mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            releaseMediaPlayer(mp);

                        }
                    });
                } //the audio foucs request & playback ends here


            }
        });
        return rootView;
    }

}