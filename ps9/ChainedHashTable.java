/*
 * ChainedHashTable.java
 *
 * Computer Science 112, Boston University
 * 
 * Modifications and additions by:
 *     name: Nicola Jackson
 *     email: nicolacj@bu.edu
 */

import java.util.*;     // to allow for the use of Arrays.toString() in testing

/*
 * A class that implements a hash table using separate chaining.
 */
public class ChainedHashTable implements HashTable {
    /* 
     * Private inner class for a node in a linked list
     * for a given position of the hash table
     */
    private class Node {
        private Object key;
        private LLQueue<Object> values;
        private Node next;
        
        private Node(Object key, Object value) {
            this.key = key;
            values = new LLQueue<Object>();
            values.insert(value);
            next = null;
        }
    }
    
    private Node[] table;      // the hash table itself
    private int numKeys;       // the total number of keys in the table
        
    /* hash function */
    public int h1(Object key) {
        int h1 = key.hashCode() % table.length;
        if (h1 < 0) {
            h1 += table.length;
        }
        return h1;
    }
    
    /*** Add your constructor here ***/
    public ChainedHashTable(int size){
        if (size < 0){
            throw new IllegalArgumentException("Size can't be negative.");
        } else {
            table = new Node[size];
            numKeys = 0;
        }
    }
    
    /*
     * insert - insert the specified (key, value) pair in the hash table.
     * Returns true if the pair can be added and false if there is overflow.
     */
    public boolean insert(Object key, Object value) {
        /** Replace the following line with your implementation. **/
        if (key == null) {
            throw new IllegalArgumentException("key must be non-null");
        }
        int h1 = h1(key);
        if (table[h1] == null){
            table[h1] = new Node(key, value);
            numKeys++;
            return true;
        } else {
            Node trav = table[h1];
            while (trav.next != null){
                if (trav.key.equals(key)){
                    trav.values.insert(value);
                    return true;
                } else {
                    trav = trav.next;
                }
            }
            if (trav.key.equals(key)) {
                trav.values.insert(value);
            } else {
                trav.next = new Node(key, value);
            }
            numKeys++;
            return true;
        }
    }
    
    /*
     * search - search for the specified key and return the
     * associated collection of values, or null if the key 
     * is not in the table
     */
    public Queue<Object> search(Object key) {
        if (key == null) {
            throw new IllegalArgumentException("key must be non-null");
        }
        int h1 = h1(key);
        Node trav = table[h1];
        while (trav != null){
            if (trav.key.equals(key)){
                return trav.values;
            } else {
                trav = trav.next;
            }
        }
        return null;
    }
    
    /* 
     * remove - remove from the table the entry for the specified key
     * and return the associated collection of values, or null if the key 
     * is not in the table
     */
    public Queue<Object> remove(Object key) {
        if (key == null) {
            throw new IllegalArgumentException("key must be non-null");
        }
        int h1 = h1(key);
        Node trav = table[h1];
        Node prev = null;
        while (trav != null){
            if (trav.key.equals(key)) {
                Queue<Object> removed = trav.values;
                if (prev == null){
                    table[h1] = trav.next;
                } else {
                    prev.next = trav.next;
                }
                numKeys--;
                return removed;
            }
            prev = trav;
            trav = trav.next;
        }
        return null;
        
    }
    
    
    /*** Add the other required methods here ***/
    // an accessor method for the number of keys 
    public int getNumKeys(){
        return this.numKeys;
    }

    // returns a value of type double that represents the load factor of the table
    public double load(){
        return ((double) getNumKeys() / table.length);
    }

    // returns an array of type Object containing all of the keys in the hash table
    
    public Object[] getAllKeys(){
        Object[] keyArray = new Object[getNumKeys()];
        int j = 0;
        for (int i = 0; i < table.length; i++){
            Node trav = table[i];
            while (trav != null){
                keyArray[j] = trav.key;
                j++;
                trav = trav.next;
            }
        }
        return keyArray;
    } 

    // takes an integer representing the new size, and that grows the table to have that new size
    public void resize(int newSize){
        ChainedHashTable newTable = new ChainedHashTable(newSize);
        if (newSize < table.length){
            throw new IllegalArgumentException("New size is less than current size.");
        } else if (newSize == table.length){
            return;
        } else {
            for (int i = 0; i < table.length; i++){
                Node trav = table[i];
                while (trav != null){
                    while (!trav.values.isEmpty()) {
                        Object value = trav.values.remove();
                        newTable.insert(trav.key, value);
                    }
                    trav = trav.next;
                }
            }
        }
        this.table = newTable.table;
        this.numKeys = newTable.getNumKeys();
    }
    
    
    /*
     * toString - returns a string representation of this ChainedHashTable
     * object. *** You should NOT change this method. ***
     */
    public String toString() {
        String s = "[";
        
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) {
                s += "null";
            } else {
                String keys = "{";
                Node trav = table[i];
                while (trav != null) {
                    keys += trav.key;
                    if (trav.next != null) {
                        keys += "; ";
                    }
                    trav = trav.next;
                }
                keys += "}";
                s += keys;
            }
        
