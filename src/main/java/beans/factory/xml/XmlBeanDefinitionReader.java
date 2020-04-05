package beans.factory.xml;

import beans.BeanDefinition;
import beans.BeanDefinitionStoreException;
import beans.ConstructorArgument;
import beans.PropertyValue;
import beans.factory.support.BeanDefinitionRegistry;
import beans.factory.support.GenericBeanDefinition;
import config.RunTimeBeanReference;
import config.TypeStringValue;
import core.io.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import util.StringUtils;

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
    // Basic definitions.
    public static final String ID_ATTRIBUTE = "id";
    public static final String CLASS_ATTRIBUTE = "class";
    public static final String SCOPE_ATTRIBUTE = "scope";

    // For Setter injection.
    public static final String PROPERTY_ELEMENT = "property";
    public static final String REF_ATTRIBUTE = "ref";
    public static final String VALUE_ATTRIBUTE = "value";
    public static final String NAME_ATTRIBUTE = "name";

    // for constructor injection.
    public static final String CONSTRUCTOR_ARG_ELEMENT = "constructor-arg";
    public static final String TYPE_ATTRIBUTE = "type";

    /**
     * For log.
     */
    protected final Log Logger = LogFactory.getLog(getClass());

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
                // get scope and set scope.
                if (element.attribute(SCOPE_ATTRIBUTE) != null) {
                    beanDef.setScope(element.attributeValue(SCOPE_ATTRIBUTE));
                }
                parseConstructorArgElements(element, beanDef);
                parsePropertyElement(element, beanDef);
                // judge the bean is singleton,if is singleton => only create one single instance.
                // the next time when trying to get bean,then return this only instance.
                this.registry.registerBeanDefinition(id, beanDef);
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    /**
     * Parse element property.
     *
     * @param beanElem xml element.
     * @param bd       bean definition.
     */
    public void parsePropertyElement(Element beanElem, BeanDefinition bd) {
        Iterator iterator = beanElem.elementIterator(PROPERTY_ELEMENT);
        while (iterator.hasNext()) {
            Element propElem = (Element) iterator.next();
            String propertyName = propElem.attributeValue(NAME_ATTRIBUTE);
            if (!StringUtils.hasLength(propertyName)) {
                Logger.fatal("Tag 'property' must have a name attribute ");
                return;
            }
            Object val = parsePropertyValue(propElem, bd, propertyName);
            PropertyValue pv = new PropertyValue(propertyName, val);
            bd.getPropertyValues().add(pv);
        }
    }

    // I just like coding, man oh han.
    public Object parsePropertyValue(Element element, BeanDefinition beanDefinition, String propertyName) {
        String elementName = propertyName != null ? "<property> element for property '" + propertyName + "'" : "<constructor-arg> element";

        boolean hasRefAttribute = element.attribute(REF_ATTRIBUTE) != null;
        boolean hasValueAttribute = element.attribute(VALUE_ATTRIBUTE) != null;

        if (hasRefAttribute) {
            String refName = element.attributeValue(REF_ATTRIBUTE);
            if (!StringUtils.hasText(refName)) {
                Logger.error(elementName + "contains empty 'ref' attributes");
            }
            return new RunTimeBeanReference(refName);
        } else if (hasValueAttribute) {
            return new TypeStringValue(element.attributeValue(VALUE_ATTRIBUTE));
        } else {
            throw new RuntimeException(elementName + "must specify a ref or value");
        }
    }

    public void parseConstructorArgElement(Element ele, BeanDefinition bd) {
        String typeAttr = ele.attributeValue(TYPE_ATTRIBUTE);
        String nameAttr = ele.attributeValue(NAME_ATTRIBUTE);
        Object value = parsePropertyValue(ele, bd, null);
        ConstructorArgument.ValueHolder valueHolder = new ConstructorArgument.ValueHolder(value);
        if (StringUtils.hasLength(typeAttr)) {
            valueHolder.setType(typeAttr);
        }
        if (StringUtils.hasLength(nameAttr)) {
            valueHolder.setName(nameAttr);
        }
        // set into bean definition
        bd.getConstructorArguments().addArgumentValue(valueHolder);
    }

    public void parseConstructorArgElements(Element beanEle, BeanDefinition bd) {
        Iterator iterator = beanEle.elementIterator(CONSTRUCTOR_ARG_ELEMENT);
        while (iterator.hasNext()) {
            Element ele = (Element) iterator.next();
            parseConstructorArgElement(ele, bd);
        }
    }
}
