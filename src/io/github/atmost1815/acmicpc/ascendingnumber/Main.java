package io.github.atmost1815.acmicpc.ascendingnumber;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {
    private static Scanner sc = new Scanner(System.in);

    public static void main(final String[] args) {
        final int tc = sc.nextInt();

        IntStream.of(IntStream.rangeClosed(tc + 1, tc + 9)
                .reduce(1, (left, right) -> left * right % 10_007))
                .map(v -> v * 6995 % 10_007)
                .forEach(System.out::print);

        final int[][] dp = new int[tc][10];
        int result = 55;
        for (int i = 1; i < tc; i++) {
            dp[i][0] = result;
            if (i + 1 == tc) {
                break;
            }

            for (int j = 1; j < 8; j++) {
                dp[i][j] = dp[i][j - 1] - dp[i - 1][j - 1];
                if (dp[i][j] < 0) {
                    dp[i][j] += 10_007;
                }
                result += dp[i][j];
            }
            result += i + 3;
            result %= 10_007;
        }
        System.out.println(dp[tc - 1][0]);
    }

    static class Scanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        Scanner(final InputStream is) {
            this.reader = new BufferedReader(new InputStreamReader(is));
        }

        String next() {
            if (this.tokenizer == null || !this.tokenizer.hasMoreTokens()) {
                try {
                    this.tokenizer = new StringTokenizer(this.reader.readLine());
                } catch (final IOException e) {
                    throw new NoSuchElementException(e.getMessage());
                }
            }
            return this.tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}

