package DAL.DAO;

import BE.Category;
import BE.Movie;
import DAL.DatabaseConnector;
import DAL.Intefaces.CatMovieIDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CatMovieDAO implements CatMovieIDAO {
    private DatabaseConnector DBconnector;

    public CatMovieDAO(){
        DBconnector = new DatabaseConnector();
    }


    @Override
    public void setCategoriesForMovie(Movie movie, Category category1, Category category2, Category category3) {

    } //This method should


    public List<Movie> getCategoryMovie(int id) throws SQLException {
        List<Movie> listOfMovies = new ArrayList<>();
            try (Connection connection = DBconnector.getConnection()){
                String sql = "";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);

                preparedStatement.setInt(1, id);

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    //Movie movieToAdd = new Movie();
                }
            }



        return listOfMovies;
    } // returns the list of all movies for the specific category by ID
    // I added some SQLException in this method.
}
