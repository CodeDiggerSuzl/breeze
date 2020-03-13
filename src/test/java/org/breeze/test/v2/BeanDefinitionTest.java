package org.breeze.test.v2;

import beans.BeanDefinition;
import beans.PropertyValue;
import beans.factory.support.DefaultBeanFactory;
import beans.factory.xml.XmlBeanDefinitionReader;
import config.RunTimeBeanReference;
import core.io.ClassPathResource;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Test new bean definition with new properties.
 *
 * @author Suz1
 */
public class BeanDefinitionTest {
    @Test
    public void testGetBeanDefinition() {
        DefaultBeanFactory factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinition(new ClassPathResource("petStore.xml"));
        BeanDefinition bd = factory.getBeanDefinition("petStore");
        List<PropertyValue> pvs = bd.getPropertyValues();
        Assert.assertEquals(2, pvs.size());
        // no exact order.
        {
            PropertyValue pv = this.getPropertyValues("account", pvs);
            Assert.assertNotNull(pv);
            Assert.assertTrue(pv.getValue() instanceof RunTimeBeanReference);
        }
        {
            PropertyValue pv = this.getPropertyValues("item", pvs);
            Assert.assertNotNull(pv);
            Assert.assertTrue(pv.getValue() instanceof RunTimeBeanReference);
        }
    }

    private PropertyValue getPropertyValues(String name, List<PropertyValue> pvs) {
        for (PropertyValue pv : pvs) {
            if (pv.getName().equals(name)) {
                return pv;
            }
        }
        return null;
    }
}
