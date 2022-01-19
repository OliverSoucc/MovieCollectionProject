package GUI.Controller;

import BE.Category;
import BE.Movie;
import BLL.Exceptions.CatMovieDAOException;
import BLL.Exceptions.CategoryDAOException;
import BLL.Exceptions.MovieCollectionManagerException;
import BLL.Exceptions.MovieDAOException;
import GUI.Model.MainPageModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static javax.swing.JOptionPane.showMessageDialog;

public class MainPageController implements Initializable {
    @FXML
    public TableColumn<Movie, String> nameColumn, ratingColumn, imdbRatingColumn;
    @FXML
    public TextField filter;
    @FXML
    public TableView<Movie> tableView;
    @FXML
    public Button filterButton, deleteMovieBtn;
    @FXML
    private TableView<Category> categoryTableView;
    @FXML
    TableColumn<Category, String> categoryColumn;
    @FXML
    private TableView<Movie> MovieListTableView;
    @FXML
    private TableColumn<Movie, String> nameMovieTableColumn;
    @FXML
    private Label currentMovie;
    MainPageModel mainPageModel;
    float newValueFloat;
    boolean wasChecked = false;
    private ObservableList<Movie> catMoviesToShowList;
    private Category currentCategory;
    private int currentMovieInt = -1;
    public MainPageController() throws MovieCollectionManagerException {
        mainPageModel = MainPageModel.getInstance();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            tableViewProperty();
        } catch (MovieDAOException e) {
            e.printStackTrace();
        }
        try {
            setupCategoryTableView();
        } catch (CategoryDAOException e) {
            e.printStackTrace();
        }
        try {
            updateTableViewMovie();
            updateTableViewCategory();
        } catch (MovieDAOException | CategoryDAOException e) {
            e.printStackTrace();
        }
        try {
            filterLogic();
        } catch (MovieDAOException e) {
            e.printStackTrace();
        }
    }


    //Button methods
    public void createNewMovie(ActionEvent actionEvent) throws IOException {
        Parent root;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/GUI/View/AddRemoveMovie.fxml"));
        root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Add/Remove Movie");
        stage.setScene(new Scene(root));
        stage.show();
    }


    public void createNewCategory(ActionEvent actionEvent) throws IOException {
        Parent root;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/GUI/View/AddRemoveCategory.fxml"));
        root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Add/Remove Category");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void createNewRating(ActionEvent actionEvent) throws IOException, MovieDAOException {
        Parent root;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/GUI/View/AddChangeRating.fxml"));
        root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Add/Change Rating");
        stage.setScene(new Scene(root));
        stage.show();
        AddChangeRatingController addChangeRatingController = loader.getController();
        addChangeRatingController.setMainPageController(this);

        currentMovieInt = tableView.getSelectionModel().getSelectedItem().getId();
        addChangeRatingController.setMovieToBeUpdated(mainPageModel.getMovie(currentMovieInt));
    }


    private void tableViewProperty() throws MovieDAOException {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ratingColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));
        imdbRatingColumn.setCellValueFactory(new PropertyValueFactory<>("imdb"));
        tableView.setItems(mainPageModel.getMovieObservableList());

        if(wasChecked == false)
        {
            //warningWindow();
            System.out.println("not checked");
        }
        wasChecked = true;
    }

    private void setupCategoryTableView() throws CategoryDAOException {
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        categoryTableView.setItems(mainPageModel.getAllCategories());
    }


    private void filterLogic() throws MovieDAOException {
        FilteredList<Movie> filteredData = new FilteredList<>(mainPageModel.getMovieObservableList(), b -> true);

        filter.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(movie -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String stringLowerCaseFilter = newValue.toLowerCase();

                if (movie.getName().toLowerCase().contains(stringLowerCaseFilter)) {
                    return true;
                } else if (isNumeric(stringLowerCaseFilter)) {
                    newValueFloat = Float.parseFloat(stringLowerCaseFilter);
                    if (movie.getRating() >= newValueFloat && movie.getImdb() >= newValueFloat) {
                        return true;
                    }
                }
                return false;
            });
        });
        SortedList<Movie> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedData);
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

    public void updateTableViewMovie() throws MovieDAOException, CategoryDAOException {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ratingColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));
        imdbRatingColumn.setCellValueFactory(new PropertyValueFactory<>("imdb"));
        tableView.setItems(mainPageModel.getMovieObservableList());
    }

    public void updateTableViewCategory() throws MovieDAOException, CategoryDAOException {
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        categoryTableView.setItems(mainPageModel.getAllCategories());
    }

    public void updateTableViewCatMovies() throws MovieDAOException, CategoryDAOException {
        nameMovieTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        MovieListTableView.setItems(catMoviesToShowList);
    }


    public void playMovie(ActionEvent actionEvent) {
        Runtime runtime = Runtime.getRuntime();
        String os = System.getProperty("os.name");
        try {
            if(os.contains("Windows")) {
                if (tableView.getSelectionModel().getSelectedItem() != null) {
                    String[] command = {"cmd.exe", "/k", "Start", tableView.getSelectionModel().getSelectedItem().getFileLink()};
                    Process p = runtime.exec(command);}
                else if (MovieListTableView.getSelectionModel().getSelectedItem() != null) {
                    String[] command = {"cmd.exe", "/k", "Start", MovieListTableView.getSelectionModel().getSelectedItem().getFileLink()};
                    Process p = runtime.exec(command);}
            }
            else {
                if(tableView.getSelectionModel().getSelectedItem() != null){
            String[] command2 = {"open -a /Applications/Utilities/Terminal.app", tableView.getSelectionModel().getSelectedItem().getFileLink()};
            Process p = runtime.exec(command2);}
            else if(MovieListTableView.getSelectionModel().getSelectedItem() != null) {
                    String[] command2 = {"open -a /Applications/Utilities/Terminal.app", MovieListTableView.getSelectionModel().getSelectedItem().getFileLink()};
                    Process p = runtime.exec(command2);}
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void handleDeleteMovieBtn(ActionEvent event) throws MovieDAOException, CatMovieDAOException, CategoryDAOException {
        Movie movieToDelete = tableView.getSelectionModel().getSelectedItem();
        mainPageModel.removeMoviesFromCat(movieToDelete);
        mainPageModel.removeMovie(movieToDelete);
        updateTableViewMovie();
    }

    public void handleDeleteCategoryBtn(ActionEvent event) throws CategoryDAOException, CatMovieDAOException {
        Category categoryToDelete = categoryTableView.getSelectionModel().getSelectedItem();
        mainPageModel.removeFromCat(categoryToDelete);
        mainPageModel.removeCategory(categoryToDelete);
        setupCategoryTableView();


        try {
            updateTableViewMovie();
        } catch (MovieDAOException e) {
            e.printStackTrace();
        }
        setupCategoryTableView();
        try {
            updateTableViewCatMovies();
        } catch (MovieDAOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void toCloseApp(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void handleRemoveMovieFromCategory(ActionEvent event) throws CatMovieDAOException {
        Movie movietoDelete = MovieListTableView.getSelectionModel().getSelectedItem();
        mainPageModel.removeFromCategory(currentCategory, movietoDelete);
        //updateTableViewCatMovies();
        catMoviesToShowList.remove(movietoDelete);
    }

    @FXML
    void handleAddMovieToCategory(ActionEvent event) throws CatMovieDAOException {
        Movie movietoAdd = tableView.getSelectionModel().getSelectedItem();
        Category categoryToBeAdded = categoryTableView.getSelectionModel().getSelectedItem();
        mainPageModel.addToCategory(categoryToBeAdded, movietoAdd);
        catMoviesToShowList.add(movietoAdd);
    }

    @FXML
    void handleShowMoviesInCategory(MouseEvent event) {
        Category categoryMoviesToShow = categoryTableView.getSelectionModel().getSelectedItem();
        if (categoryMoviesToShow.getAllMoviesInCategory() == null) {
            MovieListTableView.getItems().clear();
            System.out.println("the chosen category is empty.");
        }
        else {
            catMoviesToShowList = FXCollections.observableArrayList();
            catMoviesToShowList.setAll(categoryMoviesToShow.getAllMoviesInCategory());
            nameMovieTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            MovieListTableView.setItems(catMoviesToShowList);
            currentCategory = categoryMoviesToShow;
        }
    }

    public void selectedMovie(MouseEvent mouseEvent) {
        if(tableView.getSelectionModel().getSelectedItem() != null)
        currentMovie.setText(tableView.getSelectionModel().getSelectedItem().getName());
        if(MovieListTableView.getSelectionModel().getSelectedItem() != null)
            MovieListTableView.getSelectionModel().clearSelection();
    }
    public void selectedMovie1(MouseEvent mouseEvent){
        if(MovieListTableView.getSelectionModel().getSelectedItem() != null)
            currentMovie.setText(MovieListTableView.getSelectionModel().getSelectedItem().getName());
        if(tableView.getSelectionModel().getSelectedItem() != null)
            tableView.getSelectionModel().clearSelection();
    }
}



    /*public void warningWindow()
    {
        for(int i = 0;i < tableView.getItems().size();i++) {
            float rating = tableView.getItems().get(i).getRating();
            if (rating < 6.0) {

                    Alert alert;
                    alert = new Alert(Alert.AlertType.WARNING);
                    alert.setContentText("Remember to delete movies with personal rating under 6.0 and movies that have not been watched for over two years.");
                    alert.showAndWait();
                    break;

            }
        }
    }*/
