package io.github.atmost1815.acmicpc.turret;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;


public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));


    public static void main(final String[] args) throws Exception {
        final int tc = sc.nextInt();

        long x1, y1, x2, y2;
        int r1, r2;
        for (int i = 0; i < tc; i++) {
            x1 = sc.nextInt();
            y1 = sc.nextInt();
            r1 = sc.nextInt();

            x2 = sc.nextInt();
            y2 = sc.nextInt();
            r2 = sc.nextInt();

            final double distance = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
            final int longR = Math.max(r1, r2);
            final int shortR = Math.min(r1, r2);

            int count = 0; // 두 원이 만나지 않는 경우: 외부에 있을 때, 내부에 있을 때, 동심원이면서 반지름이 다를 때
            if (distance == 0 && longR == shortR) { // 동심원이면서 반지름도 같을 때
                count = -1;
            } else if (longR - shortR == distance || distance == longR + shortR) { // 내접 또는 외접
                count = 1;
            } else if (longR - shortR < distance && distance < longR + shortR) { // 두 원이 만나서 두 점이 겹치는 경우: 두 원이 외부에 있거나 큰 원 내부에 작은 원이 있는 경우
                count = 2;
            }
            wr.write(count + "\n");
        }
        wr.flush();
        wr.close();
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
