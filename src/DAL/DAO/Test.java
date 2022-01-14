package DAL.DAO;


import BE.Category;
import BE.Movie;

public class Test {
    public static void main(String[] args) throws Exception {
        Movie movie = new Movie(0, "Samanko", 9.5F, "seruste", 4, 6.5F,
                "Hororos", "Erotika", "Shemale");
        MovieDAO databaseMovie = new MovieDAO();
        /*databaseMovie.createMovie(new Movie(0,  "Batman", 6.5F, "nikto",
                5, 8.5F, "kako", "kakao", "kak"));*/

        CategoryDAO databaseCat = new CategoryDAO();
        /*databaseCat.createCategory("Si Gay");*/

        databaseMovie.updateMovie(movie);

    }
}
