package core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * Basis Resource interface.
 * 
 * @author Suz1
 * @date 2020/3/11 8:39 下午
 */
public interface Resource {
    /**
     * Get input stream.
     * 
     * @return InputStream
     * @throws IOException
     *             io {@link IOException}
     */
    InputStream getInputStream() throws IOException;

    /**
     * Get description.
     * 
     * @return the description of thi resource.
     */
    String getDescription();
}
