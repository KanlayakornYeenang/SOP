package com.example.sop_lab_5;

import java.util.ArrayList;

public class Word {
    ArrayList<String> badWords = new ArrayList<>();
    ArrayList<String> goodWords = new ArrayList<>();

    public Word() {
        this.badWords.add("fuck");
        this.badWords.add("olo");
        this.goodWords.add("happy");
        this.goodWords.add("enjoy");
        this.goodWords.add("like");
    }

}
