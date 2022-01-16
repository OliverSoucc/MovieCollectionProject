package GUI.Controller;

import BE.Category;
import BE.Movie;
import BLL.Exceptions.CategoryDAOException;
import BLL.Exceptions.MovieCollectionManagerException;
import BLL.Exceptions.MovieDAOException;
import GUI.Model.MainPageModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static javax.swing.JOptionPane.showMessageDialog;

public class MainPageController implements Initializable {
    @FXML
    public TableColumn<Movie, String> nameColumn, ratingColumn, imdbRatingColumn;
    @FXML
    public TextField filter;
    @FXML
    public TableView<Movie> tableView;
    @FXML
    public Button filterButton;
    @FXML
    private TableView<Category> categoryTableView;
    @FXML
    TableColumn<Category, String> categoryColumn;

    MainPageModel mainPageModel;
    float newValueFloat;
    boolean wasChecked = false;

    public MainPageController() throws MovieCollectionManagerException {
        mainPageModel = new MainPageModel();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            tableViewProperty();
        } catch (MovieDAOException e) {
            e.printStackTrace();
        }
        try {
            setupCategoryTableView();
        } catch (CategoryDAOException e) {
            e.printStackTrace();
        }
        try {
            updateTableView();
        } catch (MovieDAOException | CategoryDAOException e) {
            e.printStackTrace();
        }
//        try {
////            filterLogic();
//        } catch (MovieDAOException e) {
//            e.printStackTrace();
//        }
    }


    //Button methods
    public void createNewMovie(ActionEvent actionEvent) throws IOException {
        Parent root;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/GUI/View/AddRemoveMovie.fxml"));
        root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Add/Remove Movie");
        stage.setScene(new Scene(root));
        stage.show();
    }


    public void createNewCategory(ActionEvent actionEvent) throws IOException {
        Parent root;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/GUI/View/AddRemoveCategory.fxml"));
        root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Add/Remove Category");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void createNewRating(ActionEvent actionEvent) throws IOException {
        Parent root;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/GUI/View/AddChangeRating.fxml"));
        root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Add/Change Rating");
        stage.setScene(new Scene(root));
        stage.show();
    }


    private void tableViewProperty() throws MovieDAOException {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ratingColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));
        imdbRatingColumn.setCellValueFactory(new PropertyValueFactory<>("imdb"));
        tableView.setItems(mainPageModel.getMovieObservableList());

        if(wasChecked == false)
        {
            //alertWindow();
        }
        wasChecked = true;
    }

    private void setupCategoryTableView() throws CategoryDAOException {
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        categoryTableView.setItems(mainPageModel.getAllCategories());
    }
    // int id, String name, float rating, String fileLink, int lastView, String category1, String category2, String category3


    private void filterLogic() throws MovieDAOException {
        FilteredList<Movie> filteredData = new FilteredList<>(mainPageModel.getMovieObservableList(), b -> true);

        filter.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(movie -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String stringLowerCaseFilter = newValue.toLowerCase();

                if (movie.getName().toLowerCase().contains(stringLowerCaseFilter)) {
                    return true;
                } else if (isNumeric(stringLowerCaseFilter)) {
                    newValueFloat = Float.parseFloat(stringLowerCaseFilter);
                    if (movie.getRating() >= newValueFloat && movie.getImdb() >= newValueFloat) {
                        return true;
                    }
                }
                return false;
            });
        });
        tableView.setItems(filteredData);
    }

    private static boolean isNumeric(String str) {
        try {
            Float.parseFloat(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void filterOnAction(KeyEvent keyEvent) {
        filterButton.setText("Clear");
    }

    public void filterButtonOnAction(ActionEvent actionEvent) {
        filter.clear();
        filterButton.setText("Search");
    }

    private void updateTableView() throws MovieDAOException, CategoryDAOException {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ratingColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));
        imdbRatingColumn.setCellValueFactory(new PropertyValueFactory<>("imdb"));
        tableView.setItems(mainPageModel.getMovieObservableList());

        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        categoryTableView.setItems(mainPageModel.getAllCategories());

    }

    public void handleRefresh(ActionEvent event) throws MovieDAOException, CategoryDAOException {
        updateTableView();
    }

    public void playMovie(ActionEvent actionEvent) {
        Runtime runtime = Runtime.getRuntime();
        try {
            String[] command = {"cmd.exe", "/k", "Start", tableView.getSelectionModel().getSelectedItem().getFileLink()};
            Process p =  runtime.exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void alertWindow()
    {
        for(int i = 0;i < tableView.getItems().size();i++) {
            float rating = tableView.getItems().get(i).getRating();
            if (rating < 6.0) {
                showMessageDialog(null, "Remember to delete movies with personal rating under 6.0 and movies that have not been watched for over two years");
                break;
            }
        }
    }
}