package primetest;

import org.junit.Assert;
import org.junit.Test;

/**
 * TDD demo
 * 编写一个函数，返回小于跟定值的max的所有素数组成的数组
 * public static int[] getPrimes(int max)
 * <p>
 * 步骤：
 * 1. 任务分解
 * <p>
 * 1.1 边界条件 输入 0，-1，2
 * 1.2 正常输入 9，17，30
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
