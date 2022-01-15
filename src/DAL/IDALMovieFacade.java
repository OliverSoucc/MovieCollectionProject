package DAL;


import BE.Category;
import BE.Movie;
import BLL.Exceptions.CategoryDAOException;

import java.util.List;

public interface IDALMovieFacade {

    List<Category> getAllCategories() throws CategoryDAOException;
    Category createCategory(String categoryName) throws CategoryDAOException;
    void deleteCategory(Category categoryToDelete) throws Exception, CategoryDAOException;
    List<Movie> getAllMovies() throws Exception;
    Movie createMovie(Movie movieToCreate) throws Exception;
    void deleteMovie(Movie movieToDelete) throws Exception;
    void updateMovie(Movie movie) throws Exception;
    Movie getMovie(int id) throws Exception;
}
