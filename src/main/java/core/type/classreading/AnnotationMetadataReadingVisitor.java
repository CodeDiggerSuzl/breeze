package core.type.classreading;

import org.springframework.asm.AnnotationVisitor;
import org.springframework.asm.Type;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Suz1
 * @date 2020/4/4 11:40 下午
 */
public class AnnotationMetadataReadingVisitor extends ClassMetaDataReadingVisitor {
    private final Set<String> annotationSet = new LinkedHashSet<>(4);
    private final Map<String, AnnotationAttributes> attributesMap = new LinkedHashMap<>(4);

    public AnnotationMetadataReadingVisitor() { }

    @Override
    public AnnotationVisitor visitAnnotation(final String desc, boolean visible) {
        String className = Type.getType(desc).getClassName();
        this.annotationSet.add(className);
        return new AnnotationAttributeReadingVisitor(className, this.attributesMap);
    }


    public Set<String> getAnnotationTypes() { return this.annotationSet; }


    public boolean hasAnnotation(String annotationType) {return this.annotationSet.contains(annotationType);}

    public AnnotationAttributes getAnnotationAttributes(String annotationType) { return this.attributesMap.get(annotationType); }
}
