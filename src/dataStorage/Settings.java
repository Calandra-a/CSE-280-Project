package dataStorage;

import java.io.*;

/**
 * Created by Noah McGivern on 2/4/2017.
 * A serializable object containing public program settings,
 * i.e. a log-in screen remember me option. For more detailed
 * notes on loading/saving the file, look at User.
 */
public class Settings implements java.io.Serializable {

    private boolean rememberMe;
    private String rememberMeString;

    /**
     * Creates a default Settings object
     */
    public Settings() {
        rememberMe = false;
        rememberMeString = "";
    }

    /**
     * Creates and serializes a Settings object to a save file.
     *
     * @return True if the settings were successfully created and saved.
     */
    private static boolean createSettings() {
        return saveSettings(null);
    }

    /**
     * Returns the settings from a save file in a Settings object.
     *
     * @return A Settings object from a save file.
     */
    public static Settings loadSettings() {
        try {
            File file = new File("data", "settings");
            if (! file.exists())
                createSettings();
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Settings settings = (Settings) in.readObject();
            in.close();
            fileIn.close();
            return settings;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Serializes a Settings object to a save file.
     *
     * @param settings The Settings object to be saved.
     * @return True if the settings were successfully saved.
     */
    public static boolean saveSettings(Settings settings) {
        try {
            new File("data").mkdir();
            File file = new File("data", "settings");
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject((settings != null) ? settings : new Settings());
            out.close();
            fileOut.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    public String getRememberMeString() {
        return rememberMeString;
    }

    public void setRememberMeString(String rememberMeString) {
        this.rememberMeString = rememberMeString;
    }
}
