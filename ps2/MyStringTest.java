/*
 * Problem Set 2
 * 
 * Problem 6
 * 
 * A simple class to test strings.
 *
 * CS112
 *
 * name: Nicola Jackson
 * email: nicolacj@bu.edu
 * 
 */

 import java.util.*;

 public class MyStringTest{
    public static boolean isApalindrome( String s ) {
        boolean isApal = false;    // assume that it is not
     
        // code to determine if the string s is a palindrome
        String lowercase = s.toLowerCase();
        String noNonAlpha = "";
        for (int i = 0; i < lowercase.length(); i++){
            int toASCII = (int) lowercase.charAt(i); 
            if (toASCII >= 65 && toASCII <= 90){
                noNonAlpha += lowercase.charAt(i);
            } else if (toASCII >= 97 && toASCII <= 122){
                noNonAlpha += lowercase.charAt(i);
            } else if (toASCII >= 48 && toASCII <= 57){
                noNonAlpha += lowercase.charAt(i);
            }
        }
        String playString = noNonAlpha;
        while (playString.length() > 1) {
            if (playString.charAt(0) == playString.charAt(playString.length() - 1)) {
                isApal = true;
                playString = playString.substring(1, playString.length() - 1);
            } else {
                break;
            }
        }


        // If the default (as above) assumes the string is not a palindrome,
        // the logic here should determine if it is and reassign the return
        // variable isApal appropriately, or vice verse.
        return( isApal );
     }

     public static int[] getString(){

        System.out.println("\nWelcome to the String Test Program!");
        System.out.println("This demonstrates how to input strings from the console.");

        // Declare a scanner object for user input

        Scanner userInput = new Scanner(System.in);

        System.out.print("\nType in a line of text (a String) or \"quit\" to end: ");

        // Continue to receive user input until some
        // sentinal (i.e. final) value is entered.
        // In this case, the user must enter the
        // word "quit".
        int numTimesCalled = 0;
        int numTimesTrue = 0;
        while (userInput.hasNextLine()) {
          String line = userInput.nextLine();
          if (line.equals("quit"))
	    // User has specified input it over,
	    // break out of the loop.
            break;
          else {
            System.out.println("You input: " + line);
            numTimesCalled += 1;
            if (isApalindrome(line)) {
                System.out.println("Your string " + line + " is a palindrome.");
                numTimesTrue +=1;
            } else {
                System.out.println("Your string " + line + " is not a palindrome.");
            }
            System.out.print("\nType in a line of text (a String) or \"quit\" to end: ");
          }
        } // while

        System.out.println("bye!");
        userInput.close();
        int[] outputArray = {numTimesCalled, numTimesTrue};
        return outputArray;
     } // inputString()
     


     public static void main(String[] args) {
        getString();
     }
 }

