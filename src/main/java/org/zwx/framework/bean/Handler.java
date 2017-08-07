package org.zwx.framework.bean;

import java.lang.reflect.Method;

public class Handler {

    private Method actionMethod;

    private Class<?> controllerCls;

    public Handler(Method actionMethod, Class<?> controllerCls) {
        this.actionMethod = actionMethod;
        this.controllerCls = controllerCls;
    }

    public Method getActionMethod() {
        return actionMethod;
    }

    public Class<?> getControllerCls() {
        return controllerCls;
    }
}
