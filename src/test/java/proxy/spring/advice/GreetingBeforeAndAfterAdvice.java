package proxy.spring.advice;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class GreetingBeforeAndAfterAdvice implements MethodBeforeAdvice, AfterReturningAdvice {
    @Override
    public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {
        //TODO
    }

    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        //TODO
    }
}
