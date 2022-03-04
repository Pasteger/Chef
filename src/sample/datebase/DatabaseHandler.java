package sample.datebase;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DatabaseHandler extends Configs{
    Connection dbConnection;
    private final ObservableList<UsersData> userObservableList = FXCollections.observableArrayList();
    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
    }

    public void signUpUser()
            throws SQLException, ClassNotFoundException {
        String insert = "INSERT INTO " + Const.USER_TABLE + "(" +
                Const.USERNAMES + "," + Const.PASSWORDS + "," + Const.ADMINISTRATORS + ") VALUES(?,?,?)";

        PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
        preparedStatement.setString(1, User.getUsername());
        preparedStatement.setString(2, User.getPassword());
        preparedStatement.setBoolean(3, User.isAdministrator());

        preparedStatement.executeUpdate();
    }

    public ResultSet signInUser() throws SQLException, ClassNotFoundException {
        String select = "SELECT * FROM " + Const.USER_TABLE + " WHERE " + Const.USERNAMES + "=? AND " + Const.PASSWORDS + "=?";

        PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
        preparedStatement.setString(1, User.getUsername());
        preparedStatement.setString(2, User.getPassword());

        return preparedStatement.executeQuery();
    }

    public Boolean checkingForAnAdministrator() throws SQLException, ClassNotFoundException {
        String select = "SELECT " + Const.ADMINISTRATORS + " FROM " + Const.USER_TABLE + " WHERE " + Const.USERNAMES + "=\"" + User.getUsername() + "\"";

        Statement statement = getDbConnection().createStatement();

        ResultSet resultSet = statement.executeQuery(select);
        resultSet.next();

        return resultSet.getBoolean(1);
    }

    public void writeToHistory() throws SQLException, ClassNotFoundException {
        String insert = "INSERT INTO " + Const.HISTORY_TABLE + "(" +
                Const.USERNAMES + "," + Const.MENU + "," + Const.EXPENSES +") VALUES(?,?,?)";

        PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
        preparedStatement.setString(1, User.getUsername());
        preparedStatement.setString(2, User.getMenu());
        preparedStatement.setInt(3, User.getExpenses());

        preparedStatement.executeUpdate();
    }

    public void returnUsers(String select) throws SQLException, ClassNotFoundException {
        Statement statement = getDbConnection().createStatement();

        ResultSet resultSet = statement.executeQuery(select);
        while (resultSet.next()){
            int id = resultSet.getInt(1);
            String username = resultSet.getString(2);
            String password = resultSet.getString(3);
            boolean isAdministrator = resultSet.getBoolean(4);

            userObservableList.add(new UsersData(id, username, password, isAdministrator));
        }
        UsersOL.setUserObservableList(userObservableList);
    }
}
