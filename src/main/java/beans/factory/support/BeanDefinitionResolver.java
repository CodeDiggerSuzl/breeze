package beans.factory.support;

import config.ConfigurableBeanFactory;
import config.RunTimeBeanReference;
import config.TypeStringValue;

/**
 * Resolve bean.
 *
 * @author Suz1
 * @date 2020/3/13 8:46 上午
 */
public class BeanDefinitionResolver {
    // use ConfigurableBeanFactory
    //    private final DefaultBeanFactory factory;
    private final ConfigurableBeanFactory factory;

    public BeanDefinitionResolver(ConfigurableBeanFactory factory) {
        this.factory = factory;
    }


    public Object resolveValueIfNecessary(Object value) {
        // if reference.
        if (value instanceof RunTimeBeanReference) {
            RunTimeBeanReference ref = (RunTimeBeanReference) value;
            String beanName = ref.getBeanName();
            return this.factory.getBean(beanName);
            // if just string.
        } else if (value instanceof TypeStringValue) {
            return ((TypeStringValue) value).getValue();
        } else {
            // TODO: maybe inner bean.
            throw new RuntimeException("The value " + value + "has not been implemented.");
        }
    }
}

