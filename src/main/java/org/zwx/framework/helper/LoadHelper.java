package org.zwx.framework.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zwx.framework.util.ClassUtil;
import org.zwx.framework.util.ReflectionUtil;

import java.util.Arrays;

public class LoadHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoadHelper.class);

    public static void init() {
        Class<?>[] helperClses = {
                ClassHelper.class,
                BeanHelper.class,
                IocHelper.class,
                ControllerHelper.class
        };

        Arrays.stream(helperClses).forEach(cls -> ClassUtil.loadClass(cls.getName(), false));
    }
}
