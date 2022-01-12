package gui.controller;

import gui.model.AddRemoveMovieModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;

public class AddRemoveMovieController {
    @FXML
    public Button cancelSongBtn;
    @FXML
    public TextField titleTextField, ratingTextField, pathTextField, timeTextField;
    @FXML
    public AnchorPane anchorPane;

    AddRemoveMovieModel addRemoveMovieModel;

    public AddRemoveMovieController() {
        addRemoveMovieModel = new AddRemoveMovieModel();
    }

    public void handleSaveNewMovie(ActionEvent actionEvent) {
        addRemoveMovieModel.createMovie(
                titleTextField.getText(),
                Float.parseFloat(ratingTextField.getText()),
                pathTextField.getText(),
                handleTimeField(timeTextField.getText())
        );
        System.out.println(addRemoveMovieModel.getMovieDB());
    }


    public void handleCancelMovieBtn(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alert window");
        alert.setHeaderText("Do you want to close this window?");


        if(alert.showAndWait().get() == ButtonType.OK ) {
            Stage stage = (Stage) cancelSongBtn.getScene().getWindow();
            stage.close();
        }
    }


    // Choosing a movie from the finder
    public void handleChooseFile(ActionEvent actionEvent) {
        final DirectoryChooser directoryChooser = new DirectoryChooser();

        Stage stage = (Stage) anchorPane.getScene().getWindow();
        File file = directoryChooser.showDialog(stage);

        if(file != null){
            pathTextField.setText(file.getAbsolutePath());
            //System.out.println(file.getAbsolutePath());
        }

    }

    public void horrorAction(ActionEvent actionEvent) {
    }

    public void trillerAction(ActionEvent actionEvent) {
    }

    public void actionOnAction(ActionEvent actionEvent) {
    }

    public void actionErotic(ActionEvent actionEvent) {
    }
    private int handleTimeField(String actualTime) {
        int time;
        if (actualTime.equals(" ") || actualTime.isEmpty()) {
            time = 0;
        }
        else {
            time = Integer.parseInt(actualTime);
        }
        return time;
    }
}
