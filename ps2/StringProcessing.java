/*
 * Problem Set 2
 * 
 * Problem 5
 * 
 * A simple class to process strings.
 *
 * CS112
 *
 * name: Nicola Jackson
 * email: nicolacj@bu.edu
 * 
 */

 import java.util.*;

 public class StringProcessing{
    
    /* takes a String as its parameter and prints decreasing substrings of the original string */
    public static void printSubstrings(String myString) {
        for (int i = myString.length(); i > 0; i--) {
            System.out.println(myString.substring(0, i));
        }
    }

     /* takes a string str as its parameter and returns a new string formed by combining the first and last characters of str */
    public static String combineChars(String str) {
        String newString;
        if (str.length() == 1) {
            newString = str;
        } else {
            newString = str.substring(0, 1) + str.substring(str.length() - 1, str.length());
        }
        return newString;
    }

    /* takes as its two parameters a string str and a character ch and returns the index of the last occurrence of ch in str */
    public static int lastOccurrence(String str, char ch){
        int occurrence = -1;
        for (int i = str.length() - 1; i >= 0; i--) {
            if (str.charAt(i) == ch) {
                occurrence = i;
                break;
            }
        }
        return occurrence;
    }

    /* takes as its two parameters a string str and an integer n and returns a new string consisting of n copies of str */
    public static String addRepetitions(String str, int n){
        String result = "";

        for (int i = n; i > 0; i--){
            result += str;
        }

        return result;
    }

    public static void main(String[] args){
        printSubstrings("method");
        System.out.println(combineChars("Boston"));
        System.out.println(combineChars("University"));
        System.out.println(combineChars("a"));
        System.out.println(lastOccurrence("Boston", 'o'));
        System.out.println(lastOccurrence("banana", 'a'));
        System.out.println(lastOccurrence("banana", 'b'));
        System.out.println(lastOccurrence("banana", 'x'));
        System.out.println(addRepetitions("Java", 3));
        System.out.println(addRepetitions("oh!", 7));
    }
 }
