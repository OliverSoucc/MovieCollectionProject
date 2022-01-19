package BLL;

import BE.Category;
import BE.Movie;
import BLL.Exceptions.CategoryDAOException;
import BLL.Exceptions.MovieDAOException;
import BLL.Exceptions.MovieException;

import java.util.List;

public interface MovieCollectionFacade {
    public Movie createMovie(String name, float rating, String fileLink, int lastView, float imdb) throws MovieDAOException;
    List<Movie> getAllMovies() throws MovieDAOException;
    List<Category> getAllCategories() throws CategoryDAOException;
    void deleteMovie(Movie movie) throws MovieDAOException;
    void updateMovie(Movie movie) throws MovieDAOException;
    Movie getMovie(int id) throws MovieDAOException;
    Category createCategory(String categoryName) throws CategoryDAOException;
    void deleteCategory(Category category) throws CategoryDAOException;
    void updateMovieRating(Movie movie) throws MovieDAOException;
    public List<Movie> getCategoryMovie(int id) throws Exception;
    public void addToCategory(Category selectedCategory, Movie selectedMovie) throws Exception;
    public void removeFromCategory(Category selectedCatagory, Movie selectedMovie) throws Exception;
    public void removeFromCat(Category selectedItem) throws Exception;
    public void removeMoviesFromCat(Movie selectedItem) throws Exception;
}
