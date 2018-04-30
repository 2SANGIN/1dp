package io.github.atmost1815.acmicpc.eatitem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class Main {
    private static Scanner sc = new Scanner(System.in, false);

    public static void main(final String[] args) {
        int rows = sc.nextInt(), cols = sc.nextInt(), numItems = sc.nextInt(), numObstacles = sc.nextInt();

        int[][] dp = new int[rows + 1][cols + 1];
        boolean[][] map = new boolean[rows + 1][cols + 1];
        final List<Point> items = new ArrayList<>(numItems);

        for (int i = 0; i < numItems; i++) {
            int r = sc.nextInt(), c = sc.nextInt();
            items.add(new Point(r, c));
        }
        items.add(new Point(rows, cols)); // 목적지
        items.sort(Point::compareTo);

        for (int o = 0; o < numObstacles; o++) {
            int r = sc.nextInt(), c = sc.nextInt();
            map[r][c] = true;
        }

        int ways = 1;
        Point prevItem = new Point(1, 1);
        dp[1][0] = 1;
        while (!items.isEmpty()) {
            Point nextItem = items.remove(0);
            for (int curY = prevItem.row; curY <= nextItem.row; curY++) {
                for (int curX = prevItem.col; curX <= nextItem.col; curX++) {
                    if (map[curY][curX]) continue;
                    dp[curY][curX] = dp[curY - 1][curX] + dp[curY][curX - 1];
                    dp[prevItem.row][prevItem.col] = 1;
                }
            }
            ways *= dp[nextItem.row][nextItem.col];
            prevItem = nextItem;
        }
        System.out.println(ways);
    }

    static class Point implements Comparable<Point> {
        int row;
        int col;

        Point(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public int compareTo(Main.Point o) {
            return (this.row + this.col) - (o.row + o.col);
        }
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
