package org.breeze.test.v2;

import context.ApplicationContext;
import context.support.ClassPathXmlApplicationContext;
import org.breeze.entity.v2.Account;
import org.breeze.entity.v2.Item;
import org.breeze.entity.v2.PetStore;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Mainly test setter injection.
 *
 * @author Suz1
 */
public class ApplicationContextV2 {
    @Test
    public void testGetProperty() throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("petStore.xml");
        PetStore petStore = (PetStore) ctx.getBean("petStore");
        assertNotNull(petStore.getAccount());
        assertNotNull(petStore.getItem());
        assertTrue(petStore.getAccount() instanceof Account);
        assertTrue(petStore.getItem() instanceof Item);
        // Test String type
        assertEquals("Leonard", petStore.getBoss());
    }
}
