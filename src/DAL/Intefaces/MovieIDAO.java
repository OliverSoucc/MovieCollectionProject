package DAL.Intefaces;

import BE.Movie;

import java.sql.Date;
import java.util.List;

public interface MovieIDAO {
    List<Movie> getAllMovies() throws Exception;

    Movie createMovie(String name, float rating, String fileLink, Date lastView, float imdb) throws Exception;

    void deleteMovie(Movie movieToDelete) throws Exception;

    Movie getMovie(int id) throws Exception;

    void updateMovieRating(Movie movie) throws Exception;

    void updateMovieLastView(Movie selectedItem) throws Exception;
}
