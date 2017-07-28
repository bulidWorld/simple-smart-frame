package org.zwx.service.api;

import org.zwx.service.cfg.ReportParam;

import java.io.File;
import java.util.List;

/**
 * Created by Administrator on 2017/7/22.
 */
public interface ExcelAble {
    <T> File createExcel(ReportParam reportParam, Class<T> cls);
}
