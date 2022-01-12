package DAL.Intefaces;

import BE.Movie;

import java.util.List;

public interface MovieIDAO {
    public List<Movie> getAllMovies();
    public Movie createMovie(Movie movieToCreate);
    public void deleteMovie(Movie movieToDelete);
    // public Movie updateCategory(Movie movieToUpdate);
}
