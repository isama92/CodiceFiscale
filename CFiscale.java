package com.example;
import com.example.models.CodiceFiscale;
import java.io.IOException;


public class CFiscale {
    public static void main(String[] args) throws IOException {
        CodiceFiscale c = new CodiceFiscale("Borzoni", "Stefano", 25, 4, 1992, 'm', "Gattinara");
        String CF = c.calcolaCF();
        System.out.println(CF);
    }
}
