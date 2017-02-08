package scenes.test;

import controllers.BudgetMeController;

/**
 * Created by Noah McGivern on 2/4/2017.
 */
public class Controller extends BudgetMeController {

    private boolean pressed;

    public void initialize() {
        pressed = false;
    }

    /**
     * An example of getting a value, and returning it if this window is a pop up
     *
     * @throws Exception
     */
    public void returnFn() throws Exception {
        pressed = true;
    }

    public boolean isPressed() {
        return pressed;
    }
}
