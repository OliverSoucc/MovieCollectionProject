package DAL.Intefaces;

import BE.Movie;

import java.util.List;

public interface MovieIDAO {
    public List<Movie> getAllMovies() throws Exception;
    public Movie createMovie(Movie movieToCreate) throws Exception;
    public void deleteMovie(Movie movieToDelete) throws Exception;
    Movie getMovie(int id) throws Exception;
    public Movie updateMovieRating(Movie movie, float newRating) throws Exception;
}
