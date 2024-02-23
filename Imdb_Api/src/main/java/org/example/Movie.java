package org.example;

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
        return "org.example.Movie{" +
                "title='" + title + '\'' +
                ", year=" + year +
                ", director='" + director + '\'' +
                ", imdbRating=" + imdbRating +
                '}';
    }
}
