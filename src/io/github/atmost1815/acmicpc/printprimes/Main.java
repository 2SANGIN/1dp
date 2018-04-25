package io.github.atmost1815.acmicpc.printprimes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class Main {
    private static Scanner sc = new Scanner(System.in, false);

    public static void main(final String[] args) {
        int min = sc.nextInt();
        int max = sc.nextInt();
        List<Integer> primes = MathUtil.getPrimeNumbersSieveOfEratosthenes(max);
        if (min == 1)
            System.out.println(1);
        if (max != 1)
            for (int num : primes) {
                if (num >= min) System.out.println(num);
            }
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


    static public class MathUtil {
        private static List<Integer> primes;

        public static List<Integer> getPrimeNumbersSieveOfEratosthenes(int to) {
            getPrimes();

            int offset = primes.size() > 0 ? primes.get(primes.size() - 1) + 1 : 0;
            while (offset < to) {
                boolean[] sieve = new boolean[Math.min(to - offset + 1, to / 5)];

                for (int prime : primes)
                    for (int natural = Math.max(prime << 1, 2); natural - offset < sieve.length; natural += prime) {
                        if (natural - offset < 0 || sieve[natural - offset]) continue;
                        sieve[natural - offset] = true;
                    }

                for (int i = 0; i < sieve.length; i++)
                    for (int natural = Math.max(i + offset << 1, offset); natural - offset < sieve.length; natural += (i + offset)) {
                        if (natural - offset < 0 || sieve[natural - offset]) continue;
                        sieve[natural - offset] = true;
                    }

                for (int i = 0; i < sieve.length; i++) {
                    if (!sieve[i]) primes.add(i + offset);
                }

                offset += sieve.length;
            }
            return primes;
        }

        private static void getPrimes() {
            if (primes == null) {
                synchronized (MathUtil.class) {
                    if (primes == null) {
                        primes = new ArrayList<>();
                        primes.add(2);
                    }
                }
            }
        }
    }
}
