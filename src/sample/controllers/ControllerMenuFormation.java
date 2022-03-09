package sample.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample.datebase.BillOfLading;
import sample.datebase.DatabaseHandler;
import sample.datebase.User;

public class ControllerMenuFormation {
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
    @FXML private Label expensesLabel;
    @FXML private Label menuLabel;
    @FXML private Label productsLabel;
    @FXML private Label countPersonLabel;
    @FXML private Button addDishesButton1;
    @FXML private Button addDishesButton2;
    @FXML private Button addDishesButton3;
    @FXML private Button addDishesButton4;
    @FXML private Button addDishesButton5;
    @FXML private Button addPersonButton;
    @FXML private Button exitButton;
    @FXML private Button recipesButton;
    @FXML private Button subtractDishesButton1;
    @FXML private Button subtractDishesButton2;
    @FXML private Button subtractDishesButton3;
    @FXML private Button subtractDishesButton4;
    @FXML private Button subtractDishesButton5;
    @FXML private Button subtractPersonButton;
    @FXML private Button administratorButton;

    @FXML void initialize() {
        expensesLabel.setText(String.valueOf(BillOfLading.getExpenses()));
        menuLabel.setText(BillOfLading.menu.toString());
        countPersonLabel.setText(String.valueOf(BillOfLading.getCountPerson()));
        exitButton.setOnAction(actionEvent -> openOtherWindow("/sample/layout/authorization.fxml"));
        recipesButton.setOnAction(actionEvent -> openOtherWindow("/sample/layout/recipes.fxml"));
        administratorButton.setOnAction(actionEvent -> openOtherWindow("/sample/layout/administrator_room.fxml"));
        addDishesButton1.setOnAction(actionEvent -> {
            if(BillOfLading.getCountFirstDishesForOnePerson() < 100){
                addDishes1();
            }
        });
        addDishesButton2.setOnAction(actionEvent -> {
            if(BillOfLading.getCountSecondDishesForOnePerson() < 100) {
                addDishes2();
            }
        });
        addDishesButton3.setOnAction(actionEvent -> {
            if(BillOfLading.getCountThirdDishesForOnePerson() < 100) {
                addDishes3();
            }
        });
        addDishesButton4.setOnAction(actionEvent -> {
            if(BillOfLading.getCountFourthDishesForOnePerson() < 100) {
                addDishes4();
            }
        });
        addDishesButton5.setOnAction(actionEvent -> {
            if(BillOfLading.getCountFifthDishesForOnePerson() < 100) {
                addDishes5();
            }
        });

        subtractDishesButton1.setOnAction(actionEvent -> {
            if(BillOfLading.getCountFirstDishesForOnePerson() > 0){
                subtractDishes1();
            }
        });
        subtractDishesButton2.setOnAction(actionEvent -> {
            if(BillOfLading.getCountSecondDishesForOnePerson() > 0){
                subtractDishes2();
            }
        });
        subtractDishesButton3.setOnAction(actionEvent -> {
            if(BillOfLading.getCountThirdDishesForOnePerson() > 0){
                subtractDishes3();
            }
        });
        subtractDishesButton4.setOnAction(actionEvent -> {
            if(BillOfLading.getCountFourthDishesForOnePerson() > 0){
                subtractDishes4();
            }
        });
        subtractDishesButton5.setOnAction(actionEvent -> {
            if(BillOfLading.getCountFifthDishesForOnePerson() > 0){
                subtractDishes5();
            }
        });


        addPersonButton.setOnAction(actionEvent -> {
            if(BillOfLading.getCountPerson() < 100) {
                BillOfLading.incrementCountPerson();
            }
            processingPersonButton();
        });
        subtractPersonButton.setOnAction(actionEvent -> {
            if(BillOfLading.getCountPerson() > 1) {
                BillOfLading.decrementCountPerson();
            }
            processingPersonButton();
        });
    }

