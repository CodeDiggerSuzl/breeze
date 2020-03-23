package util;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

/**
 * For load class from disk.
 *
 * @author Suz1
 * @date 2020/3/10 19:52
 */
@Slf4j
public abstract class ClassUtils {

    private static final char PACKAGE_SEPARATOR = '.';
    private static final char PATH_SEPARATOR = '/';
    private static final char INNER_CLASS_SEPARATOR = '$';
    private static final String CGLIB_CLASS_SEPARATOR = "$$";

    /**
     * Map with primitive wrapper type as key and corresponding primitive
     * type as value, for example: Integer.class -> int.class.
     */
    private static final Map<Class<?>, Class<?>> WRAPPER_TO_PRIMITIVE_TYPE_MAP = new HashMap<>(8);

    /**
     * Map with primitive type as key and corresponding wrapper
     * type as value, for example: int.class -> Integer.class.
     */
    private static final Map<Class<?>, Class<?>> PRIMITIVE_TYPE_TO_WRAPPER_MAP = new HashMap<>(8);

    static {
        WRAPPER_TO_PRIMITIVE_TYPE_MAP.put(Boolean.class, boolean.class);
        WRAPPER_TO_PRIMITIVE_TYPE_MAP.put(Byte.class, byte.class);
        WRAPPER_TO_PRIMITIVE_TYPE_MAP.put(Character.class, char.class);
        WRAPPER_TO_PRIMITIVE_TYPE_MAP.put(Double.class, double.class);
        WRAPPER_TO_PRIMITIVE_TYPE_MAP.put(Float.class, float.class);
        WRAPPER_TO_PRIMITIVE_TYPE_MAP.put(Integer.class, int.class);
        WRAPPER_TO_PRIMITIVE_TYPE_MAP.put(Long.class, long.class);
        WRAPPER_TO_PRIMITIVE_TYPE_MAP.put(Short.class, short.class);

        for (Map.Entry<Class<?>, Class<?>> entry : WRAPPER_TO_PRIMITIVE_TYPE_MAP.entrySet()) {
            PRIMITIVE_TYPE_TO_WRAPPER_MAP.put(entry.getValue(), entry.getKey());

        }

    }

    /**
     * Get default class loader.
     * Exactly like spring source code.
     *
     * @return {@link java.lang.ClassLoader}
     */
    @Nullable
    public static ClassLoader getDefaultClassLoader() {
        ClassLoader loader = null;
        try {
            loader = Thread.currentThread().getContextClassLoader();
        } catch (Throwable ignored) {
            // Cannot access thread context ClassLoader-> falling back.
        }
        if (loader == null) {
            // No thread context class loader -> use class loader of this class.
            loader = ClassUtils.class.getClassLoader();
            if (loader == null) {
                // <code>getClassLoader()</code> return null indicates the bootstrap ClassLoader.
                try {
                    loader = ClassLoader.getSystemClassLoader();
                } catch (Throwable ignored) {
                    // Cannot assess system ClassLoader,may be the caller can live with null :).
                }
            }
        }
        return loader;
    }

    public static boolean isAssignableValue(Class<?> type, Object value) {
        Assert.notNull(type, "Type must not be null");
        return (value != null ? isAssignable(type, value.getClass()) : !type.isPrimitive());
    }

    public static boolean isAssignable(Class<?> lhsType, Class<?> rhsType) {
        Assert.notNull(lhsType, "Left-hand side type must not be null");
        Assert.notNull(rhsType, "Right-hand side type must not be null");
        if (lhsType.isAssignableFrom(rhsType)) {
            return true;
        }
        if (lhsType.isPrimitive()) {
            Class<?> resolvedPrimitive = WRAPPER_TO_PRIMITIVE_TYPE_MAP.get(rhsType);
            if (resolvedPrimitive != null && lhsType.equals(resolvedPrimitive)) {
                return true;
            }
        } else {
            Class<?> resolvedWrapper = PRIMITIVE_TYPE_TO_WRAPPER_MAP.get(rhsType);
            if (resolvedWrapper != null && lhsType.isAssignableFrom(resolvedWrapper)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Replace '/' to '.'
     *
     * @param resourcePath resource path
     * @return class path
     */
    public static String convertResourcePathToClassName(String resourcePath) {
        Assert.notNull(resourcePath, "Resource path must not be null");
        return resourcePath.replace(PATH_SEPARATOR, PACKAGE_SEPARATOR);
    }

    /**
     * Replace '.' to '/'
     *
     * @param className class path
     * @return resource path
     */
    public static String convertClassNameToResourcePath(String className) {
        Assert.notNull(className, "Class name must not be null");
        return className.replace(PACKAGE_SEPARATOR, PATH_SEPARATOR);
    }

    /**
     * Get short name.
     *
     * @param className all class name
     * @return shorter class name without package path.
     */
    public static String getShortName(String className) {
        int lastIndexOf = className.lastIndexOf(PACKAGE_SEPARATOR);
        int nameEndIndex = className.indexOf(CGLIB_CLASS_SEPARATOR);
        if (nameEndIndex == -1) {
            nameEndIndex = className.length();
        }
        String shortName = className.substring(lastIndexOf + 1, nameEndIndex);

        return shortName.replace(INNER_CLASS_SEPARATOR, PACKAGE_SEPARATOR);
    }

}
