package org.breeze.test.v2converter;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author Suz1
 * @date 2020/3/13 10:12 上午
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        ApplicationContextV2.class,
        BeanDefinitionResolverTest.class,
        BeanDefinitionTest.class,
        TypeConverterTest.class,
        CustomNumberEditorTest.class,
        CustomBooleanEditorTest.class})
public class V2AllTest {
}
