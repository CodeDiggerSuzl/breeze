package beans.propertyeditors;

import util.NumberUtils;
import util.StringUtils;

import java.beans.PropertyEditorSupport;
import java.text.NumberFormat;

/**
 * {@link PropertyEditorSupport}
 *
 * @author Suz1
 * @date 2020/3/13 11:02 上午
 */
public class CustomNumberEditor extends PropertyEditorSupport {
    private final Class<? extends Number> numberClass;
    private final NumberFormat numberFormat;
    private final boolean allowEmpty;

    public CustomNumberEditor(Class<? extends Number> numberClass, boolean allowEmpty) throws IllegalArgumentException {
        this(numberClass, null, allowEmpty);
    }

    public CustomNumberEditor(Class<? extends Number> numberClass, NumberFormat numberFormat, boolean allowEmpty) throws IllegalArgumentException {
        if (numberClass == null || !Number.class.isAssignableFrom(numberClass)) {
            throw new IllegalArgumentException("Property class must be a subclass of Number");
        }
        this.numberClass = numberClass;
        this.numberFormat = numberFormat;
        this.allowEmpty = allowEmpty;
    }

    /**
     * Set (or change) the object that is to be edited.
     *
     * @param value The new target object to be edited.  Note that this
     *              object should not be modified by the PropertyEditor, rather
     *              the PropertyEditor should create a new object to hold any
     *              modified value.
     */
    @Override
    public void setValue(Object value) {
        if (value instanceof Number) {
            super.setValue(NumberUtils.convertNumberToTargetClass((Number) value, this.numberClass));
        }
        super.setValue(value);
    }

    /**
     * Sets the property value by parsing a given String.  May raise
     * java.lang.IllegalArgumentException if either the String is
     * badly formatted or if this kind of property can't be expressed
     * as text.
     *
     * @param text The string to be parsed.
     */
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (this.allowEmpty && !StringUtils.hasText(text)) {
            setValue(null);
        } else if (this.numberFormat != null) {
            setValue(NumberUtils.parseNumber(text, this.numberClass, this.numberFormat));
        } else {
            setValue(NumberUtils.parseNumber(text, this.numberClass));
        }
    }
}
