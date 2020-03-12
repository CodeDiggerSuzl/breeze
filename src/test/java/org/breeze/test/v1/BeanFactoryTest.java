package org.breeze.test.v1;

import beans.BeanCreationException;
import beans.BeanDefinition;
import beans.BeanDefinitionStoreException;
import beans.factory.support.DefaultBeanFactory;
import beans.factory.xml.XmlBeanDefinitionReader;
import core.io.ClassPathResource;
import junit.framework.Assert;
import org.breeze.service.v1.Wind;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Suz1
 * @date 2020/3/10 8:08
 */
public class BeanFactoryTest {

    DefaultBeanFactory factory = null;
    XmlBeanDefinitionReader reader = null;

    @Before
    public void setUp() {
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
        reader.loadBeanDefinition(new ClassPathResource("wind-v1.xml"));
        BeanDefinition bd = factory.getBeanDefinition("wind");

        assertTrue(bd.isSingleton());
        assertFalse(bd.isProtoType());
        assertEquals(BeanDefinition.SCOPE_SINGLETON, bd.getScope());
        // judge if is equals to the bean we expected: by class name
        // expected, real value
        assertEquals("org.breeze.service.v1.Wind", bd.getBeanClassName());

        // get the instance
        Wind wind = (Wind) factory.getBean("wind");
        assertNotNull(wind);
        Wind wind1 = (Wind) factory.getBean("wind");
        assertEquals(wind, wind1);
    }

    @Test
    // Test BeanCreationException
    public void testInvalidBean() {
        reader.loadBeanDefinition(new ClassPathResource("wind-v1.xml"));
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
            reader.loadBeanDefinition(new ClassPathResource("xxx.xml"));
        } catch (BeanDefinitionStoreException e) {
            return;
        }
        Assert.fail("Expect BeanDefinitionStoreException");
    }
}
