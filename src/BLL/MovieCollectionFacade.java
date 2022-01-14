package BLL;

import BE.Category;
import BE.Movie;
import BLL.Exceptions.CategoryDAOException;
import BLL.Exceptions.MovieDAOException;
import BLL.Exceptions.MovieException;

import java.util.List;

public interface MovieCollectionFacade {
    Movie createMovie(Movie movie) throws MovieDAOException;
    List<Movie> getAllMovies() throws MovieDAOException;
    void deleteMovie(Movie movie) throws MovieDAOException;
    void updateMovie(Movie movie) throws MovieDAOException;
    Movie getMovie(int id) throws MovieDAOException;
    Category createCategory(Category category) throws CategoryDAOException;
    void deleteCategory(Category category) throws CategoryDAOException;


}
