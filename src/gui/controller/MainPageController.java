package gui.controller;

import be.Movie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;
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

    private final ObservableList <Movie> movieObservableList = FXCollections.observableArrayList();

    public void movieButtonOnAction(ActionEvent actionEvent) throws IOException {
        Parent root;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/gui/view/AddRemoveMovie.fxml"));
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
        loader.setLocation(getClass().getResource("/gui/view/AddRemoveCategory.fxml"));
        root= loader.load();
        Stage stage = new Stage();
        stage.setTitle("Add/Remove Category");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void ratingButtonOnAction(ActionEvent actionEvent) throws IOException {
        Parent root;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/gui/view/AddChangeRating.fxml"));
        root= loader.load();
        Stage stage = new Stage();
        stage.setTitle("Add/Change Rating");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    private void tableView(){
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ratingColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));
        //category1Column.setCellValueFactory(new PropertyValueFactory<>(""));
        //category2Column.setCellValueFactory(new PropertyValueFactory<>(""));
        //category3Column.setCellValueFactory(new PropertyValueFactory<>(""));
    }
}
