package DAL;


import BE.Category;
import BE.Movie;
import BLL.Exceptions.CategoryDAOException;

import java.util.List;

public interface IDALMovieFacade {

    List<Category> getAllCategories() throws Exception;
    Category createCategory(String categoryName) throws Exception;
    void deleteCategory(Category categoryToDelete) throws Exception;
    List<Movie> getAllMovies() throws Exception;
    Movie createMovie(Movie movieToCreate) throws Exception;
    void deleteMovie(Movie movieToDelete) throws Exception;
    void updateMovie(Movie movie) throws Exception;
    Movie getMovie(int id) throws Exception;

    public List<Movie> getCategoryMovie(int id) throws Exception;
    public void addToCategory(Category selectedCategory, Movie selectedMovie) throws Exception;
    public void removeFromCategory(Category selectedCatagory, Movie selectedMovie) throws Exception;
    public void removeFromCat(Category selectedItem) throws Exception;
    public void removeMoviesFromCat(Movie selectedItem) throws Exception;

    void updateRating()

}
