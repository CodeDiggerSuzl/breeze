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
    private boolean singleton = true;
    private boolean prototype = false;
    private String scope = SCOPE_DEFAULT;


    public GenericBeanDefinition(String id, String beanClassName) {
        this.id = id;
        this.beanClassName = beanClassName;
    }

    /**
     * Judge this bean is singleton or not.
     *
     * @return is singleton.
     */
    @Override
    public boolean isSingleton() {
        return this.singleton;
    }

    /**
     * Judge this bean is prototype or not.
     *
     * @return is prototype.
     */
    @Override
    public boolean isProtoType() {
        return this.prototype;
    }

    /**
     * Get the scope of this bean.
     *
     * @return scope.
     */
    @Override
    public String getScope() {
        return this.scope;
    }

    /**
     * Set the scope of this bean.
     *
     * @param scope bean's scope.
     */
    @Override
    public void setScope(String scope) {
        this.scope = scope;
        // if the input is SCOPE_SINGLETON or "",the is singleton otherwise is the prototype.
        this.singleton = SCOPE_SINGLETON.equals(scope) || SCOPE_DEFAULT.equals(scope);
        this.prototype = SCOPE_PROTOTYPE.equals(scope);
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
