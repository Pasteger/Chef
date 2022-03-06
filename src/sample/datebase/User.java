package sample.datebase;

public class User {
    private static String username;
    private static String password;
    private static boolean administrator;
    private static int expenses;
    private static StringBuilder menu;

    public User(String username, String password, boolean administrator) {
        User.username = username;
        User.password = password;
        User.administrator = administrator;
        User.expenses = 0;
        User.menu = new StringBuilder();
    }
    public static String getUsername() {return username;}
    public static void setUsername(String username) {User.username = username;}
    public static String getPassword() {return password;}
    public static void setPassword(String password) {User.password = password;}
    public static boolean isAdministrator() {return administrator;}
    public static void setAdministrator(boolean administrator) {User.administrator = administrator;}
    public static int getExpenses() {return expenses;}
    public static void setExpenses(int expenses) {User.expenses = expenses;}
    public static StringBuilder getMenu() {return menu;}
    public static void setMenu(StringBuilder menu) {User.menu = menu;}
}
