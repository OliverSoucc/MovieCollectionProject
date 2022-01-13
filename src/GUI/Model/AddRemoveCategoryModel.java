package GUI.model;

import BE.Category;

import java.util.ArrayList;

public class AddRemoveCategoryModel {
    ArrayList<Category> categoryArrayList;

    public AddRemoveCategoryModel () {
        categoryArrayList = new ArrayList<>();
    }

    public void createCategory(String categoryName) {
        Category newCategory;
        newCategory = new Category(categoryName);
        categoryArrayList.add(newCategory);
    }
    public ArrayList<Category> getCategoryArrayList (){
        return categoryArrayList;
    }

}
