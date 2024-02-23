package org.example;

import org.json.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Scanner;

public class Main {
    public static final String URL = "https://www.omdbapi.com/?apikey=5140cb61&i=";
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
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

    public static void main(String[] args) {
        JSONObject json = null;
        try {
            System.out.println("Prasome ivesti norimo filmo id (pvz: tt3896198) ");
            // Sukuriamas Scanner klases objektas darbui su vartotojo ivedamais duomenimis
            Scanner scanner = new Scanner(System.in);
            // Nuskaitomi vartotojo ivesti duomenys(next, nextline - string'us, nextDouble - double, nextInt - int tipus)
            String movieId = scanner.next();

            //Kodas skirtas gauti http statuso kodui
            URL url = new URL(URL+movieId);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int code = connection.getResponseCode();
            System.out.println("Http stauso kodas: "+code);
            json = readJsonFromUrl(URL+movieId);
            String atsakymas = json.get("Response").toString().toLowerCase();
            boolean atsakymas1 = Boolean.parseBoolean(atsakymas);

            if (code == HttpURLConnection.HTTP_OK && atsakymas1) {

                System.out.println("Apie filma: ");
                System.out.println(json);
                System.out.println("Filmo pavadinimas: ");
                System.out.println(json.get("Title"));
                //TODO:
                //1. Susikurti 4 kintamuosius, kuriems priskirsime reiksmes is json atitinkamai pagal raktus
                //2. Susikurti org.example.Movie klases objekta, kuriam per konstruktoriu perduosime anksciau susikurtus kintamuosius
                //3. Atspausdinti vartotojui objekta
                String pavadinimas = json.getString("Title");
                int metai = json.getInt(("Year"));
                String direktorius = json.getString("Director");
                double imdb = json.getDouble("imdbRating");
                Movie filmas = new Movie(pavadinimas, metai, direktorius, imdb);
                System.out.println("Spausdinamas filmo pavadinimas, metai, direktorius ir Imdb");
                System.out.println(filmas);

            }
            else {
                System.out.println("Tokio filmo Id nera!");
            }

        } catch (IOException e) {
            System.out.println("Nepavyko gauti duomenu is filmu sistemos. Problemos su srauto nuskaitymu. Placiau: " + e.getMessage());
        } catch (JSONException e) {
            System.out.println("Nepavyko gauti duomenu is filmu sistemos. Problemos su json failu. Placiau: " + e.getMessage());
        }
        //JSONObject json = readJsonFromUrl(URL);




    }
}
