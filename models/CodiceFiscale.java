package com.example.models;

import org.json.JSONException;
import java.io.IOException;
import java.util.*;

/**
 * Created by isama92 on 4/7/17.
 */

public class CodiceFiscale {
    private String cognome;
    private String nome;
    private int giorno_nascita;
    private int mese_nascita;
    private int anno_nascita;
    private char sesso;
    private String comune;
    private String CF;

    public CodiceFiscale(String cognome, String nome, int g, int m, int a, char sesso, String comune) {
        this.cognome = cognome.toLowerCase();
        this.nome = nome.toLowerCase();
        this.giorno_nascita = g;
        this.mese_nascita = m;
        this.anno_nascita = a;
        this.sesso = sesso;
        this.comune = comune.toLowerCase();
        this.CF = "";
    }

    public String calcola() throws IOException {
        this.calcolaCFCognome();
        this.calcolaCFNome();
        this.calcolaAnno();
        this.calcolaMese();
        this.calcolaGiorno();
        this.calcolaComune();
        this.calcolaControllo();
        return this.CF.toUpperCase();
    }

    private void calcolaCFCognome() {
        ArrayList<Lettera> vocali = new ArrayList<>();
        ArrayList<Lettera> consonanti = new ArrayList<>();
        String CFCognome = "";
        int CFCognomeLenght ;

        for(char c: this.cognome.toCharArray()){
            Lettera l = new Lettera(c);
            if(l.isValid())
                if(l.isVocal())
                    vocali.add(l);
                else if(l.isConsonant())
                    consonanti.add(l);
        }

        for(int i = 0; i < 3 && i < consonanti.size(); i++)
            CFCognome += consonanti.get(i).getChar();
        CFCognomeLenght = CFCognome.length();
        if(CFCognomeLenght < 3)
            for(int i = 0; i < (3 - CFCognomeLenght) && i < vocali.size(); i++)
                CFCognome += vocali.get(i).getChar();
        CFCognomeLenght = CFCognome.length();
        if(CFCognomeLenght  < 3)
            for(int i = 0; i < (3 - CFCognomeLenght); i++)
                CFCognome += "x";
        this.CF += CFCognome;
    }

    private void calcolaCFNome() {
        ArrayList<Lettera> vocali = new ArrayList<>();
        ArrayList<Lettera> consonanti = new ArrayList<>();
        String CFNome = "";
        int CFNomeLenght ;

        for(char c: this.nome.toCharArray()){
            Lettera l = new Lettera(c);
            if(l.isValid())
                if(l.isVocal())
                    vocali.add(l);
                else if(l.isConsonant())
                    consonanti.add(l);
        }

        if(consonanti.size() >= 4) {
            CFNome += consonanti.get(0).getChar();
            CFNome += consonanti.get(2).getChar();
            CFNome += consonanti.get(3).getChar();
        }
        CFNomeLenght = CFNome.length();
        if(CFNomeLenght < 3)
            for(int i = 0; i < 3 && i < consonanti.size(); i++)
                CFNome += consonanti.get(i).getChar();
        CFNomeLenght = CFNome.length();
        if(CFNomeLenght < 3)
            for(int i = 0; i < (3 - CFNomeLenght) && i < vocali.size(); i++)
                CFNome += vocali.get(i).getChar();
        CFNomeLenght = CFNome.length();
        if(CFNomeLenght < 3)
            for(int i = 0; i < (3 - CFNomeLenght); i++)
                CFNome += "x";
        this.CF += CFNome;
    }

    private void calcolaAnno() {
        String anno = Integer.toString(this.anno_nascita);
        this.CF += anno.charAt(2);
        this.CF += anno.charAt(3);
    }

    private void calcolaMese() {
        char mese = '/';
        switch(this.mese_nascita){
            case 1:
                mese = 'a';
                break;
            case 2:
                mese = 'b';
                break;
            case 3:
                mese = 'c';
                break;
            case 4:
                mese = 'd';
                break;
            case 5:
                mese = 'e';
                break;
            case 6:
                mese = 'h';
                break;
            case 7:
                mese = 'l';
                break;
            case 8:
                mese = 'm';
                break;
            case 9:
                mese = 'p';
                break;
            case 10:
                mese = 'r';
                break;
            case 11:
                mese = 's';
                break;
            case 12:
                mese = 't';
                break;
            default:
                System.out.println("Errore non previsto in calcoloMese()");
                break;
        }
        this.CF += mese;
    }

