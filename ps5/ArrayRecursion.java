/**
 * ArrayRecursion.java
 * 
 * Recursive methods to operate on arrays.
 *
 * Computer Science 112, Boston University
 *
 * your name: Nicola Jackson
 *
 */

public class ArrayRecursion {
    // uses recursion instead of iteration to search for an item which can be of any reference type (i.e. String, Integer, etc) in an array of said reference types
    public static boolean search(Object item, Object[] arr, int start){
        if (item == null || arr == null){
            return false;
        }
        if (start == arr.length){
            return false;
        } else {
            if (arr[start].equals(item)){
                return true;
            } else {
                return search(item, arr, start + 1);
            }
        }
    }
    
    // uses recursion to create a string of all the elements in an array, but in reverse order.
    public static String reverseArrayToString(Object[] arr, int index ){
        if (arr == null){
            return "";
        }
        if (index == arr.length){
            return "[";
        } else {
            if (index == 0){
                return reverseArrayToString(arr, index + 1) + arr[index] + "]";
            } else {
                return reverseArrayToString(arr, index + 1) + arr[index] + ", ";
            }
        }
    }

    public static void main(String[] args) {
        // to test method one
        Object[] arr = {"hi", 0, 9, "hello"};
        System.out.println(search("hello", arr, 0));
        
        // to test method two
        String a[] = { "abc", "def", "ghi", "klm", "nop", "qrs" };
        System.out.println(reverseArrayToString(a, 0));
    }
}
