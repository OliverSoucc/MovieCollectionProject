package DAL.Intefaces;

import BE.Movie;

import java.util.List;

public interface MovieIDAO {
    public List<Movie> getAllMovies();
    public Movie createCategory(Movie movieToCreate);
    public void deleteCategory(Movie movieToDelete);
    // public Movie updateCategory(Movie movieToUpdate);
}
