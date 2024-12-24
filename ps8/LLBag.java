// Nicola Jackson
// nicolacj@bu.edu

import java.util.*;

public class LLBag implements Bag {
    private int numItems;
    private Node head;

    private class Node {
        private Object item;
        private Node next;
        
        private Node(Object i, Node n) {
            item = i;
            next = n;
        }
    }

    public LLBag(){
        numItems = 0;
        head = null;
    }

    public int numItems() {
        return numItems;
    }

    // to add an item to the bag
    public boolean add(Object item){
        if (item == null) {
            throw new IllegalArgumentException("item must be non-null");
        }
        head = new Node(item, head);
        numItems++;
        return true;
    }

    // to remove an item from the bag
    public boolean remove(Object item){
        if (item == null) {
            throw new IllegalArgumentException("item must be non-null");
        }
        if (head == null){
            return false;
        }
        if (head.item.equals(item)) {
            head = head.next;
            numItems--;
            return true;
        }
        Node trav = head;
        Node trail = null;
        while (trav!= null){
            if (trav.item.equals(item)){
                trail.next = trav.next;
                numItems--;
                return true;
            } else {
                trail = trav;
                trav = trav.next;
            }
        }
        return false;
    }

    // to see if an item is in the bag
    public boolean contains(Object item){
        if (item == null) {
            throw new IllegalArgumentException("item must be non-null");
        }
        Node trav = head;
        while (trav != null){
            if (trav.item.equals(item)){
                return true;
            }
            trav = trav.next;
        }
        return false;
    }

    // to grab an item from the bag
    public Object grab() {
        if (numItems == 0) {
            throw new IllegalStateException("the bag is empty");
        }
        int whichOne = (int)(Math.random() * numItems);
        int index = 0;
        Node trav = head;
        while(index != whichOne){
            trav = trav.next;
            index++;
        }
        return trav.item;
    }

    // to return an array containing the current contents of the bag
    public Object[] toArray() {
        Object[] myArr = new Object[numItems];
        Node trav = head;
        int index = 0;
        while (trav != null){
            myArr[index] = trav.item;
            index++;
            trav = trav.next;
        }
        return myArr;
    }

    // to string
    public String toString() {
        String str = "{";
        Node trav = head;
        while (trav != null){
            str += trav.item;
            if (trav.next != null){
                str += ", ";
            }
            trav = trav.next;
        }
        str += "}";
        return str;
    }

    // main!
    public static void main(String[] args) {
        Bag bag = new LLBag();
        bag.add(2);
        bag.add(5);
        bag.add(8);
        System.out.println("bag = " + bag);
        System.out.println();
        System.out.println(bag.contains(5));
        System.out.println(bag.remove(5));
        System.out.println("bag = " + bag);
        /*
        // Create a Scanner object for user input.
        Scanner scan = new Scanner(System.in);
        
        // Create an LLBag named bag1.
        System.out.print("number of items in bag 1: ");
        int numItems = scan.nextInt();
        Bag bag1 = new LLBag();
        scan.nextLine();    // consume the rest of the line
        
        // Read in strings, add them to bag1, and print out bag1.
        String itemStr;        
        for (int i = 0; i < numItems; i++) {
            System.out.print("item " + i + ": ");
            itemStr = scan.nextLine();
            bag1.add(itemStr);
        }
        System.out.println("bag 1 = " + bag1);
        System.out.println();
        
        // Select a random item and print it.
        Object item = bag1.grab();
        System.out.println("grabbed " + item);
        System.out.println();
        
        // Iterate over the objects in bag1, printing them one per line.
        Object[] items = bag1.toArray();
        for (int i = 0; i < items.length; i++) {
            System.out.println(items[i]);
        }
        System.out.println();
        
        // Get an item to remove from bag1, remove it, and reprint the bag.
        System.out.print("item to remove: ");
        itemStr = scan.nextLine();
        if (bag1.contains(itemStr)) {
            bag1.remove(itemStr);
        }
        System.out.println("bag 1 = " + bag1);
        System.out.println();
        */
    }
}