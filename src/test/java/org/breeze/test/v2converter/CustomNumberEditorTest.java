package org.breeze.test.v2converter;

import beans.propertyeditors.CustomNumberEditor;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test from number in xml to number type of java.
 *
 * @author Suz1
 * @date 2020/3/13 10:53 上午
 */
public class CustomNumberEditorTest {
    @Test
    public void testConverterString() {
        // normal test.
        CustomNumberEditor editor = new CustomNumberEditor(Integer.class, true);
        editor.setAsText("3");
        Object value = editor.getValue();
        Assert.assertTrue(value instanceof Integer);
        Assert.assertEquals(3, ((Integer) editor.getValue()).intValue());

        // boundary test.
        editor.setAsText("");
        Assert.assertNull(editor.getValue());

        // test abnormal case.
        try {
            editor.setAsText("3.1");
        } catch (IllegalArgumentException e) {
            return;
        }
        Assert.fail();
    }
}
