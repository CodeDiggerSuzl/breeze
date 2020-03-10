package beans;

import beans.factory.support.BeanException;

/**
 * Bean Definition Exception,during a bean create process.
 *
 * @author Suz1
 * @date 2020/3/10 9:21
 */
public class BeanDefinitionStoreException extends BeanException {
    public BeanDefinitionStoreException(String message) {
        super(message);
    }

    public BeanDefinitionStoreException(String message, Throwable cause) {
        super(message, cause);
    }
}
