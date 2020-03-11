package beans.factory.support;

import beans.BeanCreationException;
import beans.BeanDefinition;
import beans.factory.BeanFactory;
import util.ClassUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This factory mainly for get default bean factory, get bean definition and get bean.
 *
 * @author Suz1
 * @date 2020/3/10 8:40
 */
public class DefaultBeanFactory implements BeanFactory, BeanDefinitionRegistry {

    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(64);

    /**
     * Get a xml file path form classpath then to parse the xml file,
     * Using dom4j to parse xml.
     */
    public DefaultBeanFactory() {
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanId) {
        return this.beanDefinitionMap.get(beanId);
    }

    /**
     * Register Bean.
     *
     * @param beanId         bean id
     * @param beanDefinition bean df.
     */
    @Override
    public void registerBeanDefinition(String beanId, BeanDefinition beanDefinition) {
        this.beanDefinitionMap.put(beanId, beanDefinition);
    }

    /**
     * Bean-Definition to Bean-Instance.
     */
    @Override
    public Object getBean(String beanId) {
        BeanDefinition definition = this.getBeanDefinition(beanId);
        if (definition == null) {
            throw new BeanCreationException("Bean definition does not exist.");
        }
        ClassLoader classLoader = ClassUtils.getDefaultClassLoader();
        String beanClassName = definition.getBeanClassName();
        try {
            assert classLoader != null;
            Class<?> clz = classLoader.loadClass(beanClassName);
            // by reflect
            return clz.newInstance();
        } catch (Exception e) {
            throw new BeanCreationException("Create the bean for" + beanClassName + "failed");
        }
    }
}
