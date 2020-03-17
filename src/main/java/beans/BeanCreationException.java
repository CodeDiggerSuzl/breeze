package beans;

import beans.factory.support.BeanException;

/**
 * During reading a xml file.
 *
 * @author Suz1
 * @date 2020/3/10 9:21
 */
public class BeanCreationException extends BeanException {
    private String beanName;

    public BeanCreationException(String message) {
        super(message);
    }

    public BeanCreationException(String message, Throwable cause) {
        super(message, cause);
    }

    public BeanCreationException(String beanName, String msg) {
        super("Error creating bean with name '" + beanName + "': " + msg);
        this.beanName = beanName;
    }

    public BeanCreationException(String beanName, String msg, Throwable cause) {
        this(beanName, msg);
        initCause(cause);
    }

    public String getBeanName() {
        return this.beanName;
    }
}
