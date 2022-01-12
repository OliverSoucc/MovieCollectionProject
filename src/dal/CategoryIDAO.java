package dal;

import be.Category;

import java.util.List;

public interface CategoryIDAO {
    public List<Category> getAllCategories();
    Category createCategory(Category categorytoCreate);
    public void deleteCategory();
    //public void alterCategory();

}
