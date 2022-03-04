package sample.controllers;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.datebase.DatabaseHandler;
import sample.datebase.User;

public class ControllerAuthorization {
    @FXML private Button buttonAuthorization;
    @FXML private Button buttonRegistration;
    @FXML private Label errorLabel;
    @FXML private TextField login;
    @FXML private TextField password;
    @FXML
    void initialize() {
        buttonRegistration.setOnAction(actionEvent -> openOtherWindow("/sample/layout/registration.fxml"));

        buttonAuthorization.setOnAction(actionEvent -> {
            String loginText = login.getText().trim();
            String passwordText = password.getText().trim();

            if(!loginText.equals("") && !passwordText.equals("")) {
                try {
                    if(loginUser(loginText, passwordText)) {
                        User.setAdministrator(new DatabaseHandler().checkingForAnAdministrator());
                        openOtherWindow("/sample/layout/menu_formation.fxml");
                    }
                    else {
                        errorLabel.setText("Логин или пароль неверен");
                    }
                } catch (SQLException | ClassNotFoundException throwable) {
                    throwable.printStackTrace();
                }
            }
            else {
                errorLabel.setText("Поле логина или пароля пустое");
            }
        });
    }

    private boolean loginUser(String login, String password) throws SQLException, ClassNotFoundException {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        User.setUsername(login);
        User.setPassword(password);
        User.setMenu("");
        User.setExpenses(0);
        ResultSet resultSet = databaseHandler.signInUser();
        int counter = 0;
        while (resultSet.next()){
            counter++;
        }
        return counter >= 1;
    }

    private void openOtherWindow(String window){
        buttonRegistration.getScene().getWindow().hide();
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
