package gui.Controller;

import gui.model.AddRemoveCategoryModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import javax.swing.*;


public class AddRemoveCategoryController {
    @FXML
    private TextField nameTextField;

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

    }
}
