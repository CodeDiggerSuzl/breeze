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

    public AbstractApplicationContext(String cfgFilePath) {
        factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        Resource resource = this.getResourceByPath(cfgFilePath);
        reader.loadBeanDefinition(resource);
        // make factory to be configurable.
        factory.setBeanClassLoader(this.beanClassLoader);
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
    public void setBeanClassLoader(ClassLoader beanClassLoader) {
        this.beanClassLoader = beanClassLoader;
    }

    @Override
    public ClassLoader getBeanClassLoader() {
        return this.beanClassLoader != null ? this.beanClassLoader : ClassUtils.getDefaultClassLoader();
    }
}
