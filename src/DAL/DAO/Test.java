package DAL.DAO;


import BE.Category;
import BE.Movie;

public class Test {
    public static void main(String[] args) {
        MovieDAO databaseMovie = new MovieDAO();
        databaseMovie.deleteMovie(new Movie(0, "Vajko", 6.5F, "vajcak", 5));

        CategoryDAO databaseCat = new CategoryDAO();


    }
}
