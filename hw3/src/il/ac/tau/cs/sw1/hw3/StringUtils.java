package il.ac.tau.cs.sw1.hw3;

import java.util.Arrays;

public class StringUtils {

    public static String findSortedSequence(String str) {
        if (str.equals(""))
            return "";

        String output = "";
        int curLongest = 1, curLen = 1;
        String[] strings = str.split(" ");
        String curStr = "";

        if (strings.length == 0)
            return "";
        if (strings.length == 1)
            return strings[0];

        for (int i = 0; i < strings.length - 1; i++) {
            if (strings[i].compareTo(strings[i + 1]) <= 0) {
                if (curStr.equals("")) {
                    curLen = 2;
                    curStr = strings[i] + " " + strings[i + 1] + " ";
                } else {
                    curLen++;
                    curStr = curStr.concat(strings[i + 1] + " ");
                }
                if (curLen >= curLongest) {
                    curLongest = curLen;
                    output = curStr;
                }
            } else {
                curStr = "";
                curLen = 1;
            }
        }
        if (curLen >= curLongest)
            output = curStr;
        if (curLongest == 1)
            return strings[strings.length - 1];
        return output.substring(0, output.length() - 1);
    }

    public static String parityXorStrings(String a, String b) {
        int[] aLetters = new int[26];
        int[] bLetters = new int[26];
        String output = "";

        initLettersForParity(a, aLetters);
        initLettersForParity(b, bLetters);

        for (int i = 0; i < a.length(); i++) {
            char curChar = a.charAt(i);
            if (aLetters[curChar - 97] % 2 == 1 && bLetters[curChar - 97] % 2 == 0)
                output += curChar;
        }

        return output;

    }

    /**
     * Initializes the letters array for the string "str"
     *
     * @param str     the string that the array is initialized by
     * @param letters the array that is being initialized, len has to be 26.
     */
    private static void initLettersForParity(String str, int[] letters) {
        for (int i = 0; i < str.length(); i++) {
            letters[str.charAt(i) - 97]++;
        }
    }


    public static boolean isAnagram(String a, String b) {
		char[] aChars, bChars;
    	if(a.length() > b.length()) {
			aChars = a.toCharArray();
			bChars = b.toCharArray();
		}
    	else {
			aChars = b.toCharArray();
			bChars = a.toCharArray();
		}
        Arrays.sort(aChars);
        Arrays.sort(bChars);
        char[] aRevChars = reverseArray(aChars);
		char[] bRevChars = reverseArray(bChars);

		for (int i = 0; i < aRevChars.length; i++) {
			if(i == bRevChars.length){
				return aRevChars[i] == ' ';
			}
			if(aRevChars[i] != bRevChars[i])
				return false;
		}
        return true;

    }

    private static char[] reverseArray(char[] arr){
    	char[] reverseArr = new char[arr.length];
		for (int i = 0; i < arr.length; i++) {
			reverseArr[i] = arr[arr.length - i - 1];
		}
		return reverseArr;
	}
}
