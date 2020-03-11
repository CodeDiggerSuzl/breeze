package config;

import beans.factory.BeanFactory;

/**
 * @author Suz1
 * @date 2020/3/11 22:49
 */
public interface ConfigurableBeanFactory extends BeanFactory {
    /**
     * Set bean factory class loader with input class loader,aim to config the class loader.
     *
     * @param beanClassLoader input class loader.
     */
    void setBeanClassLoader(ClassLoader beanClassLoader);
    /**
     * Get class loader,also aim to config.
     * @return this class's class loader.
     */
    ClassLoader getBeanClassLoader();
}
