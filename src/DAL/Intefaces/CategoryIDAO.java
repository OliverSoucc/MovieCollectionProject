package DAL.Intefaces;

import BE.Category;

import java.util.List;

public interface CategoryIDAO {
    public List<Category> getAllCategories();
    Category createCategory(Category categoryToCreate);
    public void deleteCategory(Category categoryToDelete);
    //public void updateCategory();

}
