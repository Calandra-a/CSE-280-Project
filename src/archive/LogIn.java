package archive;

import dataStorage.User;

import java.io.*;

/**
 * Created by Noah McGivern on 2/4/2017.
 */
public class LogIn {

    public static boolean register(String username, String password) {

        try {
            File file = new File(username + ".txt");
            BufferedWriter output = new BufferedWriter(new FileWriter(file));
            output.write(password);
            output.close();
            return true;
        } catch (IOException e) {
            return false;
        }

    }

    public static String logIn(String username, String password) {

        try {
            File file = new File(username + ".txt");
            BufferedReader input = new BufferedReader(new FileReader(file));
            System.out.println(input.readLine().equals(password));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Serializes a User object to a save file.
     *
     * @param user The User object to be saved.
     * @return True if the user was successfully created and saved.
     */
    public static boolean registerUser(User user) {
        try {
            new File("users").mkdir();
            File file = new File("users", user.getUsername());
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(user);
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

    /**
     * Returns the User object with the specified username.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     * @return The User object with the matching username and password.
     */
    public static User logInUser(String username, String password) {
        try {
            File file = new File("users", username);
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            User user = (User) in.readObject();
            in.close();
            fileIn.close();
            if (password.equals(user.getPassword()))
                return user;
            else
                return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            //Alert message pop-up?
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
