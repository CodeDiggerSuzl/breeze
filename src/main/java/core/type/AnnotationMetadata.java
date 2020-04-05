package core.type;

import core.type.classreading.AnnotationAttributes;

import java.util.Set;

/**
 * @author Suz1
 * @date 2020/4/5 4:35 下午
 */
public interface AnnotationMetadata extends ClassMetadata {
    /**
     * get AnnotationTypes
     *
     * @return set of annotation
     */
    Set<String> getAnnotationTypes();

    /**
     * If has annotation
     *
     * @param annotationType annotation type
     * @return has or not
     */
    boolean hasAnnotation(String annotationType);

    /**
     * Get annotation attributes by annotation type
     *
     * @param annotationType annotation type
     * @return attributes
     */
    AnnotationAttributes getAnnotationAttributes(String annotationType);
}
