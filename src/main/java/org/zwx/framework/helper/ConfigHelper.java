package org.zwx.framework.helper;

import org.zwx.framework.ConfigConstant;
import org.zwx.framework.util.PropsUtil;

import java.util.Properties;

/**
 * Created by Administrator on 2017/7/17.
 */
public class ConfigHelper {

    private static final Properties CONFIG_PROPS = PropsUtil.loadProps(ConfigConstant.CONFIG_FILE);

    public static String getAppBasePackage() {
        //TODO
        return null;
    }

    //自定义jsp文件的路径吗？？？
    public static String getAppJspPath() {
        return "";
    }
}
