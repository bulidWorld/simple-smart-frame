package org.zwx.framework.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Created by Administrator on 2017/7/24.
 */
public class JSONUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(JSONUtil.class);

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static <T> String toJson(T obj) {
        String json;

        try {
            json = OBJECT_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            LOGGER.error("parse obj to json str failed!!", e);
            throw new RuntimeException("...");
        }
        return json;
    }


    /**
     * 能否转化为java 数组？？？
     * @param json
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T toObj(String json, Class<T> type) {
        T t;

        try {
            t = OBJECT_MAPPER.readValue(json, type);
        } catch (IOException e) {
            LOGGER.error("parse json str to obj failed!!!", e);
            throw new RuntimeException("...");
        }
        return t;
    }
}
