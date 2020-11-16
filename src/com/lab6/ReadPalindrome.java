package com.lab6;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Simple programme to read a palindrome from a given file
 *
 * @author Adam Zientek
 * @version 1.0.0
 */
public class ReadPalindrome {
    /**
     * Tries to read a palinfrome.
     * @param fileName Serialized Palindrome object to read from.
     * @return The read palindrome if reading is successful, null otherwise.
     */
    public static Palindrome readPalindrome(String fileName) {
        Palindrome palindromeToDeserialize = null;

        try (ObjectInputStream oos = new ObjectInputStream(new FileInputStream(fileName))) {
            palindromeToDeserialize = (Palindrome) oos.readObject();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (ClassNotFoundException cce) {
            // Because type casting in oss.readObject() can fail
            cce.printStackTrace();
        }

        return palindromeToDeserialize;
    }

    public static void main(String[] args) {
        // First, let's create a palindrome, serialize and write and save it
        Palindrome palindromeToWrite = new Palindrome("Kobyła ma mały bok.");
        new WritePalindrome(palindromeToWrite, "palindrome.plr");

        // Then, let's read and deserialize the palindrome we created above.
        Palindrome palindromeToRead = ReadPalindrome.readPalindrome("palindrome.plr");
        System.out.println(palindromeToRead.getRawPalindrome());
        System.out.println(palindromeToRead.getPalindrome());
    }
}
