public class GenerateSums {
    public static String generateSums(int n) {
        String current = "";
        String answer = "";
        int currentSum = 0;
        
        for (int i = 1; i < n + 1; i++) {
            if (i == 1) {
                current += i;
                currentSum += i;
                answer += current;
            } else {
                current += " + " + i;
                currentSum += i;
                answer += "\n" + current + " = " + currentSum;
            }
        }
        
        answer += "\n";
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(generateSums(6));
    }
}
