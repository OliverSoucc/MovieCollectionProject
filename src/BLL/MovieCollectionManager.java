package BLL;

import BE.Category;
import BE.Movie;
import BLL.Exceptions.CatMovieDAOException;
import BLL.Exceptions.CategoryDAOException;
import BLL.Exceptions.MovieCollectionManagerException;
import BLL.Exceptions.MovieDAOException;
import DAL.DAOFacade;
import DAL.IDALMovieFacade;

import java.sql.Date;
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
    public Movie createMovie(String name, float rating, String fileLink, Date lastView, float imdb) throws MovieDAOException {
        try {
            return idalMovieFacade.createMovie(name, rating, fileLink, lastView, imdb);
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
    public void updateMovieRating(Movie movie) throws MovieDAOException {
        try {
            idalMovieFacade.updateMovieRating(movie);
        } catch (Exception e) {
            throw new MovieDAOException("Couldn't update rating", e);
        }
    }

    @Override
    public List<Movie> getCategoryMovie(int id) throws CatMovieDAOException {
        try {
            return idalMovieFacade.getCategoryMovie(id);
        } catch (Exception e) {
            throw new CatMovieDAOException("Could not get all category movie (CatMovie)",e);
        }
    }

    @Override
    public void addToCategory(Category selectedCategory, Movie selectedMovie) throws CatMovieDAOException {
        try {
            idalMovieFacade.addToCategory(selectedCategory, selectedMovie);
        } catch (Exception e) {
            throw new CatMovieDAOException("Could not add movie to category (CatMovie)",e);
        }
    }

    @Override
    public void removeFromCategory(Category selectedCatagory, Movie selectedMovie) throws CatMovieDAOException {
        try {
            idalMovieFacade.removeFromCategory(selectedCatagory, selectedMovie);
        } catch (Exception e) {
            throw new CatMovieDAOException("Could not remove movie from category (CatMovie)",e);
        }
    }

    @Override
    public void removeFromCat(Category selectedItem) throws CatMovieDAOException {
        try {
            idalMovieFacade.removeFromCat(selectedItem);
        } catch (Exception e) {
            throw new CatMovieDAOException("Could not remove category from CatMovie, when deleting whole category",e);
        }
    }

    @Override
    public void removeMoviesFromCat(Movie selectedItem) throws CatMovieDAOException {
        try {
            idalMovieFacade.removeMoviesFromCat(selectedItem);
        } catch (Exception e) {
            throw new CatMovieDAOException("Could not remove movie from CatMovie, when deleting whole movie",e);
        }
    }

    @Override
    public void updateMovieLastView(Movie selectedItem) throws MovieDAOException {
        try {
            idalMovieFacade.updateMovieLastView(selectedItem);
        } catch (Exception e) {
            throw new MovieDAOException("Couldn't update LastView", e);
        }
    }
}
