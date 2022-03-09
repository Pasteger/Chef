package sample.datebase;

public class BillOfLading {
    private static int expenses;
    private static int countPerson = 1;
    public static StringBuilder menu = new StringBuilder();
    private static boolean firstDishesSet;
    private static boolean secondDishesSet;
    private static boolean thirdDishesSet;
    private static boolean fourthDishesSet;
    private static boolean fifthDishesSet;
    private static int countFirstDishesForOnePerson;
    private static int countSecondDishesForOnePerson;
    private static int countThirdDishesForOnePerson;
    private static int countFourthDishesForOnePerson;
    private static int countFifthDishesForOnePerson;

    public static int getExpenses() {return expenses;}
    public static void setExpenses(int expenses) {BillOfLading.expenses = expenses;}
    public static int getCountPerson() {return countPerson;}
    public static boolean isFirstDishesSet() {return firstDishesSet;}
    public static boolean isSecondDishesSet() {return secondDishesSet;}
    public static boolean isThirdDishesSet() {return thirdDishesSet;}
    public static boolean isFourthDishesSet() {return fourthDishesSet;}
    public static boolean isFifthDishesSet() {return fifthDishesSet;}
    public static void setFirstDishesSet(boolean firstDishesSet) {BillOfLading.firstDishesSet = firstDishesSet;}
    public static void setSecondDishesSet(boolean secondDishesSet) {BillOfLading.secondDishesSet = secondDishesSet;}
    public static void setThirdDishesSet(boolean thirdDishesSet) {BillOfLading.thirdDishesSet = thirdDishesSet;}
    public static void setFourthDishesSet(boolean fourthDishesSet) {BillOfLading.fourthDishesSet = fourthDishesSet;}
    public static void setFifthDishesSet(boolean fifthDishesSet) {BillOfLading.fifthDishesSet = fifthDishesSet;}
    public static void incrementCountPerson(){countPerson++;}
    public static void decrementCountPerson(){countPerson--;}
    public static int getCountFirstDishesForOnePerson() {return countFirstDishesForOnePerson;}
    public static void setCountFirstDishesForOnePerson(int countFirstDishesForOnePerson) {BillOfLading.countFirstDishesForOnePerson = countFirstDishesForOnePerson;}
    public static int getCountSecondDishesForOnePerson() {return countSecondDishesForOnePerson;}
    public static void setCountSecondDishesForOnePerson(int countSecondDishesForOnePerson) {BillOfLading.countSecondDishesForOnePerson = countSecondDishesForOnePerson;}
    public static int getCountThirdDishesForOnePerson() {return countThirdDishesForOnePerson;}
    public static void setCountThirdDishesForOnePerson(int countThirdDishesForOnePerson) {BillOfLading.countThirdDishesForOnePerson = countThirdDishesForOnePerson;}
    public static int getCountFourthDishesForOnePerson() {return countFourthDishesForOnePerson;}
    public static void setCountFourthDishesForOnePerson(int countFourthDishesForOnePerson) {BillOfLading.countFourthDishesForOnePerson = countFourthDishesForOnePerson;}
    public static int getCountFifthDishesForOnePerson() {return countFifthDishesForOnePerson;}
    public static void setCountFifthDishesForOnePerson(int countFifthDishesForOnePerson) {BillOfLading.countFifthDishesForOnePerson = countFifthDishesForOnePerson;}
    public static void incrementCountFirstDishesForOnePerson(){countFirstDishesForOnePerson++;}
    public static void decrementCountFirstDishesForOnePerson(){countFirstDishesForOnePerson--;}
    public static void incrementCountSecondDishesForOnePerson(){countSecondDishesForOnePerson++;}
    public static void decrementCountSecondDishesForOnePerson(){countSecondDishesForOnePerson--;}
    public static void incrementCountThirdDishesForOnePerson(){countThirdDishesForOnePerson++;}
    public static void decrementCountThirdDishesForOnePerson(){countThirdDishesForOnePerson--;}
    public static void incrementCountFourthDishesForOnePerson(){countFourthDishesForOnePerson++;}
    public static void decrementCountFourthDishesForOnePerson(){countFourthDishesForOnePerson--;}
    public static void incrementCountFifthDishesForOnePerson(){countFifthDishesForOnePerson++;}
    public static void decrementCountFifthDishesForOnePerson(){countFifthDishesForOnePerson--;}
    public static void update(){
        expenses = 0;
        countPerson = 1;
        menu = new StringBuilder();
        firstDishesSet = false;
        secondDishesSet = false;
        thirdDishesSet = false;
        fourthDishesSet = false;
        fifthDishesSet = false;
        countFirstDishesForOnePerson = 0;
        countSecondDishesForOnePerson = 0;
        countThirdDishesForOnePerson = 0;
        countFourthDishesForOnePerson = 0;
        countFifthDishesForOnePerson = 0;
    }
}
