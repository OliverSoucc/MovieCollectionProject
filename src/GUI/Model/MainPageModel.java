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
    private  ObservableList<Movie> movieObservableList;
    private  ObservableList<Category> categoryObservableList;
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
        movieCollectionFacade = new MovieCollectionManager();
    }
    //Movie methods
    public ObservableList<Movie> getMovieObservableList() throws MovieDAOException {
        movieObservableList = FXCollections.observableArrayList();
        movieObservableList.setAll(movieCollectionFacade.getAllMovies());
        return movieObservableList;
    }
    public void createMovie(Movie movie) throws MovieDAOException {
        Movie newMovie = new Movie(movie.getName(), movie.getRating(), movie.getFileLink(), movie.getLastView(), movie.getImdb());
        movieCollectionFacade.createMovie(newMovie);
        movieObservableList.add(newMovie);
    }
    public void removeMovie(Movie movie) throws MovieDAOException {
        movieCollectionFacade.deleteMovie(movie);
        movieObservableList.remove(movie);
    }
    //Category methods
    public ObservableList<Category> getAllCategories() throws CategoryDAOException {
        categoryObservableList = FXCollections.observableArrayList();
        categoryObservableList.setAll(movieCollectionFacade.getAllCategories());
        return categoryObservableList;
    }
    public void createCategory(String name) throws CategoryDAOException {
        movieCollectionFacade.createCategory(name);
        categoryObservableList.add(new Category(name));
        System.out.println(movieCollectionFacade.getAllCategories());
    }
    public void removeCategory(Category category) throws CategoryDAOException {
        movieCollectionFacade.deleteCategory(category);
        categoryObservableList.remove(category);
    }
// CatMovie methods
    public List<Movie> getCategoryMovie(int id) throws Exception {
        return movieCollectionFacade.getCategoryMovie(id);
    }
    public void addToCategory(Category selectedCategory, Movie selectedMovie) throws Exception {
        movieCollectionFacade.addToCategory(selectedCategory, selectedMovie);
        List<Movie> movieList = selectedCategory.getAllMoviesInCategory();
        movieList.add(selectedMovie);
        selectedCategory.setAllMoviesInCategory(movieList);
    }

    public void removeFromCategory(Category selectedCatagory, Movie selectedMovie) throws Exception {
        movieCollectionFacade.removeFromCategory(selectedCatagory, selectedMovie);
        List<Movie> movieList = selectedCatagory.getAllMoviesInCategory();
        movieList.remove(selectedMovie);
        selectedCatagory.setAllMoviesInCategory(movieList);
    }// when I remove the exact connection of movie and category

    public void removeFromCat(Category selectedItem) throws Exception {
        movieCollectionFacade.removeFromCat(selectedItem);
    }// when I remove the category at all

    public void removeMoviesFromCat(Movie selectedItem) throws Exception {
        movieCollectionFacade.removeMoviesFromCat(selectedItem);
    } // when I remove the movie at all

}
