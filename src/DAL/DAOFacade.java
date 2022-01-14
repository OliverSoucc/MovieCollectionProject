package DAL;

import BE.Category;
import BE.Movie;
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

    public List<Category> getAllCategories() {
        return categoryDAO.getAllCategories();
    }

    public Category createCategory(String name){
        return categoryDAO.createCategory(name);
    }

    public void deleteCategory (Category categoryToDelete) {
        categoryDAO.deleteCategory(categoryToDelete);
    }

    public List<Movie> getAllMovies() {
        return movieDAO.getAllMovies();
    }

    public Movie createMovie(Movie movieToCreate){
        return movieDAO.createMovie(movieToCreate);
    }

    public void deleteMovie(Movie movieToDelete){
        movieDAO.deleteMovie(movieToDelete);
    }


    // TODO: Create methods in MovieDAO!!!

    @Override
    public void updateMovie(Movie movie) throws Exception {

    }

    @Override
    public Movie getMovie(int id) throws Exception {
        return null;
    }
}
