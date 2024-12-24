/* 
 * Problem Set 4
 * 
 * Problem 4
 * 
 * BigInt.java
 *
 * A class for objects that represent non-negative integers of 
 * up to 20 digits.
 * 
 * name: Nicola Jackson
 * email: nicolacj@bu.edu
 * 
 */

public class BigInt  {
    // the maximum number of digits in a BigInt -- and thus the length
    // of the digits array
    private static final int MAX_SIZE = 20;
    
    // the array of digits for this BigInt object
    private int[] digits;
    
    // the number of significant digits in this BigInt object
    private int numSigDigits;
    private boolean overFlow;

    /*
     * Default, no-argument constructor -- creates a BigInt that 
     * represents the number 0.
     */
    public BigInt() {
        digits = new int[MAX_SIZE];
        numSigDigits = 1;  // 0 has one sig. digit--the rightmost 0!
	    overFlow = false;
    }

    // first custom constructor use the contents of the array that is passed in as the basis of the new BigInt object
    public BigInt(int[] arr) {
        // validating the array
        if (arr == null || arr.length < 0 || arr.length > MAX_SIZE){
            throw new IllegalArgumentException("Please enter a valid array.");
        } 
        if (isValid(arr) == false) {
            throw new IllegalArgumentException("Please enter an array with valid elements.");
        } 

        // checking for negative elements in the arr
        for (int i = 0; i < numSigDigits; i++) {
            if (digits[i] < 0) {
                throw new IllegalArgumentException("Please enter an array with valid elements.");
            }
        }

        digits = new int[MAX_SIZE];
        numSigDigits = 0;

        // creating the array
        for (int i = 0; i < arr.length; i ++){
                digits[MAX_SIZE - arr.length + i] = arr[i];
            }
        // numSigDigits
        for (int i = 0; i < MAX_SIZE; i++) {
            if (digits[i] != 0) {
                numSigDigits = MAX_SIZE - i;
                break;
            }
        }
        if (numSigDigits == 0){
            numSigDigits = 1;
        }

        overFlow = false;
    }

    // helper method for first constructor
    private boolean isValid(int[] arr) {
        boolean isDigit = true;
        for (int i = 0; i < arr.length; i++){
            if (arr[i] > 9 || arr[i] < 0){
                isDigit = false;
            }
        }
        return isDigit;
    }

    // accessor method for numSigDigits
    public int getNumSigDigits(){
        return this.numSigDigits;
    }

    // toString method
    public String toString(){
        String str = "";
        int i = 0;
        if (numSigDigits == 1 && digits[MAX_SIZE - 1] == 0){
            str += 0;
        } else {
            while (digits[i] == 0 && i < MAX_SIZE - 1){
                i++;
            }
            for (int j = i; j < digits.length; j++){
                str += digits[j];
            }
        }
        return str;
    }

    // second constructor, takes an integer and turns it into a bigint object
    public BigInt(int n) {
        digits = new int[MAX_SIZE];
        numSigDigits = 0;

        if (n < 0){
            throw new IllegalArgumentException("Number cannot be negative.");
        } 

        if (n == 0){
            digits[MAX_SIZE - 1] = 0;
            numSigDigits = 1;
        }

        int i = 0;
        while (n > 0){
            digits[MAX_SIZE - 1 - i] = n % 10;
            n = n / 10;
            i++;
        }
        
        numSigDigits = i;
        overFlow = false;
    }
    
    // should compare the called BigInt object to the parameter other
    public int compareTo(BigInt other){
        int equality = 0;
        if (other == null){
            throw new IllegalArgumentException("Please enter another BigInt object.");
        }
        if (this.numSigDigits > other.numSigDigits){
            equality = 1;
        } else if (other.numSigDigits > this.numSigDigits){
            equality = -1;
        } else {
            for (int i = MAX_SIZE - this.numSigDigits; i < MAX_SIZE; i++){
                if (this.digits[i] > other.digits[i]){
                    equality = 1;
                    break;
                } else if (this.digits[i] < other.digits[i]){
                    equality = -1;
                    break;
                } else {
                    equality = 0;
                }
            }
        }
        return equality;
    }

    // method to create and return a new BigInt object for the sum of the integers represented by the called object and other
    public BigInt add(BigInt other) {
        BigInt sum = new BigInt();
        if (other == null){
            throw new IllegalArgumentException("Please enter another BigInt object.");
        }
        // to check if one object is 0
        if (isZero(other)){
            sum = this;
        } 
        if (isZero(this)){
            sum = other;
        }
        
        // to add together the numbers
        for (int i = MAX_SIZE - 1; i >= 0; i--){
            if ((this.digits[i] + other.digits[i]) + sum.digits[i] < 10){
                sum.digits[i] = (this.digits[i] + other.digits[i] + sum.digits[i]);
            } else {
                sum.digits[i-1] = 1;
                sum.digits[i] = ((this.digits[i] + other.digits[i] + sum.digits[i]) % 10);
            }
        }
        if ((this.digits[0] + other.digits[0] + sum.digits[0]) > 10){
            overFlow = true;
            throw new ArithmeticException("These numbers produce an overflow error.");
        }

        int i = 0;
        while (i < MAX_SIZE && sum.digits[i] == 0) {
            i++;
        }
        sum.numSigDigits = MAX_SIZE - i;

        if (sum.numSigDigits == 0) {
            sum.numSigDigits = 1;
        }

        return sum;

    }
    // create and return a new BigInt object for the product of the integers represented by the called object and other
    public BigInt mul(BigInt other){
        BigInt product = new BigInt();
        if (other == null){
            throw new IllegalArgumentException("Please enter another BigInt object.");
        }
        // to check if one object is 0
        if (isZero(other) || isZero(this)){
            product.digits[MAX_SIZE - 1] = 0;
            product.numSigDigits = 1;
        } 
        if (isOne(this)){
            product = other;
        }
        if (isOne(other)){
            product = this;
        }

        // to perform the actual multiplication
        for (int i = MAX_SIZE - 1; i >= MAX_SIZE - this.numSigDigits; i--) {
            for (int j = MAX_SIZE - 1; j >= MAX_SIZE - other.numSigDigits; j--) {
                int toPlace = i + j - (MAX_SIZE - 1);
                if (toPlace >= 0) {
                    int productResult = this.digits[i] * other.digits[j] + product.digits[toPlace];
                    product.digits[toPlace] = productResult % 10;
                    if (toPlace - 1 >= 0) {
                        product.digits[toPlace - 1] += productResult / 10;
                    }
                }
            }
        }

        // to calculate sigDigits
        int i = 0;
        while (i < MAX_SIZE && product.digits[i] == 0) {
            i++;
        }
        product.numSigDigits = MAX_SIZE - i;

        if (product.numSigDigits == 0) {
            product.numSigDigits = 1;
        }

        if (((this.digits[0] * other.digits[0]) + product.digits[0]) > 10){
            overFlow = true;
            throw new ArithmeticException("These numbers produce an overflow error.");
        }

        return product;
    }


