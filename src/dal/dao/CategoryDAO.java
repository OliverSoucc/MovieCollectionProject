package dal.dao;

import be.Category;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.CategoryIDAO;
import dal.DatabaseConnector;

import java.sql.*;
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
    public Category createCategory(Category categorytoCreate) {
        int id = categorytoCreate.getId();
        String name = categorytoCreate.getName();

        try (Connection connection = DBconnector.getConnection()){
            String sql = "INSERT INTO Category(Id, Name) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.addBatch();
            preparedStatement.executeBatch();

        } catch (SQLServerException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        categorytoCreate = new Category(id, name);
        return categorytoCreate;
    }
    
    @Override
    public void deleteCategory() {

    }
}
