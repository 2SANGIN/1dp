package io.github.atmost1815.acmicpc.laboratory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    private static Scanner sc = new Scanner(System.in, false);

    static int spreadViruses(final int[][] lab, int maxSafezone, int initialSpace) {
        // initialize
        int n = lab.length;
        int m = lab[0].length;
        int[][] infected = new int[n][];
        for (int r = 0; r < n; r++)
            infected[r] = Arrays.copyOf(lab[r], m);

        // top, right, bottom, left
        final int[] dy = {-1, 0, 1, 0};
        final int[] dx = {0, 1, 0, -1};
        Stack<Integer> vertY = new Stack<>();
        Stack<Integer> vertX = new Stack<>();
        int infectedSpace = 0;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (lab[r][c] == 2) { // virus
                    infected[r][c] = 0;
                    vertY.push(r);
                    vertX.push(c);
                    while (!vertX.isEmpty()) {
                        int x = vertX.pop(), y = vertY.pop();
                        if (x < 0 || m <= x) continue;
                        if (y < 0 || n <= y) continue;
                        if (infected[y][x] != 0) continue;

                        infected[y][x] = 2;
                        if (initialSpace - (++infectedSpace) < maxSafezone)
                            return -1;
                        for (int idx = 0; idx < 4; idx++) {
                            vertY.push(y + dy[idx]);
                            vertX.push(x + dx[idx]);
                        }
                    }
                }
            }
        }

        int safezone = 0;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (infected[r][c] == 0)
                    safezone++;
            }
        }
        return safezone;
    }

    public static void main(final String[] args) {
        int n = sc.nextInt(), m = sc.nextInt();
        int[][] lab = new int[n][m];
        int initialSpace = 0;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                lab[r][c] = sc.nextInt();
                if (lab[r][c] == 0)
                    initialSpace++;
            }
        }

        int maxSafezone = -1;
        int[][] isolated = new int[n][];

        int numOfWalls = 3;
        while (numOfWalls-- > 0) {
            for (int r = 0; r < n; r++)
                isolated[r] = Arrays.copyOf(lab[r], m);

            int maxX = 0, maxY = 0;
            for (int y = 0; y < n; y++) {
                for (int x = 0; x < m; x++) {
                    if (isolated[y][x] != 0) continue;
                    isolated[y][x] = 3;
                    int safezone = spreadViruses(isolated, maxSafezone, initialSpace);
                    if (safezone > maxSafezone) {
                        maxSafezone = safezone;
                        maxY = y;
                        maxX = x;
                    }
                    isolated[y][x] = 0;
                }
            }
            lab[maxY][maxX] = 3;

            for (int r = 0; r < n; r++) {
                for (int c = 0; c < m; c++) {
                    System.out.print(lab[r][c] + " ");
                }
                System.out.println();
            }
            System.out.println();
            System.out.println(maxSafezone);
            System.out.println();
        }

        System.out.println(maxSafezone);
    }

    static class Scanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;
        private boolean eof;

        Scanner(final InputStream is) {
            this(is, true);
        }

        Scanner(final InputStream is, boolean eof) {
            this.reader = new BufferedReader(new InputStreamReader(is));
            this.eof = eof;
        }

        boolean hasMoreTokens() {
            return this.tokenizer != null && this.tokenizer.hasMoreTokens();
        }

        String next() {
            return getTokenizer().nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        private StringTokenizer getTokenizer() {
            if (!hasMoreTokens()) {
                try {
                    StringBuilder builder = new StringBuilder();
                    if (eof) {
                        int ch;
                        while ((ch = this.reader.read()) != -1) {
                            builder.append((char) ch);
                        }
                    } else {
                        builder.append(this.reader.readLine());
                    }
                    this.tokenizer = new StringTokenizer(builder.toString());
                } catch (final IOException e) {
                    throw new NoSuchElementException(e.getMessage());
                }
            }
            return tokenizer;
        }
    }
}
