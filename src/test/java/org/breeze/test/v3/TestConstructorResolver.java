package org.breeze.test.v3;

import beans.BeanDefinition;
import beans.factory.support.ConstructorResolver;
import beans.factory.support.DefaultBeanFactory;
import beans.factory.xml.XmlBeanDefinitionReader;
import core.io.ClassPathResource;
import org.breeze.entity.v3.PetStore;
import org.junit.Assert;
import org.junit.Test;

/**
 * Convert constructor args to the needed type
 *
 * @author Suz1
 * @date 2020/3/18 12:08 上午
 */
public class TestConstructorResolver {
    @Test
    public void testAutowireConstructor() {

        DefaultBeanFactory factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        ClassPathResource resource = new ClassPathResource("petStore-constructor.xml");
        reader.loadBeanDefinition(resource);
        BeanDefinition bd = factory.getBeanDefinition("petStore");

        ConstructorResolver resolver = new ConstructorResolver(factory);
        // So this is the autowired
        PetStore petStore = (PetStore) resolver.autowireConstructor(bd);
        int value = petStore.getYear();
        Assert.assertEquals(1, petStore.getYear());
        Assert.assertNotNull(petStore.getAccount());
        Assert.assertNotNull(petStore.getItem());
    }


}
