package com.example.models;

import org.json.JSONObject;
import java.io.*;
import java.util.*;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by isama92 on 4/7/17.
 */

public class Comune {
    Map<String, String> listaComuni = new HashMap<String, String>();

    public Comune() throws IOException {
        JSONObject json = this.readJsonFromUrl("http://94.177.175.42/comuni.php");
        String cod;
        for(Iterator<String> iter = json.keys(); iter.hasNext();) {
            cod = iter.next();
            this.listaComuni.put(String.valueOf(json.get(cod)).toLowerCase(), cod.toLowerCase());
        }
    }

    public String getCodiceComune(String comune) {
        return this.listaComuni.get(comune);
    }

    private String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    private JSONObject readJsonFromUrl(String url) throws IOException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }
}
