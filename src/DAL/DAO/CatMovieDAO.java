package DAL.DAO;

import BE.Category;
import BE.Movie;
import DAL.DatabaseConnector;
import DAL.Intefaces.CatMovieIDAO;

public class CatMovieDAO implements CatMovieIDAO {
    private DatabaseConnector DBconnector;

    public CatMovieDAO(){
        DBconnector = new DatabaseConnector();
    }


    @Override
    public void setCategoriesForMovie(Movie movie, Category category1, Category category2, Category category3) {

    } //This method should


}
