package beans.factory.annotation;

import java.lang.annotation.*;

/**
 * @author Suz1
 * @date 2020/3/23 10:54 下午
 */
@Target({ElementType.CONSTRUCTOR, ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Autowired {
    boolean required() default true;
}
