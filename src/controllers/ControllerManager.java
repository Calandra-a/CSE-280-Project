package controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import scenes.logIn.Controller;


/**
 * Created by Noah McGivern on 2/4/2017.
 * A manager for switching between scenes and their controllers.
 */
public class ControllerManager {

    private FXMLLoader fxmlLoader;
    private Parent root;
    private Stage stage;

    /**
     * We could add more variables that we want to carry with us
     * throughout our program, a Settings variable for example,
     * so that Controllers can access it via calling from their
     * ControllerManager.
     */

    /**
     * Initializes a ControllerManager that manages controllers of the scenes in Stage stage
     *
     * @param stage The stage the scenes are to be set.
     */
    public ControllerManager(Stage stage) {
        this.stage = stage;
    }

    /**
     * Switches to the logInScene
     *
     * @throws Exception IOException: file wasn't found, etc
     */
    public void logInScene() throws Exception {
        setScene("../scenes/logIn/Scene.fxml");
    }

    public void testScene() throws Exception {
        setScene("../scenes/test/Scene.fxml");
    }
    /*public void registerScene() throws Exception{

        setScene("../scenes/register/Scene.fxml");
    }*/

    /**
     * Switches to the designated Scene.
     *
     * @param name The pathname of the .fxml to load.
     * @throws Exception IOException: file wasn't found, etc
     */
    public void setScene(String name) throws Exception {
        //Get the scene and load it
        fxmlLoader = new FXMLLoader(getClass().getResource(name));
        root = fxmlLoader.load();
        //Get the new controller, and cast it to a BudgetMeController in order to call
        //setControllerManager to this ControllerManager
        ((BudgetMeController) fxmlLoader.getController()).setControllerManager(this);
        //Finally, change to the new scene
        stage.setScene(new Scene(root));

        stage.setResizable(true);
    }
    public void registerPopUp() throws Exception {
        //Returns the value of pressed from the test Controller, by calling setPopUp
        //to create a new scene in a new window, then using its FXMLLoader to get
        //the Controller to get the desired variable
        setPopUp("../scenes/register/Scene.fxml");
    }

    /**
     * Returns the FXMLLoader of the specified .fxml file, after the pop up window is closed.
     *
     * @param name The pathname of the .fxml to load.
     * @return The FXMLLoader of the specified .fxml file, after the pop up window is closed.
     * @throws Exception IOException: file wasn't found, etc
     */
    public FXMLLoader setPopUp(String name) throws Exception {
        //Cwwreate a new stage, and change the modality so it must be dealt with
        Stage popUp = new Stage();
        popUp.initModality(Modality.APPLICATION_MODAL);

        //Do the same as in setScene, but with this popUp stage
        FXMLLoader tempLoader = new FXMLLoader(getClass().getResource(name));
        Parent scene = tempLoader.load();
        ((BudgetMeController) tempLoader.getController()).setControllerManager(this);
        popUp.setScene(new Scene(scene));
        popUp.showAndWait();
        //Wait until the window is dealt with (closed), then return the FXMLLoader
        //used in loading the window... calling getController() on a controller
        //cast to a BudgetMeController, you can access variables from the pop up

        return tempLoader;
    }
}
