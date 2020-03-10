package beans.factory.support;

import beans.BeanDefinition;
import beans.factory.BeanFactory;

/**
 * @author Suz1
 * @date 2020/3/10 8:40
 */
public class DefaultBeanFactory implements BeanFactory {
    /**
     * Get a xml path form classpath then to parse the xml file.
     * Using dom4j to parse xml.
     * @param path xml path.
     */
    public DefaultBeanFactory(String path) {

    }

    @Override
    public BeanDefinition getBeanDefinition(String wind) {
        return null;
    }

    @Override
    public Object getBean(String beanId) {
        return null;
    }
}
