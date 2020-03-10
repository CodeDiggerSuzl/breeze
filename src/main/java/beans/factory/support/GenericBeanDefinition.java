package beans.factory.support;

import beans.BeanDefinition;

/**
 * @author Suz1
 * @date 2020/3/10 9:06
 */
public class GenericBeanDefinition implements BeanDefinition {
    /**
     * Bean id
     */
    private String id;
    private String beanClassName;

    public GenericBeanDefinition(String id, String beanClassName) {
        this.id = id;
        this.beanClassName = beanClassName;
    }

    /**
     * Get the class name.
     *
     * @return class name
     */
    @Override
    public String getBeanClassName() {
        return this.beanClassName;
    }

    public String getId() {
        return id;
    }
}
