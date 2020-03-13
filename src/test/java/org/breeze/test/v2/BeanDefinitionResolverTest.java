package org.breeze.test.v2;

import beans.factory.support.BeanDefinitionResolver;
import beans.factory.support.DefaultBeanFactory;
import beans.factory.xml.XmlBeanDefinitionReader;
import config.RunTimeBeanReference;
import config.TypeStringValue;
import core.io.ClassPathResource;
import org.breeze.entity.v2.Account;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Test from xml ref to a real bean instance.
 *
 * @author Suz1
 * @date 2020/3/13 8:38 上午
 */
public class BeanDefinitionResolverTest {
    DefaultBeanFactory factory;
    XmlBeanDefinitionReader reader;
    BeanDefinitionResolver resolver;

    @Before
    public void setUp() {
        factory = new DefaultBeanFactory();
        reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinition(new ClassPathResource("petStore.xml"));
        resolver = new BeanDefinitionResolver(factory);
    }

    @Test
    public void testResolveRuntimeBeanReference() {
        RunTimeBeanReference reference = new RunTimeBeanReference("account");
        Object value = resolver.resolveValueIfNecessary(reference);
        Assert.assertNotNull(value);
        Assert.assertTrue(value instanceof Account);
    }

    @Test
    public void testResolveTypedStringValue() {
        TypeStringValue stringValue = new TypeStringValue("test");
        Object value = resolver.resolveValueIfNecessary(stringValue);
        Assert.assertNotNull(value);
        Assert.assertEquals("test", value);
    }
}
