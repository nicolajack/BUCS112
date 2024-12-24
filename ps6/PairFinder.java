public class PairFinder{
    public static void findPairSums(int k, int[] arr){
        for (int i = 0; i < arr.length; i++){
            for (int j = 1; j < arr.length - 1; j++){
                if((arr[i] + arr[j]) == k){
                    System.out.println(arr[i] +  " + " + arr[j] + " = " + k);
                    break;
                }
            }
        }
    }
    public static void findPairSumsFaster(int k, int[] arr){
        Sort.quickSort(arr);
        int n = 0; // index into beginning of array
        int m = arr.length - 1; // index into end of array
        while(!(n > m)){
            if((arr[n] + arr[m]) == k){
                System.out.println(arr[n] +  " + " + arr[m] + " = " + k);
                n++;
                m--;
            } else if ((arr[n] + arr[m]) < k){
                n++;
            } else {
                m--;
            }
        }
    }
    public static void main(String[] args) {
        int[] myarr = {10, 4, 7, 7, 8, 5, 15};
        findPairSums(12, myarr);
        findPairSumsFaster(12, myarr);
    }
}