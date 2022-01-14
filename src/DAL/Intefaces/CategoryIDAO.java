package DAL.Intefaces;

import BE.Category;

import java.util.List;

public interface CategoryIDAO {
    public List<Category> getAllCategories();
    public Category createCategory(String name);
    public void deleteCategory(Category categoryToDelete);
    // public Category updateCategory(Category categoryToUpdate);
}
