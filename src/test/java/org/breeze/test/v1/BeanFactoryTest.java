package org.breeze.test.v1;

import beans.BeanCreationException;
import beans.BeanDefinition;
import beans.BeanDefinitionStoreException;
import beans.factory.support.DefaultBeanFactory;
import beans.factory.xml.XmlBeanDefinitionReader;
import junit.framework.Assert;
import org.breeze.service.v1.Wind;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Suz1
 * @date 2020/3/10 8:08
 */
public class BeanFactoryTest {

    DefaultBeanFactory factory = null;
    XmlBeanDefinitionReader reader = null;

    @Before
    public void  setUp() {
        factory = new DefaultBeanFactory();
        reader = new XmlBeanDefinitionReader(factory);

    }
    /**
     * Test to get entity form xml files.
     * <p>
     * Goal: get bean definition from xml file.
     */
    @Test
    public void testGetBean() {
        reader.loadBeanDefinition("wind-v1.xml");
        BeanDefinition bd = factory.getBeanDefinition("wind");

        // judge if is equals to the bean we expected: by class name
        // expected, real value
        assertEquals("org.breeze.service.v1.Wind", bd.getBeanClassName());

        // get the instance
        Wind wind = (Wind)factory.getBean("wind");
        assertNotNull(wind);
    }

    @Test
    // Test BeanCreationException
    public void testInvalidBean() {
        reader.loadBeanDefinition("wind-v1.xml");
        BeanDefinition bd = factory.getBeanDefinition("wind");
        try {
            factory.getBean("invalidBean");
        } catch (BeanCreationException e) {
            return;
        }
        Assert.fail("Expect BeanCreationException");
    }

    @Test
    // Test BeanCreationException
    public void testInvalidXML() {
        try {
            reader.loadBeanDefinition("xxx.xml");
        } catch (BeanDefinitionStoreException e) {
            return;
        }
        Assert.fail("Expect BeanDefinitionStoreException");
    }
}
