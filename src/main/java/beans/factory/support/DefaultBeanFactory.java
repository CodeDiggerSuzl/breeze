package beans.factory.support;

import beans.BeanCreationException;
import beans.BeanDefinition;
import beans.PropertyValue;
import config.ConfigurableBeanFactory;
import util.ClassUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This factory mainly for get default bean factory, get bean definition and get bean.
 *
 * @author Suz1
 * @date 2020/3/10 8:40
 */
public class DefaultBeanFactory extends DefaultSingletonBeanRegistry
        implements ConfigurableBeanFactory, BeanDefinitionRegistry {

    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(64);
    private ClassLoader beanClassLoader;

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
        // Judge the bean is single or not
        if (definition.isSingleton()) {
            Object bean = this.getSingleton(beanId);
            if (bean == null) {
                bean = createBean(definition);
                this.registerSingleton(beanId, bean);
            }
            return bean;
        }
        return createBean(definition);
    }

    /**
     * Create a bean with bean definition, need to judge the bean's scope, setter injection and constructor injection.
     *
     * @param bd bean definition.
     * @return an instance of the bean.
     */
    private Object instantiateBean(BeanDefinition bd) {
        ClassLoader classLoader = this.getBeanClassLoader();
        String beanClassName = bd.getBeanClassName();
        try {
            Class<?> clz = classLoader.loadClass(beanClassName);
            // ? by reflect,can only with null arg constructor TODO
            return clz.newInstance();
        } catch (Exception e) {
            throw new BeanCreationException("Create the bean for: " + beanClassName + " failed");
        }
    }


    private Object createBean(BeanDefinition definition) {
        // create instance.
        Object bean = instantiateBean(definition);
        // set properties.
        populateBean(definition, bean);
        return bean;
    }

    protected void populateBean(BeanDefinition definition, Object bean) {
        List<PropertyValue> propertyValues = definition.getPropertyValues();
        if (propertyValues.isEmpty() || propertyValues == null) {
            return;
        }
        BeanDefinitionResolver resolver = new BeanDefinitionResolver(this);
        try {
            for (PropertyValue pv : propertyValues) {
                String propertyName = pv.getName();
                Object originValue = pv.getValue();
                Object resolveValue = resolver.resolveValueIfNecessary(originValue);
                // ! how to set into the object ? !!!
                BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
                PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
                for (PropertyDescriptor pd : propertyDescriptors) {
                    if (pd.getName().equals(propertyName)) {
                        pd.getWriteMethod().invoke(bean, resolveValue);
                        break;
                    }
                }
            }
        } catch (Exception ex) {
            throw new BeanCreationException("Fail to obtain bean for class [" + definition.getBeanClassName() + " ] ");
        }
    }

    @Override
    public ClassLoader getBeanClassLoader() {
        return (this.beanClassLoader != null ? this.beanClassLoader : ClassUtils.getDefaultClassLoader());
    }

    @Override
    public void setBeanClassLoader(ClassLoader beanClassLoader) {
        this.beanClassLoader = beanClassLoader;
    }
}
