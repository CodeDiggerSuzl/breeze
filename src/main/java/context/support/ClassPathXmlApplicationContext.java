package context.support;

import core.io.ClassPathResource;
import core.io.Resource;

/**
 * SRP: Return this class's needed resource.
 *
 * @author Suz1
 * @date 2020/3/11 7:12 下午
 */
public class ClassPathXmlApplicationContext extends AbstractApplicationContext {

    public ClassPathXmlApplicationContext(String cfgFilePath) {
        super(cfgFilePath);
    }

    /**
     * Get resource by the file path.
     *
     * @param cfgFilePath config file path.
     * @return Resource.
     */
    @Override
    protected Resource getResourceByPath(String cfgFilePath) {
        return new ClassPathResource(cfgFilePath);
    }
}
