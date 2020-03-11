package beans.factory.support;

import com.google.errorprone.annotations.Var;
import config.SingletonBeanRegistry;
import util.Assert;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Suz1
 * @date 2020/3/11 23:48
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
    private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>(64);


    /**
     * Register a bean to singleton by its name.
     *
     * @param beanName        bean's name.
     * @param singletonObject the Object that need to register.
     */
    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
        Assert.notNull(beanName, "'beanName' must not be null.");
        Object oldObject = this.singletonObjects.get(beanName);
        if (oldObject != null) {
            throw new IllegalStateException("Could not register object [" + singletonObject + "] under bean name '" + beanName
                    + "': there is already an Object [" + oldObject + "] defined bound.");
        }
        this.singletonObjects.put(beanName, singletonObject);
    }

    /**
     * Get a singleton instance by the bean's name.
     *
     * @param beanName bean
     * @return Singleton instance.
     */
    @Override
    public Object getSingleton(String beanName) {
        return this.singletonObjects.get(beanName);
    }
}
