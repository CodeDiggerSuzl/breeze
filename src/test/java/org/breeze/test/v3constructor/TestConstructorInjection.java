package org.breeze.test.v3constructor;

import context.support.ClassPathXmlApplicationContext;
import org.breeze.entity.v3.PetStore;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Suz1
 * @date 2020/3/17 10:48 下午
 */
public class TestConstructorInjection {
    ClassPathXmlApplicationContext context;
    PetStore petStore;

    @Before
    public void setup() {
        context = new ClassPathXmlApplicationContext("petStore-constructor.xml");
        petStore = (PetStore) context.getBean("petStore");
    }

    @Test
    public void testGetBeanProperty() {
        Assert.assertNotNull(petStore.getAccount());
        Assert.assertNotNull(petStore.getItem());
        Assert.assertEquals(1, petStore.getYear());
    }
}
