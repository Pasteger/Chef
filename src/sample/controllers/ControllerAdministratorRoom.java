package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.datebase.Const;
import sample.datebase.UsersOL;
import sample.datebase.UsersData;

import java.io.IOException;

public class ControllerAdministratorRoom {
    @FXML private Button backButton;
    @FXML private Button updateButton;
    @FXML private TextField searchTextField;
    @FXML private Button searchButton;
    @FXML private Button assignAnAdministratorButton;
    @FXML private TableView<UsersData> usersTable;
    @FXML private TableColumn<UsersData, Integer> idColumn;
    @FXML private TableColumn<UsersData, String> usernameColumn;
    @FXML private TableColumn<UsersData, String> passwordColumn;
    @FXML private TableColumn<UsersData, Boolean> isAdministratorColumn;

    @FXML void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        isAdministratorColumn.setCellValueFactory(new PropertyValueFactory<>("administrator"));

        usersTable.setItems(UsersOL.getUserObservableList("SELECT * FROM " + Const.USER_TABLE));

        searchButton.setOnAction(
                actionEvent -> usersTable.setItems(UsersOL.getUserObservableList(
                        "SELECT * FROM " + Const.USER_TABLE +
                                " WHERE " + Const.USERNAMES + " = \"" + searchTextField.getText() + "\"")));
        updateButton.setOnAction(actionEvent ->
                usersTable.setItems(UsersOL.getUserObservableList("SELECT * FROM " + Const.USER_TABLE)));
        backButton.setOnAction(actionEvent -> openOtherWindow("/sample/layout/menu_formation.fxml"));
    }

    private void openOtherWindow(String window){
        backButton.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));
        try {
            loader.load();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
