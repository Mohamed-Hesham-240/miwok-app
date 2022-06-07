package com.example.miwok;

import android.widget.ImageView;

public class Word {
    //state
    private String miowkWord;
    private String defaultWord;
    private int imgResourceID=-1;
    private int audioResouceID;
    //cons
    public Word(String miowkWord, String defaultWord, int imgResourceID, int audioResouceID){
        this.miowkWord=miowkWord;
        this.defaultWord=defaultWord;
        this.imgResourceID=imgResourceID;
        this.audioResouceID=audioResouceID;
    }
    public Word(String miowkWord, String defaultWord,int audioResouceID){
        this.miowkWord=miowkWord;
        this.defaultWord=defaultWord;
        this.audioResouceID=audioResouceID;
        this.imgResourceID=-1;
    }
    //methods

    public String getDefaultWord() {
        return defaultWord;
    }

    public String getMiowkWord() {
        return miowkWord;
    }
    public int getImgResourceID(){ return this.imgResourceID; }
    public int getAudioResouceID(){ return this.audioResouceID; }
    public boolean hasImage(){
        return imgResourceID!=-1;
    }
}
