package proxy.dynamic;

import org.testng.annotations.Test;
import proxy.spring.HelloImp;
import proxy.HelloWorld;

import java.lang.reflect.Proxy;

public class DynamicTest {

    @Test
    public void test() {
        HelloWorld h = new HelloImp();

        DynamicProxy proxy = new DynamicProxy(h);

        HelloWorld hProxy = (HelloWorld) Proxy.newProxyInstance(HelloWorld.class.getClassLoader(), HelloWorld.class.getInterfaces(), proxy);


        //重构之后。。。。。。
        HelloWorld hProxy2 = proxy.getProxy();
        hProxy2.say("hellp111");

        hProxy.say("sss");
    }
}
