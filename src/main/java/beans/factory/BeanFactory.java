package beans.factory;

import beans.BeanDefinition;

/**
 * @author Suz1
 * @date 2020/3/10 8:33
 */
public interface BeanFactory {
    /**
     * Get Bean definition by bean id in xml files.
     *
     * @param beanId bean id
     * @return the beanDefinition.
     */
    BeanDefinition getBeanDefinition(String beanId);

    /**
     * Get the bean instance by beanId.
     *
     * @param beanId beanId
     * @return Bean Instance.
     */
    Object getBean(String beanId);
}
