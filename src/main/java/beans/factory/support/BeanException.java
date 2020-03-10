package beans.factory.support;

/**
 * Base Bean Exception.
 *
 * @author Suz1
 * @date 2020/3/10 9:22
 */
public class BeanException extends RuntimeException {
    public BeanException(String message) {
        super(message);
    }

    public BeanException(String message, Throwable cause) {
        super(message, cause);
    }
}
