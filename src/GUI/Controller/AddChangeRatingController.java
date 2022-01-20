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
import javafx.stage.Stage;

public class AddChangeRatingController {

    @FXML
    private Button cancelButton, saveButton;

    @FXML
    private TextField ratingTextField;

    MainPageModel mainPageModel;
    MainPageController mainPageController;
    Movie movie;

    public AddChangeRatingController() throws MovieCollectionManagerException {
        mainPageModel = new MainPageModel();
    }
    public void setMainPageController(MainPageController mainPageController){
        this.mainPageController = mainPageController;
    }
    public void setMovieToBeUpdated(Movie movie) {
        this.movie = movie;
        ratingTextField.setText(String.valueOf(movie.getRating()));
    }

    public void isCanceled(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alert window");
        alert.setHeaderText("Do you want to close this window?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
        }
    }

    public void isSaved(ActionEvent event) throws MovieDAOException, CategoryDAOException {
       if (ratingTextField.getText() == null || ratingTextField.getText().equals("")){
           System.out.println("Enter a valid name");
       } else {
           movie.setRating(Float.parseFloat(ratingTextField.getText().trim()));
           mainPageModel.updateRating(movie);
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
           alert.setTitle("Alert window");
           alert.setHeaderText("Do you want to close this window?");

           if (alert.showAndWait().get() == ButtonType.OK) {
               mainPageController.updateTableViewMovie();
               Stage stage = (Stage) saveButton.getScene().getWindow();
               stage.close();
           }
       }
    }
}