    private void processingPersonButton(){
        countPersonLabel.setText(String.valueOf(BillOfLading.getCountPerson()));
        if(BillOfLading.isFirstDishesSet()){
            BillOfLading.decrementCountFirstDishesForOnePerson();
            addDishes1();
        }
        if(BillOfLading.isSecondDishesSet()){
            BillOfLading.decrementCountSecondDishesForOnePerson();
            addDishes2();
        }
        if(BillOfLading.isThirdDishesSet()){
            BillOfLading.decrementCountThirdDishesForOnePerson();
            addDishes3();
        }
        if(BillOfLading.isFourthDishesSet()){
            BillOfLading.decrementCountFourthDishesForOnePerson();
            addDishes4();
        }
        if(BillOfLading.isFifthDishesSet()){
            BillOfLading.decrementCountFifthDishesForOnePerson();
            addDishes5();
        }
    }

    private void addDishes1(){
        int countFirstDishes;
        if(!BillOfLading.isFirstDishesSet()){
            BillOfLading.setCountFirstDishesForOnePerson(1);
            countFirstDishes = BillOfLading.getCountFirstDishesForOnePerson() * BillOfLading.getCountPerson();
            BillOfLading.menu.append("Борщ - ").append(countFirstDishes).append("\n");
            expensesFirstDishes = priceFirstDishes * BillOfLading.getCountPerson();
            BillOfLading.setFirstDishesSet(true);
        }
        else {
            BillOfLading.incrementCountFirstDishesForOnePerson();
            Pattern pattern = Pattern.compile("Борщ - \\d+\n");
            Matcher matcher = pattern.matcher(BillOfLading.menu);
            matcher.find();
            int start = matcher.start();
            int end = matcher.end();
            countFirstDishes = BillOfLading.getCountFirstDishesForOnePerson() * BillOfLading.getCountPerson();
            BillOfLading.menu.replace(start + 7, end-1, String.valueOf(countFirstDishes));

            BillOfLading.setExpenses(BillOfLading.getExpenses() - expensesFirstDishes);
            expensesFirstDishes = priceFirstDishes * countFirstDishes;
        }
        BillOfLading.setExpenses(BillOfLading.getExpenses() + expensesFirstDishes);
        expensesLabel.setText(String.valueOf(BillOfLading.getExpenses()));
        menuLabel.setText(BillOfLading.menu.toString());
    }

    private void addDishes2(){
        int countSecondDishes;
        if(!BillOfLading.isSecondDishesSet()){
            BillOfLading.setCountSecondDishesForOnePerson(1);
            countSecondDishes = BillOfLading.getCountSecondDishesForOnePerson() * BillOfLading.getCountPerson();
            BillOfLading.menu.append("Плов - ").append(countSecondDishes).append("\n");
            expensesSecondDishes = priceSecondDishes * BillOfLading.getCountPerson();
            BillOfLading.setSecondDishesSet(true);
        }
        else {
            BillOfLading.incrementCountSecondDishesForOnePerson();
            Pattern pattern = Pattern.compile("Плов - \\d+\n");
            Matcher matcher = pattern.matcher(BillOfLading.menu);
            matcher.find();
            int start = matcher.start();
            int end = matcher.end();
            countSecondDishes = BillOfLading.getCountSecondDishesForOnePerson() * BillOfLading.getCountPerson();
            BillOfLading.menu.replace(start + 7, end-1, String.valueOf(countSecondDishes));

            BillOfLading.setExpenses(BillOfLading.getExpenses() - expensesSecondDishes);
            expensesSecondDishes = priceSecondDishes * countSecondDishes;
        }
        BillOfLading.setExpenses(BillOfLading.getExpenses() + expensesSecondDishes);
        expensesLabel.setText(String.valueOf(BillOfLading.getExpenses()));
        menuLabel.setText(BillOfLading.menu.toString());
    }

