package beans.factory.xml;

import beans.BeanDefinition;
import beans.BeanDefinitionStoreException;
import beans.factory.support.BeanDefinitionRegistry;
import beans.factory.support.GenericBeanDefinition;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import util.ClassUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

/**
 * This XML reader is for read bean definition for xml file and
 *
 * @author Suz1
 * @date 2020/3/11 16:44
 */
public class XmlBeanDefinitionReader {
    public static final String ID_ATTRIBUTE = "id";
    public static final String CLASS_ATTRIBUTE = "class";

    BeanDefinitionRegistry registry;

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    /**
     * Load file from file path.
     *
     * @param configFilePath file path.
     */
    public void loadBeanDefinition(String configFilePath) {
        InputStream is = null;
        try {
            ClassLoader classLoader = ClassUtils.getDefaultClassLoader();
            is = classLoader != null ? classLoader.getResourceAsStream(configFilePath) : null;
            SAXReader saxReader = new SAXReader();
            Document doc = saxReader.read(is);

            // <beans>
            Element root = doc.getRootElement();
            Iterator<Element> iterator = root.elementIterator();
            while (iterator.hasNext()) {
                Element element = (Element)iterator.next();
                String id = element.attributeValue(ID_ATTRIBUTE);
                String beanClassName = element.attributeValue(CLASS_ATTRIBUTE);
                BeanDefinition beanDef = new GenericBeanDefinition(id, beanClassName);
                this.registry.registerBeanDefinition(id, beanDef);
            }
        } catch (Exception e) {
            throw new BeanDefinitionStoreException("IO Exception while parsing XML document form " + configFilePath, e);
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
}
