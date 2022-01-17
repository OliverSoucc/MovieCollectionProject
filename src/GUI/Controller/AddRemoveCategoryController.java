package GUI.Controller;

import BLL.Exceptions.CategoryDAOException;
import BLL.Exceptions.MovieCollectionManagerException;
import GUI.Model.MainPageModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;


public class AddRemoveCategoryController {
    @FXML
    private TextField nameTextField;
    @FXML
    private Button cancelButton, saveBtn;

    MainPageModel mainPageModel;
    JFrame jFrame;

    public AddRemoveCategoryController() throws MovieCollectionManagerException {
       mainPageModel = MainPageModel.getInstance();
    }

    public void handleSaveButton(ActionEvent event) throws CategoryDAOException {
        mainPageModel.createCategory(nameTextField.getText());
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void handleCancelButton(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alert window");
        alert.setHeaderText("Do you want to close this window?");

        if(alert.showAndWait().get() == ButtonType.OK ) {
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
        }
    }
}
