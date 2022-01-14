package DAL;


import BE.Category;
import BE.Movie;

import java.util.List;

public interface IDALMovieFacade {

    List<Category> getAllCategories() throws Exception;
    Category createCategory(String categoryName) throws Exception;
    void deleteCategory(Category categoryToDelete) throws Exception;
    List<Movie> getAllMovies() throws Exception;
    Movie createMovie(Movie movieToCreate) throws Exception;
    void deleteMovie(Movie movieToDelete) throws Exception;
}
