package com.example.models;

import org.json.JSONException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by isama92 on 4/7/17.
 */

public class CodiceFiscale {
    private static String CF;

    public static String calcola(String cognome, String nome, int giorno, int mese, int anno, char sesso, String comune) throws IOException {
        CF = "";
        calcolaCognome(cognome.toLowerCase());
        calcolaNome(nome.toLowerCase());
        calcolaAnno(anno);
        calcolaMese(mese);
        calcolaGiorno(giorno, sesso);
        calcolaComune(comune.toLowerCase());
        calcolaControllo();
        return CF.toUpperCase();
    }

    private static void calcolaCognome(String cognome) {
        ArrayList<Lettera> vocali = new ArrayList<>();
        ArrayList<Lettera> consonanti = new ArrayList<>();
        String CFCognome = "";
        int CFCognomeLenght ;
        Lettera lettera;

        for(char c: cognome.toCharArray()){
            lettera = new Lettera(c);
            if(lettera.isValid())
                if(lettera.isVocal())
                    vocali.add(lettera);
                else if(lettera.isConsonant())
                    consonanti.add(lettera);
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
        CF += CFCognome;
    }

    private static void calcolaNome(String nome) {
        ArrayList<Lettera> vocali = new ArrayList<>();
        ArrayList<Lettera> consonanti = new ArrayList<>();
        String CFNome = "";
        int CFNomeLenght ;
        Lettera lettera;

        for(char c: nome.toCharArray()){
            lettera = new Lettera(c);
            if(lettera.isValid())
                if(lettera.isVocal())
                    vocali.add(lettera);
                else if(lettera.isConsonant())
                    consonanti.add(lettera);
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
        CF += CFNome;
    }

    private static void calcolaAnno(int anno_nascita) {
        String anno = Integer.toString(anno_nascita);
        CF += anno.charAt(2);
        CF += anno.charAt(3);
    }

    private static void calcolaMese(int mese_nascita) {
        char mese = '/';
        switch(mese_nascita){
            case 1:     mese = 'a';     break;
            case 2:     mese = 'b';     break;
            case 3:     mese = 'c';     break;
            case 4:     mese = 'd';     break;
            case 5:     mese = 'e';     break;
            case 6:     mese = 'h';     break;
            case 7:     mese = 'l';     break;
            case 8:     mese = 'm';     break;
            case 9:     mese = 'p';     break;
            case 10:    mese = 'r';     break;
            case 11:    mese = 's';     break;
            case 12:    mese = 't';     break;
        }
        CF += mese;
    }

    private static void calcolaGiorno(int giorno_nascita, char sesso) {
        switch(sesso){
            case 'm':
            case 'M':
                CF += Integer.toString(giorno_nascita);
                break;
            case 'f':
            case 'F':
                CF += Integer.toString(giorno_nascita + 40);
                break;
            default:
                System.out.println("Errore non previsto in calcolaGiorno()");
                break;
        }
    }

    private static void calcolaComune(String com) throws IOException {
        Comune comune = new Comune();
        CF += comune.getCodiceComune(com);
    }

    private static void calcolaControllo() {
        char[] controllo = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        int sommaPari = 0;
        int sommaDispari = 0;
        int sommaPD;

        for(int i = 1; i <= CF.length(); i++){
            if(i % 2 == 0)
                sommaPari += controlloPari(CF.toCharArray()[i-1]);
            else
                sommaDispari += controlloDispari(CF.toCharArray()[i-1]);
        }
        sommaPD = sommaPari + sommaDispari;
        CF += controllo[sommaPD % 26];
    }

    private static int controlloPari(char c) {
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
            case 'k':   cod = 10;   break;
            case 'l':   cod = 11;   break;
            case 'm':   cod = 12;   break;
            case 'n':   cod = 13;   break;
            case 'o':   cod = 14;   break;
            case 'p':   cod = 15;   break;
            case 'q':   cod = 16;   break;
            case 'r':   cod = 17;   break;
            case 's':   cod = 18;   break;
            case 't':   cod = 19;   break;
            case 'u':   cod = 20;   break;
            case 'v':   cod = 21;   break;
            case 'w':   cod = 22;   break;
            case 'x':   cod = 23;   break;
            case 'y':   cod = 24;   break;
            case 'z':   cod = 25;   break;
        }
        return cod;
    }

    private static int controlloDispari(char c) {
        int cod = 0;
        switch(c){
            case '0':   cod = 1;    break;
            case '1':   cod = 0;    break;
            case '2':   cod = 5;    break;
            case '3':   cod = 7;    break;
            case '4':   cod = 9;    break;
            case '5':   cod = 13;   break;
            case '6':   cod = 15;   break;
            case '7':   cod = 17;   break;
            case '8':   cod = 19;   break;
            case '9':   cod = 21;   break;
            case 'a':   cod = 1;    break;
            case 'b':   cod = 0;    break;
            case 'c':   cod = 5;    break;
            case 'd':   cod = 7;    break;
            case 'e':   cod = 9;    break;
            case 'f':   cod = 13;   break;
            case 'g':   cod = 15;   break;
            case 'h':   cod = 17;   break;
            case 'i':   cod = 19;   break;
            case 'j':   cod = 21;   break;
            case 'k':   cod = 2;    break;
            case 'l':   cod = 4;    break;
            case 'm':   cod = 18;   break;
            case 'n':   cod = 20;   break;
            case 'o':   cod = 11;   break;
            case 'p':   cod = 3;    break;
            case 'q':   cod = 6;    break;
            case 'r':   cod = 8;    break;
            case 's':   cod = 12;   break;
            case 't':   cod = 14;   break;
            case 'u':   cod = 16;   break;
            case 'v':   cod = 10;   break;
            case 'w':   cod = 22;   break;
            case 'x':   cod = 25;   break;
            case 'y':   cod = 24;   break;
            case 'z':   cod = 23;   break;
        }
        return cod;
    }
}
