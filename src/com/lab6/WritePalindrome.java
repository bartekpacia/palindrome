package com.lab6;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Simple program to write a palindrome into the file.
 *
 * @author kmi
 * @version 1.0.0
 */
public class WritePalindrome {
    /**
     * Writes the given palindrome into the given file.
     */
    public WritePalindrome(Palindrome p, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(p);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Starts the program.
     *
     * @param args not used.
     */
    public static void main(String[] args) {
        new WritePalindrome(new Palindrome("Kobyła ma mały bok."), "palindrome.plr");
    }
}