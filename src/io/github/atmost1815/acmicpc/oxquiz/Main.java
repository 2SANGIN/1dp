package io.github.atmost1815.acmicpc.oxquiz;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
  public static void main(final String[] args) {
    try ( BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)) ) {
      final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

      for (int loop = Integer.valueOf(reader.readLine()); loop > 0; loop--) {
        int answer;
        int total = 0, score = 0;
        while ((answer = reader.read()) != '\n') {
          if (answer == 'O') {
            total += ++score;
          } else {
            score = 0;
          }
        }
        writer.write(Integer.toString(total));
        writer.newLine();
      }
      writer.flush();
      writer.close();
    } catch (NumberFormatException | IOException e) {
      e.printStackTrace();
    }
  }
}
