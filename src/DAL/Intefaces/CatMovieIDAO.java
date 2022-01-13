package DAL.Intefaces;

import BE.Category;
import BE.Movie;

public interface CatMovieIDAO {

    public void setCategoriesForMovie(Movie movie, Category category1, Category category2, Category category3);
}
