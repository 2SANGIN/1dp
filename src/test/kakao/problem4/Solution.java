package test.kakao.problem4;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Solution {
    private static final Scanner scanner = new Scanner(System.in);


    /*
     * Complete the programmerStrings function below.
     */
    static int programmerStrings(String s) {
        /*
         * Write your code here.
         */
        final int[] required = new int['z' + 1];
        required['p'] = 1;
        required['r'] = 3;
        required['o'] = 1;
        required['g'] = 1;
        required['a'] = 1;
        required['m'] = 2;
        required['e'] = 1;
        final char[] indexes = new char[] {'p', 'r', 'o', 'g', 'a', 'm', 'e'};

        int left = 0;
        int[] count = new int['z' + 1];
        while (left < s.length()) {
            count[s.charAt(left)]++;

            if (left >= 9) {
                boolean all = true;
                for (char idx : indexes) {
                    all = all && (count[idx] >= required[idx]);
                }
                if (all) {
                    break;
                }
            }
            left++;
        }

        int right = s.length() - 1;
        count = new int['z' + 1]; // array reset
        while (right > left) {
            count[s.charAt(right)]++;

            if (right < s.length() - 10) {
                boolean all = true;
                for (char idx : indexes) {
                    all = all && (count[idx] >= required[idx]);
                }
                if (all) {
                    break;
                }
            }
            right--;
        }
        return right > left ? right - left - 1 : 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = scanner.nextLine();

        int res = programmerStrings(s);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
