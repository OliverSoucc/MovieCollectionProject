package GUI.Model;

import BE.Category;
import BE.Movie;
import BLL.Exceptions.MovieCollectionManagerException;
import BLL.Exceptions.MovieDAOException;
import BLL.MovieCollectionFacade;
import BLL.MovieCollectionManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class MainPageModel {
    private final ObservableList<Movie> movieObservableList;
    private final ObservableList<Category> categoryObservableList;
    private final MovieCollectionFacade movieCollectionFacade;

    public MainPageModel() throws MovieCollectionManagerException {
        movieObservableList = FXCollections.observableArrayList();
        categoryObservableList = FXCollections.observableArrayList();

        movieCollectionFacade = new MovieCollectionManager();

    }
    
    public ObservableList<Movie> getMovieObservableList() throws MovieDAOException {
        movieObservableList.clear();
        List<Movie> newMovieList = movieCollectionFacade.getAllMovies();
        movieObservableList.addAll(newMovieList);
        return movieObservableList;
    }
    public void createMovie(Movie movie) throws MovieDAOException {
        Movie newMovie = new Movie(movie.getName(), movie.getRating(), movie.getFileLink(), movie.getLastView(), movie.getImdb());
        movieCollectionFacade.createMovie(newMovie);
        movieObservableList.addAll(newMovie);
    }



}
