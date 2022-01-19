package GUI.Controller;

import BLL.Exceptions.MovieCollectionManagerException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddChangeRatingController {
    @FXML
    private Button cancelButton;
    @FXML
    private TextField ratingTextField;

    MainPageController mainPageController;

    public AddChangeRatingController() {
        try {
            mainPageController = new MainPageController();
        } catch (MovieCollectionManagerException e) {
            e.printStackTrace();
        }
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

    public void isSaved(ActionEvent event) {
        float rating = mainPageController.tableView.getSelectionModel().getSelectedItem().getRating();
        ratingTextField.setText(String.valueOf(rating));
    }
}
