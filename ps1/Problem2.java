/*
 * Problem2.java
 * 
 * A program with lots of syntax errors!
 */

import java.util.*;

public class Problem2 {
    /*
     * This static method should take an integer x and return:
     *    - the opposite of x when x is negative
     *    - 10 more than x when x is non-negative and even
     *    - the unchanged value of x when x is non-negative and odd
     */
    public static int adjust(int x) {
        if (x < 0) { // checks if x is negative
            x *= -1; //manipulates and stores x
        } else if (x % 2 == 0) { // checks if x is even
            x += 10; // manipulates and stores x
        }
        return x; // returns x either unchanged if non-negative and odd or changed if the if/elif statements apply
    }

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in); // establishes a new scanner object to take user input for x
        System.out.print("Enter an integer x: "); // asks the user for an integer x
        int x = console.nextInt(); // reads the users input

        System.out.println("adjust(x) = " + adjust(x)); // outputs the integer after running through the adjust function
    }
}