            if (i < table.length - 1) {
                s += ", ";
            }
        }       
        
        s += "]";
        return s;
    }

    public static void main(String[] args) {
        /** Add your unit tests here **/
        // to test insert
        // test one: normal table
        try {
            System.out.println("Testing insert.");
            ChainedHashTable table = new ChainedHashTable(5);
            System.out.println(table.insert(15, "apple"));
            System.out.println(table.insert(12, "orange"));
            System.out.println(table.insert(15, "mango"));
            System.out.println(table.toString());
        } catch (IllegalArgumentException e) {
            System.out.println("Error.");
        }
        // test two: a fuller table 
        try {
            System.out.println("Testing insert.");
            ChainedHashTable table = new ChainedHashTable(5);
            System.out.println(table.insert(15, "apple"));
            System.out.println(table.insert(12, "orange"));
            System.out.println(table.insert(15, "mango"));
            System.out.println(table.insert(14, "banana"));
            System.out.println(table.insert(16, "kiwi"));
            System.out.println(table.insert(22, "strawberry"));
            System.out.println(table.insert(22, "raspberry"));
            System.out.println(table.toString());
        } catch (IllegalArgumentException e) {
            System.out.println("Error.");
        }

        // to test search
        // test one: key is in table
        try {
            System.out.println();
            System.out.println("Testing search.");
            ChainedHashTable table = new ChainedHashTable(5);
            System.out.println(table.insert(15, "apple"));
            System.out.println(table.insert(12, "orange"));
            System.out.println(table.insert(15, "mango"));
            System.out.println(table.search(15));
        } catch (IllegalArgumentException e) {
            System.out.println("Error.");
        }
        // test two: key is not in table
        try {
            System.out.println();
            System.out.println("Testing search.");
            ChainedHashTable table = new ChainedHashTable(5);
            System.out.println(table.insert(15, "apple"));
            System.out.println(table.insert(12, "orange"));
            System.out.println(table.insert(15, "mango"));
            System.out.println(table.search(33));
        } catch (IllegalArgumentException e) {
            System.out.println("Error.");
        }

        // to test remove
        // test one: key is in table
        try {
            System.out.println();
            System.out.println("Testing remove.");
            ChainedHashTable table = new ChainedHashTable(5);
            System.out.println(table.insert(15, "apple"));
            System.out.println(table.insert(12, "orange"));
            System.out.println(table.insert(15, "mango"));
            System.out.println(table.remove(12));
            System.out.println(table.toString());
        } catch (IllegalArgumentException e) {
            System.out.println("Error.");
        }
        // test two: key is not in table
        try {
            System.out.println();
            System.out.println("Testing remove.");
            ChainedHashTable table = new ChainedHashTable(5);
            System.out.println(table.insert(15, "apple"));
            System.out.println(table.insert(12, "orange"));
            System.out.println(table.insert(15, "mango"));
            System.out.println(table.remove(87));
            System.out.println(table.toString());
        } catch (IllegalArgumentException e) {
            System.out.println("Error.");
        }

        // to test getNumKeys
        // test one: empty table
        try {
            System.out.println();
            System.out.println("Testing getNumKeys.");
            ChainedHashTable table = new ChainedHashTable(5);
            System.out.println(table.getNumKeys());
        } catch (IllegalArgumentException e) {
            System.out.println("Error.");
        }
        // test tw0: non-empty table
        try {
            System.out.println();
            System.out.println("Testing getNumKeys.");
            ChainedHashTable table = new ChainedHashTable(5);
            System.out.println(table.insert(15, "apple"));
            System.out.println(table.insert(12, "orange"));
            System.out.println(table.insert(15, "mango"));
            System.out.println(table.getNumKeys());
        } catch (IllegalArgumentException e) {
            System.out.println("Error.");
        }

        // to test load
        // test one: empty table
        try {
            System.out.println();
            System.out.println("Testing load.");
            ChainedHashTable table = new ChainedHashTable(5);
            System.out.println(table.load());
        } catch (IllegalArgumentException e) {
            System.out.println("Error.");
        }
        // test two: non-empty table
        try {
            System.out.println();
            System.out.println("Testing load.");
            ChainedHashTable table = new ChainedHashTable(5);
            System.out.println(table.insert(15, "apple"));
            System.out.println(table.insert(12, "orange"));
            System.out.println(table.insert(15, "mango"));
            System.out.println(table.load());
        } catch (IllegalArgumentException e) {
            System.out.println("Error.");
        }

        // to test getAllKeys
        // test one: empty table
        try {
            System.out.println();
            System.out.println("Testing getAllKeys.");
            ChainedHashTable table = new ChainedHashTable(5);
            System.out.println(Arrays.toString(table.getAllKeys()));
        } catch (IllegalArgumentException e) {
            System.out.println("error");
        }
        // test two: non-empty table
        try {
            System.out.println();
            System.out.println("Testing getAllKeys.");
            ChainedHashTable table = new ChainedHashTable(5);
            System.out.println(table.insert(15, "apple"));
            System.out.println(table.insert(12, "orange"));
            System.out.println(table.insert(15, "mango"));
            System.out.println(Arrays.toString(table.getAllKeys()));
        } catch (IllegalArgumentException e) {
            System.out.println("error");
        }

        // to test resize
        // test one: passed size is smaller than current size
        try {
            System.out.println();
            System.out.println("Testing resize.");
            ChainedHashTable table = new ChainedHashTable(5);
            System.out.println(table.insert(15, "apple"));
            System.out.println(table.insert(12, "orange"));
            System.out.println(table.insert(15, "mango"));
            table.resize(3);
            System.out.println(Arrays.toString(table.getAllKeys()));
        } catch (IllegalArgumentException e) {
            System.out.println("error");
        }

        // test two: passed size is not smaller than current size
        try {
            System.out.println();
            System.out.println("Testing resize.");
            ChainedHashTable table = new ChainedHashTable(5);
            System.out.println(table.insert(15, "apple"));
            System.out.println(table.insert(12, "orange"));
            System.out.println(table.insert(15, "mango"));
            table.resize(9);
            System.out.println(Arrays.toString(table.getAllKeys()));
        } catch (IllegalArgumentException e) {
            System.out.println("error");
        }
    }
}
