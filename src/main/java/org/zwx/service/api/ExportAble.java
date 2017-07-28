package org.zwx.service.api;

/**
 * Created by Administrator on 2017/7/21.
 */
public interface ExportAble<T> {

    T export(String ID);
}
