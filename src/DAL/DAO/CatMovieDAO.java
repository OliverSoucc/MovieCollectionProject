package DAL.DAO;

import BE.Category;
import BE.Movie;
import DAL.DatabaseConnector;
import DAL.Intefaces.CatMovieIDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CatMovieDAO implements CatMovieIDAO {
    private DatabaseConnector DBconnector;

    public CatMovieDAO() {
        DBconnector = new DatabaseConnector();
    }

    public List<Movie> getCategoryMovie(int id) throws Exception {
        List<Movie> newMovieList = new ArrayList();
        try (Connection con = DBconnector.getConnection()) {
            String query = ""
                    + "SELECT Movie.Id, Movie.Name , Movie.Rating , Movie.FileLink, Movie.LastView , Movie.Imdb FROM CatMovie "
                    + "INNER JOIN Movie "
                    + "ON CatMovie.MovieId = Movie.id "
                    + "WHERE CatMovie.CategoryId = ? ";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, id);
            ResultSet rs = preparedStmt.executeQuery();
            while (rs.next()) {
                Movie movie = new Movie(rs.getInt("Id"), rs.getString("Name"), rs.getFloat("Rating"),
                        rs.getString("FileLink"), rs.getDate("LastView"), rs.getInt("Imdb"));
                newMovieList.add(movie);
            }
            return newMovieList;
        }

    }

    public void addToCategory(Category selectedCategory, Movie selectedMovie) throws Exception {
        String sql = "INSERT INTO CatMovie(CategoryId,MovieId) VALUES (?,?)";
        System.out.println("Id of the selected " + selectedCategory.getName() + " is " + selectedCategory.getId());
        System.out.println("Id of the selected " + selectedMovie.getName() + " is " + selectedMovie.getId());
        try (Connection con = DBconnector.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, selectedCategory.getId());
            ps.setInt(2, selectedMovie.getId());
            ps.addBatch();
            ps.executeBatch();
        }
    }

    public void removeFromCategory(Category selectedCatagory, Movie selectedMovie) throws Exception {
        try (Connection con = DBconnector.getConnection()) {
            System.out.println(selectedCatagory.getId() + " " + selectedMovie.getId());
            String query = "DELETE from CatMovie WHERE CategoryId = ? AND MovieId = ?";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, selectedCatagory.getId());
            preparedStmt.setInt(2, selectedMovie.getId());
            preparedStmt.execute();
        }
    } // removes the exact connection of movie and category

    public void removeFromCat(Category selectedItem) throws Exception {
        try (Connection con = DBconnector.getConnection()) {
            String query = "DELETE from CatMovie WHERE CategoryId = ?";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, selectedItem.getId());
            preparedStmt.execute();
        }
    } // removes the category at all

    public void removeMoviesFromCat(Movie selectedItem) throws Exception {
        try (Connection con = DBconnector.getConnection()) {
            String query = "DELETE from CatMovie WHERE MovieId = ?";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, selectedItem.getId());
            preparedStmt.execute();
        }
    } // removes the movie at all
}
