package org.breeze.test.v1;

import context.ApplicationContext;
import context.support.ClassPathXmlApplicationContext;
import context.support.FileSystemXmlApplicationContext;
import org.breeze.service.v1.Wind;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test application context: combine XML reader and bean factory.
 *
 * @author Suz1
 * @date 2020/3/11 6:58 下午
 */
public class ApplicationContextTest {
    @Test
    public void testGetBean() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("wind-v1.xml");
        Wind wind = (Wind) ctx.getBean("wind");
        Assert.assertNotNull(wind);
    }

    @Test
    public void testGetBeanFromFileSystemContext() {
        ApplicationContext ctx = new FileSystemXmlApplicationContext("/Users/suzl/dev/java/breeze/src/test/resources/wind-v1.xml");
        Wind wind = (Wind) ctx.getBean("wind");
        Assert.assertNotNull(wind);
    }
}