    // helper for if BigInt represents 0
    private boolean isZero(BigInt ourInt){
        boolean represents = false;
        if (ourInt.numSigDigits == 1 && ourInt.digits[MAX_SIZE - 1] == 0){
            represents = true;
        }
        return represents;
    }

    // helper for if BigInt represents 1
    private boolean isOne(BigInt ourInt){
        boolean represents = false;
        if (ourInt.numSigDigits == 1 && ourInt.digits[MAX_SIZE - 1] == 1){
            represents = true;
        }
        return represents;
    }


    // getter method
    public int[] getDigits(){
        return this.digits;
    }

    public static void main(String [] args) {

        /* 
         * You should uncomment and run each test--one at a time--
         * after you build the corresponding methods of the class.
         */

	
        System.out.println("Test 1: result should be 7");
        int[] a1 = { 1,2,3,4,5,6,7 };
        BigInt b1 = new BigInt(a1);
        System.out.println(b1.getNumSigDigits());
        System.out.println();

        System.out.println("Test 2: result should be 1234567");
        b1 = new BigInt(a1);
        System.out.println(b1);
        System.out.println();
            
        System.out.println("Test 3: result should be 0");
        int[] a2 = { 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 };
        BigInt b2 = new BigInt(a2);
        System.out.println(b2);
        System.out.println();
        
        System.out.println("Test 4: should throw an IllegalArgumentException");
        try {
            int[] a3 = { 0,0,0,0,23,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 };
            BigInt b3 = new BigInt(a3);
            System.out.println("Test failed.");
        } catch (IllegalArgumentException e) {
            System.out.println("Test passed.");
        } catch (Exception e) {
            System.out.println("Test failed: threw wrong type of exception.");
        }
        System.out.println();

        System.out.println("Test 5: result should be 1234567");
        b1 = new BigInt(1234567);
        System.out.println(b1);
        System.out.println();

        System.out.println("Test 6: result should be 0");
        b2 = new BigInt(0);
        System.out.println(b2);
        System.out.println();

        System.out.println("Test 7: should throw an IllegalArgumentException");
        try {
            BigInt b3 = new BigInt(-4);
            System.out.println("Test failed.");
        } catch (IllegalArgumentException e) {
            System.out.println("Test passed.");
        } catch (Exception e) {
            System.out.println("Test failed: threw wrong type of exception.");
        }
        System.out.println();


        System.out.println("Test 8: result should be 0");
        b1 = new BigInt(12375);
        b2 = new BigInt(12375);
        System.out.println(b1.compareTo(b2));
        System.out.println();

        System.out.println("Test 9: result should be -1");
        b2 = new BigInt(12378);
        System.out.println(b1.compareTo(b2));
        System.out.println();

        System.out.println("Test 10: result should be 1");
        System.out.println(b2.compareTo(b1));
        System.out.println();

        System.out.println("Test 11: result should be 0");
        b1 = new BigInt(0);
        b2 = new BigInt(0);
        System.out.println(b1.compareTo(b2));
        System.out.println();


        System.out.println("Test 12: result should be\n123456789123456789");
        int[] a4 = { 3,6,1,8,2,7,3,6,0,3,6,1,8,2,7,3,6 };
        int[] a5 = { 8,7,2,7,4,0,5,3,0,8,7,2,7,4,0,5,3 };
        BigInt b4 = new BigInt(a4);
        BigInt b5 = new BigInt(a5);
        BigInt sum = b4.add(b5);
        System.out.println(sum);
        System.out.println();

        System.out.println("Test 13: result should be\n123456789123456789");
        System.out.println(b5.add(b4));
        System.out.println();

        System.out.println("Test 14: result should be\n3141592653598");
        b1 = new BigInt(0);
        int[] a6 = { 3,1,4,1,5,9,2,6,5,3,5,9,8 };
        b2 = new BigInt(a6);
        System.out.println(b1.add(b2));
        System.out.println();

        System.out.println("Test 15: result should be\n10000000000000000000");
        int[] a19 = { 9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9 };    // 19 nines!
        b1 = new BigInt(a19);
        b2 = new BigInt(1);
        System.out.println(b1.add(b2));
        System.out.println();
    }
}
