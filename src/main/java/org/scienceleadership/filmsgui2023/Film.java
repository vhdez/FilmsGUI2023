package org.scienceleadership.filmsgui2023;

import java.io.Serializable;
import java.util.ArrayList;

class Film implements Serializable {
    // Fields
    private static ArrayList<Film> allFilms = new ArrayList<>();
    private int rank;
    private String title;
    private int releaseYear;
    private long gross;

    // Constructors
    Film(int rank, String title, int releaseYear, long gross) {
        this.rank = rank;
        this.title = title;
        this.releaseYear = releaseYear;
        this.gross = gross;
        allFilms.add(this);
    }

    // Setters/Getters

    public static ArrayList<Film> getAllFilms() {
        return allFilms;
    }

    public static void setAllFilms(ArrayList<Film> allFilms) {
        Film.allFilms = allFilms;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public long getGross() {
        return gross;
    }

    public void setGross(long gross) {
        this.gross = gross;
    }

    // Methods
    void describeSelf() {
        System.out.println("THIS IS A FILM: " + title + " " + releaseYear);
    }
}
