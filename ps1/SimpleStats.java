/*
 * Problem Set 1
 * 
 * A simple interactive program that performs operations 
 * on a set of three integers.
 *
 * CS112
 *
 * name: Nicola Jackson
 * email: nicolacj@bu.edu
 * 
 */

 import java.util.*;

 /*
  * Program Class Defintion
  */
 public class SimpleStats {
 
     /*
      * printMenu()
      *
      * Method to display user options.
      */
     public static void printMenu() {
         System.out.println("(0) Enter new numbers");
         System.out.println("(1) Find the largest");
         System.out.println("(2) Compute the sum");
         System.out.println("(3) Compute the range (largest - smallest)");
         System.out.println("(4) Compute the average");
         System.out.println("(5) Print the numbers in ascending order");
         System.out.println("(6) Quit");
         System.out.println();
         System.out.print("Enter choice: ");
     }
     
     /*** PUT YOUR SEPARATE HELPER METHODS FOR OPTIONS 1-5 HERE ***/
     
     // helper method for option 1
     public static int findLargest(int n1, int n2, int n3) {
        int largestNumber = n1;
        if (n2 >= largestNumber && n2 >= n3) {
            largestNumber = n2;
        } else if (n3 >= largestNumber && n3 >= n2) {
                largestNumber = n3;
        } return largestNumber;
     }

     // helper method for option 2
     public static int computeSum(int n1, int n2, int n3) {
        return n1 + n2 + n3;
     }

     // helper method for option 3
     public static int computeRange(int n1, int n2, int n3) {
        int smallestNumber = n1;
        if (n2 <= smallestNumber && n2 <= n3) {
            smallestNumber = n2;
        } else if (n3 <= smallestNumber && n3 <= n2) {
            smallestNumber = n3;
        } return findLargest(n1, n2, n3) - smallestNumber;
     }
     
     // helper method for option 4
     public static double computeAverage(int n1, int n2, int n3) {
        return ((double) n1 + (double) n2 + (double) n3) / 3;
     }

     // helper method for option 5
     public static String ascendingOrder(int n1, int n2, int n3) {
        int smallestNumber = n1;
        if (n2 <= smallestNumber && n2 <= n3) {
            smallestNumber = n2;
        } else if (n3 <= smallestNumber && n3 <= n2) {
            smallestNumber = n3;
        }
        String theOrder = "" + smallestNumber + " ";

        int middleNumber;
        if (n1 <= n2 && n2 <= n3 || n3 <= n2 && n2 <= n1) {
            middleNumber = n2;
        } else if (n1 <= n3 && n3 <= n2 || n2 <= n3 && n3 <= n1) {
            middleNumber = n3;
        } else {
            middleNumber = n1;
        }
        theOrder += middleNumber + " ";

        theOrder += findLargest(n1, n2, n3);

        return theOrder;
    }
     /*
      * main()
      *
      * Program execution begins with this method.
      */
     public static void main(String[] args) {
        System.out.println("Welcome to my program!"); 
        System.out.println("Let's do some math!");
        Scanner scan = new Scanner(System.in);        
 
         // variable declarations 
         int n1 = 0;
         int n2 = 0;
         int n3 = 0;

         boolean numbersInitialized = false;
 
         boolean more_input = true;
         
     /*
       * Control loop
       */
         do {
            if (numbersInitialized) {
                System.out.println();
                System.out.println("Numbers are: " + n1 + " " + n2 + " " + n3);
             }
             printMenu();
             
             /*
             * Write the conditional logic that processes the possible
             * menu choices.
             */

            int choice = scan.nextInt();

            if (choice == 0) {
                System.out.print("Enter three numbers: ");
                n1 = scan.nextInt();
                n2 = scan.nextInt();
                n3 = scan.nextInt();
                numbersInitialized = true;
                System.out.println("Numbers entered are: " + n1 + " " + n2 + " " + n3);
            } else if (choice == 1) {
                if (numbersInitialized) {
                    System.out.println("Largest number entered is: " + findLargest(n1, n2, n3));
                } else {
                    System.out.println("Cannot compute, numbers have not been entered.");
                    System.out.println();
                }
            } else if (choice == 2) {
                if (numbersInitialized) {
                    System.out.println("Sum is: " + computeSum(n1, n2, n3));
                } else {
                    System.out.println("Cannot compute, numbers have not been entered.");
                    System.out.println();
                }
            } else if (choice == 3) {
                if (numbersInitialized) {
                    System.out.println("Range is: " + computeRange(n1, n2, n3));
                } else {
                    System.out.println("Cannot compute, numbers have not been entered.");
                    System.out.println();
                }
            } else if (choice == 4) {
                if (numbersInitialized) {
                    System.out.println("Average is: " + computeAverage(n1, n2, n3));
                } else {
                    System.out.println("Cannot compute, numbers have not been entered.");
                    System.out.println();
                }
            } else if (choice == 5) {
                if (numbersInitialized) {
                    System.out.println("Ascending is: The numbers in ascending order are: " + ascendingOrder(n1, n2, n3));
                } else {
                    System.out.println("Cannot compute, numbers have not been entered.");
                    System.out.println();
                }
            } else if (choice == 6) {
                more_input = false;
            }
            else {
                System.out.println("Invalid choice. Please try again.");
                System.out.println();
            }
            
             
         } while (more_input);
         System.out.println();
         System.out.println("Have a nice day!");
     }
 }