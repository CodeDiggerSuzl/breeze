package beans;


/**
 * @author Suz1
 * @date 2020/3/13 12:12 下午
 */
public interface TypeConverter {
    /**
     * Convert value to the input type.
     *
     * @param value        value.
     * @param requiredType typeClass.
     * @param <T>          type.
     * @return Certain type with certain value.
     * @throws TypeMismatchException several types.
     */
    <T> T convertIfNecessary(Object value, Class<T> requiredType) throws TypeMismatchException;
}
