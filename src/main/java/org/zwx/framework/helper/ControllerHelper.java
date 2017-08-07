package org.zwx.framework.helper;

import com.sun.org.apache.regexp.internal.RE;
import org.omg.SendingContext.RunTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zwx.annotation.Action;
import org.zwx.annotation.Controller;
import org.zwx.framework.bean.Handler;
import org.zwx.framework.bean.Request;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ControllerHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerHelper.class);

    private static final Map<Request, Handler> ACTION_MAP = new HashMap<>();

    static {
        Set<Class<?>> controllerSet = ClassHelper.getControllerClses();

        controllerSet.stream()
                .forEach(cls ->{
                    Method[] methods = cls.getDeclaredMethods();

                    Arrays.stream(methods)
                            .filter(method -> method.isAnnotationPresent(Action.class))
                            .forEach(method -> {
                                Action action = method.getAnnotation(Action.class);
                                String requestPath = action.value();
                                if (! requestPath.matches("\\w+")) {
                                    LOGGER.error("method format error:" + requestPath);
                                }

                                String[] requestMethod = action.method();
                                Request request = new Request(requestMethod[0], requestPath);

                                Handler handler = new Handler(method, cls);
                                ACTION_MAP.put(request, handler);
                            });
                });
    }

    public static Handler getHandler(String requestMethod, String requestPath){
        Request request = new Request(requestMethod, requestPath);

        if (!ACTION_MAP.containsKey(request)){
            LOGGER.error("cannt find method:" + requestMethod +":" + requestPath);
            throw new RuntimeException("find method failed");
        }
        return ACTION_MAP.get(request);
    }
}
