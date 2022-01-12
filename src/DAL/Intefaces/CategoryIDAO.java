package DAL.Intefaces;

import BE.Category;

import java.util.List;

public interface CategoryIDAO {
    public List<Category> getAllCategories();
    public Category createCategory(Category categoryToCreate);
    public void deleteCategory(Category categoryToDelete);
    // public Category updateCategory(Category categoryToUpdate);
}
