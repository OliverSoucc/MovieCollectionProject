package BLL;

import BE.Category;
import BE.Movie;
import BLL.Exceptions.CategoryDAOException;
import BLL.Exceptions.MovieCollectionManagerException;
import BLL.Exceptions.MovieDAOException;
import DAL.DAOFacade;
import DAL.IDALMovieFacade;

import java.util.List;

public class MovieCollectionManager implements MovieCollectionFacade {
    IDALMovieFacade idalMovieFacade;


    public MovieCollectionManager() throws MovieCollectionManagerException {
        try {
            idalMovieFacade = new DAOFacade();
        } catch (Exception e) {
            throw new MovieCollectionManagerException("Failed to initialize a MovieManager class", e);
        }
    }

    @Override
    public Movie createMovie(Movie movie) throws MovieDAOException {
        try {
            return idalMovieFacade.createMovie(movie);
        } catch (Exception e) {
            throw new MovieDAOException("Couldn't create a movie", e);
        }
    }

    @Override
    public List<Movie> getAllMovies() throws MovieDAOException {
        try {
            return idalMovieFacade.getAllMovies();
        } catch (Exception e) {
            throw new MovieDAOException("Couldn't get all movies", e);
        }
    }

    @Override
    public List<Category> getAllCategories() throws CategoryDAOException {
        try {
            return idalMovieFacade.getAllCategories();
        } catch (Exception e) {
            throw new CategoryDAOException("Couldn't get all categories", e);
        }
    }


    @Override
    public void deleteMovie(Movie movie) throws MovieDAOException {
        try {
            idalMovieFacade.deleteMovie(movie);
        } catch (Exception e) {
            throw new MovieDAOException("Couldn't delete a movie", e);
        }
    }

    @Override
    public void updateMovie(Movie movie) throws MovieDAOException {
        try {
            idalMovieFacade.updateMovie(movie);
        } catch (Exception e) {
            throw new MovieDAOException("Couldn't update a movie", e);
        }
    }

    @Override
    public Movie getMovie(int id) throws MovieDAOException {
        try {
            return idalMovieFacade.getMovie(id);
        } catch (Exception e) {
            throw new MovieDAOException("Couldn't get a movie with id", e);
        }
    }

    @Override
    public Category createCategory(String categoryName) throws CategoryDAOException {
        try {
            return idalMovieFacade.createCategory(categoryName);
        } catch (Exception e) {
            throw new CategoryDAOException("Couldn't create a category", e);
        }
    }

    @Override
    public void deleteCategory(Category category) throws CategoryDAOException {
        try {
            idalMovieFacade.deleteCategory(category);
        } catch (Exception e) {
            throw new CategoryDAOException("Couldn't delete a category", e);
        }
    }

    @Override
    public List<Movie> getCategoryMovie(int id) throws Exception {
        return idalMovieFacade.getCategoryMovie(id);
    }

    @Override
    public void addToCategory(Category selectedCategory, Movie selectedMovie) throws Exception {
        idalMovieFacade.addToCategory(selectedCategory, selectedMovie);
    }

    @Override
    public void removeFromCategory(Category selectedCatagory, Movie selectedMovie) throws Exception {
        idalMovieFacade.removeFromCategory(selectedCatagory, selectedMovie);
    }

    @Override
    public void removeFromCat(Category selectedItem) throws Exception {
        idalMovieFacade.removeFromCat(selectedItem);
    }

    @Override
    public void removeMoviesFromCat(Movie selectedItem) throws Exception {
        idalMovieFacade.removeMoviesFromCat(selectedItem);
    }
}
