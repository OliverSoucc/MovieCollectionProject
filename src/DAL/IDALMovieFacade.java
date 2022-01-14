package DAL;


import BE.Category;

import java.util.List;

public interface IDALMovieFacade {
    List<Category> getAllCategories() throws Exception;
}
