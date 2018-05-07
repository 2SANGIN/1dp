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

    private static int spreadViruses(final int[][] lab, final int maxSafezone, final int initialSpace, int[][] tmpRecord) {
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
        for (int index = 0; index < n * m; index++) {
            final int r = index / m;
            final int c = index % m;

            if (lab[r][c] == 2) { // virus
                for (int idx = 0; idx < 4; idx++) {
                    vertY.push(r + dy[idx]);
                    vertX.push(c + dx[idx]);
                }

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

        if (tmpRecord != null) {
            for (int i = 0; i < n; i++) {
                tmpRecord[i] = Arrays.copyOf(infected[i], m);
            }
        }
        return initialSpace - infectedSpace;
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
        initialSpace -= 3; // number of walls

        int maxSafezone = 0;
        int[] wallIdx = new int[3];
        final int lastWall = wallIdx.length - 1;

        /* set initial position of walls */
        for (int nthWall = 0; nthWall < lastWall; nthWall++) {
            if (nthWall > 0 && wallIdx[nthWall - 1] >= wallIdx[nthWall]) {
                wallIdx[nthWall] = wallIdx[nthWall - 1] + 1;
            }
            wallIdx[nthWall] = buildWall(lab, wallIdx[nthWall]);
        }

        int[][] highRecord = new int[n][];
        int[][] tmpRecord = new int[n][];

        while (wallIdx[0] + wallIdx.length < n * m) {
            /* spread virus and count safe zone */
            while (wallIdx[lastWall] < n * m) {
                final int y = wallIdx[lastWall] / m;
                final int x = wallIdx[lastWall] % m;

                if (lab[y][x] == 0) {
                    lab[y][x] = 3;
                    int tmpSafezone = spreadViruses(lab, maxSafezone, initialSpace, tmpRecord);
                    if (tmpSafezone > maxSafezone) {
                        for (int i = 0; i < n; i++) {
                            highRecord[i] = Arrays.copyOf(tmpRecord[i], m);
                        }
                        print(highRecord, "high record!!");
                        maxSafezone = tmpSafezone;
                    }
                    lab[y][x] = 0;
                }

                wallIdx[lastWall]++;
            }

            /*  increment of the index that reached the array size */
            boolean[] up = new boolean[wallIdx.length - 1];
            for (int nthWall = lastWall; nthWall > 0; nthWall--) {
                if (wallIdx[nthWall] == n * m) {
                    wallIdx[nthWall] = 0;

                    {
                        int prevWallIdx = wallIdx[nthWall - 1];
                        if (prevWallIdx < n * m) {
                            lab[prevWallIdx / m][prevWallIdx % m] = 0; // back-tracking
                        }
                    }

                    wallIdx[nthWall - 1]++;
                    up[nthWall - 1] = true;
                }
            }

            for (int nthWall = 0; nthWall < lastWall; nthWall++) {
                if (nthWall > 0 && wallIdx[nthWall - 1] >= wallIdx[nthWall]) {
                    wallIdx[nthWall] = wallIdx[nthWall - 1] + 1;
                }
                if (up[nthWall]) {
                    wallIdx[nthWall] = buildWall(lab, wallIdx[nthWall]);
                }
            }
        }

        System.out.println(maxSafezone);
    }

    private static int buildWall(int[][] map, int wallIdx) {
        int n = map.length;
        int m = map[0].length;
        while (wallIdx < n * m) {
            if (map[wallIdx / m][wallIdx % m] == 0) {
                map[wallIdx / m][wallIdx % m] = 3;
                break;
            }
            wallIdx++;
        }
        return wallIdx;
    }

    private static void print(int[][] map, String title) {
        System.out.println("<< " + title + " >>");
        for (int[] row/**/ : map) {
            for (int c = 0; c < map[0].length; c++) {
                System.out.print(row[c] + " ");
            }
            System.out.println();
        }
        System.out.println();
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
