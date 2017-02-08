package scenes.logIn;

import dataStorage.*;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import controllers.BudgetMeController;

public class Controller extends BudgetMeController {

    @FXML
    private TextField textField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private CheckBox checkBox;

    private Settings settings;

    public void initialize() {
        settings = Settings.loadSettings();

        //Apply appropriate settings
        checkBox.setSelected(settings.isRememberMe());
        if (settings.isRememberMe())
            textField.setText(settings.getRememberMeString());
    }

    public void printUsernameAndPassword() {
        //PasswordHandler.handlePassword(textField.getText(), passwordField.getText());
        User user = User.logIn(textField.getText(), passwordField.getText());
        System.out.println("Password is " + ((user.getUsername() != null) ? "correct." : "incorrect."));
        passwordField.clear();
    }

    public void rememberFunction() throws Exception {
        //checkBox.setSelected(getControllerManager().testPopUp()); //demo for popups
        System.out.println("Remember me: " + (checkBox.isSelected() ? "" : "un") + "toggled");
        settings.setRememberMe(checkBox.isSelected());
        settings.setRememberMeString(textField.getText());
        Settings.saveSettings(settings);
    }
}
