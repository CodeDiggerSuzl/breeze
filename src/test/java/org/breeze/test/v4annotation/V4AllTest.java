package org.breeze.test.v4annotation;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author Suz1
 * @date 2020/4/5 5:05 下午
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        ApplicationContextTestV4.class,
        ClassReaderTest.class,
        MetadataReaderTest.class,
        PackageResourceLoaderTest.class})
public class V4AllTest {
}