    private void addDishes3(){
        int countThirdDishes;
        if(!BillOfLading.isThirdDishesSet()){
            BillOfLading.setCountThirdDishesForOnePerson(1);
            countThirdDishes = BillOfLading.getCountThirdDishesForOnePerson() * BillOfLading.getCountPerson();
            BillOfLading.menu.append("Пельмени - ").append(countThirdDishes).append("\n");
            expensesThirdDishes = priceThirdDishes * BillOfLading.getCountPerson();
            BillOfLading.setThirdDishesSet(true);
        }
        else {
            BillOfLading.incrementCountThirdDishesForOnePerson();
            Pattern pattern = Pattern.compile("Пельмени - \\d+\n");
            Matcher matcher = pattern.matcher(BillOfLading.menu);
            matcher.find();
            int start = matcher.start();
            int end = matcher.end();
            countThirdDishes = BillOfLading.getCountThirdDishesForOnePerson() * BillOfLading.getCountPerson();
            BillOfLading.menu.replace(start + 11, end-1, String.valueOf(countThirdDishes));

            BillOfLading.setExpenses(BillOfLading.getExpenses() -  expensesThirdDishes);
            expensesThirdDishes = priceThirdDishes * countThirdDishes;
        }
        BillOfLading.setExpenses(BillOfLading.getExpenses() +  expensesThirdDishes);
        expensesLabel.setText(String.valueOf(BillOfLading.getExpenses()));
        menuLabel.setText(BillOfLading.menu.toString());
    }

    private void addDishes4(){
        int countFourthDishes;
        if(!BillOfLading.isFourthDishesSet()){
            BillOfLading.setCountFourthDishesForOnePerson(1);
            countFourthDishes = BillOfLading.getCountFourthDishesForOnePerson() * BillOfLading.getCountPerson();
            BillOfLading.menu.append("Венегрет - ").append(countFourthDishes).append("\n");
            expensesFourthDishes = priceFourthDishes * BillOfLading.getCountPerson();
            BillOfLading.setFourthDishesSet(true);
        }
        else {
            BillOfLading.incrementCountFourthDishesForOnePerson();
            Pattern pattern = Pattern.compile("Венегрет - \\d+\n");
            Matcher matcher = pattern.matcher(BillOfLading.menu);
            matcher.find();
            int start = matcher.start();
            int end = matcher.end();
            countFourthDishes = BillOfLading.getCountFourthDishesForOnePerson() * BillOfLading.getCountPerson();
            BillOfLading.menu.replace(start + 11, end-1, String.valueOf(countFourthDishes));

            BillOfLading.setExpenses(BillOfLading.getExpenses() - expensesFourthDishes);
            expensesFourthDishes = priceFourthDishes * countFourthDishes;
        }
        BillOfLading.setExpenses(BillOfLading.getExpenses() + expensesFourthDishes);
        expensesLabel.setText(String.valueOf(BillOfLading.getExpenses()));
        menuLabel.setText(BillOfLading.menu.toString());
    }

    private void addDishes5(){
        int countFifthDishes;
        if(!BillOfLading.isFifthDishesSet()){
            BillOfLading.setCountFifthDishesForOnePerson(1);
            countFifthDishes = BillOfLading.getCountFifthDishesForOnePerson() * BillOfLading.getCountPerson();
            BillOfLading.menu.append("Шаурма - ").append(countFifthDishes).append("\n");
            expensesFifthDishes = priceFifthDishes * BillOfLading.getCountPerson();
            BillOfLading.setFifthDishesSet(true);
        }
        else {
            BillOfLading.incrementCountFifthDishesForOnePerson();
            Pattern pattern = Pattern.compile("Шаурма - \\d+\n");
            Matcher matcher = pattern.matcher(BillOfLading.menu);
            matcher.find();
            int start = matcher.start();
            int end = matcher.end();
            countFifthDishes = BillOfLading.getCountFifthDishesForOnePerson() * BillOfLading.getCountPerson();
            BillOfLading.menu.replace(start + 9, end-1, String.valueOf(countFifthDishes));

            BillOfLading.setExpenses(BillOfLading.getExpenses() - expensesFifthDishes);
            expensesFifthDishes = priceFifthDishes * countFifthDishes;
        }
        BillOfLading.setExpenses(BillOfLading.getExpenses() + expensesFifthDishes);
        expensesLabel.setText(String.valueOf(BillOfLading.getExpenses()));
        menuLabel.setText(BillOfLading.menu.toString());
    }