    private void calcolaGiorno() {
        switch(this.sesso){
            case 'm':
                this.CF += Integer.toString(this.giorno_nascita);
                break;
            case 'f':
                this.CF += Integer.toString(this.giorno_nascita + 40);
                break;
            default:
                System.out.println("Errore non previsto in calcolaGiorno()");
                break;
        }
    }

    private void calcolaComune() throws IOException {
        Comune comune = new Comune();
        this.CF += comune.getCodiceComune(this.comune);
    }

    private void calcolaControllo() {
        char[] controllo = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        int sommaPari = 0;
        int sommaDispari = 0;
        int sommaPD;

        for(int i = 1; i <= this.CF.length(); i++){
            if(i % 2 == 0)
                sommaPari += controlloPari(this.CF.toCharArray()[i-1]);
            else
                sommaDispari += controlloDispari(this.CF.toCharArray()[i-1]);
        }
        sommaPD = sommaPari + sommaDispari;
        this.CF += controllo[sommaPD % 26];
    }

    private int controlloPari(char c) {
        int cod = 0;
        switch(c){
            case '0':   cod = 0;    break;
            case '1':   cod = 1;    break;
            case '2':   cod = 2;    break;
            case '3':   cod = 3;    break;
            case '4':   cod = 4;    break;
            case '5':   cod = 5;    break;
            case '6':   cod = 6;    break;
            case '7':   cod = 7;    break;
            case '8':   cod = 8;    break;
            case '9':   cod = 9;    break;
            case 'a':   cod = 0;    break;
            case 'b':   cod = 1;    break;
            case 'c':   cod = 2;    break;
            case 'd':   cod = 3;    break;
            case 'e':   cod = 4;    break;
            case 'f':   cod = 5;    break;
            case 'g':   cod = 6;    break;
            case 'h':   cod = 7;    break;
            case 'i':   cod = 8;    break;
            case 'j':   cod = 9;    break;
            case 'k':   cod = 10;    break;
            case 'l':   cod = 11;    break;
            case 'm':   cod = 12;    break;
            case 'n':   cod = 13;    break;
            case 'o':   cod = 14;    break;
            case 'p':   cod = 15;    break;
            case 'q':   cod = 16;    break;
            case 'r':   cod = 17;    break;
            case 's':   cod = 18;    break;
            case 't':   cod = 19;    break;
            case 'u':   cod = 20;    break;
            case 'v':   cod = 21;    break;
            case 'w':   cod = 22;    break;
            case 'x':   cod = 23;    break;
            case 'y':   cod = 24;    break;
            case 'z':   cod = 25;    break;
        }
        return cod;
    }

    private int controlloDispari(char c) {
        int cod = 0;
        switch(c){
            case '0':   cod = 1;    break;
            case '1':   cod = 0;    break;
            case '2':   cod = 5;    break;
            case '3':   cod = 7;    break;
            case '4':   cod = 9;    break;
            case '5':   cod = 13;    break;
            case '6':   cod = 15;    break;
            case '7':   cod = 17;    break;
            case '8':   cod = 19;    break;
            case '9':   cod = 21;    break;
            case 'a':   cod = 1;    break;
            case 'b':   cod = 0;    break;
            case 'c':   cod = 5;    break;
            case 'd':   cod = 7;    break;
            case 'e':   cod = 9;    break;
            case 'f':   cod = 13;    break;
            case 'g':   cod = 15;    break;
            case 'h':   cod = 17;    break;
            case 'i':   cod = 19;    break;
            case 'j':   cod = 21;    break;
            case 'k':   cod = 2;    break;
            case 'l':   cod = 4;    break;
            case 'm':   cod = 18;    break;
            case 'n':   cod = 20;    break;
            case 'o':   cod = 11;    break;
            case 'p':   cod = 3;    break;
            case 'q':   cod = 6;    break;
            case 'r':   cod = 8;    break;
            case 's':   cod = 12;    break;
            case 't':   cod = 14;    break;
            case 'u':   cod = 16;    break;
            case 'v':   cod = 10;    break;
            case 'w':   cod = 22;    break;
            case 'x':   cod = 25;    break;
            case 'y':   cod = 24;    break;
            case 'z':   cod = 23;    break;
        }
        return cod;
    }
}
