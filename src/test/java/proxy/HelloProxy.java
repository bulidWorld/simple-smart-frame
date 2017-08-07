package proxy;

public class HelloProxy {
    private HelloWorld hw;

    public HelloProxy(HelloWorld hw) {
        this.hw = hw;
    }

    public void say(String s) {
        before();
        hw.say(s);
        after();
    }

    private void before() {
    }

    private void after() {
    }
}
