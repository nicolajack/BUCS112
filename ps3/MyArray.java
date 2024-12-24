/* 
 * Problem Set 3
 * 
 * Problem 4
 * 
 * File: MyArray
 *
 * Author:  CS112 
 *
 * Purpose: To create a class that allows you to
 * manipulate an array of integers.
 * 
 * name: Nicola Jackson
 * email: nicolacj@bu.edu
 */

 import java.util.*;                  

 public class MyArray  {
 
     // the sentinel value used to indicate end of input, initialized to -999
     final private static int SENTINEL = -999;
     // the default size of the array if one is not specified, initialized to 20
     final private static int DEFAULT_SIZE = 20;
     // the lower bound of the range of integer elements, initialized to 10
     final private static int LOWER = 10;
     // the upper bound of the range of integer elements, initialized to 50
     final private static int UPPER = 50;
     // a data member to reference an array of integers
     private int[] arr;
     // a data member to represent the number of elements entered into the array
     private int numElements;
     // represents the sum of all the elements in the array
     private int sum;
     // represents the minimum value of all elements in the array
     private int min;
     // represents the maximum value of all elements in the array
     private int max;
     // represents the average of all the elements in the array
     private double avg;

 
 // CONSTRUCTORS
     // Initializes a MyArray object using default members
     public MyArray() {
        arr = new int[DEFAULT_SIZE];
        numElements = 0;
     }
     
     // a custom constructor that creates the object’s array based on the input argument passed to the method
     public MyArray( int n ) {
        if (n <= 0) {
            throw new IllegalArgumentException("Array size has to be positive.");
        }
        arr = new int[n];
        numElements = 0;
     }

     // a custom constructor that creates and initializes the object’s array based on the array of integers passed to the method
     public MyArray( int[] arr ) {
        this.arr = new int[arr.length];
        this.numElements = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= LOWER && arr[i] <= UPPER) {
                this.arr[numElements] = arr[i];
                this.numElements += 1;
            }
        }
        computeStatistics();
     }

// methods
     // a method that prompts the user to enter integer elements (within the range specified by LOWER and UPPER bound) and store them into the object’s array
     public void inputElements() {
        Scanner scan = new Scanner(System.in);
        numElements = 0;
        for (int i = 0; i < arr.length; i++) {
            System.out.print("Enter up to " + arr.length + " integers between " +  LOWER + " and " + UPPER + " input " + SENTINEL + " to stop. ");
            int currentInput = scan.nextInt();
            if (currentInput == SENTINEL) {
                break;
            }
            
            if (currentInput >= LOWER && currentInput <= UPPER) {
                arr[numElements] = currentInput; // Store in next available spot
                numElements++;
            } else {
                System.out.println("Input must be between " + LOWER + " and " + UPPER + ".");
            }
        }
        computeStatistics();
     }

     // a class level method to determine if the integer passed to the method is within the bounds as specified by the static variables of the class, LOWER and UPPER
     public static boolean validInput( int num ) {
        boolean isValid;
        if (num <= UPPER && num >= LOWER){
            isValid = true;
        } else {
            isValid = false;
        }
        return isValid;
     }

     // a method that creates and returns a String representing the contents of the array.
     public String toString() {
        String newString = "[";
        for (int i = 0; i < numElements; i++){
            newString += arr[i];
            if (i < numElements - 1) {
                newString += ", ";
            }
        }
        return newString + "]";
     }

     // a method that computes the expected statistics of the array
     public void computeStatistics() {
        // to check if the array has elements
        if (numElements == 0) {
            min = 0;
            max = 0;
            sum = 0;
            avg = 0;
            return;
        }

        // to find minimum
        int currentMin = arr[0];
        for (int i = 0; i < numElements; i++){
            if (arr[i] < currentMin) {
                currentMin = arr[i];
            }
        }
        min = currentMin;
        // to find maximum
        int currentMax = arr[0];
        for (int i = 0; i < numElements; i++){
            if (arr[i] > currentMax) {
                currentMax = arr[i];
            }
        }
        max = currentMax;
        // to find sum
        int currentSum = 0;
        for (int i = 0; i < numElements; i++){
            currentSum += arr[i];
        }
        sum = currentSum;
        // to find average
        avg = (double) sum / numElements;
        
     }

     // a method that accepts a number and computes and returns how many times (if any) that number appears as an element in the array
     public int numOccurrences(int n) {
        int numTimes = 0;
        for (int i = 0; i < numElements; i++) {
            if (arr[i] == n){
                numTimes += 1;
            }
        }
        return numTimes;
     }

     // a method that allows you to insert a specified number at a specified position within the currently filled portion of the array
     public boolean insertElement(int n, int position) {
        boolean canInsert;
        if (position < 0 || position > numElements || numElements == arr.length){
            canInsert = false;
        } else {
            for (int i = numElements; i > position; i--) {
            arr[i] = arr[i-1];
            }
            arr[position] = n;
            numElements += 1;
            canInsert = true;
        }
        computeStatistics();
        return canInsert;
     }

     // a method that removes an element at a specified position in the array
     public int removeElement(int position) {
        int removed = -1;
        if (position >= 0 && position < numElements){
            removed = arr[position];
            for (int i = position; i < numElements - 1; i++){
                arr[i] = arr[i+1];
            }
            arr[numElements - 1] = 0;
            numElements--;
        }
        computeStatistics();
        return removed;
     }

     // a method to grow the physical array by some additional size n
     public boolean growArray(int n) {
        if (n <= 0){
            throw new IllegalArgumentException("Grow size has to be positive.");
        }
        int[] newArray = new int[arr.length + n];
        for (int i = 0; i < numElements; i++){
            newArray[i] = arr[i];
        }
        arr = newArray;
        computeStatistics();
        return true;
     }

     // accessor methods for each of the private data members
     public int getSum() {
        return this.sum;
    }
    
    public int getMin() {
        return this.min;
    }
    
    public int getMax() {
        return this.max;
    }
    
    public double getAvg() {
        return this.avg;
    }
    
    public int[] getArr() {
        return this.arr;
    }

    // creates and returns s string which is a histogram like asterisk reprenstation of the array
    public String computeHistogram(){
        String myHistogram = "";
        for (int i = 0; i < numElements; i++){
            for (int j = 0; j < arr[i]; j++){
                myHistogram += "*";
            }
            myHistogram += "\n";
        }
        return myHistogram;
    }

 
     public static void main(String [] args) {
 
     // Fill in your unit tests
 
     }
 }