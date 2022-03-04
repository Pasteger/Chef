package sample.datebase;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class UsersOL {
    private static ObservableList<UsersData> userObservableList = FXCollections.observableArrayList();

    public static ObservableList<UsersData> getUserObservableList(String select) {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        try {
            databaseHandler.returnUsers(select);
        } catch (SQLException | ClassNotFoundException throwable) {
            throwable.printStackTrace();
        }
        return userObservableList;
    }

    public static void setUserObservableList(ObservableList<UsersData> userObservableList) {
        UsersOL.userObservableList = userObservableList;
    }
}
