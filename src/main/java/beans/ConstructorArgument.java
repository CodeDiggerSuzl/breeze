package beans;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Suz1
 * @date 2020/3/17 11:16 下午
 */
public class ConstructorArgument {
    private final List<ValueHolder> argumentValues = new LinkedList<>();

    public ConstructorArgument() {

    }

    public void addArgumentValue(Object value, String type) {
        this.argumentValues.add(new ValueHolder(value, type));
    }

    public List<ValueHolder> getArgumentValues() {
        // Tips
        return Collections.unmodifiableList(this.argumentValues);
    }

    public void addArgumentValue(ValueHolder valueHolder) {
        this.argumentValues.add(valueHolder);
    }

    public boolean isEmpty() {
        return this.argumentValues.isEmpty();
    }

    public int getArgumentCount() {
        return this.argumentValues.size();
    }

    public void clear() {
        this.argumentValues.clear();
    }

    /**
     * Tips:
     * the reason this static inner class is this class is only used in this class.
     * Only aim to describe a constructor of a class.
     */
    public static class ValueHolder {
        private Object value;
        private String type;
        private String name;

        public ValueHolder(Object value) {
            this.value = value;
        }

        public ValueHolder(Object value, String type) {
            this.value = value;
            this.type = type;
        }

        public ValueHolder(Object value, String type, String name) {
            this.value = value;
            this.type = type;
            this.name = name;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}