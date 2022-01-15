package GUI.Model;

import BE.Movie;
import BLL.Exceptions.MovieCollectionManagerException;
import BLL.Exceptions.MovieDAOException;
import BLL.MovieCollectionFacade;
import BLL.MovieCollectionManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class MainPageModel {
    private ObservableList<Movie> movieObservableList;
    private MovieCollectionFacade movieCollectionManager;
    public MainPageModel() throws MovieCollectionManagerException {
        movieObservableList = FXCollections.observableArrayList();
        movieCollectionManager = new MovieCollectionManager();
        Movie movie1 = new Movie("Penis", 6.4f, "pornhub.com", 6, 5.5f);
        Movie movie2 = new Movie("Penis", 6.4f, "pornhub.com", 5, 5.2f);
        Movie movie3 = new Movie("Penis", 9.4f, "pornhub.com", 4, 1.3f);
        Movie movie4 = new Movie("Kokot", 3.4f, "pornhub.com", 3, 3f);

        movieObservableList.add(movie1);
        movieObservableList.add(movie2);
        movieObservableList.add(movie3);
        movieObservableList.add(movie4);
    }
    public ObservableList<Movie> getMovieObservableList() throws MovieDAOException {
        List<Movie> newMovieList = movieCollectionManager.getAllMovies();
        movieObservableList.addAll(newMovieList);
        return movieObservableList;
    }
    public void createMovie(Movie movie) {
        Movie newMovie = new Movie(movie.getName(), movie.getRating(), movie.getFileLink(), movie.getLastView(), movie.getImdb());
        movieObservableList.addAll(newMovie);
    }


}
