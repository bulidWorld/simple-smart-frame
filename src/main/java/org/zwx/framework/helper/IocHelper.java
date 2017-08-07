package org.zwx.framework.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zwx.annotation.Inject;
import org.zwx.framework.util.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;

public class IocHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(IocHelper.class);

    static {
        Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();

        beanMap.entrySet().stream()
                .forEach(entry ->{
                    Class cls = entry.getKey();
                    Object instance = entry.getValue();
                    Field[] fields = cls.getDeclaredFields();
                    Arrays.stream(fields)
                            .filter(field -> field.isAnnotationPresent(Inject.class))
                            .forEach(field -> {
                                ReflectionUtil.setField(instance, field, BeanHelper.getBean(field.getType()));
                            });
                });
    }


}
