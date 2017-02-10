import controllers.ControllerManager;
import dataStorage.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private Parent root;

    //Passes primaryStage into Controller so it can switch windows
    @Override
    public void start(Stage primaryStage) throws Exception {
        ControllerManager controllerManager = new ControllerManager(primaryStage);
        controllerManager.logInScene();
        primaryStage.setMinWidth(1200);
        primaryStage.setMinHeight(800);
        primaryStage.setTitle("BudgetMe");
        primaryStage.show();
    }

    //@Override
    public void oldstart(Stage primaryStage) throws Exception {
        root = FXMLLoader.load(getClass().getResource("../scenes/logIn/Scene.fxml"));
        primaryStage.setMinWidth(600);
        primaryStage.setMinHeight(400);
        primaryStage.setTitle("BudgetMe");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        //logInLogic();
        launch(args);
    }

    public static void logInLogic() {
        /**
         * This is sample logic for creating an account
         */
        //returns true if a new file was made, false if it already exists
        boolean wasRegistered = User.register(new User("John Doe", "password"));
        if (wasRegistered)
            System.out.println("User was registered");
        else
            System.out.println("User already exists");

        /**
         * This is sample logic for logging in and retrieving an object
         */
        //Logging in returns a User object that will contain the user's data
        User user = User.logIn("John Doe", "password");

        if (user == null)
            System.out.println("Incorrect password");
        else {
            if (user.getUsername() == null)
                System.out.println("User not found");
            else {
                /**
                 * Changing the password to test saving data
                 */
                System.out.println(user.getPassword());
                user.setPassword("changed_password");
                System.out.println(user.getPassword());
                User.save(user);
            }
        }
    }
}
