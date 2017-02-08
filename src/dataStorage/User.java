package dataStorage;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.*;
import java.nio.file.Files;
import java.security.MessageDigest;

/**
 * Created by Noah McGivern on 2/4/2017.
 * A serializable object containing user program settings and data.
 *
 * *A note about serializable objects: adding new variables will
 * make saved data obsolete and unable to be read in
 */
public class User implements java.io.Serializable {

    private String username, password;

    /**
     * Creates a new User with a username and password.
     *
     * @param username
     * @param password
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Creates a blank User.
     */
    public User() {

    }

    /**
     * Returns the User object with the specified username.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     * @return The User object with the matching username and password.
     */
    public static User logIn(String username, String password) {
        try {
            //Get the file and create an InputStream
            File file = new File("users", username);

            //Decrypt the file specified
            byte[] decryptedData = decrypt(Files.readAllBytes(file.toPath()), password);
            //Read a byte array in from decryptedData, then read an object in from a byte array
            ByteArrayInputStream byteIn = new ByteArrayInputStream(decryptedData);
            ObjectInputStream in = new ObjectInputStream(byteIn);
            //Create the object User
            User user = (User) in.readObject();
            //Close what's open
            byteIn.close();
            in.close();

            return user; //Password correct, user's account returned
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            return new User(); //User wasn't found, return a blank user to signal this
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            //Password didn't decrypt the file,
            //return null because it's incorrect
            return null;
        }

        return new User(); //User probably wasn't found, return a blank user to signal this
    }

    /**
     * Serializes a User object to a save file.
     *
     * @param user The User object to be saved.
     * @return True if the user was successfully saved.
     */
    public static boolean save(User user) {
        //If the directory "users" doesn't exist, create it
        File file = new File("users");
        if (!file.exists())
            new File("users").mkdir();

        //Gets the user's filepath, if the file doesn't exist, try to create a new User
        file = new File("users", user.getUsername() + ".tmp");
        try {
            //Creates a FileOutputStream to write to the file,
            FileOutputStream fileOut = new FileOutputStream(file);
            //a ByteArrayOutputStream to write to
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            //and an ObjectOutputStream to do the writing of the object to byte[]
            ObjectOutputStream out = new ObjectOutputStream(byteOut);

            //Write the object as a byte[]
            out.writeObject(user);
            out.flush();
            out.close();

            //Encrypts the byte[] it receives from byteOut, with the user's password
            byte[] encryptedData = encrypt(byteOut.toByteArray(), user.getPassword());
            byteOut.close();

            //Finally, writes the byte[] to the specified filepath
            fileOut.write(encryptedData);
            fileOut.close();

            //Replace the real file with the tmp file
            new File("users", user.getUsername()).delete();
            file.renameTo(new File("users", user.getUsername()));
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Serializes a User object to a save file.
     *
     * @param user The User object to be saved.
     * @return True if the user was successfully created and saved.
     */
    public static boolean register(User user) {
        //If the directory "users" doesn't exist, create it
        File file = new File("users");
        if (!file.exists())
            new File("users").mkdir();

        //Gets the user's filepath, if the file doesn't exist, try to create a new User
        file = new File("users", user.getUsername());
        if (!file.exists())
            try {
                //Creates a FileOutputStream to write to the file,
                FileOutputStream fileOut = new FileOutputStream(file);
                //a ByteArrayOutputStream to write to
                ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
                //and an ObjectOutputStream to do the writing of the object to byte[]
                ObjectOutputStream out = new ObjectOutputStream(byteOut);

                //Write the object as a byte[]
                out.writeObject(user);
                out.flush();
                out.close();

                //Encrypts the byte[] it receives from byteOut, with the user's password
                byte[] encryptedData = encrypt(byteOut.toByteArray(), user.getPassword());
                byteOut.close();

                //Finally, writes the byte[] to the specified filepath
                fileOut.write(encryptedData);
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
     * Decrypts the byte[] passed with the specified password.
     *
     * @param text     The byte[] to be decrypted.
     * @param password The password of the encryption.
     * @return A decrypted byte[] of text.
     */
    private static byte[] decrypt(byte[] text, String password) {
        return encryption(text, password, false);
    }

    /**
     * Encrypts the byte[] passed with the specified password.
     *
     * @param text     The byte[] to be Encrypted.
     * @param password The password of the encryption.
     * @return An encrypted byte[] of text.
     */
    private static byte[] encrypt(byte[] text, String password) {
        return encryption(text, password, true);
    }

    /**
     * Encrypts or decrypts the byte[] passed with the specified password.
     *
     * @param text     The byte[] to be Encrypted.
     * @param password The password of the encryption.
     * @param encrypt  True if encrypting, false if decrypting.
     * @return An encrypted byte[] of text.
     */
    private static byte[] encryption(byte[] text, String password, boolean encrypt) {
        try {
            //Take the password and use it in a MessageDigest to get a byte[] to be used as a key
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(password.getBytes());
            byte[] digest = messageDigest.digest();

            //Create a DESKeySpec from the byte[] key
            DESKeySpec desKeySpec = new DESKeySpec(digest);
            //and use it to generate a key via SecretKeyFactory
            SecretKey secretKey = SecretKeyFactory.getInstance("DES").generateSecret(desKeySpec);
            Cipher cipher = Cipher.getInstance("DES");

            //Depending on the encrypt variable, init to encrypt or decrypt text and return it
            cipher.init(encrypt ? Cipher.ENCRYPT_MODE : Cipher.DECRYPT_MODE, secretKey);

            return cipher.doFinal(text);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null; //return null, because the password was most likely incorrect
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
