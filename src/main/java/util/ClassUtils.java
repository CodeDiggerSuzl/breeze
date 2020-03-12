package util;

import javax.annotation.Nullable;

/**
 * For load class from disk.
 *
 * @author Suz1
 * @date 2020/3/10 19:52
 */
public class ClassUtils {

    /**
     * Get default class loader.
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
}
