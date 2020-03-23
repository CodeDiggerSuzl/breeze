package org.breeze.test.v3constructor;

import beans.BeanDefinition;
import beans.ConstructorArgument;
import beans.factory.support.DefaultBeanFactory;
import beans.factory.xml.XmlBeanDefinitionReader;
import config.RunTimeBeanReference;
import config.TypeStringValue;
import core.io.ClassPathResource;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Test get constructor values for bean definition.
 *
 * @author Suz1
 * @date 2020/3/17 11:04 下午
 */
public class TestBeanDefinitionConstructor {
    @Test
    public void testConstructorArgs() {
        DefaultBeanFactory factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        ClassPathResource resource = new ClassPathResource("petStore-constructor.xml");
        reader.loadBeanDefinition(resource);

        BeanDefinition bd = factory.getBeanDefinition("petStore");
        Assert.assertEquals("org.breeze.entity.v3.PetStore", bd.getBeanClassName());

        ConstructorArgument args = bd.getConstructorArguments();
        List<ConstructorArgument.ValueHolder> valueHolders = args.getArgumentValues();

        Assert.assertEquals(3, valueHolders.size());
        RunTimeBeanReference ref1 = (RunTimeBeanReference) valueHolders.get(0).getValue();
        Assert.assertEquals("account", ref1.getBeanName());
        RunTimeBeanReference ref2 = (RunTimeBeanReference) valueHolders.get(1).getValue();
        Assert.assertEquals("item", ref2.getBeanName());
        TypeStringValue strValue = (TypeStringValue) valueHolders.get(2).getValue();
        Assert.assertEquals("1", strValue.getValue());

    }
}
