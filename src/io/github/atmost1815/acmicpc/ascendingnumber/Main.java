package io.github.atmost1815.acmicpc.ascendingnumber;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
  public static void main(final String[] args) throws Exception {
    try ( BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)) ) {
      final int N = Integer.valueOf(reader.readLine());

      final int[] answers = new int[Math.max(N, 3)];
      answers[0] = 10;
      answers[1] = 55;
      answers[2] = 220;

      if (N <= 3) {
        System.out.println(answers[N - 1]);
      }

      for (int step = 3; step < N; step++) {
        answers[step] = 0;
        for (int i = 1; i < 10; i++) { // 1 ~ 9
          answers[step] += answers[step - 2] - i;
        }
      }
    }
  }
}
