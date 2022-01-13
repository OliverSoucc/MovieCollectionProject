package DAL.DAO;


import BE.Category;
import BE.Movie;

public class Test {
    public static void main(String[] args) {
        // int id, String name, float rating, String fileLink, int lastView, String category1, String category2, String category3
        MovieDAO databaseMovie = new MovieDAO();
        /*databaseMovie.createMovie(new Movie(0,  "Batman", 6.5F, "nikto",
                5, "hk", "hkk", "hhh"));*/ //TODO I cannot create a movie with category 1,2,3 longer than 1 char. Maybe the problem is in the settings of the SQL columns.



        CategoryDAO databaseCat = new CategoryDAO();


    }
}
