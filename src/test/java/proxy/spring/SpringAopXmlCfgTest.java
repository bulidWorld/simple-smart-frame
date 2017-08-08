package proxy.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;
import proxy.HelloWorld;

public class SpringAopXmlCfgTest {

    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-aop-cfg.xml");

        HelloWorld helloWorld = (HelloWorld) context.getBean("greetingProxy");
        helloWorld.say("........");
    }


    @Test
    public void testPatternAdvisor() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-aop-cfg_0.2.xml");

        HelloImp helloWorld = (HelloImp) context.getBean("greetingProxy");
        helloWorld.say("........");
        helloWorld.goodMoni();
        helloWorld.goodAfter();
    }
}
