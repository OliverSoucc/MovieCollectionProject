package GUI.model;

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
    public void createMovie(int id, String name, float rating, String fileLink, int lastView, String category1, String category2, String category3) {
        Movie newMovie = new Movie(id, name, rating, fileLink, lastView, category1, category2, category3);
        movieDB.add(newMovie);
    }
}
