package GUI.Controller;

import BE.Movie;
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
    public TableColumn<Movie, String> nameColumn, ratingColumn;
    @FXML
    public TextField filter;
    @FXML
    public TableView<Movie> tableView;
    @FXML
    public Button filterButton;
    @FXML
    public TableColumn<Movie, String> imdbRatingColumn;

    MainPageModel mainPageModel;
    float newValueFloat;

    public MainPageController() throws MovieCollectionManagerException {
        mainPageModel = new MainPageModel();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableViewProperty();
        try {
            filterLogic();
        } catch (MovieDAOException e) {
            e.printStackTrace();
        }
        //updateTableView();
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


    private void tableViewProperty() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ratingColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));
        imdbRatingColumn.setCellValueFactory(new PropertyValueFactory<>("imdb"));


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

    private void updateTableView() throws MovieDAOException {
        tableView.getItems().clear();
        tableView.refresh();
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ratingColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));
        imdbRatingColumn.setCellValueFactory(new PropertyValueFactory<>("imdb"));
        tableView.getItems().setAll(mainPageModel.getMovieObservableList());

    }
}