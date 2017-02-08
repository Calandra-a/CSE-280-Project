package controllers;

/**
 * Created by Noah McGivern on 2/5/2017.
 * An abstract class from which Controllers will extend,
 * enabling them to link to a ControllerManager.
 */
public abstract class BudgetMeController {

    private ControllerManager controllerManager;

    /**
     * Gets the ControllerManager.
     *
     * @return The ControllerManager linked to this BudgetMeController.
     */
    public ControllerManager getControllerManager() {
        return controllerManager;
    }

    /**
     * Sets the ControllerManager.
     *
     * @param controllerManager The ControllerManager to be linked to this BudgetMeController.
     */
    public void setControllerManager(ControllerManager controllerManager) {
        this.controllerManager = controllerManager;
    }
}
