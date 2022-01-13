package gui.Controller;

import BE.Movie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainPageController implements Initializable {
    @FXML
    public TableColumn<Movie, String> nameColumn;
    @FXML
    public TableColumn<Movie, String> ratingColumn;
    @FXML
    public TableColumn<Movie, String> category1Column;
    @FXML
    public TableColumn <Movie, String> category2Column;
    @FXML
    public TableColumn <Movie, String> category3Column;
    @FXML
    public TextField filter;
    @FXML
    public TableView <Movie> tableView;


    float newValueFloat;
    private final ObservableList <Movie> movieObservableList = FXCollections.observableArrayList();

    public void movieButtonOnAction(ActionEvent actionEvent) throws IOException {
        Parent root;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/GUI/View/AddRemoveMovie.fxml"));
        root= loader.load();
        //AddRemoveMovieController movieController = loader.getController();
        //movieController.setController(this);
        Stage stage = new Stage();
        stage.setTitle("Add/Remove Movie");
        stage.setScene(new Scene(root));
        stage.show();
    }


    public void categoryButtonOnAction(ActionEvent actionEvent) throws IOException {
        Parent root;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/GUI/View/AddRemoveCategory.fxml"));
        root= loader.load();
        Stage stage = new Stage();
        stage.setTitle("Add/Remove Category");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void ratingButtonOnAction(ActionEvent actionEvent) throws IOException {
        Parent root;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/GUI/View/AddChangeRating.fxml"));
        root= loader.load();
        Stage stage = new Stage();
        stage.setTitle("Add/Change Rating");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableViewProperty();
        filterLogic();

    }

    private void tableViewProperty(){
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ratingColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));
        category1Column.setCellValueFactory(new PropertyValueFactory<>("category1"));
        category2Column.setCellValueFactory(new PropertyValueFactory<>("category2"));
        category3Column.setCellValueFactory(new PropertyValueFactory<>("category3"));

        Movie movie = new Movie("Star wars", 6.5F,"Fiction", "Sci-fi","Action");
        Movie movie1 = new Movie("Star wars 1", 7.5F,"Sci-fi", "Fiction","Lolo");
        Movie movie2 = new Movie("Star wars 2", 5.5F,"Fiction", "Action","Lolo");
        movieObservableList.addAll(movie, movie1, movie2); // there will be movies from database
    }

    private void filterLogic(){
        FilteredList<Movie> filteredData = new FilteredList<>(movieObservableList, b -> true);

        filter.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(employee -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String stringLowerCaseFilter = newValue.toLowerCase();

                if (employee.getName().toLowerCase().contains(stringLowerCaseFilter)) {
                    return true;
                } else if ((employee.getCategory1() + " " + employee.getCategory2() + " "
                            + employee.getCategory3()).toLowerCase().contains(stringLowerCaseFilter)) {
                    return true;
                } else if ((employee.getCategory1() + " " + employee.getCategory3() + " "
                        + employee.getCategory2()).toLowerCase().contains(stringLowerCaseFilter)) {
                    return true;
                } else if ((employee.getCategory2() + " " + employee.getCategory1() + " "
                        + employee.getCategory3()).toLowerCase().contains(stringLowerCaseFilter)) {
                    return true;
                }else if((employee.getCategory2() + " " + employee.getCategory3() + " "
                        + employee.getCategory1()).toLowerCase().contains(stringLowerCaseFilter)){
                    return true;
                }else if((employee.getCategory3() + " " + employee.getCategory2() + " "
                        + employee.getCategory1()).toLowerCase().contains(stringLowerCaseFilter)) {
                    return true;
                }else if((employee.getCategory3() + " " + employee.getCategory1() + " "
                        + employee.getCategory2()).toLowerCase().contains(stringLowerCaseFilter)) {
                    return true;
                }else if (isNumeric(stringLowerCaseFilter)) {
                    newValueFloat = Float.parseFloat(stringLowerCaseFilter);
                    if (employee.getRating() >= newValueFloat) {
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
}
