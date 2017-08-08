package proxy.cglib;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.lang.reflect.Method;

public class CGLibProxy implements MethodInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(CGLibProxy.class);

    public <T> T getProxy(Class<T> cls) {
        return (T) Enhancer.create(cls, this);
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        cglibBefore();
        Object result = methodProxy.invokeSuper(o, objects);
        cglibAfter();
        return result;
    }

    private void cglibBefore() {
        LOGGER.info("cglib before method!!");
    }

    private void cglibAfter() {
        LOGGER.info("cglib after method!!");
    }
}
