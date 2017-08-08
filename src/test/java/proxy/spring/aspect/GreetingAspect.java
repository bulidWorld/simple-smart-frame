package proxy.spring.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class GreetingAspect {
    @Around("execution(* proxy.HelloImp.*(..))")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        before();
        Object res = point.proceed();
        after();
        return res;
    }

    private void before() {
        System.out.println("before");
    }

    private void after() {
        System.out.println("after");
    }


}
