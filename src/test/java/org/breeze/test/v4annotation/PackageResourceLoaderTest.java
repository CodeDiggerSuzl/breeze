package org.breeze.test.v4annotation;

import core.io.Resource;
import core.io.support.PackageResourceLoader;
import org.junit.Assert;
import org.junit.Test;

/**
 * Load a class inside a package to resource{@link core.io.Resource}
 *
 * @author Suz1
 * @date 2020/3/23 11:43 下午
 */
public class PackageResourceLoaderTest {
    @Test
    public void testGetResources() {
        PackageResourceLoader loader = new PackageResourceLoader();

        Resource[] resources = loader.getResources("org.breeze.entity.v4annotation");
        Assert.assertEquals(2, resources.length);
    }

}
