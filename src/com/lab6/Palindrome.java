package com.lab6;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Palindrome implements Serializable {
    /**
     * This palindrome.
     */
    private String palindrome;
    /**
     * This palindrome with punctuation removed.
     */
    private String rawPalindrome;

    public Palindrome() {

    }

    /**
     * @return This palindrome.
     */
    public String getPalindrome() {
        return palindrome;
    }

    /**
     * @return This palindrom in raw, internal form.
     */
    public String getRawPalindrome() {
        return rawPalindrome;
    }

    /**
     * Makes the raw representation of this palindrome.
     */
    private void makeRawPalindrome() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < palindrome.length(); i++) {
            if (Character.isLetter(palindrome.charAt(i))) {
                sb.append(palindrome.charAt(i));
            }
        }
        rawPalindrome = sb.toString().toLowerCase();
    }

    /**
     * Creates a palindrome from the given word.
     *
     * @param palindrome to set. If this word is not a palindrome, a runtime
     *                   exception is thrown (need not to be checked).
     */
    public Palindrome(String palindrome) {
        this.palindrome = palindrome;
        makeRawPalindrome();
        if (!isPalindrome(rawPalindrome)) {
            throw new IllegalArgumentException("[" + palindrome + "] is not a palindrome.");
        }
    }

    @Override
    public String toString() {
        return palindrome;
    }

    @Override
    public boolean equals(Object anObject) {
        if (anObject == null) {
            return false;
        }
        if (anObject instanceof Palindrome) {
            return rawPalindrome.equals(((Palindrome) anObject).getRawPalindrome());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return palindrome.hashCode();
    }

    static int findLongestPalindromeCounter = 0;
    static int analyzeSubstringCounter = 0;
    static int isPalindromeCounter = 0;

    static public void resetCounters() {
        findLongestPalindromeCounter = 0;
        analyzeSubstringCounter = 0;
        isPalindromeCounter = 0;
    }

    static public void printCounters() {
        printCounter("findLongestPalindromeCounter", findLongestPalindromeCounter);
        printCounter("analyzeSubstringCounter", analyzeSubstringCounter);
        printCounter("isPalindromeCounter", isPalindromeCounter);
    }

    static private void printCounter(String msg, int counter) {
        System.out.println(msg + " : " + counter);
    }

    /**
     * Library function that finds the longest palindrome in the given word.
     *
     * @param word to be searched in to find the longest palindrome.
     * @return the longest palindrome found or empty string if no palindrome in the
     * given word.
     */
    public static String findLongestPalindrome(String word) {
        findLongestPalindromeCounter++;
        String longestPalidrome = "";
        for (int i = 0; i < word.length(); i++) {
            String current = analyzeSubstring(word.substring(i));
            if (longestPalidrome.length() < current.length()) {
                longestPalidrome = current;
            }
        }
        return longestPalidrome;
    }

    /**
     * An auxiliary function that tries to find the palindrome in the given word,
     * starting from its beginning.
     *
     * @param word to be searched in to find a palindrome.
     * @return palindrome found or empty string if no palindrome was found in the
     * given word.
     */
    private static String analyzeSubstring(String word) {
        analyzeSubstringCounter++;
        for (int i = word.length(); i > 0; i--) {
            String subword = word.substring(0, i);
            if (isPalindrome(subword)) {
                return subword;
            }
        }
        return "";
    }

    /**
     * Checks if the word is a palindrome.
     *
     * @param word to be checked.
     * @return <code>true</code> if the given word is a palindrome,
     * <code>false</code> otherwise.
     */
    public static boolean isPalindrome(String word) {
        isPalindromeCounter++;
        return word.equals(new StringBuilder(word).reverse().toString());
    }

    /**
     * Simple class' test.
     *
     * @param args not used.
     */
    public static void main(String[] args) {
        Palindrome p1 = new Palindrome("kobyłamamałybok");
        Palindrome p2 = new Palindrome("kobyła ma mały bok!");
        Palindrome p3 = new Palindrome("elf układał kufle");
        System.out.println("p1 == p2 : " + (p1 == p2));
        System.out.println("p1.equals(p2) : " + p1.equals(p2));
        System.out.println("p1.equals(p3) : " + p1.equals(p3));
        resetCounters();
        String word = "rrrrr abccba kobyłamamałybok";
        String longestPalindrome = findLongestPalindrome(word);
        System.out.println("longestPalindrome: " + longestPalindrome);
        printCounters();
    }
}
