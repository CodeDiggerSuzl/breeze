package org.breeze.test;

import org.breeze.test.v1.V1AllTest;
import org.breeze.test.v2.V2AllTest;
import org.breeze.test.v3.V3AllTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author Suz1
 * @date 2020/3/13 1:16 下午
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        V1AllTest.class,
        V2AllTest.class,
        V3AllTest.class})
public class AllTest {
}
