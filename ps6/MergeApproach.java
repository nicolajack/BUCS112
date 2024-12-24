import java.util.*;

public class MergeApproach{
    public static int[] union(int[] a1, int[] a2){
        // to throw exceptions
        if (a1 == null || a2 == null){
            throw new IllegalArgumentException("Arrays cannot be null.");
        }
        // make a new array to hold the union array
        int[] unionArray = new int[a1.length + a2.length];
        // efficiently sort the two arrays
        Sort.mergeSort(a1);
        Sort.mergeSort(a2);
        // to merge the two sorted arrays
        int i = 0; //index into first array
        int j = 0; // index into second array
        int k = 0; // index into union array
        while (i < a1.length && j < a2.length){
            if(a1[i] < a2[j]){
                // because the elements are sorted, simply checking the previous element of the sorted array accounts for duplicates
                if (k == 0 || unionArray[k-1] != a1[i]){
                    unionArray[k] = a1[i];
                    k++;
                }
                i++;
            } else if (a1[i] > a2[j]){
                if (k == 0 || unionArray[k-1] != a2[j]){
                    unionArray[k] = a2[j];
                    k++;
                }
                j++;
            } else {
                if (k == 0 || unionArray[k - 1] != a1[i]) {
                    unionArray[k] = a1[i];
                    k++;
                }
                i++;
                j++;
            }
        }
        // add any remaining elements
        while (i <= a1.length - 1) {
            if (k == 0 || unionArray[k-1] != a1[i]){
                unionArray[k] = a1[i];
                k++;
            }
            i++; 
        }
        while (j <= a2.length - 1) {
            if (k == 0 || unionArray[k-1] != a2[j]){
                unionArray[k] = a2[j];
                k++;
            }
            j++; 
        }
        // return the union array
        return unionArray;
    }

    public static int[] intersect(int[] a1, int[] a2){
        int[] myarr;
        int shorter;
        if (a1.length < a2.length){
            shorter = a1.length;
        } else {
            shorter = a2.length;
        }
        myarr = new int[shorter];
        // efficiently sort the two arrays
        Sort.mergeSort(a1);
        Sort.mergeSort(a2);
        // to merge the two sorted arrays
        int i = 0; //index into first array
        int j = 0; // index into second array
        int k = 0; // index into union array
        while (i < a1.length && j < a2.length){
            if (a1[i] < a2[j]) {
                i++;
            } else if (a1[i] > a2[j]) {
                j++;
            } else {
                if (k == 0 || myarr[k - 1] != a1[i]) {
                    myarr[k] = a1[i];
                    k++;
                }
                i++;
                j++;
            }
        }
        return myarr;
    }
    public static void main(String[] args) {
        int[] a1 = {10, 5, 7, 5, 9, 4};
        int[] a2 = {7, 5, 15, 7, 7, 9, 10};
        int[] result1 = union(a1, a2);
        System.out.println("result1: " + Arrays.toString(result1));
        int[] a3 = {0, 2, -4, 6, 10, 8};
        int[] a4 = {12, 0, -4, 8};
        int[] result2 = union(a3, a4);
        System.out.println("result2: " + Arrays.toString(result2));
        int[] a5 = {10, 5, 7, 5, 9, 4};
        int[] a6 = {7, 5, 15, 7, 7, 9, 10};
        int[] result3 = intersect(a5, a6);
        System.out.println(Arrays.toString(result3));
        int[] a7 = {0, 2, -4, 6, 10, 8};
        int[] a8 = {12, 0, -4, 8};
        int[] result4 = intersect(a7, a8);
        System.out.println(Arrays.toString(result4));
    }
}