package DAL.DAO;

import BE.Category;
import DAL.Intefaces.CategoryIDAO;
import DAL.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO implements CategoryIDAO {
    private DatabaseConnector DBconnector;

    public CategoryDAO(){
        DBconnector = new DatabaseConnector();
    }

    @Override
    public List<Category> getAllCategories() throws Exception{
        List<Category> allCategories = new ArrayList<>();
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
        }
        return allCategories; // returns List of all Categories
    }

    @Override
    public Category createCategory(String name) throws Exception {
        int id = 0;
        try (Connection connection = DBconnector.getConnection()){
            String sql = "INSERT INTO Category(Name) VALUES (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.addBatch();
            preparedStatement.executeBatch();
        }
        Category categoryToCreate = new Category(id, name);
        return categoryToCreate; // returns created Category object
    }

    @Override
    public void deleteCategory(Category categoryToDelete) throws Exception{
        try (Connection connection = DBconnector.getConnection()){
            String sql = "DELETE FROM Category WHERE Id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, categoryToDelete.getId());
            preparedStatement.execute();
        }
    } // deletes the specific category by ID
}
