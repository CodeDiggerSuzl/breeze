package context.support;

import beans.factory.support.DefaultBeanFactory;
import beans.factory.xml.XmlBeanDefinitionReader;
import context.ApplicationContext;

/**
 * @author Suz1
 * @date 2020/3/11 7:12 下午
 */
public class ClassPathXmlApplicationContext implements ApplicationContext {

    private DefaultBeanFactory factory;

    public ClassPathXmlApplicationContext(String xmlPath) {
        factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinition(xmlPath);

    }

    /**
     * Get the bean instance by beanId.
     *
     * @param beanId beanId
     * @return Bean Instance.
     */
    @Override
    public Object getBean(String beanId) {
        return factory.getBean(beanId);
    }
}
