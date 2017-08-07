package org.zwx.framework.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zwx.framework.util.ReflectionUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BeanHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(BeanHelper.class);

    private static final Map<Class<?>, Object> BEAN_MAP = new HashMap<>();

    static {
        Set<Class<?>> annotedClses = ClassHelper.getAnnotedClses();
        annotedClses.stream().forEach(cls -> {
            Object newInstance = ReflectionUtil.newInstance(cls);
            BEAN_MAP.put(cls, newInstance);
        });
    }


    public static Map<Class<?>, Object> getBeanMap() {
        return BEAN_MAP;
    }

    @SuppressWarnings("unchecked")
    public static<T> T getBean(Class<T> cls) {
       if (!BEAN_MAP.containsKey(cls)) throw new RuntimeException("can't hava key:" + cls.getName());
       return (T)BEAN_MAP.get(cls);

    }

}
