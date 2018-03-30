package io.github.atmost1815.acmicpc.ascendingnumber;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
  public static void main(final String[] args) throws Exception {
    final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    try ( reader ) {
      try ( writer ) {
        final int N = Integer.valueOf(reader.readLine());

        final int[] answers = new int[Math.max(N, 3)];
        answers[0] = 10;
        answers[1] = 55;
        answers[2] = 220;

        if (N <= 3) {
          writer.write("" + answers[N - 1]);
        }

        for (int step = 3; step < N; step++) {
          answers[step] = 0;
          for (int i = 1; i < 10; i++) { // 1 ~ 9
            answers[step] += answers[step - 2] - i;
          }
        }

        writer.write("");

        writer.newLine();

        writer.flush();
      }
    }
  }
}
