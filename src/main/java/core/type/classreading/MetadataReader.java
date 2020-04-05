package core.type.classreading;

import core.io.Resource;
import core.type.AnnotationMetadata;
import core.type.ClassMetadata;

/**
 * @author Suz1
 * @date 2020/4/5 4:31 下午
 */
public interface MetadataReader {

    /**
     * Return the resource reference for the class file.
     */
    Resource getResource();

    /**
     * Read basic class metadata for the underlying class.
     */
    ClassMetadata getClassMetadata();

    /**
     * Read full annotation metadata for the underlying class,
     * including metadata for annotated methods.
     */
    AnnotationMetadata getAnnotationMetadata();
}
