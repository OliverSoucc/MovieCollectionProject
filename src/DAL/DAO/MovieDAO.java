package DAL.DAO;

import BE.Movie;
import DAL.DatabaseConnector;
import DAL.Intefaces.MovieIDAO;
import com.microsoft.sqlserver.jdbc.SQLServerException;

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
                Movie movie = new Movie(id, name, rating, fileLink, lastView, imdb);

                allMovies.add(movie);
            }
        }
        return allMovies; // returns List of all Movies
    }

    @Override
    public Movie createMovie(String name, float rating, String fileLink, int lastView, float imdb) throws Exception{

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
        Movie movieToCreate = new Movie(getNextId(), name, rating, fileLink, lastView, imdb);
        System.out.println("Id of the new created movie in DAO is " + movieToCreate.getId());
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
    public Movie updateMovieRating(Movie movie, float newRating) throws Exception {
        String sql = "UPDATE Movie SET Rating = ? WHERE Id = ? ";
        Movie newMovie;
        try (Connection connection = DBconnector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setFloat(1, newRating);
            statement.setInt(2, movie.getId());
            statement.executeUpdate();
            newMovie = new Movie(movie.getId(), movie.getName(), newRating, movie.getFileLink(), movie.getLastView(), movie.getImdb());
        }
        return newMovie;
    } // returns an updated movie with new personal rating

    private int getNextId() throws Exception {
        int newestID = -1;
        try (Connection con = DBconnector.getConnection()) {
            String query = "SELECT TOP(1) * FROM Movie ORDER by id desc";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            ResultSet rs = preparedStmt.executeQuery();
            while (rs.next()) {
                newestID = rs.getInt("id");
            }
            return newestID;
        } catch (SQLServerException ex) {
            throw new Exception("Cannot connect to server");
        } catch (SQLException ex) {
            throw new Exception("Query cannot be executed");
        }
    }
}
