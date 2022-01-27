package DAL.Intefaces;

import BE.Category;
import BLL.Exceptions.CategoryDAOException;

import java.util.List;

public interface CategoryIDAO {
    List<Category> getAllCategories() throws Exception;

    Category createCategory(String name) throws Exception;

    void deleteCategory(Category categoryToDelete) throws Exception;
}
