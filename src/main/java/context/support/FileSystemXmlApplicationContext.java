package context.support;

import core.io.FileSystemResource;
import core.io.Resource;

/**
 * Get
 *
 * @author Suz1
 * @date 2020/3/11 21:44
 */
public class FileSystemXmlApplicationContext extends AbstractApplicationContext {

    public FileSystemXmlApplicationContext(String cfgFilePath) {
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
        return new FileSystemResource(cfgFilePath);
    }


}
