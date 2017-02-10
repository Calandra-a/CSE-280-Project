package scenes.register;

import controllers.BudgetMeController;
import dataStorage.User;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


/**
 * Created by Boba3d on 2/9/2017.
 */
public class Controller extends BudgetMeController {
    @FXML
     TextField textField;
    @FXML
     PasswordField passwordField1;
    @FXML
     PasswordField passwordField2;


      public void registerUser() throws Exception{

        if(textField.getText().length() < 6 && textField.getText().length() > 20){
          System.out.println("Your username is either too long or too short!");}
        else if(passwordField1.getText().length() < 4 && passwordField1.getText().length() > 10){
          System.out.println("Your password is either too long or too short!");}
        else {
            User newUser = new User(textField.getText(), passwordField1.getText());
            User.register(newUser);
            System.out.println("Registered!");
        }
    }
    public void switchScene() throws Exception{
        controllerManager.setScene("../scenes/logIn/Scene.fxml");

    }


}
