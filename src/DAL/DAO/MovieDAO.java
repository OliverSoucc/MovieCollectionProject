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
                String category1 = resultSet.getString("Category1");
                String category2 = resultSet.getString("Category2");
                String category3 = resultSet.getString("Category3");

                Movie movie = new Movie(id, name, rating, fileLink, lastView, imdb, category1, category2, category3);

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
        String category1 = movieToCreate.getCategory1();
        String category2 = movieToCreate.getCategory2();
        String category3 = movieToCreate.getCategory3();

        try (Connection connection = DBconnector.getConnection()){
            String sql = "INSERT INTO Movie VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setFloat(2, rating);
            preparedStatement.setString(3, fileLink);
            preparedStatement.setInt(4, lastView);
            preparedStatement.setFloat(5, imdb);
            preparedStatement.setString(6, category1);
            preparedStatement.setString(7, category2);
            preparedStatement.setString(8, category3);
            preparedStatement.execute();

        }
        movieToCreate = new Movie(id, name, rating, fileLink, lastView, imdb, category1, category2, category3);
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
                String category1 = resultSet.getString("Category1");
                String category2 = resultSet.getString("Category2");
                String category3 = resultSet.getString("Category3");
                movie = new Movie(id, name, rating, fileLink, lastView, imdb, category1, category2, category3);
            }
        }
        return movie;
    }

    @Override
    public void updateMovie(Movie movie) throws Exception {
        String sql = "UPDATE Movie SET Rating = ?, Category1 = ?,Category2 = ?,Category3 = ? WHERE Id = ? ";
        try (Connection connection = DBconnector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setFloat(1, movie.getRating());
            statement.setString(2, movie.getCategory1());
            statement.setString(3, movie.getCategory2());
            statement.setString(4, movie.getCategory3());
            statement.executeUpdate();
        }
    }
}
