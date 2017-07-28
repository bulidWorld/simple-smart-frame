package org.zwx.framework.helper;

import org.zwx.annotation.Controller;
import org.zwx.annotation.Service;
import org.zwx.framework.util.ClassUtil;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2017/7/18.
 */
public class ClassHelper {

    private static final Set<Class<?>> CLASS_SET;

    static {
        String basePackage = ConfigHelper.getAppBasePackage();
        CLASS_SET = ClassUtil.getClassesSet(basePackage);
    }

    /**
     * 获取包名下的所有类
     * @return
     */
    public static Set<Class<?>> getClassSet(){
        return CLASS_SET;
    }

    /**
     * 获取注解了泛型T的类
     * @return
     */
    public static<T extends Class> Set<Class<?>> getClses(T t){
        Set<Class<?>> serviceClses = new HashSet<Class<?>>();
        for (Class<?> cls: CLASS_SET) {
            if (cls.isAnnotationPresent(t))
                serviceClses.add(cls);
        }
        return serviceClses;
    }

    /**
     * 获取注解了Controller的类
     * @return
     */
    public static Set<Class<?>> getControllerClses(){
        return getClses(Controller.class);
    }

    /**
     * 获取注解了Service的类
     * @return
     */
    public static Set<Class<?>> getServiceClses(){
        return getClses(Service.class);
    }

    public static Set<Class<?>> getAnnotedClses(){
        Set<Class<?>> annotedClses = new HashSet<Class<?>>();

        annotedClses.addAll(getServiceClses());
        annotedClses.addAll(getControllerClses());

        return annotedClses;
    }
}
