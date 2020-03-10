# Getting Started

- Try to write a spring framework: breeze.

- With TDD.

- Base on [Spring 3.2.18](https://repo.spring.io/release/org/springframework/spring/3.2.18.RELEASE/)

## IoC 和 DI

`DI` 就是依赖注入。一个 Java 对象如果依赖另一个 java 对象，只需要声明好，容器就会跟据我们的配置信息（类是怎么依赖的、setter 注入还是构造器注入、类创建起来的实例是单例、还是原型、怎么初始化？）进行装配，

配置信息：
1. xml 格式
2. 注解格式
3. java 配置


## AOP
系统分为两类：
- 业务系统

    订单、用户、支付等等
- 非用户系统

    日志、安全、事务、性能统计等

业务系统之间和非业务系统之间的关系是正交的，不是耦合性应该很小的。线性无关的。

使用配置信息，对非业务代码进行织入。

`Spring` 使用的是运行时，将代码织入：运行时候创建了代理类，代理类来代替实际类做事情。

`aspectJ` 是在编译的时候对将代码织入到了业务代码中。



## TDD: 测试驱动开发

程序员基本技能：
1. 测试用例
2. 重构

TDD 四个步骤：
1. 思考，写一个测试用例
2. 让测试用例失败
3. 写 Just enough 的代码让测试通过
4. 重构代码，保证代码测试通过

测试用例就是安全网，保证代码的安全。

TDD demo
- 编写一个函数，返回小于跟定值的 max 的所有素数组成的数组
    ```java
    public static int[] getPrimes(int max)
    ```
步骤：
1. 任务分解
   1. 边界条件 输入 0，-1，2
   2. 正常输入 9，17，30

    Origin code:
    ```java
    /**
     * Returns an array of all primes less than max with a fixed value.
     *
     * @param max Input integer
     * @return Array of primes.
     */
    public static int[] getPrimes(int max) {
        if (max <= 2) {
            return new int[]{};
        } else {
            int[] newArray = new int[max];
            int size = 0, j;
            // tow for loops
            // the outer loop: 2 -> max-1
            for (int i = 2; i < max; i++) {
                // judge each of the outer loop
                for (j = 2; j < i / 2 + 1; j++) {
                    // check the outer element can be divided by the inner number or not
                    if (i % j == 0) {
                        break;
                    }
                }
                if (j == i / 2 + 1) {
                    newArray[size++] = i;
                }
            }
            // the max size might be too large, to save memory
            newArray = Arrays.copyOf(newArray, size);
            return newArray;
        }
    }
    ```
    Unit tests:
    ```java
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
    ```
2. 重构
   2. **控制逻辑和策略逻辑的分离**：抽离方法
   3. 算法的优化
   2. 代码上的优化 clean code
        1. 重命名 `i`、 `j`、`array` 等难以让人明白的变量名，代码逻辑上的优化
        2. 减少 `if else` 代码：提前 `return`



(完)
---
主动思考
坚持
动手
--

- 正交
- strictfp
