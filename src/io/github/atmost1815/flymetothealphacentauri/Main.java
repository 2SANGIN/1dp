package io.github.atmost1815.flymetothealphacentauri;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
  public static void main(final String[] args) {
    try ( BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)) ) {
      final int testcase = Integer.valueOf(reader.readLine());
      final int[] counts = new int[testcase];
      for (int i = 0; i < testcase; i++) {
        final StringTokenizer st = new StringTokenizer(reader.readLine(), " ");

        final int d = -Integer.valueOf(st.nextToken()) + Integer.valueOf(st.nextToken());
        final int c = (int) Math.sqrt(d);

        if (d == c * c) {
          counts[i] = 2 * c - 1;
        } else if (d - c <= c * c) {
          counts[i] = 2 * c;
        } else if (d - 2 * c <= c * c) {
          counts[i] = 2 * c + 1;
        }
      }

      final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
      for (int i = 0; i < testcase; i++) {
        writer.write(counts[i] + "\n");
      }
      writer.flush();
      writer.close();
    } catch (final IOException e) {
      e.printStackTrace();
    }
  }
}
