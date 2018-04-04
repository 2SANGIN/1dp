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
  static Scanner        sc = new Scanner(System.in);

  static BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));



  public static void main(final String[] args) throws Exception {
    final int tc = sc.nextInt();

    int x1, y1, r1;
    int x2, y2, r2;
    for (int i = 0; i < tc; i++) {
      x1 = sc.nextInt();
      y1 = sc.nextInt();
      r1 = sc.nextInt();

      x2 = sc.nextInt();
      y2 = sc.nextInt();
      r2 = sc.nextInt();

      final int distance = (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1);
      final int r = r1 * r1 + r2 * r2;
      final int longR = Math.max(r1 * r1, r2 * r2);
      final int shortR = Math.min(r1 * r1, r2 * r2);

      int count;
      if (distance == 0) {
        if (shortR == longR) { // 겹치고 크기가 같은 원
          count = -1;
        } else { // 겹치지만 크기가 다른 원
          count = 0;
        }
      } else if (distance < longR) { // 큰 원 안에 작은 원이 있을 때
        if (distance + shortR < longR) { // 완전히 안에 있음
          count = 0;
        } else if (distance + shortR == longR) { // 큰 원에 닿아있음
          count = 1;
        } else { // if (distance + shortR > longR) { // 큰 원 밖으로 삐져나가 있음
          count = 2;
        }
      } else {
        if (r == distance) { // 서로 맞닿아 있음
          count = 1;
        } else if (r > distance) { // 원이 약간 겹쳐 있음
          count = 2;
        } else { // 원이 멀리 떨어져 있음
          count = 0;
        }
      }
      wr.write(count + "\n");
    }
    wr.flush();
    wr.close();
  }


  static class Scanner {
    private BufferedReader  reader;

    private StringTokenizer tokenizer;



    public Scanner(final InputStream is) {
      this.reader = new BufferedReader(new InputStreamReader(is));
    }



    public String next() {
      if (this.tokenizer == null || !this.tokenizer.hasMoreTokens()) {
        try {
          this.tokenizer = new StringTokenizer(this.reader.readLine());
        } catch (final IOException e) {
          throw new NoSuchElementException(e.getMessage());
        }
      }
      return this.tokenizer.nextToken();
    }



    public int nextInt() {
      return Integer.parseInt(next());
    }
  }
}
