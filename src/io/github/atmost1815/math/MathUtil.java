package io.github.atmost1815.math;

import java.util.ArrayList;
import java.util.List;

public class MathUtil {
    private static List<Integer> primes;

    public static void main(String[] args) {
        {
            long startTime = System.currentTimeMillis();
            List<Integer> primeNumbers = getPrimeNumbers(100_000_000);
            long elapsed = System.currentTimeMillis() - startTime;
            System.out.println("normal way");
            System.out.println(primeNumbers.get(primeNumbers.size() - 1));
            System.out.println(primeNumbers.size());
            System.out.printf("elapsed time: %,.3f\n", elapsed / 1000f);
        }
        primes = null;
        {
            long startTime = System.currentTimeMillis();
            List<Integer> primeNumbers = getPrimeNumbersSieveOfEratosthenes(100_000_000);
            long elapsed = System.currentTimeMillis() - startTime;
            System.out.println("Sieve of Eratosthenes");
            System.out.println(primeNumbers.get(primeNumbers.size() - 1));
            System.out.println(primeNumbers.size());
            System.out.printf("elapsed time: %,.3f\n", elapsed / 1000f);
        }
    }


    public static List<Integer> getPrimeNumbers(long to) {
        getPrimes();

        int i;
        for (int subject = primes.get(primes.size() - 1) + 1; subject <= to; subject++) {
            int length = (int) java.lang.Math.ceil(java.lang.Math.sqrt(primes.size()));
            for (i = 0; i < length; i++) {
                if (subject % primes.get(i) == 0) {
                    break;
                }
            }
            if (i == length) {
                primes.add(subject);
            }
        }
        return primes;
    }


    public static List<Integer> getPrimeNumbersSieveOfEratosthenes(int to) {
        getPrimes();

        int offset = primes.get(primes.size() - 1) + 1;
        while (offset < to) {
            boolean[] sieve = new boolean[Math.min(to - offset, to / 5)];

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
                    primes.add(3);
                    primes.add(5);
                    primes.add(7);
                }
            }
        }
    }
}
