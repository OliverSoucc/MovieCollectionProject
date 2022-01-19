package DAL;

import BE.Category;
import BE.Movie;
import BLL.Exceptions.CategoryDAOException;
import DAL.DAO.CatMovieDAO;
import DAL.DAO.CategoryDAO;
import DAL.DAO.MovieDAO;

import java.util.List;

public class DAOFacade implements IDALMovieFacade{
    CategoryDAO categoryDAO;
    CatMovieDAO catMovieDAO;
    MovieDAO movieDAO;

    public DAOFacade() {
        categoryDAO = new CategoryDAO();
        catMovieDAO = new CatMovieDAO();
        movieDAO = new MovieDAO();
    }

    public List<Category> getAllCategories() throws Exception {
        return categoryDAO.getAllCategories();
    }

    public Category createCategory(String name) throws Exception{

        return categoryDAO.createCategory(name);
    }

    public void deleteCategory (Category categoryToDelete) throws Exception{
        categoryDAO.deleteCategory(categoryToDelete);
    }

    public List<Movie> getAllMovies() throws Exception {
        return movieDAO.getAllMovies();
    }

    public Movie createMovie(String name, float rating, String fileLink, int lastView, float imdb) throws Exception {
        return movieDAO.createMovie(name, rating, fileLink, lastView, imdb);
    }

    public void deleteMovie(Movie movieToDelete) throws Exception{
        movieDAO.deleteMovie(movieToDelete);
    }

    @Override
    public void updateMovie(Movie movie) throws Exception {

    }

    public void updateMovie(Movie movie, float newRating) throws Exception {
        movieDAO.updateMovieRating(movie, newRating);
    }

    public Movie getMovie(int id) throws Exception {
        return movieDAO.getMovie(id);
    }

    @Override
    public List<Movie> getCategoryMovie(int id) throws Exception {
        return catMovieDAO.getCategoryMovie(id);
    }

    @Override
    public void addToCategory(Category selectedCategory, Movie selectedMovie) throws Exception {
        catMovieDAO.addToCategory(selectedCategory, selectedMovie);
    }

    @Override
    public void removeFromCategory(Category selectedCatagory, Movie selectedMovie) throws Exception {
        catMovieDAO.removeFromCategory(selectedCatagory, selectedMovie);
    }

    @Override
    public void removeFromCat(Category selectedItem) throws Exception {
        catMovieDAO.removeFromCat(selectedItem);
    }

    @Override
    public void removeMoviesFromCat(Movie selectedItem) throws Exception {
        catMovieDAO.removeMoviesFromCat(selectedItem);
    }

}
