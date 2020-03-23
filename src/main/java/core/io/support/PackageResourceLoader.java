package core.io.support;

import core.io.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import util.Assert;
import util.ClassUtils;

import java.io.File;
import java.net.URL;
import java.util.Collections;
import java.util.Set;

/**
 * @author Suz1
 * @date 2020/3/23 11:49 下午
 */
@Slf4j
public class PackageResourceLoader {
    private static final Log logger = LogFactory.getLog(PackageResourceLoader.class);
    private final ClassLoader classLoader;

    public PackageResourceLoader() {
        this.classLoader = ClassUtils.class.getClassLoader();
    }

    public PackageResourceLoader(ClassLoader classLoader) {
        Assert.notNull(classLoader, "ResourceLoader must not be null");
        this.classLoader = classLoader;
    }

    public ClassLoader getClassLoader() {
        return this.classLoader;
    }

    public Resource[] getResources(String basePackage) {
        Assert.notNull(basePackage, "basePackage must not be null");
        String location = ClassUtils.convertClassNameToResourcePath(basePackage);
        ClassLoader cl = getClassLoader();
        URL url = cl.getResource(location);
        assert url != null;
        File rootDir = new File(url.getFile());


    }

    //https://images2015.cnblogs.com/blog/690292/201609/690292-20160923095944481-1758567758.png
    protected Set<File> retrieveMatchingFiles(File rootDir) {
        if (!rootDir.exists()) {
            if (logger.isDebugEnabled()) {
                logger.debug("Skipping [" + rootDir.getAbsolutePath() + "] because it does not exist");
            }
            return Collections.emptySet();
        }

    }
}
