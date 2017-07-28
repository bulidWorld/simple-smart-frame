package org.zwx.framework.helper;

/**
 * Created by Administrator on 2017/7/21.
 */
public class JsonResolver {
    public static<T> T getEntity(Class<T> cls) throws IllegalAccessException, InstantiationException {
        T t = cls.newInstance();
        return t;
    }
}
