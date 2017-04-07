package com.example.models;

/**
 * Created by isama92 on 4/7/17.
 */

public class Lettera {
    private char c;
    public Lettera(char c){
        if(Character.isLetter(c))
            this.c = c;
        else
            this.c = '/';
    }

    public boolean isValid() {
        if(this.c == '/')
            return false;
        return true;
    }

    public boolean isVocal() {
        if(!this.isValid())
            System.out.println("A letter was expected");
        if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')
            return true;
        return false;
    }

    public boolean isConsonant() {
        if(!this.isValid())
            System.out.println("This character is not a letter");
        if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')
            return false;
        return true;
    }

    public char getChar() {
        return c;
    }

    public void setChar(char c) {
        this.c = c;
    }
}
