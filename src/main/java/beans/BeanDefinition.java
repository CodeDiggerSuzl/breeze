package beans;

/**
 * @author Suz1
 * @date 2020/3/10 8:42
 */
public interface BeanDefinition {
    public static final String SCOPE_SINGLETON = "singleton";
    public static final String SCOPE_PROTOTYPE = "prototype";
    public static final String SCOPE_DEFAULT = "";

    /**
     * Judge this bean is singleton or not.
     *
     * @return is singleton.
     */
    boolean isSingleton();

    /**
     * Judge this bean is prototype or not.
     *
     * @return is prototype.
     */
    boolean isProtoType();

    /**
     * Get the scope of this bean.
     *
     * @return scope.
     */
    String getScope();

    /**
     * Set the scope of this bean.
     *
     * @param scope bean's scope.
     */
    void setScope(String scope);

    /**
     * Get the class name.
     *
     * @return class name
     */
    String getBeanClassName();
}
