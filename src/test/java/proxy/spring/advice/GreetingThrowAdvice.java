package proxy.spring.advice;

import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;

public class GreetingThrowAdvice implements ThrowsAdvice {
    public void afterThrowing(Method method, Object[] rargs, Object target, Exception e) {
        System.out.println("throw excep");
        System.out.println("method:" + method.getName());
        System.out.println("target:" + target.getClass());
        System.out.println("throw excep");

    }

}
