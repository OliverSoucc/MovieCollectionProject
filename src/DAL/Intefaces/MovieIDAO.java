package DAL.Intefaces;

import BE.Movie;

import java.sql.Date;
import java.util.List;

public interface MovieIDAO {
    public List<Movie> getAllMovies() throws Exception;
    public Movie createMovie(String name, float rating, String fileLink, Date lastView, float imdb) throws Exception;
    public void deleteMovie(Movie movieToDelete) throws Exception;
    Movie getMovie(int id) throws Exception;
    public void updateMovieRating(Movie movie) throws Exception;
    public void updateMovieLastView(Movie selectedItem)throws Exception;
}
