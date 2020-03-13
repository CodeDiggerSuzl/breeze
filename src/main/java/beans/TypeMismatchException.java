package beans;

import beans.factory.support.BeanException;

/**
 * @author Suz1
 * @date 2020/3/13 12:19 下午
 */
public class TypeMismatchException extends BeanException {
    // TODO
    private transient Object value;
    private Class<?> requiredType;

    public TypeMismatchException(Object value, Class<?> requiredType) {
        super("Failed to convert value :" + value + " to type " + requiredType);
        this.value = value;
        this.requiredType = requiredType;
    }

    public Object getValue() {
        return value;
    }

    public Class<?> getRequiredType() {
        return requiredType;
    }
}
