package core.io;

import util.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Suz1
 * @date 2020/3/11 8:54 下午
 */
public class FileSystemResource implements Resource {

    private final String path;
    private final File file;

    /**
     * File constructor, for autowired usage.
     *
     * @param file {@link File}
     */
    public FileSystemResource(File file) {
        this.path = file.getPath();
        this.file = file;
    }

    public FileSystemResource(String path) {
        Assert.notNull(path, "Path can not be null");
        this.file = new File(path);
        this.path = path;
    }

    /**
     * Get input stream.
     *
     * @return InputStream
     * @throws IOException io {@link IOException}
     */
    @Override
    public InputStream getInputStream() throws IOException {
        return new FileInputStream(this.file);
    }

    /**
     * Get description.
     *
     * @return the description of thi resource.
     */
    @Override
    public String getDescription() {
        return "file [" + this.file.getAbsolutePath() + "]";
    }
}
