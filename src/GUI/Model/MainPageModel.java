package GUI.Model;

import BE.Category;
import BE.Movie;
import BLL.Exceptions.CategoryDAOException;
import BLL.Exceptions.MovieCollectionManagerException;
import BLL.Exceptions.MovieDAOException;
import BLL.MovieCollectionFacade;
import BLL.MovieCollectionManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public class MainPageModel {
    private final ObservableList<Movie> movieObservableList;
    private final ObservableList<Category> categoryObservableList;
    private final MovieCollectionFacade movieCollectionFacade;

    private static MainPageModel single_instance = null;

    // Static method
    // Static method to create instance of Singleton class
    public static MainPageModel getInstance() throws MovieCollectionManagerException {
        if (single_instance == null)
            single_instance = new MainPageModel();

        return single_instance;
    }

    public MainPageModel() throws MovieCollectionManagerException {
        movieObservableList = FXCollections.observableArrayList();
        categoryObservableList = FXCollections.observableArrayList();

        movieCollectionFacade = new MovieCollectionManager();
    }
    //Movie methods
    public ObservableList<Movie> getMovieObservableList() throws MovieDAOException {
        movieObservableList.clear();
        List<Movie> newMovieList = movieCollectionFacade.getAllMovies();
        movieObservableList.setAll(newMovieList);
        return movieObservableList;
    }
    public void createMovie(Movie movie) throws MovieDAOException {
        Movie newMovie = new Movie(movie.getName(), movie.getRating(), movie.getFileLink(), movie.getLastView(), movie.getImdb());
        movieCollectionFacade.createMovie(newMovie);
        movieObservableList.add(newMovie);
    }
    public void removeMovie(Movie movie) throws MovieDAOException {
        movieCollectionFacade.deleteMovie(movie);
    }
    //Category methods
    public ObservableList<Category> getAllCategories() throws CategoryDAOException {
        categoryObservableList.clear();
        List<Category> newCategoryList = movieCollectionFacade.getAllCategories();
        categoryObservableList.setAll(newCategoryList);
        return categoryObservableList;
    }
    public void createCategory(String name) throws CategoryDAOException {
        movieCollectionFacade.createCategory(name);
        categoryObservableList.add(new Category(name)); // NEW
    }
    public void removeCategory(Category category) throws CategoryDAOException {
        movieCollectionFacade.deleteCategory(category);
    }

    public List<Movie> getCategoryMovie(int id) throws Exception {
        return movieCollectionFacade.getCategoryMovie(id);
    }
    public void addToCategory(Category selectedCategory, Movie selectedMovie) throws Exception {
        movieCollectionFacade.addToCategory(selectedCategory, selectedMovie);
    }
    public void removeFromCategory(Category selectedCatagory, Movie selectedMovie) throws Exception {
        movieCollectionFacade.removeFromCategory(selectedCatagory, selectedMovie);
    }
    public void removeFromCat(Category selectedItem) throws Exception {
        movieCollectionFacade.removeFromCat(selectedItem);
    }
    public void removeMoviesFromCat(Movie selectedItem) throws Exception {
        movieCollectionFacade.removeMoviesFromCat(selectedItem);
    } // these last 5 methods need to change exceptions
}
