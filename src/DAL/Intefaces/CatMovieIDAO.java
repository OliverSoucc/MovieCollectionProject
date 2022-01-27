package DAL.Intefaces;

import BE.Category;
import BE.Movie;

import java.util.List;

public interface CatMovieIDAO {
    List<Movie> getCategoryMovie(int id) throws Exception;

    void addToCategory(Category selectedItem, Movie selectedMovie) throws Exception;

    void removeFromCategory(Category selectedItem, Movie selectedMovie) throws Exception;

    void removeFromCat(Category selectedItem) throws Exception;

    void removeMoviesFromCat(Movie selectedItem) throws Exception;
}
