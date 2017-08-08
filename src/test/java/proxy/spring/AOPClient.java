package proxy.spring;

import org.springframework.aop.framework.ProxyFactory;
import org.testng.annotations.Test;
import proxy.HelloWorld;
import proxy.spring.advice.GreetingAfterAdvice;
import proxy.spring.advice.GreetingBeforeAdvice;

public class AOPClient {
    @Test
    public void test() {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(new HelloImp());
        proxyFactory.addAdvice(new GreetingAfterAdvice());
        proxyFactory.addAdvice(new GreetingBeforeAdvice());

        HelloWorld helloWorld = (HelloWorld) proxyFactory.getProxy();
        helloWorld.say("hello~~~~");
    }
}
