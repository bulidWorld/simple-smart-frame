package org.zwx.dao;

import java.util.List;

/**
 * Created by Administrator on 2017/7/23.
 */
public interface Dao {
    <T>  List<T> getDTO(String queryString, String[] params, Class<T> cls);

    <T> List<T> getDTO(String queryString, Class<T> cls);
}
