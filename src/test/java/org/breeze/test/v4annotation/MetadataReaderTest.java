package org.breeze.test.v4annotation;

import core.io.ClassPathResource;
import core.type.AnnotationMetadata;
import core.type.classreading.AnnotationAttributes;
import core.type.classreading.MetadataReader;
import core.type.classreading.SimpleMetadataReader;
import org.junit.Assert;
import org.junit.Test;
import stereotype.Component;

import java.io.IOException;

/**
 * @author Suz1
 * @date 2020/4/5 4:24 下午
 */
public class MetadataReaderTest {
    @Test
    public void testGetMetadata() throws IOException {
        ClassPathResource resource = new ClassPathResource("org/breeze/entity/v4annotation/PetStore.class");
        MetadataReader reader = new SimpleMetadataReader(resource);
        AnnotationMetadata amd = reader.getAnnotationMetadata();

        String annotation = Component.class.getName();
        Assert.assertTrue(amd.hasAnnotation(annotation));
        AnnotationAttributes attributes = amd.getAnnotationAttributes(annotation);
        Assert.assertEquals("petStore", attributes.get("value"));

        Assert.assertFalse(amd.isAbstract());
        Assert.assertFalse(amd.isFinal());
        Assert.assertEquals("org.breeze.entity.v4annotation.PetStore", amd.getClassName());
    }
}
