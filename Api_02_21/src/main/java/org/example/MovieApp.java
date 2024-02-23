package org.example;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ResourceBundle;

public class MovieApp {
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

    public static void main(String[] args) throws IOException, JSONException {
        JSONObject json = readJsonFromUrl("http://www.omdbapi.com/?i=tt3896198&apikey=bd5bfd88");
        System.out.println(json.toString());
        System.out.println(json.get("id"));
    }

    public class Movie {
        private String title;
        private int year;
        private String director;
        private double imdbRating;

        public Movie(String title, int year, String director, double imdbRating) {
            this.title = title;
            this.year = year;
            this.director = director;
            this.imdbRating = imdbRating;
        }
        public String getTitle() {
            return title;
        }
        public void  setTitle(String title) {
            this.title = title;
        }
        public int getYear() {
            return year;
        }
        public void setYear(int year) {
            this.year = year;
        }
        public String getDirector() {
            return director;
        }
        public void  setDirector(String director) {
            this.director = director;
        }
        public double getImdbRating() {
            return imdbRating;
        }
        public void  setImdbRating(double imdbRating) {
            this.imdbRating = imdbRating;
        }

        @Override
        public String toString() {
            return "Movie{" +
                    "title='" + title + '\'' +
                    ", year=" + year +
                    ", director='" + director + '\'' +
                    ", imdbRating=" + imdbRating +
                    '}';
        }
    }


    private ResourceBundle json;
    //Todo
    //1. Susikurti 4 kintamuosius, kuriems priskirsime reiksmes is json atitinkamai pagal raktus
    //2. Susikurism Movie klases objekta kuriam per konstruktoriu perduosime anksciau sukurtus kintamuoisius
    //3. Atspausdinti vartotojui objekta
    String pavadinimas = json.getString("Title");
    int metai = json.getInt(("Year"));
    String direktorius = json.getString("Director");
    double imdb = json.getDouble("imdbRating");
    Movie filmas = new Movie("pavadinimas, metai, direktorus")




}