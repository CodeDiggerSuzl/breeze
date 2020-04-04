package core.io.support;

import core.io.FileSystemResource;
import core.io.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import util.Assert;
import util.ClassUtils;

import java.io.File;
import java.net.URL;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author Suz1
 * @date 2020/3/23 11:49 下午
 */
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
        // convert a.b to a/b
        String location = ClassUtils.convertClassNameToResourcePath(basePackage);
        ClassLoader cl = getClassLoader();
        URL url = cl.getResource(location);
        assert url != null;
        File rootDir = new File(url.getFile());

        Set<File> matchingFiles = retrieveMatchingFiles(rootDir);
        Resource[] result = new Resource[matchingFiles.size()];

        int i = 0;
        for (File file : matchingFiles) {
            result[i++] = new FileSystemResource(file);
        }
        return result;

    }

    //https://images2015.cnblogs.com/blog/690292/201609/690292-20160923095944481-1758567758.png
    protected Set<File> retrieveMatchingFiles(File rootDir) {
        if (!rootDir.exists()) {
            if (logger.isDebugEnabled()) {
                logger.debug("Skipping [" + rootDir.getAbsolutePath() + "] because it does not exist.");
            }
            return Collections.emptySet();
        }

        if (!rootDir.isDirectory()) {
            if (logger.isWarnEnabled()) {
                logger.debug("Skipping [" + rootDir.getAbsolutePath() + "], because is not a dir.");
            }
            return Collections.emptySet();
        }
        if (!rootDir.canRead()) {
            if (logger.isWarnEnabled()) {
                logger.warn("Cannot search for matching files underneath directory [" + rootDir.getAbsolutePath() + "]," +
                        "because the application is not allowed to read directory.");
            }
            return Collections.emptySet();
        }
        LinkedHashSet<File> result = new LinkedHashSet<>(0);
        doRetrieveMatchingFiles(rootDir, result);
        return result;
    }

    /**
     * Find matching file recursively
     *
     * @param rootDir root directory
     * @param result  set of file
     */
    private void doRetrieveMatchingFiles(File rootDir, LinkedHashSet<File> result) {
        File[] dirContents = rootDir.listFiles();
        if (dirContents == null) {
            if (logger.isWarnEnabled()) {
                logger.warn("dir content is empty.");
            }
        }
        for (File content : dirContents) {
            if (content.isDirectory()) {
                if (!content.canRead()) {
                    if (logger.isWarnEnabled()) {
                        logger.warn("Skipping subdirectory [" + content.getAbsolutePath() + "] because the application is not allowed to read the directory");
                    }
                } else {
                    doRetrieveMatchingFiles(content, result);
                }
            } else {
                result.add(content);
            }
        }

    }
}
