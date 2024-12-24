/**
 * StringRecursion.java
 * 
 * Recursive methods to operate on strings.
 *
 * Computer Science 112, Boston University
 *
 * your name: Nicola Jackson
 *
 */

public class StringRecursion {
    public static void printReverse(String str){
        if (str == null || str.equals("")){
            return;
        }
        System.out.print(str.charAt(str.length() - 1));
        printReverse(str.substring(0, str.length() - 1));
    }

    public static String trim(String str) {
        if (str == null){
            return null;
        }
        if (str.equals("")){
            return "";
        }
        if (str.length() == 0){
            return "";
        } else {
            // to remove leading whitespace
            if ((str.charAt(0) == ' ')){
                return trim(str.substring(1, str.length()));
            }
            // to remove trailing whitespace
            if ((str.charAt(str.length() - 1) == ' ')){
                return trim(str.substring(0, str.length() - 1));
            }
            // for valid characters of the string
            return str;
        }
    }

    public static int find(char ch, String str){
        if (str.equals("")){
            return -1;
        }
        if (str == null){
            return -1;
        }
        if (str.charAt(0) == ch){
            return 0;
        } else {
            int restOfStr = find(ch, str.substring(1, str.length()));
            if (restOfStr == -1){
                return -1;
            } else {
                return 1 + restOfStr;
            }
        }
    }

    public static String weave(String str1, String str2){
        if (str1 == null || str2 == null){
            throw new IllegalArgumentException("Please enter a valid string.");
        }
        if (str1.equals("") && !str2.equals("")){
            return str2;
        }
        if (str2.equals("") && !str1.equals("")){
            return str1;
        }
        if (str1.equals("") && str2.equals("")){
            return "";
        } else {
            String restOfWeave = weave(str1.substring(1, str1.length()), str2.substring(1, str2.length()));
            return str1.substring(0, 1) + str2.substring(0, 1) + restOfWeave;
        }
    }

    public static int indexOf(char ch, String str){
        if (str.equals("")){
            return -1;
        }
        if (str == null){
            return -1;
        }
        if (str.charAt(0) == ch){
            return 0;
        } else {
            int restOfStr = find(ch, str.substring(1, str.length()));
            if (restOfStr == -1){
                return -1;
            } else {
                return 1 + restOfStr;
            }
        }
    }

    // main method
    public static void main(String[] args){
        printReverse("Terriers");
        System.out.println();
        System.out.println(trim(" hello world    "));
        System.out.println(find('b', "Rabbit"));
        System.out.println(find('P', "Rabbit"));
        System.out.println(weave("aaaa", "bbbb"));
        System.out.println(weave("hello", "world"));
        System.out.println(weave("recurse", "NOW"));
        System.out.println(weave("hello", ""));
        System.out.println(weave("", ""));
        System.out.println( indexOf('b', "Rabbit") ); 
        System.out.println( indexOf('P', "Rabbit") );
    }
}
