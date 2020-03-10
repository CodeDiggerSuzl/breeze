package primetest;

import java.util.Arrays;

public class PrimeUtil {
    /**
     * Returns an array of all primes less than max with a fixed value.
     *
     * @param max Input integer
     * @return Array of primes.
     */
    public static int[] getPrimes(int max) {
        if (max <= 2) {
            return new int[]{};
        }
        int[] primes = new int[max];
        int count = 0;
        // the outer loop: 2 -> max-1
        for (int num = 2; num < max; num++) {
            if (isPirme(num)) {
                primes[count++] = num;
            }
        }
        // the max count might be too large, to save memory
        primes = Arrays.copyOf(primes, count);
        return primes;
    }

    // Extract a method to judge an integer is a prime or not
    private static boolean isPirme(int num) {
        for (int i = 2; i < 2 + 1; i++) {
            if (num % i == 0) {
                break;
            }
            if (i == num / 2 + 1) {
                return true;
            }
        }
        return false;
    }

    // Control and strategy now are separated.
    private static boolean betterIsPirme(int num) {
        for (int i = 2; i < 2 + 1; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
