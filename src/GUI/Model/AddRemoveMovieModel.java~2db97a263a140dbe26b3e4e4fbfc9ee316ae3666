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
    public void createMovie(String movieName, float movieRating, String movieLink) {
        Movie newMovie = new Movie(movieName, movieRating, movieLink);
        movieDB.add(newMovie);
    }
}
