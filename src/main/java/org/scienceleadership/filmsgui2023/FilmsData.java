package org.scienceleadership.filmsgui2023;

class FilmsData {
     public static void main(String[] args) {

        BoxOfficeFilm.readAllData();
        HomeVideo.readAllData();
        //Film film = each of the films inside of allFilm
        for (Film film: Film.getAllFilms()) {
            System.out.println(film);
        }
    }
}
