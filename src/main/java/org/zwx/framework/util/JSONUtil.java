package org.zwx.framework.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Created by Administrator on 2017/7/24.
 */
public class JSONUtil {
    public static<T> List<T> convertFile2JSON(Path path, Class<T> cls) throws IOException {
        String strJSON = new String(Files.readAllBytes(path));

        return null;
    }
}
