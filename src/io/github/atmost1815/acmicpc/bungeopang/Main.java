package io.github.atmost1815.acmicpc.bungeopang;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(final String[] args) throws Exception {
    final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    final int count = Integer.parseInt(reader.readLine());
    final int[] dp = new int[count + 1];

    final StringTokenizer st = new StringTokenizer(reader.readLine());
    for (int i = 1; i <= count; i++) {
      dp[i] = Integer.parseInt(st.nextToken());
      for (int j = i / 2; j > 0; j--) {
        dp[i] = Math.max(dp[i - j] + dp[j], dp[i]);
      }
    }
    System.out.println(dp[count]);
  }
}
