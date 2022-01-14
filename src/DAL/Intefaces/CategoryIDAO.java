package DAL.Intefaces;

import BE.Category;

import java.util.List;

public interface CategoryIDAO {
    public List<Category> getAllCategories() throws Exception;
    public Category createCategory(String name) throws Exception;
    public void deleteCategory(Category categoryToDelete) throws Exception;
    // public Category updateCategory(Category categoryToUpdate);
}