    private void subtractDishes1(){
        int countFirstDishes;
        if(BillOfLading.isFirstDishesSet()) {
            if (BillOfLading.getCountFirstDishesForOnePerson() == 1) {
                Pattern pattern = Pattern.compile("Борщ - \\d+\n");
                Matcher matcher = pattern.matcher(BillOfLading.menu);
                matcher.find();
                int start = matcher.start();
                int end = matcher.end();
                BillOfLading.menu.replace(start, end, "");
                expensesFirstDishes = -priceFirstDishes * BillOfLading.getCountPerson();
                BillOfLading.setFirstDishesSet(false);
            } else {
                BillOfLading.decrementCountFirstDishesForOnePerson();
                Pattern pattern = Pattern.compile("Борщ - \\d+\n");
                Matcher matcher = pattern.matcher(BillOfLading.menu);
                matcher.find();
                int start = matcher.start();
                int end = matcher.end();
                countFirstDishes = BillOfLading.getCountFirstDishesForOnePerson() * BillOfLading.getCountPerson();
                BillOfLading.menu.replace(start + 7, end - 1, String.valueOf(countFirstDishes));

                BillOfLading.setExpenses(BillOfLading.getExpenses() - expensesFirstDishes);
                expensesFirstDishes = priceFirstDishes * countFirstDishes;
            }
            BillOfLading.setExpenses(BillOfLading.getExpenses() + expensesFirstDishes);
            expensesLabel.setText(String.valueOf(BillOfLading.getExpenses()));
            menuLabel.setText(BillOfLading.menu.toString());
        }
    }

    private void subtractDishes2(){
        int countSecondDishes;
        if(BillOfLading.isSecondDishesSet()) {
            if (BillOfLading.getCountSecondDishesForOnePerson() == 1) {
                Pattern pattern = Pattern.compile("Плов - \\d+\n");
                Matcher matcher = pattern.matcher(BillOfLading.menu);
                matcher.find();
                int start = matcher.start();
                int end = matcher.end();
                BillOfLading.menu.replace(start, end, "");
                expensesSecondDishes = -priceSecondDishes * BillOfLading.getCountPerson();
                BillOfLading.setSecondDishesSet(false);
            } else {
                BillOfLading.decrementCountFirstDishesForOnePerson();
                Pattern pattern = Pattern.compile("Плов - \\d+\n");
                Matcher matcher = pattern.matcher(BillOfLading.menu);
                matcher.find();
                int start = matcher.start();
                int end = matcher.end();
                countSecondDishes = BillOfLading.getCountSecondDishesForOnePerson() * BillOfLading.getCountPerson();
                BillOfLading.menu.replace(start + 7, end - 1, String.valueOf(countSecondDishes));

                BillOfLading.setExpenses(BillOfLading.getExpenses() - expensesSecondDishes);
                expensesSecondDishes = priceSecondDishes * countSecondDishes;
            }
            BillOfLading.setExpenses(BillOfLading.getExpenses() + expensesSecondDishes);
            expensesLabel.setText(String.valueOf(BillOfLading.getExpenses()));
            menuLabel.setText(BillOfLading.menu.toString());
        }
    }

    private void subtractDishes3(){
        int countThirdDishes;
        if(BillOfLading.isThirdDishesSet()) {
            if (BillOfLading.getCountThirdDishesForOnePerson() == 1) {
                Pattern pattern = Pattern.compile("Пельмени - \\d+\n");
                Matcher matcher = pattern.matcher(BillOfLading.menu);
                matcher.find();
                int start = matcher.start();
                int end = matcher.end();
                BillOfLading.menu.replace(start, end, "");
                expensesThirdDishes = -priceThirdDishes * BillOfLading.getCountPerson();
                BillOfLading.setThirdDishesSet(false);
            } else {
                BillOfLading.decrementCountThirdDishesForOnePerson();
                Pattern pattern = Pattern.compile("Пельмени - \\d+\n");
                Matcher matcher = pattern.matcher(BillOfLading.menu);
                matcher.find();
                int start = matcher.start();
                int end = matcher.end();
                countThirdDishes = BillOfLading.getCountThirdDishesForOnePerson() * BillOfLading.getCountPerson();
                BillOfLading.menu.replace(start + 11, end - 1, String.valueOf(countThirdDishes));

                BillOfLading.setExpenses(BillOfLading.getExpenses() - expensesThirdDishes);
                expensesThirdDishes = priceThirdDishes * countThirdDishes;
            }
            BillOfLading.setExpenses(BillOfLading.getExpenses() + expensesThirdDishes);
            expensesLabel.setText(String.valueOf(BillOfLading.getExpenses()));
            menuLabel.setText(BillOfLading.menu.toString());
        }
    }

