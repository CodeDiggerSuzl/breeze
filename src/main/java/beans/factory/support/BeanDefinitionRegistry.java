package beans.factory.support;

import beans.BeanDefinition;

/**
 * For get bean definition and register bean.
 *
 * @author Suz1
 * @date 2020/3/11 16:49
 */
public interface BeanDefinitionRegistry {

    /**
     * Get bean definition and return a bean-definition,{@link BeanDefinition}
     *
     * @param beanId bean id in xml
     * @return An instance of bean definition.
     */
    BeanDefinition getBeanDefinition(String beanId);

    /**
     * Register Bean.
     *
     * @param beanId         bean id
     * @param beanDefinition bean df.
     */
    void registerBeanDefinition(String beanId, BeanDefinition beanDefinition);

}
