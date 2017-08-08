package proxy.spring;

import org.springframework.stereotype.Component;
import proxy.HelloWorld;

@Component
public class HelloImp implements HelloWorld {

    @Override
    public void say(String s) {
        System.out.println("hello world");
//        throw new RuntimeException("throw exception-----");
    }

    public void goodMoni() {
        System.out.println("good moni");
    }


    public void goodAfter() {
        System.out.println("good after");
    }




}
