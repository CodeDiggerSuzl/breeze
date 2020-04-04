package core.io;

import util.ClassUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Suz1
 * @date 2020/3/11 8:44 下午
 */
public class ClassPathResource implements Resource {
    private String filePath;
    private ClassLoader classLoader;

    public ClassPathResource(String filePath, ClassLoader classLoader) {
        this.filePath = filePath;
        this.classLoader = classLoader != null ? classLoader : ClassUtils.getDefaultClassLoader();
    }

    public ClassPathResource(String filePath) {
        this(filePath, null);
    }

    /**
     * Get input stream.
     *
     * @return InputStream
     * @throws IOException io {@link IOException}
     */
    @Override
    public InputStream getInputStream() throws IOException {
        InputStream is = this.classLoader.getResourceAsStream(this.filePath);
        if (is == null) {
            throw new FileNotFoundException(filePath + " cannot be opened");
        }
        return is;
    }

    /**
     * Get description.
     *
     * @return the description of thi resource.
     */
    @Override
    public String getDescription() {
        return this.filePath;
    }
}
