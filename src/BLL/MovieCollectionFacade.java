package BLL;

import BE.Category;
import BE.Movie;
import BLL.Exceptions.CatMovieDAOException;
import BLL.Exceptions.CategoryDAOException;
import BLL.Exceptions.MovieDAOException;

import java.sql.Date;
import java.util.List;

public interface MovieCollectionFacade {
    public Movie createMovie(String name, float rating, String fileLink, Date lastView, float imdb) throws MovieDAOException;
    List<Movie> getAllMovies() throws MovieDAOException;
    List<Category> getAllCategories() throws CategoryDAOException;
    void deleteMovie(Movie movie) throws MovieDAOException;
    void updateMovie(Movie movie) throws MovieDAOException;
    Movie getMovie(int id) throws MovieDAOException;
    Category createCategory(String categoryName) throws CategoryDAOException;
    void deleteCategory(Category category) throws CategoryDAOException;
    void updateMovieRating(Movie movie) throws MovieDAOException;
    public List<Movie> getCategoryMovie(int id) throws CatMovieDAOException;
    public void addToCategory(Category selectedCategory, Movie selectedMovie) throws CatMovieDAOException;
    public void removeFromCategory(Category selectedCatagory, Movie selectedMovie) throws CatMovieDAOException;
    public void removeFromCat(Category selectedItem) throws CatMovieDAOException;
    public void removeMoviesFromCat(Movie selectedItem) throws CatMovieDAOException;
    public void updateMovieLastView(Movie selectedItem) throws MovieDAOException;
}
