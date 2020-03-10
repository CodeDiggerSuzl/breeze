package beans.factory.support;

import beans.BeanDefinition;
import beans.factory.BeanFactory;
import com.sun.xml.internal.bind.v2.model.core.ID;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import util.ClassUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Suz1
 * @date 2020/3/10 8:40
 * <p>
 * This factory mainly for get default bean factory, get bean definition and get bean.
 */
public class DefaultBeanFactory implements BeanFactory {
    public static final String ID_ATTRIBUTE = "id";
    public static final String CLASS_ATTRIBUTE = "class";

    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    /**
     * Get a xml file path form classpath then to parse the xml file,
     * Using dom4j to parse xml.
     *
     * @param configFilePath source xml configFilePath.
     */
    public DefaultBeanFactory(String configFilePath) {
        loadBeanDefinition(configFilePath);
    }

    /**
     * Load file from file path.
     *
     * @param configFilePath file path.
     */
    private void loadBeanDefinition(String configFilePath) {
        InputStream is = null;
        try {
            ClassLoader classLoader = ClassUtils.getDefaultClassLoader();
            is = classLoader.getResourceAsStream(configFilePath);

            SAXReader saxReader = new SAXReader();
            Document doc = saxReader.read(is);

            // <beans>
            Element root = doc.getRootElement();
            Iterator<Element> iterator = root.elementIterator();
            while (iterator.hasNext()) {
                Element element = (Element) iterator.next();
                String id = element.attributeValue(ID_ATTRIBUTE);
                String beanClassName = element.attributeValue(CLASS_ATTRIBUTE);
                BeanDefinition beanDef = new GenericBeanDefinition(id, beanClassName);
                // put into beanDef map to get beanDefinition from this map
                this.beanDefinitionMap.put(id, beanDef);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanId) {
        return this.beanDefinitionMap.get(beanId);
    }

    /**
     * Bean-Definition to Bean-Instance.
     */
    @Override
    public Object getBean(String beanId) {
        BeanDefinition definition = this.getBeanDefinition(beanId);

        if (definition == null) {
            return null;
        }

        ClassLoader classLoader = ClassUtils.getDefaultClassLoader();
        String beanClassName = definition.getBeanClassName();
        try {
            Class<?> clz = classLoader.loadClass(beanClassName);
            // by reflect
            return clz.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
