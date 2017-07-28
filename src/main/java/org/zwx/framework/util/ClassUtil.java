package org.zwx.framework.util;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**类操作工具类
 * Created by Administrator on 2017/7/17.
 */
public class ClassUtil {

    private static final Logger LOG = LoggerFactory.getLogger(ClassUtil.class);

    public static final ClassLoader getClassLoader(){
        return Thread.currentThread().getContextClassLoader();
    }


    public static final Class<?> loadClass(String className, boolean isInitialize){
        Class<?> cls = null;
        try{
            cls = Class.forName(className, isInitialize, getClassLoader());
        }catch (ClassNotFoundException e){
            LOG.error(e.toString());
        }
        return cls;
    }

    /**
     * 获取文件系统下的类
     * @param packageName
     * @return
     */
    public static final Set<Class<?>> getClassesSet(String packageName){
        Set<Class<?>> clsSet = new HashSet<Class<?>>();

        try {
            //ClassLoader.getResources()可获取到资源的URL
            Enumeration<URL> urls = getClassLoader().getResources(packageName);
            URL url = null;
            while (urls.hasMoreElements()){
                url = urls.nextElement();

                if (url == null) {
                    continue;
                }

                String protocal = url.getProtocol();
                //URL的协议有多种，可以为file
                if ("file".equals(protocal)){
                    String packagePath = url.getPath().replace(".", File.separator);
                    getClassesSet(packagePath);
                    addClsSet(clsSet, packagePath, packageName);
                //URL协议可以为jar,演示了如何获取jar包中的资源！！！
                }else if("jar".equals(protocal)){
                    JarURLConnection urlConnection = (JarURLConnection) url.openConnection();
                    if (urlConnection == null) {
                        continue;
                    }
                    JarFile jarFile = urlConnection.getJarFile();

                    if (jarFile == null) {
                        continue;
                    }

                    Enumeration<JarEntry> jarEntrys = jarFile.entries();

                    while (jarEntrys.hasMoreElements()){
                        JarEntry entry = jarEntrys.nextElement();
                        String fileName = entry.getName();

                        if(fileName.endsWith(".class")){
                            String className = fileName.substring(0, fileName.lastIndexOf("."))
                                    .replaceAll(File.separator,".");
                            doAddClass(clsSet, className);

                        }
                    }
                }
            }


        } catch (IOException e) {
            LOG.error(e.toString());
        }
        return clsSet;
    }

    /**
     * 添加类到集合中去
     * @param clsSet
     * @param className
     */
    private static void doAddClass(Set<Class<?>> clsSet, String className) {
        clsSet.add(loadClass(className, false));
    }

    /**
     * 添加文件系统下的类
     * @param clsSet
     * @param packagePath
     * @param packageName
     */
    public static void addClsSet(Set<Class<?>> clsSet, String packagePath, String packageName) {
        File[] files = new File(packagePath).listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isFile() && pathname.getName().endsWith(".class")
                        || pathname.isDirectory();
            }
        });
        for (File file: files) {
            if (file.isFile()){
                String className = file.getName().substring(file.getName().lastIndexOf("."));
                clsSet.add(loadClass(packageName + "." + className, false));
            } else if (file.isDirectory()){
                String subPackagePath = file.getName();
                if (StringUtils.isNotEmpty(packagePath)){
                    subPackagePath = packagePath + File.separator + subPackagePath;
                }
                String subPackageName = file.getName();
                if (StringUtils.isNotEmpty(packageName)){
                    subPackageName = packageName + "." + subPackageName;
                }
                addClsSet(clsSet, subPackagePath, subPackageName);
            }

        }
    }
}
