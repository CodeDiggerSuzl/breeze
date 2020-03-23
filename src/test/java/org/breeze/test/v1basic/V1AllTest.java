package org.breeze.test.v1basic;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Use Junit Suite to run all test units.
 *
 * @author Suz1
 * @date 2020/3/11 8:15 下午
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        ApplicationContextTest.class,
        BeanFactoryTest.class,
        ResourceTest.class
})
public class V1AllTest {
}
