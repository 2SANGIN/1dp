package com.wordpress.saecota.oxquiz;

import java.util.Scanner;

public class Main {
  public static void main(final String[] args) {
    try ( final Scanner scanner = new Scanner(System.in) ) {
      for (int loop = Integer.valueOf(scanner.nextLine()); loop > 0; loop--) {
        final String answers = scanner.nextLine();
        final int[] scores = new int[answers.length()];

        int sum = 0;
        for (int i = 0; i < answers.length(); i++) {
          if (answers.charAt(i) == 'O') {
            sum += (scores[i] = scores[Math.max(0, i - 1)] + 1);
          } else {
            scores[i] = 0;
          }
        }
        System.out.println(sum);
      }
    }
  }
}
