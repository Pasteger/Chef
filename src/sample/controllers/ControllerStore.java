package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample.datebase.DatabaseHandler;
import sample.datebase.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ControllerStore {
    private int expenses = User.getExpenses();
    private int expensesFirstDishes;
    private int expensesSecondDishes;
    private int expensesThirdDishes;
    private int expensesFourthDishes;
    private int expensesFifthDishes;
    private final int priceFirstDishes = 300;
    private final int priceSecondDishes = 250;
    private final int priceThirdDishes = 200;
    private final int priceFourthDishes = 150;
    private final int priceFifthDishes = 300;
    private final StringBuilder menu = new StringBuilder();
    private boolean firstDishesSet;
    private boolean secondDishesSet;
    private boolean thirdDishesSet;
    private boolean fourthDishesSet;
    private boolean fifthDishesSet;
    private int countFirstDishesForOnePerson;
    private int countSecondDishesForOnePerson;
    private int countThirdDishesForOnePerson;
    private int countFourthDishesForOnePerson;
    private int countFifthDishesForOnePerson;
    private int countPerson = 1;
    @FXML private Button addDishesButton1;
    @FXML private Button addDishesButton2;
    @FXML private Button addDishesButton3;
    @FXML private Button addDishesButton4;
    @FXML private Button addDishesButton5;
    @FXML private Button addPersonButton;
    @FXML private Label countPersonLabel;
    @FXML private Button exitButton;
    @FXML private Label expensesLabel;
    @FXML private Label menuLabel;
    @FXML private Button recipesButton;
    @FXML private Button storeButton;
    @FXML private Button subtractDishesButton1;
    @FXML private Button subtractDishesButton2;
    @FXML private Button subtractDishesButton3;
    @FXML private Button subtractDishesButton4;
    @FXML private Button subtractDishesButton5;
    @FXML private Button subtractPersonButton;
    @FXML private Button administratorButton;
    @FXML void initialize() {
        exitButton.setOnAction(actionEvent -> openOtherWindow("/sample/layout/authorization.fxml"));
        recipesButton.setOnAction(actionEvent -> openOtherWindow("/sample/layout/recipes.fxml"));
        storeButton.setOnAction(actionEvent -> openOtherWindow("/sample/layout/store.fxml"));
        administratorButton.setOnAction(actionEvent -> openOtherWindow("/sample/layout/administrator_room.fxml"));
        addDishesButton1.setOnAction(actionEvent -> {
            if(countFirstDishesForOnePerson < 100){
                addDishes1();
            }
        });
        addDishesButton2.setOnAction(actionEvent -> {
            if(countSecondDishesForOnePerson < 100) {
                addDishes2();
            }
        });
        addDishesButton3.setOnAction(actionEvent -> {
            if(countThirdDishesForOnePerson < 100) {
                addDishes3();
            }
        });
        addDishesButton4.setOnAction(actionEvent -> {
            if(countFourthDishesForOnePerson < 100) {
                addDishes4();
            }
        });
        addDishesButton5.setOnAction(actionEvent -> {
            if(countFifthDishesForOnePerson < 100) {
                addDishes5();
            }
        });

        subtractDishesButton1.setOnAction(actionEvent -> {
            if(countFirstDishesForOnePerson > 0){
                subtractDishes1();
            }
        });
        subtractDishesButton2.setOnAction(actionEvent -> {
            if(countSecondDishesForOnePerson > 0){
                subtractDishes2();
            }
        });
        subtractDishesButton3.setOnAction(actionEvent -> {
            if(countThirdDishesForOnePerson > 0){
                subtractDishes3();
            }
        });
        subtractDishesButton4.setOnAction(actionEvent -> {
            if(countFourthDishesForOnePerson > 0){
                subtractDishes4();
            }
        });
        subtractDishesButton5.setOnAction(actionEvent -> {
            if(countFifthDishesForOnePerson > 0){
                subtractDishes5();
            }
        });


        addPersonButton.setOnAction(actionEvent -> {
            if(countPerson < 100) {
                countPerson++;
            }
            processingPersonButton();
        });
        subtractPersonButton.setOnAction(actionEvent -> {
            if(countPerson > 1) {
                countPerson--;
            }
            processingPersonButton();
        });
    }

    private void processingPersonButton(){
        countPersonLabel.setText(String.valueOf(countPerson));
        if(firstDishesSet){
            countFirstDishesForOnePerson--;
            addDishes1();
        }
        if(secondDishesSet){
            countSecondDishesForOnePerson--;
            addDishes2();
        }
        if(thirdDishesSet){
            countThirdDishesForOnePerson--;
            addDishes3();
        }
        if(fourthDishesSet){
            countFourthDishesForOnePerson--;
            addDishes4();
        }
        if(fifthDishesSet){
            countFifthDishesForOnePerson--;
            addDishes5();
        }
    }

    private void addDishes1(){
        int countFirstDishes;
        if(!firstDishesSet){
            countFirstDishesForOnePerson = 1;
            countFirstDishes = countFirstDishesForOnePerson * countPerson;
            menu.append("Борщ - ").append(countFirstDishes).append("\n");
            expensesFirstDishes = priceFirstDishes * countPerson;
            firstDishesSet = true;
        }
        else {
            countFirstDishesForOnePerson++;
            Pattern pattern = Pattern.compile("Борщ - \\d+\n");
            Matcher matcher = pattern.matcher(menu);
            matcher.find();
            int start = matcher.start();
            int end = matcher.end();
            countFirstDishes = countFirstDishesForOnePerson * countPerson;
            menu.replace(start + 7, end-1, String.valueOf(countFirstDishes));

            expenses -= expensesFirstDishes;
            expensesFirstDishes = priceFirstDishes * countFirstDishes;
        }
        expenses += expensesFirstDishes;
        expensesLabel.setText(String.valueOf(expenses));
        menuLabel.setText(menu.toString());
    }

    private void addDishes2(){
        int countSecondDishes;
        if(!secondDishesSet){
            countSecondDishesForOnePerson = 1;
            countSecondDishes = countSecondDishesForOnePerson * countPerson;
            menu.append("Плов - ").append(countSecondDishes).append("\n");
            expensesSecondDishes = priceSecondDishes * countPerson;
            secondDishesSet = true;
        }
        else {
            countSecondDishesForOnePerson++;
            Pattern pattern = Pattern.compile("Плов - \\d+\n");
            Matcher matcher = pattern.matcher(menu);
            matcher.find();
            int start = matcher.start();
            int end = matcher.end();
            countSecondDishes = countSecondDishesForOnePerson * countPerson;
            menu.replace(start + 7, end-1, String.valueOf(countSecondDishes));

            expenses -= expensesSecondDishes;
            expensesSecondDishes = priceSecondDishes * countSecondDishes;
        }
        expenses += expensesSecondDishes;
        expensesLabel.setText(String.valueOf(expenses));
        menuLabel.setText(menu.toString());
    }

    private void addDishes3(){
        int countThirdDishes;
        if(!thirdDishesSet){
            countThirdDishesForOnePerson = 1;
            countThirdDishes = countThirdDishesForOnePerson * countPerson;
            menu.append("Пельмени - ").append(countThirdDishes).append("\n");
            expensesThirdDishes = priceThirdDishes * countPerson;
            thirdDishesSet = true;
        }
        else {
            countThirdDishesForOnePerson++;
            Pattern pattern = Pattern.compile("Пельмени - \\d+\n");
            Matcher matcher = pattern.matcher(menu);
            matcher.find();
            int start = matcher.start();
            int end = matcher.end();
            countThirdDishes = countThirdDishesForOnePerson * countPerson;
            menu.replace(start + 11, end-1, String.valueOf(countThirdDishes));

            expenses -= expensesThirdDishes;
            expensesThirdDishes = priceThirdDishes * countThirdDishes;
        }
        expenses += expensesThirdDishes;
        expensesLabel.setText(String.valueOf(expenses));
        menuLabel.setText(menu.toString());
    }

    private void addDishes4(){
        int countFourthDishes;
        if(!fourthDishesSet){
            countFourthDishesForOnePerson = 1;
            countFourthDishes = countFourthDishesForOnePerson * countPerson;
            menu.append("Венегрет - ").append(countFourthDishes).append("\n");
            expensesFourthDishes = priceFourthDishes * countPerson;
            fourthDishesSet = true;
        }
        else {
            countFourthDishesForOnePerson++;
            Pattern pattern = Pattern.compile("Венегрет - \\d+\n");
            Matcher matcher = pattern.matcher(menu);
            matcher.find();
            int start = matcher.start();
            int end = matcher.end();
            countFourthDishes = countFourthDishesForOnePerson * countPerson;
            menu.replace(start + 11, end-1, String.valueOf(countFourthDishes));

            expenses -= expensesFourthDishes;
            expensesFourthDishes = priceFourthDishes * countFourthDishes;
        }
        expenses += expensesFourthDishes;
        expensesLabel.setText(String.valueOf(expenses));
        menuLabel.setText(menu.toString());
    }

    private void addDishes5(){
        int countFifthDishes;
        if(!fifthDishesSet){
            countFifthDishesForOnePerson = 1;
            countFifthDishes = countFifthDishesForOnePerson * countPerson;
            menu.append("Шаурма - ").append(countFifthDishes).append("\n");
            expensesFifthDishes = priceFifthDishes * countPerson;
            fifthDishesSet = true;
        }
        else {
            countFifthDishesForOnePerson++;
            Pattern pattern = Pattern.compile("Шаурма - \\d+\n");
            Matcher matcher = pattern.matcher(menu);
            matcher.find();
            int start = matcher.start();
            int end = matcher.end();
            countFifthDishes = countFifthDishesForOnePerson * countPerson;
            menu.replace(start + 9, end-1, String.valueOf(countFifthDishes));

            expenses -= expensesFifthDishes;
            expensesFifthDishes = priceFifthDishes * countFifthDishes;
        }
        expenses += expensesFifthDishes;
        expensesLabel.setText(String.valueOf(expenses));
        menuLabel.setText(menu.toString());
    }

    private void subtractDishes1(){
        int countFirstDishes;
        if(firstDishesSet) {
            if (countFirstDishesForOnePerson == 1) {
                Pattern pattern = Pattern.compile("Борщ - \\d+\n");
                Matcher matcher = pattern.matcher(menu);
                matcher.find();
                int start = matcher.start();
                int end = matcher.end();
                menu.replace(start, end, "");
                expensesFirstDishes = -priceFirstDishes * countPerson;
                firstDishesSet = false;
            } else {
                countFirstDishesForOnePerson--;
                Pattern pattern = Pattern.compile("Борщ - \\d+\n");
                Matcher matcher = pattern.matcher(menu);
                matcher.find();
                int start = matcher.start();
                int end = matcher.end();
                countFirstDishes = countFirstDishesForOnePerson * countPerson;
                menu.replace(start + 7, end - 1, String.valueOf(countFirstDishes));

                expenses -= expensesFirstDishes;
                expensesFirstDishes = priceFirstDishes * countFirstDishes;
            }
            expenses += expensesFirstDishes;
            expensesLabel.setText(String.valueOf(expenses));
            menuLabel.setText(menu.toString());
        }
    }

    private void subtractDishes2(){
        int countSecondDishes;
        if(secondDishesSet) {
            if (countSecondDishesForOnePerson == 1) {
                Pattern pattern = Pattern.compile("Плов - \\d+\n");
                Matcher matcher = pattern.matcher(menu);
                matcher.find();
                int start = matcher.start();
                int end = matcher.end();
                menu.replace(start, end, "");
                expensesSecondDishes = -priceSecondDishes * countPerson;
                secondDishesSet = false;
            } else {
                countSecondDishesForOnePerson--;
                Pattern pattern = Pattern.compile("Плов - \\d+\n");
                Matcher matcher = pattern.matcher(menu);
                matcher.find();
                int start = matcher.start();
                int end = matcher.end();
                countSecondDishes = countSecondDishesForOnePerson * countPerson;
                menu.replace(start + 7, end - 1, String.valueOf(countSecondDishes));

                expenses -= expensesSecondDishes;
                expensesSecondDishes = priceSecondDishes * countSecondDishes;
            }
            expenses += expensesSecondDishes;
            expensesLabel.setText(String.valueOf(expenses));
            menuLabel.setText(menu.toString());
        }
    }

    private void subtractDishes3(){
        int countThirdDishes;
        if(thirdDishesSet) {
            if (countThirdDishesForOnePerson == 1) {
                Pattern pattern = Pattern.compile("Пельмени - \\d+\n");
                Matcher matcher = pattern.matcher(menu);
                matcher.find();
                int start = matcher.start();
                int end = matcher.end();
                menu.replace(start, end, "");
                expensesThirdDishes = -priceThirdDishes * countPerson;
                thirdDishesSet = false;
            } else {
                countThirdDishesForOnePerson--;
                Pattern pattern = Pattern.compile("Пельмени - \\d+\n");
                Matcher matcher = pattern.matcher(menu);
                matcher.find();
                int start = matcher.start();
                int end = matcher.end();
                countThirdDishes = countThirdDishesForOnePerson * countPerson;
                menu.replace(start + 11, end - 1, String.valueOf(countThirdDishes));

                expenses -= expensesThirdDishes;
                expensesThirdDishes = priceThirdDishes * countThirdDishes;
            }
            expenses += expensesThirdDishes;
            expensesLabel.setText(String.valueOf(expenses));
            menuLabel.setText(menu.toString());
        }
    }

    private void subtractDishes4(){
        int countFourthDishes;
        if(fourthDishesSet) {
            if (countFourthDishesForOnePerson == 1) {
                Pattern pattern = Pattern.compile("Венегрет - \\d+\n");
                Matcher matcher = pattern.matcher(menu);
                matcher.find();
                int start = matcher.start();
                int end = matcher.end();
                menu.replace(start, end, "");
                expensesFourthDishes = -priceFourthDishes * countPerson;
                fourthDishesSet = false;
            } else {
                countFourthDishesForOnePerson--;
                Pattern pattern = Pattern.compile("Венегрет - \\d+\n");
                Matcher matcher = pattern.matcher(menu);
                matcher.find();
                int start = matcher.start();
                int end = matcher.end();
                countFourthDishes = countFourthDishesForOnePerson * countPerson;
                menu.replace(start + 11, end - 1, String.valueOf(countFourthDishes));

                expenses -= expensesFourthDishes;
                expensesFourthDishes = priceFourthDishes * countFourthDishes;
            }
            expenses += expensesFourthDishes;
            expensesLabel.setText(String.valueOf(expenses));
            menuLabel.setText(menu.toString());
        }
    }

    private void subtractDishes5(){
        int countFifthDishes;
        if(fifthDishesSet) {
            if (countFifthDishesForOnePerson == 1) {
                Pattern pattern = Pattern.compile("Шаурма - \\d+\n");
                Matcher matcher = pattern.matcher(menu);
                matcher.find();
                int start = matcher.start();
                int end = matcher.end();
                menu.replace(start, end, "");
                expensesFifthDishes = -priceFifthDishes * countPerson;
                fifthDishesSet = false;
            } else {
                countFifthDishesForOnePerson--;
                Pattern pattern = Pattern.compile("Шаурма - \\d+\n");
                Matcher matcher = pattern.matcher(menu);
                matcher.find();
                int start = matcher.start();
                int end = matcher.end();
                countFifthDishes = countFifthDishesForOnePerson * countPerson;
                menu.replace(start + 9, end - 1, String.valueOf(countFifthDishes));

                expenses -= expensesFifthDishes;
                expensesFifthDishes = priceFifthDishes * countFifthDishes;
            }
            expenses += expensesFifthDishes;
            expensesLabel.setText(String.valueOf(expenses));
            menuLabel.setText(menu.toString());
        }
    }

    private void openOtherWindow(String window){
        User.setExpenses(expenses);
        User.setMenu(menu.toString());
        if(window.equals("/sample/layout/administrator_room.fxml")){
            if(!User.isAdministrator()){
                return;
            }
        }
        if(window.equals("/sample/layout/authorization.fxml")){
            try {new DatabaseHandler().writeToHistory();}
            catch (SQLException | ClassNotFoundException throwable){throwable.printStackTrace();}
        }
        exitButton.getScene().getWindow().hide();
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
