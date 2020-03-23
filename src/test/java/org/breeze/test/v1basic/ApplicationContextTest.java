package org.breeze.test.v1basic;

import context.ApplicationContext;
import context.support.ClassPathXmlApplicationContext;
import context.support.FileSystemXmlApplicationContext;
import org.breeze.entity.v1.Wind;
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
        // Fix: change file path from absolute file path to relative file path to fix hard code problem.
        // ApplicationContext ctx = new FileSystemXmlApplicationContext("/Users/suzl/dev/java/breeze/src/test/resources/wind-v1.xml");
        ApplicationContext ctx = new FileSystemXmlApplicationContext("src/test/resources/wind-v1.xml");
        Wind wind = (Wind) ctx.getBean("wind");
        Assert.assertNotNull(wind);
    }
}
