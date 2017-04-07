package com.example;
import com.example.models.CodiceFiscale;
import java.io.IOException;

public class CFiscale {
    public static void main(String[] args) throws IOException {
        CodiceFiscale codiceFiscale = new CodiceFiscale("Marpione", "Giuseppe", 19, 11, 1923, 'm', "Roma");
        String CF = codiceFiscale.calcola();
        System.out.println(CF);
    }
}
