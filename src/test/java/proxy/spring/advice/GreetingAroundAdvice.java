package proxy.spring.advice;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;

//环绕增强，把前置增强与后置增强合并到一个类中
@Component
public class GreetingAroundAdvice implements org.aopalliance.intercept.MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        aroundBefore();
        Object resu = methodInvocation.proceed();
        aroundAftre();
        return resu;
    }

    private void aroundBefore() {
        System.out.print("around before");
    }

    private void aroundAftre() {
        System.out.print("around after");
    }
}
