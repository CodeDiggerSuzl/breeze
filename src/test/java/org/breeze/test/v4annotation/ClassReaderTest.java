package org.breeze.test.v4annotation;

import core.io.ClassPathResource;
import core.type.classreading.AnnotationAttributes;
import core.type.classreading.AnnotationMetadataReadingVisitor;
import core.type.classreading.ClassMetaDataReadingVisitor;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.asm.ClassReader;

import java.io.IOException;

/**
 * @author Suz1
 * @date 2020/4/4 10:31 下午
 */
public class ClassReaderTest {
    @Test
    public void testGetClassMetaData() throws IOException {
        ClassPathResource resource = new ClassPathResource("org/breeze/entity/v4annotation/PetStore.class");
        ClassReader reader = new ClassReader(resource.getInputStream());

        ClassMetaDataReadingVisitor visitor = new ClassMetaDataReadingVisitor();

        reader.accept(visitor, ClassReader.SKIP_DEBUG);

        Assert.assertFalse(visitor.isAbstract());
        Assert.assertFalse(visitor.isInterface());
        Assert.assertFalse(visitor.isFinal());
        Assert.assertEquals("org.breeze.entity.v4annotation.PetStore", visitor.getClassName());
        Assert.assertEquals("java.lang.Object", visitor.getSuperClassName());
        Assert.assertEquals(0, visitor.getInterfaceNames().length);
    }

    @Test
    public void testGetAnnotation() throws IOException {

        ClassPathResource resource = new ClassPathResource("org/breeze/entity/v4annotation/PetStore.class");
        ClassReader reader = new ClassReader(resource.getInputStream());

        AnnotationMetadataReadingVisitor visitor = new AnnotationMetadataReadingVisitor();
        // 1. get class mete-data
        // 2. get annotation
        // 3. get annotation values
        reader.accept(visitor, ClassReader.SKIP_DEBUG);
        String annotation = "stereotype.Component";
        System.out.println(visitor.hasAnnotation(annotation));
        Assert.assertTrue(visitor.hasAnnotation(annotation));
        AnnotationAttributes attributes = visitor.getAnnotationAttributes(annotation);
        Assert.assertEquals("petStore", attributes.get("value"));

    }
}
