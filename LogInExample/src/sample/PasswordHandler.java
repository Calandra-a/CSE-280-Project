package sample;

public class PasswordHandler {
    public static void handlePassword(String name, String password) {
        System.out.println(name + " " + password);
        if (name.equals("user") && password.equals("pass"))
            System.out.println("Password correct");
        else
            System.out.println("Invalid username or password");
    }
}
