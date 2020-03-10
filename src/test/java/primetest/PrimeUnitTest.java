package primetest;

import org.junit.Assert;
import org.junit.Test;

/**
 * TDD demo
 * <p>
 * Write a function that returns an array of all prime numbers less than max with a fixed value
 * public static int[] getPrimes(int max)
 * <p>
 * Steps：divide and conquer
 *
 * <p>
 * 1.1 Boundary inputs. e.g. 0，-1，2
 * 1.2 Normal inputs. e.g. 9，17，30
 */
public class PrimeUnitTest {
    /*
     * Boundary test.
     */
    @Test
    public void getPrimesForEmptyResult() {
        int[] expected = {};
        Assert.assertArrayEquals(expected, PrimeUtil.getPrimes(2));
        Assert.assertArrayEquals(expected, PrimeUtil.getPrimes(0));
        Assert.assertArrayEquals(expected, PrimeUtil.getPrimes(-1));
    }

    /**
     * Normal tests.
     */
    @Test
    public void testGetPrimes() {
        Assert.assertArrayEquals(new int[]{2, 3, 5, 7}, PrimeUtil.getPrimes(9));
        Assert.assertArrayEquals(new int[]{2, 3, 5, 7, 11, 13}, PrimeUtil.getPrimes(17));
        Assert.assertArrayEquals(new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29}, PrimeUtil.getPrimes(30));
    }
}
