package context.support;

import beans.factory.support.DefaultBeanFactory;
import beans.factory.xml.XmlBeanDefinitionReader;
import context.ApplicationContext;
import core.io.ClassPathResource;
import core.io.FileSystemResource;
import core.io.Resource;

/**
 * Get
 *
 * @author Suz1
 * @date 2020/3/11 21:44
 */
public class FileSystemXmlApplicationContext implements ApplicationContext {


    private DefaultBeanFactory factory;

    public FileSystemXmlApplicationContext(String filePath) {
        factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        Resource resource = new FileSystemResource(filePath);
        reader.loadBeanDefinition(resource);
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
