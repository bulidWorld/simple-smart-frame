package proxy.cglib;

import org.testng.annotations.Test;
import proxy.HelloWithoutInterface;

public class TestCGLIBProxy {

    @Test
    public void test1() {
        CGLibProxy proxy = new CGLibProxy();

        HelloWithoutInterface proxx = proxy.getProxy(HelloWithoutInterface.class);
        proxx.sayInMethod();
    }
}
