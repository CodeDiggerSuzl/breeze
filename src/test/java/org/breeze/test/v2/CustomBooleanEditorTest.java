package org.breeze.test.v2;

import beans.propertyeditors.CustomBooleanEditor;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Suz1
 * @date 2020/3/13 11:54 上午
 */
public class CustomBooleanEditorTest {
    @Test
    public void testConvertStringToBoolean() {
        CustomBooleanEditor editor = new CustomBooleanEditor(true);

        editor.setAsText("true");
        Assert.assertTrue(((Boolean) editor.getValue()).booleanValue());
        editor.setAsText("false");
        Assert.assertFalse(((Boolean) editor.getValue()).booleanValue());

        editor.setAsText("on");
        Assert.assertTrue(((Boolean) editor.getValue()).booleanValue());
        editor.setAsText("off");
        Assert.assertFalse(((Boolean) editor.getValue()).booleanValue());


        editor.setAsText("yes");
        Assert.assertTrue(((Boolean) editor.getValue()).booleanValue());
        editor.setAsText("no");
        Assert.assertFalse(((Boolean) editor.getValue()).booleanValue());


        try {
            editor.setAsText("aabbcc");
        } catch (IllegalArgumentException e) {
            return;
        }
        Assert.fail();


    }
}
