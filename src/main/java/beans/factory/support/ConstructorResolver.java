package beans.factory.support;

import beans.BeanCreationException;
import beans.BeanDefinition;
import beans.ConstructorArgument;
import beans.SimpleTypeConverter;
import config.ConfigurableBeanFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Constructor;
import java.util.List;

/**
 * @author Suz1
 * @date 2020/3/18 12:17 上午
 */
public class ConstructorResolver {
    protected final Log logger = LogFactory.getLog(getClass());

    private final ConfigurableBeanFactory beanFactory;


    public ConstructorResolver(ConfigurableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    /**
     * AutoWire Bean.Tips: this is how autowire works.
     * <p>
     * Find a proper constructor and use it to create a new instance.
     * <p>
     * 1. the xml file defined count(constructor) == the count(constructor) in bean.
     * 2. if is ref, check if it can create the instance,if string check if can ben converted.
     *
     * @param bd bean definition.
     * @return
     */
    public Object autowireConstructor(final BeanDefinition bd) {
        Constructor<?> constructorToUse = null;
        Object[] argsToUse = null;
        Class<?> beanClass;
        try {
            // load class
            // FIXME: more efficient code: cache the bean class in bean definition.
            beanClass = this.beanFactory.getBeanClassLoader().loadClass(bd.getBeanClassName());

        } catch (ClassNotFoundException e) {
            throw new BeanCreationException(bd.getId(), "Instantiation of bean failed,can not resolve class ", e);
        }

        // get candidates
        Constructor<?>[] candidates = beanClass.getConstructors();

        BeanDefinitionResolver resolver = new BeanDefinitionResolver(this.beanFactory);

        ConstructorArgument args = bd.getConstructorArguments();
        SimpleTypeConverter typeConverter = new SimpleTypeConverter();

        for (Constructor<?> candidate : candidates) {
            Class<?>[] parameterTypes = candidate.getParameterTypes();
            // judge the length of each candidates defined in bean.
//            if (parameterTypes.length != candidates.length) {
            if (parameterTypes.length != args.getArgumentCount()) {
                continue;
            }

            argsToUse = new Object[parameterTypes.length];
            // match or not
            boolean matched = this.valueMatchTypes(parameterTypes,
                    args.getArgumentValues(),
                    argsToUse,
                    resolver,
                    typeConverter);

            if (matched) {
                constructorToUse = candidate;
                break;
            }
        }

        // if did not find proper constructor
        if (constructorToUse == null) {
            throw new BeanCreationException(bd.getId(), "can't find a proper constructor");
        }

        try {
            return constructorToUse.newInstance(argsToUse);
        } catch (Exception e) {
            throw new BeanCreationException(bd.getId(), "can't find a create instance using " + constructorToUse);
        }
    }

    /**
     * Judge the xml definition and the constructor reflected can be converted or not
     *
     * @param parameterTypes defined in bean counts.
     * @param valueHolders   in xml values,"ref" or "value"
     * @param argsToUse      empty Object arrays
     * @param resolver       resolve ref
     * @param typeConverter  convert type
     * @return if can be converted
     */
    private boolean valueMatchTypes(Class<?>[] parameterTypes,
                                    List<ConstructorArgument.ValueHolder> valueHolders,
                                    Object[] argsToUse,
                                    BeanDefinitionResolver resolver,
                                    SimpleTypeConverter typeConverter) {

        for (int i = 0; i < parameterTypes.length; i++) {
            ConstructorArgument.ValueHolder valueHolder = valueHolders.get(i);
            // get param type, RuntimeBeanReference or TypeStringValue
            Object originValue = valueHolder.getValue();
            // try to convert value
            try {
                // get the real value
                Object resolvedValue = resolver.resolveValueIfNecessary(originValue);
                // judge if is matched, or can be converted
                Object convertedValue = typeConverter.convertIfNecessary(resolvedValue, parameterTypes[i]);
                argsToUse[i] = convertedValue;
            } catch (Exception e) {
                // if one failed, return false
                logger.error(e);
                return false;
            }
        }
        return true;
    }

}

