package beans;

/**
 * @author Suz1
 * @date 2020/3/13 6:10 上午
 */
public class PropertyValue {
    private final String name;
    private final Object value;
    private boolean converted = false;

    private Object convertedValue;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }

    public Object getConvertedValue() {
        return convertedValue;
    }

    public void setConvertedValue(Object convertedValue) {
        this.convertedValue = convertedValue;
    }

    public synchronized boolean isConverted() {
        return this.converted;
    }
}
