package logIn;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.PasswordHandler;

import java.awt.*;

public class Controller {

    @FXML
    private TextField textField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private CheckBox checkBox;


    public void printUsernameAndPassword() {
        PasswordHandler.handlePassword(textField.getText(), passwordField.getText());
        passwordField.clear();
    }

    public void rememberFunction() {
        System.out.println("Remember me: " + (checkBox.isSelected() ? "" : "un") + "toggled");
    }
    public void tryLogin(){
        //Needs class to read


    }
    public void registerMe(){
        //Needs Class To Write
    }

}
