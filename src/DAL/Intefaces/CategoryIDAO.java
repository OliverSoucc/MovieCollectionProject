package DAL.Intefaces;

import BE.Category;
import BLL.Exceptions.CategoryDAOException;

import java.util.List;

public interface CategoryIDAO {
    public List<Category> getAllCategories() throws CategoryDAOException;
    public Category createCategory(String name) throws CategoryDAOException;
    public void deleteCategory(Category categoryToDelete) throws Exception, CategoryDAOException;
    // public Category updateCategory(Category categoryToUpdate);
}
