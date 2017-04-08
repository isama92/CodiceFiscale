package com.example;
import com.example.models.CodiceFiscale;
import java.io.IOException;

public class CFiscale {
    public static void main(String[] args) throws IOException {
        String CF = CodiceFiscale.calcola("Cognome", "Nome", 1, 1, 1990, 'm', "Roma");
        System.out.println(CF);
    }
}
