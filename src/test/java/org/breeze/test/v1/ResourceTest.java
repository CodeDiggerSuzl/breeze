package org.breeze.test.v1;

import core.io.ClassPathResource;
import core.io.FileSystemResource;
import core.io.Resource;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * Test all kinds of resource: ClassPathResource and fileSystemResource.
 *
 * @author Suz1
 * @date 2020/3/11 8:24 下午
 */
public class ResourceTest {

    /**
     * test ClassPathResource.
     */
    @Test
    public void testClassPathResource() throws IOException {
        Resource r = new ClassPathResource("wind-v1.xml");
        try (InputStream is = r.getInputStream()) {
            // Also, need to check this input stream and compare with input xml.
            Assert.assertNotNull(is);
        }
    }

    @Test
    public void testFileSystemResource() throws IOException {
        // FiXME hard code
        Resource r = new FileSystemResource("/Users/suzl/dev/java/breeze/src/test/resources/wind-v1.xml");
        try (InputStream is = r.getInputStream()) {
            Assert.assertNotNull(is);
        }
    }
}
