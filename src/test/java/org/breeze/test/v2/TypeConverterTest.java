package org.breeze.test.v2;

import beans.SimpleTypeConverter;
import beans.TypeConverter;
import beans.TypeMismatchException;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Test converter.
 *
 * @author Suz1
 * @date 2020/3/13 12:01 下午
 */
public class TypeConverterTest {

    @Test
    public void testConverterToInt() {
        TypeConverter converter = new SimpleTypeConverter();
        Integer i = converter.convertIfNecessary("3", Integer.class);
        Assert.assertEquals(3, i.intValue());

        try {
            converter.convertIfNecessary("3.1", Integer.class);
        } catch (TypeMismatchException e) {
            return;
        }
        fail();
    }

    @Test
    public void testConverterStringToBoolean() {
        TypeConverter converter = new SimpleTypeConverter();
        Boolean b = converter.convertIfNecessary("true", Boolean.class);
        assertEquals(true, b);

        try {
            converter.convertIfNecessary("xxx", Boolean.class);
        } catch (TypeMismatchException e) {
            return;
        }
        fail();
    }
}
