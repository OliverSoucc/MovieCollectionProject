package GUI.Model;

import BE.Movie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MainPageModel {
    private final ObservableList<Movie> movieObservableList;
    public MainPageModel() {
        movieObservableList = FXCollections.observableArrayList();
        Movie movie1 = new Movie("Penis", 6.4f, "pornhub.com", 6, 5.5f);
        Movie movie2 = new Movie("Penis", 6.4f, "pornhub.com", 5, 5.2f);
        Movie movie3 = new Movie("Penis", 9.4f, "pornhub.com", 4, 1.3f);
        Movie movie4 = new Movie("Kokot", 3.4f, "pornhub.com", 3, 3f);

        movieObservableList.add(movie1);
        movieObservableList.add(movie2);
        movieObservableList.add(movie3);
        movieObservableList.add(movie4);
    }
    public ObservableList<Movie> getMovieObservableList() {
        return movieObservableList;
    }
    public void createMovie(Movie movie) {
        Movie newMovie = new Movie(movie.getName(), movie.getRating(), movie.getFileLink(), movie.getLastView(), movie.getImdb());
        movieObservableList.add(newMovie);
    }
}
