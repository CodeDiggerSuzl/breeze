package beans;

import beans.factory.support.BeanException;

/**
 * During reading a xml file.
 *
 * @author Suz1
 * @date 2020/3/10 9:21
 */
public class BeanCreationException extends BeanException {
    public BeanCreationException(String message) {
        super(message);
    }

    public BeanCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
