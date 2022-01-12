package gui.model;

import BE.Movie;

import java.util.ArrayList;

public class AddRemoveMovieModel {
    ArrayList<Movie> movieDB;

    public AddRemoveMovieModel() {
        movieDB = new ArrayList<>();
    }
    public ArrayList<Movie> getMovieDB(){
        return movieDB;
    }
    public void createMovie(String movieName, float movieRating, String movieLink, int movieDuration) {
        Movie newMovie = new Movie(movieName, movieRating, movieLink, movieDuration);
        movieDB.add(newMovie);
    }
}
