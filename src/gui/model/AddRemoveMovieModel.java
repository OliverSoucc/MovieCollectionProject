package gui.model;

import be.Movie;
import gui.controller.MainPageController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

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