    private void subtractDishes4(){
        int countFourthDishes;
        if(BillOfLading.isFourthDishesSet()) {
            if (BillOfLading.getCountFourthDishesForOnePerson() == 1) {
                Pattern pattern = Pattern.compile("Венегрет - \\d+\n");
                Matcher matcher = pattern.matcher(BillOfLading.menu);
                matcher.find();
                int start = matcher.start();
                int end = matcher.end();
                BillOfLading.menu.replace(start, end, "");
                expensesFourthDishes = -priceFourthDishes * BillOfLading.getCountPerson();
                BillOfLading.setFourthDishesSet(false);
            } else {
                BillOfLading.decrementCountFourthDishesForOnePerson();
                Pattern pattern = Pattern.compile("Венегрет - \\d+\n");
                Matcher matcher = pattern.matcher(BillOfLading.menu);
                matcher.find();
                int start = matcher.start();
                int end = matcher.end();
                countFourthDishes = BillOfLading.getCountFourthDishesForOnePerson() * BillOfLading.getCountPerson();
                BillOfLading.menu.replace(start + 11, end - 1, String.valueOf(countFourthDishes));

                BillOfLading.setExpenses(BillOfLading.getExpenses() - expensesFourthDishes);
                expensesFourthDishes = priceFourthDishes * countFourthDishes;
            }
            BillOfLading.setExpenses(BillOfLading.getExpenses() + expensesFourthDishes);
            expensesLabel.setText(String.valueOf(BillOfLading.getExpenses()));
            menuLabel.setText(BillOfLading.menu.toString());
        }
    }

    private void subtractDishes5(){
        int countFifthDishes;
        if(BillOfLading.isFifthDishesSet()) {
            if (BillOfLading.getCountFifthDishesForOnePerson() == 1) {
                Pattern pattern = Pattern.compile("Шаурма - \\d+\n");
                Matcher matcher = pattern.matcher(BillOfLading.menu);
                matcher.find();
                int start = matcher.start();
                int end = matcher.end();
                BillOfLading.menu.replace(start, end, "");
                expensesFifthDishes = -priceFifthDishes * BillOfLading.getCountPerson();
                BillOfLading.setFifthDishesSet(false);
            } else {
                BillOfLading.decrementCountFifthDishesForOnePerson();
                Pattern pattern = Pattern.compile("Шаурма - \\d+\n");
                Matcher matcher = pattern.matcher(BillOfLading.menu);
                matcher.find();
                int start = matcher.start();
                int end = matcher.end();
                countFifthDishes = BillOfLading.getCountFifthDishesForOnePerson() * BillOfLading.getCountPerson();
                BillOfLading.menu.replace(start + 9, end - 1, String.valueOf(countFifthDishes));

                BillOfLading.setExpenses(BillOfLading.getExpenses() - expensesFifthDishes);
                expensesFifthDishes = priceFifthDishes * countFifthDishes;
            }
            BillOfLading.setExpenses(BillOfLading.getExpenses() + expensesFifthDishes);
            expensesLabel.setText(String.valueOf(BillOfLading.getExpenses()));
            menuLabel.setText(BillOfLading.menu.toString());
        }
    }

    private void calculateProducts(){

    }

    private void openOtherWindow(String window){
        if(window.equals("/sample/layout/administrator_room.fxml")){
            if(!User.isAdministrator()){
                return;
            }
        }
        if(window.equals("/sample/layout/authorization.fxml")){
            try {new DatabaseHandler().writeToHistory();}
            catch (SQLException | ClassNotFoundException throwable){throwable.printStackTrace();}
            BillOfLading.update();
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
