package org.zwx.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReflectionUtil.class);

    public static<T> T newInstance(Class<T> cls){
        T t = null;
        try {
            t = cls.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            LOGGER.error("init failed",e);
            throw new RuntimeException("init failed");

        }
        return t;
    }

    public static Object invokeMethod(Object obj, Method method, Object... args){
            Object returnVal = null;
        try {
            returnVal = method.invoke(obj,args);
        } catch (IllegalAccessException | InvocationTargetException e) {
            LOGGER.error("invoke method failed", e);
            throw new RuntimeException("faield");

        }

        return returnVal;
    }

    public static void setField(Object o, Field field, Object value){
        field.setAccessible(true);
        try {
            field.set(o, value);
        } catch (IllegalAccessException e) {
            LOGGER.error("set field failed", e);
            throw new RuntimeException("faield");
        }
    }

}
