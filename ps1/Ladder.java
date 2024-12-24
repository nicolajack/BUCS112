/* 
* Ladder.java
*
* A program that allows your friend to precompute the required length of a ladder they need 
* to reach a certain point on the outside of their house.
*
* Name: Nicola Jackson 
* Email: nicolacj@bu.edu
*/ 

import java.util.*;

public class Ladder {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.print("Enter an integer representing the height of the point the ladder needs to reach: ");
        int heightInFeet = console.nextInt();
        System.out.print("Enter an integer representing the angle in degrees at which the ladder will be positioned: ");
        int angleInDegrees = console.nextInt();
        double angleInRadians = (angleInDegrees * Math.PI) / 180.0;
        double lengthOfLadder = (heightInFeet) / Math.sin(angleInRadians);
        System.out.println("The required length is:");
        System.out.println(lengthOfLadder + " feet");
        System.out.println((lengthOfLadder / 3.0) + " yards");

        System.out.println(((int) lengthOfLadder / 3) + " yards and " + (((lengthOfLadder / 3) - (((int)(lengthOfLadder)) / 3)) * 3.0) + " feet");
    }
}
