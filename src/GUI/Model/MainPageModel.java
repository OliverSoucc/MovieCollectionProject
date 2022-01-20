package GUI.Model;

import BE.Category;
import BE.Movie;
import BLL.Exceptions.CatMovieDAOException;
import BLL.Exceptions.CategoryDAOException;
import BLL.Exceptions.MovieCollectionManagerException;
import BLL.Exceptions.MovieDAOException;
import BLL.MovieCollectionFacade;
import BLL.MovieCollectionManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Date;
import java.util.List;

public class MainPageModel {
    private  ObservableList<Movie> movieObservableList;
    private  ObservableList<Category> categoryObservableList;
    private final MovieCollectionFacade movieCollectionFacade;

    private static MainPageModel single_instance = null;

    public static MainPageModel getInstance() throws MovieCollectionManagerException {
        if (single_instance == null)
            single_instance = new MainPageModel();

        return single_instance;
    }

    public MainPageModel() throws MovieCollectionManagerException {
        movieCollectionFacade = new MovieCollectionManager();
    }

    public ObservableList<Movie> getMovieObservableList() throws MovieDAOException {
        movieObservableList = FXCollections.observableArrayList();
        movieObservableList.setAll(movieCollectionFacade.getAllMovies());
        return movieObservableList;
    }


    public Movie createMovie(String name, float rating, String fileLink, Date lastView, float imdb) throws MovieDAOException {
        Movie newMovieToCreate = movieCollectionFacade.createMovie(name, rating, fileLink, lastView, imdb);
        movieObservableList.add(newMovieToCreate);
        return newMovieToCreate;
    }



    public void removeMovie(Movie movie) throws MovieDAOException {
        movieCollectionFacade.deleteMovie(movie);
        movieObservableList.remove(movie);
    }

    public void updateRating(Movie movie) throws MovieDAOException {
        movieCollectionFacade.updateMovieRating(movie);
    }

    public void updateMovieLastView(Movie selectedItem) throws MovieDAOException {
       movieCollectionFacade.updateMovieLastView(selectedItem);
    }

    public Movie getMovie(int movieId) throws MovieDAOException {
        return movieCollectionFacade.getMovie(movieId);
    }

    public ObservableList<Category> getAllCategories() throws CategoryDAOException {
        categoryObservableList = FXCollections.observableArrayList();
        categoryObservableList.setAll(movieCollectionFacade.getAllCategories());
        return categoryObservableList;
    }

    public void createCategory(String name) throws CategoryDAOException {
        Category newCategory = movieCollectionFacade.createCategory(name);
        categoryObservableList.add(newCategory);
    }


    public void removeCategory(Category category) throws CategoryDAOException {
        movieCollectionFacade.deleteCategory(category);
        categoryObservableList.remove(category);
    }

    public List<Movie> getCategoryMovie(int id) throws CatMovieDAOException {
        return movieCollectionFacade.getCategoryMovie(id);
    }

    public void addToCategory(Category selectedCategory, Movie selectedMovie) throws CatMovieDAOException {
        movieCollectionFacade.addToCategory(selectedCategory, selectedMovie);
        List<Movie> movieList = selectedCategory.getAllMoviesInCategory();
        movieList.add(selectedMovie);
        selectedCategory.setAllMoviesInCategory(movieList);
    }

    public void removeFromCategory(Category selectedCatagory, Movie selectedMovie) throws CatMovieDAOException {
        movieCollectionFacade.removeFromCategory(selectedCatagory, selectedMovie);
        List<Movie> movieList = selectedCatagory.getAllMoviesInCategory();
        movieList.remove(selectedMovie);
        selectedCatagory.setAllMoviesInCategory(movieList);
    }

    public void removeFromCat(Category selectedItem) throws CatMovieDAOException {
        movieCollectionFacade.removeFromCat(selectedItem);
    }

    public void removeMoviesFromCat(Movie selectedItem) throws CatMovieDAOException {
        movieCollectionFacade.removeMoviesFromCat(selectedItem);
    }
}
