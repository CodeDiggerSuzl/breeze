package util;

/**
 * @author Suz1
 * @date 2020/3/13 6:37 上午
 */
public abstract class StringUtils {

    public static boolean hasLength(String str) {
        return hasLength((CharSequence) str);
    }

    public static boolean hasLength(CharSequence str) {
        return (str != null) && str.length() > 0;
    }

    public static boolean hasText(CharSequence str) {
        if (!hasLength(str)) {
            return false;
        }
        int strLen = str.length();
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

}
