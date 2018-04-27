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
        final char ITEM = '*';
        final char OBSTACLE = '-';
        int rows = sc.nextInt(), cols = sc.nextInt(), numItems = sc.nextInt(), numObstacles = sc.nextInt();

        Object[][] map = new Object[rows + 1][cols + 1];
        boolean[][] obsMap = new boolean[rows + 1][cols + 1];

        final List<Point> items = new ArrayList<>(numItems);
        final List<Point> obstalces = new ArrayList<>(numObstacles);

        for (int i = 0; i < numItems; i++) {
            int r = sc.nextInt(), c = sc.nextInt();
            items.add(new Point(r, c));
            map[r][c] = ITEM;
        }
        items.add(new Point(rows, cols)); // 목적지

        for (int o = 0; o < numObstacles; o++) {
            int r = sc.nextInt(), c = sc.nextInt();
            obstalces.add(new Point(r, c));
            map[r][c] = OBSTACLE;
            obsMap[r][c] = true;
        }


        int ways = 0;
        Point prevItem = new Point(1, 1);
        while (!items.isEmpty()) {
            Point nextItem = items.remove(0);
            int maxY = nextItem.row;
            int maxX = nextItem.col;
//            System.out.print("(" + prevItem.row + "," + prevItem.col + ")");
//            System.out.println(", (" + maxY + "," + maxX + ")");
            for (int curY = prevItem.row; curY <= maxY; curY++) {
                for (int curX = prevItem.col; curX <= maxX; curX++) {
                    if (obsMap[curY][curX]) continue;
                    if (!((curY + 1 <= maxY && obsMap[curY + 1][curX]) || (curX + 1 <= maxX && obsMap[curY][curX + 1])) && curY + 1 <= maxY && curX + 1 <= maxX) {
//                        System.out.println("beep!");
                        ways += 2;
                    }
                }
            }
            prevItem = nextItem;
        }
        System.out.println(ways);
    }

    static class Point {
        int row;
        int col;

        Point(int row, int col) {
            this.row = row;
            this.col = col;
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
