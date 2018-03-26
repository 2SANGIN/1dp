import java.util.Scanner;

public class Main {
  private static final int[] NUMBERS_LINE = {6, 2, 5, 5, 4, 5, 6, 3, 7, 5};

  public static void main(final String[] args) {

    try (final Scanner scanner = new Scanner(System.in)) {
      final String input = scanner.nextLine();
      final int N = input.length();
      final String format = "%0" + N + "d";
      final int target = countLines(input);
      final long origin = Long.valueOf(input);
      final long maximum = (int) Math.pow(10, N) - 1;

      int elapsedTime = 1;
      while (target != countLines(String.format(format, (origin + elapsedTime) % maximum))) {
        elapsedTime++;
      }
      System.out.println(elapsedTime);
    }
  }

  private static int countLines(final String number) {
    int target = 0;
    for (int i = 0; i < number.length(); i++) {
      target += NUMBERS_LINE[number.charAt(i) - '0'];
    }
    return target;
  }
}
