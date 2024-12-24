/*
 * Problem Set 2
 * 
 * Problem 7
 * 
 * A simple class to process arrays.
 *
 * CS112
 *
 * name: Nicola Jackson
 * email: nicolacj@bu.edu
 * 
 */

 import java.util.*;

 public class ArrayProcessing {
    public static final String[] DAYS = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    
    // takes a reference to an array of integers values and swaps adjacent pairs of elements
    public static void swapNeighbors(int[] values) {
        if (values == null) {
            throw new IllegalArgumentException();
        } else if (values.length % 2 == 0){
            for (int i = 0; i < values.length - 1; i += 2) {
                int firstValue = values[i];
                int secondValue = values[i+1];
                values[i] = secondValue;
                values[i+1] = firstValue;
            }
        } else {
            for (int i = 0; i < values.length - 2; i += 2) {
                int firstValue = values[i];
                int secondValue = values[i+1];
                values[i] = secondValue;
                values[i+1] = firstValue;
            }
        }
    }

    // takes a reference to an array of integers named values and two integers oldVal and newVal, and that creates and returns a new array that is a copy of the original array, but in which all occurrences of the value oldVal are replaced with the value newVal
    public static int[] replaceInNew(int[] values, int oldVal, int newVal) {
        int[] newArray = new int[values.length];
        if (values == null) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < values.length; i++){
            if (values[i] == oldVal) {
                newArray[i] = newVal;
            } else {
                newArray[i] = values[i];
            }
        }
        return newArray;
    }

    // takes a reference to an array of integers and returns the length of the longest sorted sequence of integers in the array
    public static int maxSequence(int[] values) {
        if (values == null) {
            throw new IllegalArgumentException();
        } 
        int maxLength = 0;
        if (values.length == 0) {
            maxLength = 0;
        } else if (values.length > 1) {
            maxLength = 1;
            int currentLength = 1;
        
            for (int i = 1; i < values.length; i++) {
                if (values[i] >= values[i - 1]) {
                    currentLength++;
                } else {
                    currentLength = 1;
                }
                maxLength = Math.max(maxLength, currentLength);
        }
        } else {
            maxLength = 1;
        }

        return maxLength;
    }

    // takes a reference to a string and returns the index of that string in the class array DAYS
    public static int getPositionOf(String day) {
        int indexOfDay = -1;
        String dayNoCase = day.toLowerCase();
        for (int i = 0; i < DAYS.length; i++){
            if (DAYS[i].equalsIgnoreCase(dayNoCase)){
                indexOfDay = i;
            }
        }
        return indexOfDay;
    }

    // takes references to two integer arrays and returns a reference to a third integer array
    public static int[] swapSwapSwap( int[] arr1, int [] arr2 ) {
        if (arr1 == null || arr2 == null) {
            throw new IllegalArgumentException();
        }
        int shortLength;
        if (arr1.length < arr2.length){
            shortLength = arr1.length;
        } else {
            shortLength = arr2.length;
        } 
        int[] newArray = new int[shortLength * 2];
        for (int i = 0; i < shortLength; i++){
            newArray[i * 2] = arr1[i];
            newArray[i * 2 + 1] = arr2[arr2.length - 1 - i];
        }
        return newArray;
    }

    public static void main(String[] args){
        int a1[] = {1,2,3,4,5,6};
        int a2[] = {10,11,12,13};
        System.out.println( Arrays.toString( ArrayProcessing.swapSwapSwap(a1, a2) ) );
    }
 }