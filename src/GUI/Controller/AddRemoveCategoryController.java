package gui.Controller;

import gui.model.AddRemoveCategoryModel;
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
    private Button cancelButton;

    AddRemoveCategoryModel addRemoveCategoryModel;
    JFrame jFrame;

    public AddRemoveCategoryController() {
        addRemoveCategoryModel = new AddRemoveCategoryModel();
    }

    public void handleSaveButton(ActionEvent event) {
        addRemoveCategoryModel.createCategory(nameTextField.getText());
        System.out.println(addRemoveCategoryModel.getCategoryArrayList());
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
