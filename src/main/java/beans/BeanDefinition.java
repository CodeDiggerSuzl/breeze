package beans;

import java.util.List;

/**
 * @author Suz1
 * @date 2020/3/10 8:42
 */
public interface BeanDefinition {
    String SCOPE_SINGLETON = "singleton";
    String SCOPE_PROTOTYPE = "prototype";
    String SCOPE_DEFAULT = "";

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

    /**
     * Get property value.
     *
     * @return list of value.
     */
    List<PropertyValue> getPropertyValues();

    /**
     * For constructor.
     *
     * @return {@link ConstructorArgument}
     */
    ConstructorArgument getConstructorArguments();

    /**
     * get bean id
     *
     * @return id of the bean in xml
     */
    String getId();

    /**
     * if has constructor.
     *
     * @return has or not constructor.
     */
    boolean hasConstructorArgumentValues();
}
