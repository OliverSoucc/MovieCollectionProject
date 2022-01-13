package DAL;

import BE.Category;
import BE.Movie;
import DAL.DAO.CatMovieDAO;
import DAL.DAO.CategoryDAO;
import DAL.DAO.MovieDAO;

import java.util.List;

public class DAOFacade {
    CategoryDAO categoryDAO;
    CatMovieDAO catMovieDAO;
    MovieDAO movieDAO;

    public DAOFacade() {
        categoryDAO = new CategoryDAO();
        catMovieDAO = new CatMovieDAO();
        movieDAO = new MovieDAO();
    }

    public List<Category> getAllCategories(){
        return categoryDAO.getAllCategories();
    }

    public Category createCategory(Category categoryToCreate){
        return categoryDAO.createCategory(categoryToCreate);
    }

    public void deleteCategory(Category categoryToDelete){
        categoryDAO.deleteCategory(categoryToDelete);
    }

    /*public void updateCategory (Category categoryToUpdate) {
        categoryDAO.updateCategory(categoryToUpdate);
     } */

    public List<Movie> getAllMovies() {
        return movieDAO.getAllMovies();
    }

    public Movie createMovie(Movie movieToCreate){
        return movieDAO.createMovie(movieToCreate);
    }

    public void deleteMovie(Movie movieToDelete){
        movieDAO.deleteMovie(movieToDelete);
    }

    /*public void updateCategory (Movie movieToUpdate) {
        movieDAO.updateMovie(movieToUpdate);
     } */


}