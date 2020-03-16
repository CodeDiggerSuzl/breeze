package beans;


import beans.propertyeditors.CustomBooleanEditor;
import beans.propertyeditors.CustomNumberEditor;
import util.ClassUtils;

import java.beans.PropertyEditor;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Suz1
 * @date 2020/3/13 12:13 下午
 */
public class SimpleTypeConverter implements TypeConverter {
    private Map<Class<?>, PropertyEditor> defaultEditors;

    public SimpleTypeConverter() {

    }

    /**
     * Convert value to the input type.
     *
     * @param value        value.
     * @param requiredType typeClass.
     * @return Certain type with certain value.
     * @throws TypeMismatchException {@link TypeMismatchException} only several types, not all types.
     */
    @Override
    public <T> T convertIfNecessary(Object value, Class<T> requiredType) throws TypeMismatchException {
        // if can assign value to requiredType
        if (ClassUtils.isAssignableValue(requiredType, value)) {
            return (T) value;
        } else {
            // if is string
            if (value instanceof String) {
                PropertyEditor editor = findDefaultEditor(requiredType);
                try {
                    editor.setAsText((String) value);
                } catch (IllegalArgumentException e) {
                    throw new TypeMismatchException(value, requiredType);
                }
                return (T) editor.getValue();
            } else {
                throw new RuntimeException("Todo: cant't converter value for " + value + " class: " + requiredType);
            }

        }
    }

    private PropertyEditor findDefaultEditor(Class<?> requireType) {
        PropertyEditor editor = this.getDefaultEditor(requireType);
        if (editor == null) {
            throw new RuntimeException("Editor for " + requireType + "has not been implemented");
        }
        return editor;
    }

    public PropertyEditor getDefaultEditor(Class<?> requireType) {
        if (this.defaultEditors == null) {
            createDefaultEditor();
        }
        return this.defaultEditors.get(requireType);
    }

    private void createDefaultEditor() {
        this.defaultEditors = new HashMap<>(64);
        // Spring's CustomBooleanEditor accepts more flag values than the JDK's default editor.
        this.defaultEditors.put(boolean.class, new CustomBooleanEditor(false));
        this.defaultEditors.put(Boolean.class, new CustomBooleanEditor(true));

        // The JDK does not contain default editors for number wrapper types!
        // Override JDK primitive number editors with our own CustomNumberEditor.
        this.defaultEditors.put(int.class, new CustomNumberEditor(Integer.class, false));
        this.defaultEditors.put(Integer.class, new CustomNumberEditor(Integer.class, true));
    }
}
