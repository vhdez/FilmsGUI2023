package org.scienceleadership.filmsgui2023;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Scanner;

public class BoxOfficeFilm extends Film implements Serializable {
    // Fields
    private int peak;

    // Constructors
    BoxOfficeFilm(int rank, String title, int releaseYear, long gross, int peak) {
        super(rank, title, releaseYear, gross);
        this.peak = peak;
    }

    // Setters/Getters

    public int getPeak() {
        return peak;
    }

    public void setPeak(int peak) {
        this.peak = peak;
    }

    // Methods
    public String toString() {
        String description = "BoxOfficeFilm rank #" + getRank();
        description = description + " is " + this.getTitle();
        description = description + ".  It was released in " + getReleaseYear();
        description = description + ", peaked at #" + getPeak();
        description = description + ", and grossed $" + getGross() + ".";
        return description;
    }

    static void readAllData() {
        Scanner sc = null;
        try {
            File file = new File("TopGrossingFilmsData"); // java.io.File
            sc = new Scanner(file);     // java.util.Scanner
            String line;
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                Scanner lineScanner = new Scanner(line);
                lineScanner.useDelimiter("\t");
                int rank = lineScanner.nextInt();
                int peak = lineScanner.nextInt();
                String name = lineScanner.next();
                long gross = lineScanner.nextLong();
                int year = lineScanner.nextInt();
                new BoxOfficeFilm(rank, name,year,gross,peak);
            }
        }
        catch(FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (sc != null) sc.close();
        }
    }
}
