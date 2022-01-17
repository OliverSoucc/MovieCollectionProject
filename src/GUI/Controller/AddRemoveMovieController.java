package GUI.Controller;

import BE.Movie;
import BLL.Exceptions.CategoryDAOException;
import BLL.Exceptions.MovieCollectionManagerException;
import BLL.Exceptions.MovieDAOException;
import GUI.Model.MainPageModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class AddRemoveMovieController {
    @FXML
    public Button cancelBtn, saveBtn;
    @FXML
    public TextField titleTextField, ratingTextField, fileTextField, imdbRating;
    @FXML
    public AnchorPane anchorPane;

    MainPageModel mainPageModel;
    MainPageController mainPageController;


    public AddRemoveMovieController() throws MovieCollectionManagerException {
        mainPageModel = MainPageModel.getInstance();
        mainPageController = new MainPageController();
    }

    public void handleSaveMovieBtn(ActionEvent actionEvent) throws MovieDAOException {
        Movie movie = new Movie(titleTextField.getText(), Float.parseFloat(ratingTextField.getText()), fileTextField.getText(), 0, Float.parseFloat(imdbRating.getText()));
        mainPageModel.createMovie(movie);
        System.out.println(mainPageModel.getMovieObservableList());
        closeWindow(saveBtn);
    }


    public void handleCancelMovieBtn(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alert window");
        alert.setHeaderText("Do you want to close this window?");

        if(alert.showAndWait().get() == ButtonType.OK ) {
            closeWindow(cancelBtn);
        }
    }


    //Handles choosing a file
    public void handleChooseFile(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Movie Files", "*.mp4", "*.mpeg4"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (!selectedFile.getAbsolutePath().equals("")) {
            String filepath = selectedFile.getAbsolutePath();
            fileTextField.setText(filepath);
        }
    } // TODO fix this

    //Closes current window
    private void closeWindow(Button button) {
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }

    /*public void handleChooseFile1(ActionEvent actionEvent) {
        final DirectoryChooser directoryChooser = new DirectoryChooser();

        Stage stage = (Stage) anchorPane.getScene().getWindow();
        File file = directoryChooser.showDialog(stage);


        if(file != null){
            fileTextField.setText(file.getAbsolutePath());
            //System.out.println(file.getAbsolutePath());
        }

    }*/   // old method
}
