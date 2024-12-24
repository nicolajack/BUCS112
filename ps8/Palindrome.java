/*
 * Palindrome.java
 */

public class Palindrome {
    // Add your definition of isPal here.
    public static boolean isPal(String str){
        LLStack<Character> myStack = new LLStack<>();
        LLQueue<Character> myQueue = new LLQueue<>();
        String copyString = str;
        while (copyString.length() != 0){
            char c = copyString.charAt(0);
            c = Character.toLowerCase(c);
            if (c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z'){
                myStack.push(c);
                myQueue.insert(c);
                copyString = copyString.substring(1);
            } else {
                copyString = copyString.substring(1);
            }
        }
        while (!myQueue.isEmpty()){
            if (!myStack.pop().equals(myQueue.remove())){
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        System.out.println("--- Testing method isPal ---");
        System.out.println();

        System.out.println("(0) Testing on \"A man, a plan, a canal, Panama!\"");
        try {
            boolean results = isPal("A man, a plan, a canal, Panama!");
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println("true");
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == true);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        
        System.out.println();    // include a blank line between tests
        
        /*
         * Add five more unit tests that test a variety of different
         * cases. Follow the same format that we have used above.
         */

        // test one
        System.out.println("(1) Testing on \", c.d. do!\"");
        try {
            boolean results = isPal(", c.d. do!");
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println("false");
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == false);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        
        System.out.println();

        // test two
        System.out.println("(2) Testing on \"#.,.&$!\"");
        try {
            boolean results = isPal("#.,.&$!!");
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println("true");
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == true);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        
        System.out.println();

        // test three
        System.out.println("(2) Testing on \"No lemon, no melon!!\"");
        try {
            boolean results = isPal("No lemon, no melon!!");
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println("true");
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == true);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        
        System.out.println();

        // test four
        System.out.println("(2) Testing on \"Hello, World!\"");
        try {
            boolean results = isPal("Hello, World!");
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println("false");
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == false);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        
        System.out.println();

        // test five
        System.out.println("(2) Testing on \"Was i?t a. Car ,oR a CAt i sAw?!\"");
        try {
            boolean results = isPal("Hello, World!");
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println("false");
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == false);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        
        System.out.println();
    }
}