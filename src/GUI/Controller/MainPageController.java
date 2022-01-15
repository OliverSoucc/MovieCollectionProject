package GUI.Controller;

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
    @FXML
    public Button filterButton;
    @FXML
    public Button movieButton;
    @FXML
    public TableColumn<Movie, String> imdbRatingColumn;


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
        alertWindow();

    }

    private void tableViewProperty(){
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ratingColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));
        imdbRatingColumn.setCellValueFactory(new PropertyValueFactory<>("imdb"));
//        category1Column.setCellValueFactory(new PropertyValueFactory<>("category1"));
//        category2Column.setCellValueFactory(new PropertyValueFactory<>("category2"));
//        category3Column.setCellValueFactory(new PropertyValueFactory<>("category3"));

        Movie movie = new Movie(0, "Star wars", 9.5F,"gay",3,5.8F);
        Movie movie1 = new Movie(0, "Star wars 1", 7.5F,"gay",4,7.0F);
        Movie movie2 = new Movie(0, "Star wars 2", 5.5F,"gay",5,8.0F);
        movieObservableList.addAll(movie, movie1, movie2); // there will be movies from database

    }
    // int id, String name, float rating, String fileLink, int lastView, String category1, String category2, String category3


    private void filterLogic(){
        FilteredList<Movie> filteredData = new FilteredList<>(movieObservableList, b -> true);

        filter.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(movie -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String stringLowerCaseFilter = newValue.toLowerCase();
                
                if (movie.getName().toLowerCase().contains(stringLowerCaseFilter)) {
                    return true;
//                } else if ((movie.getCategory1() + " " + movie.getCategory2() + " "
//                            + movie.getCategory3()).toLowerCase().contains(stringLowerCaseFilter)) {
//                    return true;
//                } else if ((movie.getCategory1() + " " + movie.getCategory3() + " "
//                        + movie.getCategory2()).toLowerCase().contains(stringLowerCaseFilter)) {
//                    return true;
//                } else if ((movie.getCategory2() + " " + movie.getCategory1() + " "
//                        + movie.getCategory3()).toLowerCase().contains(stringLowerCaseFilter)) {
//                    return true;
//                }else if((movie.getCategory2() + " " + movie.getCategory3() + " "
//                        + movie.getCategory1()).toLowerCase().contains(stringLowerCaseFilter)){
//                    return true;
//                }else if((movie.getCategory3() + " " + movie.getCategory2() + " "
//                        + movie.getCategory1()).toLowerCase().contains(stringLowerCaseFilter)) {
//                    return true;
//                }else if((movie.getCategory3() + " " + movie.getCategory1() + " "
//                        + movie.getCategory2()).toLowerCase().contains(stringLowerCaseFilter)) {
//                    return true;
                }else if (isNumeric(stringLowerCaseFilter)) {
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

    public void alertWindow()
    {
        for(int i = 0;i < tableView.getItems().size();i++) {
            float rating = tableView.getItems().get(i).getRating();
                if (rating < 6.0) {
                    showMessageDialog(null, "Remember to delete movies with personal rating under 6.0");
                    break;
                }
        }
    }


}