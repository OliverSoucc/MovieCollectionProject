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
    public List<Movie> getAllMovies() {
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
                int duration = resultSet.getInt("Duration");
                Movie movie = new Movie(id, name, rating, fileLink, lastView);
                allMovies.add(movie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allMovies; // returns List of all Movies
    }

    @Override
    public Movie createMovie(Movie movieToCreate) {
        int id = 0;
        String name = movieToCreate.getName();
        float rating = movieToCreate.getRating();
        String fileLink = movieToCreate.getFileLink();
        int lastView = movieToCreate.getLastView();

        try (Connection connection = DBconnector.getConnection()){
            String sql = "INSERT INTO Movie VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            //preparedStatement.setInt(1, id);
            preparedStatement.setString(1, name);
            preparedStatement.setFloat(2, rating);
            preparedStatement.setString(3, fileLink);
            preparedStatement.setInt(4, lastView);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        movieToCreate = new Movie(id, name, rating, fileLink, lastView);
        return movieToCreate; // returns created movie object
    }

    @Override
    public void deleteMovie(Movie movieToDelete) {
        try (Connection connection = DBconnector.getConnection()){
            String sql = "DELETE FROM Movie WHERE Id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, movieToDelete.getId());
            preparedStatement.execute();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    } // deletes the specific movie by ID
}
