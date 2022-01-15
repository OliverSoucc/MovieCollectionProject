package DAL.DAO;

import BE.Movie;
import DAL.DatabaseConnector;
import DAL.Intefaces.MovieIDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO implements MovieIDAO {
    private DatabaseConnector DBconnector;

    public MovieDAO(){
        DBconnector = new DatabaseConnector();
    }

    @Override
    public List<Movie> getAllMovies() throws Exception{
        ArrayList<Movie> allMovies = new ArrayList<>();
        try (Connection connection = DBconnector.getConnection()) {
            String sql = "SELECT * FROM Movie";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                int id = resultSet.getInt("Id");
                String name = resultSet.getString("Name");
                float rating = resultSet.getFloat("Rating");
                String fileLink = resultSet.getString("FileLink");
                int lastView = resultSet.getInt("LastView");
                float imdb = resultSet.getFloat("Imdb");
                //int duration = resultSet.getInt("Duration");
                Movie movie = new Movie(id, name, rating, fileLink, lastView, imdb);

                allMovies.add(movie);
            }
        }
        return allMovies; // returns List of all Movies
    }

    @Override
    public Movie createMovie(Movie movieToCreate) throws Exception{
        int id = 0;
        String name = movieToCreate.getName();
        float rating = movieToCreate.getRating();
        String fileLink = movieToCreate.getFileLink();
        int lastView = movieToCreate.getLastView();
        float imdb = movieToCreate.getImdb();

        try (Connection connection = DBconnector.getConnection()){
            String sql = "INSERT INTO Movie VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setFloat(2, rating);
            preparedStatement.setString(3, fileLink);
            preparedStatement.setInt(4, lastView);
            preparedStatement.setFloat(5, imdb);
            preparedStatement.execute();

        }
        movieToCreate = new Movie(id, name, rating, fileLink, lastView, imdb);
        return movieToCreate; // returns created movie object
    }

    @Override
    public void deleteMovie(Movie movieToDelete) throws Exception{
        try (Connection connection = DBconnector.getConnection()){
            String sql = "DELETE FROM Movie WHERE Id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, movieToDelete.getId());
            preparedStatement.execute();
        }
    } // deletes the specific movie by ID

    @Override
    public Movie getMovie(int id) throws Exception {
        Movie movie = null;
        String sql = "SELECT *  FROM Movie WHERE Id=?";
        try (Connection connection = DBconnector.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            if (resultSet.next()) {
                String name = resultSet.getString("Name");
                float rating = resultSet.getFloat("Rating");
                String fileLink = resultSet.getString("FileLink");
                int lastView = resultSet.getInt("LastView");
                float imdb = resultSet.getFloat("Imdb");
                movie = new Movie(id, name, rating, fileLink, lastView, imdb);
            }
        }
        return movie;
    }

    @Override
    public void updateMovie(Movie movie) throws Exception {
        String sql = "UPDATE Movie SET Rating = ? WHERE Id = ? ";
        try (Connection connection = DBconnector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setFloat(1, movie.getRating());
            statement.executeUpdate();
        }
    }
}
