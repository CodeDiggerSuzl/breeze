package context.support;

import beans.factory.support.DefaultBeanFactory;
import beans.factory.xml.XmlBeanDefinitionReader;
import context.ApplicationContext;
import core.io.Resource;
import util.ClassUtils;

/**
 * Use Template-Method to extract common methods.
 *
 * @author Suz1
 * @date 2020/3/11 22:10
 */
public abstract class AbstractApplicationContext implements ApplicationContext {
    private DefaultBeanFactory factory;
    private ClassLoader beanClassLoader;

    /**
     * Another constructor to config class loader.
     *
     * @param cfgFilePath config file path.
     */
    public AbstractApplicationContext(String cfgFilePath) {
        this(cfgFilePath, ClassUtils.getDefaultClassLoader());
    }

    public AbstractApplicationContext(String cfgFilePath, ClassLoader classLoader) {
        factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        Resource resource = this.getResourceByPath(cfgFilePath);
        reader.loadBeanDefinition(resource);
        // make factory to be configurable.
        // if without the method above, this class loader due to the setter method,this class loader will always be null.
        // Then it will become the default loader.
        factory.setBeanClassLoader(classLoader);
    }

    /**
     * Get resource by the file path.
     *
     * @param cfgFilePath config file path.
     * @return Resource.
     */
    protected abstract Resource getResourceByPath(String cfgFilePath);

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

    @Override
    public ClassLoader getBeanClassLoader() {
        return this.beanClassLoader != null ? this.beanClassLoader : ClassUtils.getDefaultClassLoader();
    }

    @Override
    public void setBeanClassLoader(ClassLoader beanClassLoader) {
        this.beanClassLoader = beanClassLoader;
    }
}
