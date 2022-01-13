package GUI.Controller;

import gui.model.AddRemoveMovieModel;
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
    public Button cancelSongBtn;
    @FXML
    public TextField titleTextField, ratingTextField, fileTextField, timeTextField;
    @FXML
    public AnchorPane anchorPane;

    AddRemoveMovieModel addRemoveMovieModel;


    public AddRemoveMovieController() {
        addRemoveMovieModel = new AddRemoveMovieModel();
    }

    public void handleSaveMovieBtn(ActionEvent actionEvent) {
        addRemoveMovieModel.createMovie(
                titleTextField.getText(),
                Float.parseFloat(ratingTextField.getText()),
                fileTextField.getText(),
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
            fileTextField.setText(file.getAbsolutePath());
            //System.out.println(file.getAbsolutePath());
        }

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

    public void isFileChooserPressed(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Movie Files", "*.mp4", "*.mpeg4"));
        File selectedFile = fileChooser.showOpenDialog(null);
        String filepath = selectedFile.getAbsolutePath();
        fileTextField.setText(filepath);
    }
}
