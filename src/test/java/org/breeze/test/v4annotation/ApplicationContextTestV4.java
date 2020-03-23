package org.breeze.test.v4annotation;

import context.ApplicationContext;
import context.support.ClassPathXmlApplicationContext;
import org.breeze.entity.v4annotation.PetStore;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Suz1
 * @date 2020/3/23 11:31 下午
 */
public class ApplicationContextTestV4 {
    // test get property
    @Test
    public void testGetBeanProperty() {
        ApplicationContext context = new ClassPathXmlApplicationContext("petStore-annotation.xml");
        PetStore petStore = (PetStore) context.getBean("petStore");

        Assert.assertNotNull(petStore.getAccountV4());
        Assert.assertNotNull(petStore.getItemV4());
    }
}
