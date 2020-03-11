package beans.factory;

/**
 * @author Suz1
 * @date 2020/3/10 8:33
 */
public interface BeanFactory {

    /**
     * Get the bean instance by beanId.
     *
     * @param beanId
     *            beanId
     * @return Bean Instance.
     */
    Object getBean(String beanId);
}
