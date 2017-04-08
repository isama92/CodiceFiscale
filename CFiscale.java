package com.example;
import com.example.models.CodiceFiscale;
import java.io.IOException;

public class CFiscale {
    public static void main(String[] args) throws IOException {
        String CF = CodiceFiscale.calcola("Telesca", "Leonardo", 27, 11, 1990, 'm', "Melfi");
        System.out.println(CF);
    }
}
