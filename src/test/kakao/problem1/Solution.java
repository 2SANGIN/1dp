package test.kakao.problem1;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Solution {
    private static final Scanner scanner = new Scanner(System.in);

    /*
     * Complete the decode function below.
     */
    static String decode(String encoded) {
        /*
         * Write your code here.
         */
        StringBuilder result = new StringBuilder();

        char ch = 0;
        for (int i = encoded.length() - 1; i >= 0; i--) {
            ch = (char) (ch * 10 + (encoded.charAt(i) - '0'));
            if (ch == 32 || (65 <= ch && ch <= 90) || (97 <= ch && ch <= 122)) {
                result.append(ch);
                ch = 0;
            }
        }

        return result.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String encoded = scanner.nextLine();

        String res = decode(encoded);

        bufferedWriter.write(res);
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
