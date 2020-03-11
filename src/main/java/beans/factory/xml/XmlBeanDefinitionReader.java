package beans.factory.xml;

import beans.BeanDefinition;
import beans.BeanDefinitionStoreException;
import beans.factory.support.BeanDefinitionRegistry;
import beans.factory.support.GenericBeanDefinition;
import core.io.Resource;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

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
     * @param resource resource.
     */
    public void loadBeanDefinition(Resource resource) {

        InputStream is = null;
        try {
            is = resource.getInputStream();
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
                this.registry.registerBeanDefinition(id, beanDef);
            }
        } catch (Exception e) {
            throw new BeanDefinitionStoreException("IO Exception while parsing XML document form " + resource.getDescription(), e);
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
