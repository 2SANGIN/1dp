package io.github.atmost1815.acmicpc.admiral;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
    private static Scanner sc = new Scanner(System.in);

    public static void main(final String[] args) {
        do {
            int numVertices = sc.nextInt();
            int numRoutes = sc.nextInt();

            int[][] vertices = new int[numVertices][numVertices];
            Stream.of(vertices).forEach(row -> Arrays.fill(row, -1));

            for (int i = 0; i < numRoutes; i++) {
                vertices[sc.nextInt() - 1][sc.nextInt() - 1] = sc.nextInt();
            }

            for (int i = 0; i < numVertices; i++) {
                for (int j = 0; j < numVertices; j++) {
                    System.out.print(vertices[i][j] + " ");
                }
                System.out.println();
            }
        }
        while (sc.hasMoreTokens());
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

