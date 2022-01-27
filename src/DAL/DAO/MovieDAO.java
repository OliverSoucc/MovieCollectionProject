package DAL.DAO;

import BE.Movie;
import DAL.DatabaseConnector;
import DAL.Intefaces.MovieIDAO;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;


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
                java.sql.Date lastView = resultSet.getDate("LastView");
                float imdb = resultSet.getFloat("Imdb");
                Movie movie = new Movie(id, name, rating, fileLink, lastView, imdb);

                allMovies.add(movie);
            }
        }
        return allMovies;
    }

    @Override
    public Movie createMovie(String name, float rating, String fileLink, java.sql.Date lastView, float imdb) throws Exception{

        try (Connection connection = DBconnector.getConnection()){
            String sql = "INSERT INTO Movie VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setFloat(2, rating);
            preparedStatement.setString(3, fileLink);
            preparedStatement.setDate(4, lastView);
            preparedStatement.setFloat(5, imdb);
            preparedStatement.execute();

        }
        Movie movieToCreate = new Movie(getNextId(), name, rating, fileLink, lastView, imdb);
        return movieToCreate;
    }

    @Override
    public void deleteMovie(Movie movieToDelete) throws Exception{
        try (Connection connection = DBconnector.getConnection()){
            String sql = "DELETE FROM Movie WHERE Id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, movieToDelete.getId());
            preparedStatement.execute();
        }
    }

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
                java.sql.Date lastView = resultSet.getDate("LastView");
                float imdb = resultSet.getFloat("Imdb");
                movie = new Movie(id, name, rating, fileLink, lastView, imdb);
            }
        }
        return movie;
    }

    @Override
    public void updateMovieRating(Movie movie) throws Exception {
        String sql = "UPDATE Movie SET Rating = ? WHERE Id = ? ";
        try (Connection connection = DBconnector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setFloat(1, movie.getRating());
            statement.setInt(2, movie.getId());
            statement.executeUpdate();
        }
    }

    @Override
    public void updateMovieLastView(Movie selectedItem) throws Exception {
        java.util.Date utilStartDate = new Date();
        java.sql.Date date = new java.sql.Date(utilStartDate.getTime());
        String sql = "UPDATE Movie SET LastView = ? WHERE Id = ? ";
        Movie newMovie;
        try (Connection connection = DBconnector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDate(1, date);
            statement.setInt(2, selectedItem.getId());
            statement.executeUpdate();
        }
    }

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
        }
    }

}
