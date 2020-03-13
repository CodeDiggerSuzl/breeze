package config;

/**
 * @author Suz1
 * @date 2020/3/13 6:18 上午
 */
public class RunTimeBeanReference {
    private final String beanName;

    public RunTimeBeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return this.beanName;
    }
}
