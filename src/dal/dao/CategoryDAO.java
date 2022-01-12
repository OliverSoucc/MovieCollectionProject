package dal.dao;

import be.Category;
import dal.CategoryIDAO;
import dal.DatabaseConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO implements CategoryIDAO {
    private DatabaseConnector DBconnector;

    public CategoryDAO(){
        DBconnector = new DatabaseConnector();
    }

    @Override
    public List<Category> getAllCategories() {
        ArrayList<Category> allCategories = new ArrayList<>();
        try (Connection connection = DBconnector.getConnection()) {
            String sql = "SELECT * FROM Category";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                int id = resultSet.getInt("Id");
                String name = resultSet.getString("Name");
                Category category = new Category(id, name);
                allCategories.add(category);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allCategories;
    }

    @Override
    public Category createCategory() {
        return null;
    }

    @Override
    public void deleteCategory() {

    }
}
