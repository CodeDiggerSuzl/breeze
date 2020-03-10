package org.breeze.test.v1;

import beans.BeanCreationException;
import beans.BeanDefinition;
import beans.BeanDefinitionStoreException;
import beans.factory.BeanFactory;
import beans.factory.support.DefaultBeanFactory;
import junit.framework.Assert;
import org.breeze.service.v1.Wind;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Suz1
 * @date 2020/3/10 8:08
 */
public class BeanFactoryTest {


    /**
     * note:
     * <p>
     * This test unit generates 2 interfaces(BeanFactory,BeanDefinition)and one class (DefaultBeanFactory).
     * <p>
     * As for the package name:
     * factory: is the api that for other codes to use. interfaces.
     * support: for implements class.
     * <p>
     * TDD loop steps:
     * <p>
     * 1. Write a fail test.
     * 2. Complete all code, make sure all the test unit pass.
     * 3. Refactor and improve codes.
     * 3. Start write more unit tests.
     * </p>
     * <p>
     * Then run first time to fail the test.
     * <p>
     * Implement the all code to make sure all asserts pass.
     * <p>
     * Test to get entity form xml files.
     * <p>
     * Goal: get bean definition from xml file.
     */
    @Test
    public void testGetBean() {
        // beanFactory is an interface
        BeanFactory factory = new DefaultBeanFactory("wind-v1.xml");
        // get bean definition form bean factory
        BeanDefinition bd = factory.getBeanDefinition("wind"); // id

        // judge if is equals to the bean we expected: by class name
        // expected, real value
        assertEquals("org.breeze.service.v1.Wind", bd.getBeanClassName());

        // get the instance
        Wind wind = (Wind) factory.getBean("wind");
        assertNotNull(wind);
    }

//    @Test
//    // Test BeanCreationException
//    public void testInvalidBean() {
//        DefaultBeanFactory factory = new DefaultBeanFactory("wind-v1.xml");
//        try {
//            factory.getBean("invalidBean");
//        } catch (BeanCreationException e) {
//            return;
//        }
//        Assert.fail("Expect BeanCreationException");
//    }
//
//    @Test
//    // Test BeanCreationException
//    public void testInvalidXML() {
//        try {
//            new DefaultBeanFactory("test.xml");
//        } catch (BeanDefinitionStoreException e) {
//            return;
//        }
//        Assert.fail("Expect BeanDefinitionStoreException");
//    }
}
