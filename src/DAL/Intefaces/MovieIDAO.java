package DAL.Intefaces;

import BE.Movie;

import java.util.List;

public interface MovieIDAO {
    List<Movie> getAllMovies() throws Exception;
    Movie createMovie(Movie movieToCreate) throws Exception;
    void deleteMovie(Movie movieToDelete) throws Exception;
    Movie getMovie(int id) throws Exception;
    Movie updateMovieRating(Movie movie, float newRating) throws Exception;

}
