package config;

/**
 * @author Suz1
 * @date 2020/3/11 23:41
 */
public interface SingletonBeanRegistry {
    /**
     * Register a bean to singleton by its name.
     *
     * @param beanName        bean's name.
     * @param singletonObject the Object that need to register.
     */
    void registerSingleton(String beanName, Object singletonObject);

    /**
     * Get a singleton instance by the bean's name.
     *
     * @param beanName bean
     * @return Singleton instance.
     */
    Object getSingleton(String beanName);
}
