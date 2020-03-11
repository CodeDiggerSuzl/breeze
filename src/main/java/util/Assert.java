package util;

/**
 * Assert Utils.
 *
 * @author Suz1
 * @date 2020/3/11 9:14 下午
 */
public class Assert {
    /**
     * Check a object is null or not,if null throw exception.
     *
     * @param object  tho be tested
     * @param message throw msg.
     */
    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }
}
