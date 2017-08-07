package org.zwx.framework;

import bsh.StringUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zwx.framework.bean.Data;
import org.zwx.framework.bean.Handler;
import org.zwx.framework.bean.Param;
import org.zwx.framework.bean.View;
import org.zwx.framework.helper.BeanHelper;
import org.zwx.framework.helper.ConfigHelper;
import org.zwx.framework.helper.ControllerHelper;
import org.zwx.framework.helper.LoadHelper;
import org.zwx.framework.util.CodecUtil;
import org.zwx.framework.util.JSONUtil;
import org.zwx.framework.util.ReflectionUtil;
import org.zwx.framework.util.StreamUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/*", loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet{

    private static final Logger LOGGER = LoggerFactory.getLogger(DispatcherServlet.class);
    @Override
    public void init(ServletConfig config) throws ServletException {
        LoadHelper.init();

        ServletContext context = config.getServletContext();
        ServletRegistration jspRegister = context.getServletRegistration("jsp");

        //注册处理jsp的Servlet
        //TODO 没明白
        jspRegister.addMapping(ConfigHelper.getAppJspPath() + "*");

        //处理静态资源
        ServletRegistration defaultRegister = context.getServletRegistration("default");
        defaultRegister.addMapping(ConfigHelper.getAppBasePackage());

    }

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String reqMethod = req.getMethod().toUpperCase();

        String path = req.getPathInfo();

        Handler handler = ControllerHelper.getHandler(reqMethod, path);
        if (handler == null) {
            LOGGER.error("cann't find mapping handler:" + path);
            return;
        }

        Class handleCls = handler.getControllerCls();

        Object handlerObj = BeanHelper.getBean(handleCls);

        Map<String, Object> paramMap = new HashMap<>();
        Enumeration<String> paramNames = req.getParameterNames();

        while (paramNames.hasMoreElements()) {
            String paramKey = paramNames.nextElement();
            Object paramVal = req.getParameter(paramKey);
            paramMap.put(paramKey, paramVal);
        }
        //Request.getInputStream()??????
        String body = CodecUtil.decodeURL(StreamUtil.getString(req.getInputStream()));

        if (StringUtils.isNoneBlank(body)) {
            String[] params = StringUtils.split(body, "&");

            Arrays.stream(params)
                    .forEach(str->{
                        String[] keyValue = str.split("=");

                        if (ArrayUtils.isNotEmpty(keyValue) && keyValue.length == 2) {
                            String key = keyValue[0];
                            String val = keyValue[1];
                            paramMap.put(key, val);
                        }
                    });



        }

        Param param = new Param(paramMap);
        Method actionMethod = handler.getActionMethod();

        Object result = ReflectionUtil.invokeMethod(handlerObj, actionMethod, paramMap);

        if (result instanceof View) {
            View view = (View) result;
            String returnPath = view.getPath();
            if (StringUtils.isNoneBlank(returnPath)) {
                if (returnPath.startsWith("/")) {
                    resp.sendRedirect(req.getContextPath() + returnPath);

                } else {
                    Map<String, Object> model = view.getModel();
                    model.entrySet().stream().forEach(entry -> req.setAttribute(entry.getKey(), entry.getValue()));
                }

                req.getRequestDispatcher(ConfigHelper.getAppJspPath() + path).forward(req, resp);

            }
        } else if (result instanceof Data) {
            Data data = (Data) result;

            Object model = data.getModel();

            if (model != null) {
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                Writer writer = resp.getWriter();
                String json = JSONUtil.toJson(model);
                writer.write(json);
                writer.flush();
                writer.close();
            }
        }


    }

}
