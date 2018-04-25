package test.kakao.problem5;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {
    private static final Scanner scanner = new Scanner(System.in);

    /*
     * Complete the findSimilar function below.
     */
    static long findSimilar(long a, long b) {
        /*
         * Write your code here.
         */

        char[] arrA = Long.toString(a).toCharArray();
        char[] arrB = Long.toString(b).toCharArray();
        Arrays.sort(arrA);
        Arrays.sort(arrB);

        char[] targetNumber;
        if (Arrays.equals(arrA, arrB)) { // similar numbers to a
            targetNumber = arrA;
        } else { // similar numbers to b
            targetNumber = arrB;
        }


        int cnt = 0;
        int length = targetNumber.length;
        boolean[] flags = new boolean[9];
        for (int i = 0; i < targetNumber.length; i++) {
            if (flags[targetNumber[i] - '0' - 1]) {
                cnt++;
            }
            flags[targetNumber[i] - '0' - 1] = true;
        }
        long result = factorial(length);
        for (int i = 0; i < cnt; i++) {
            result /= 2;
        }
        return result;
    }

    static long factorial(int n) {
        long sum = 1;
        while (n > 1) {
            sum = sum * n--;
        }
        return sum;
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        long a = scanner.nextLong();

        long b = scanner.nextLong();

        long res = findSimilar(a, b);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }

}
