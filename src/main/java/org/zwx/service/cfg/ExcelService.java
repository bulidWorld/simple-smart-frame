package org.zwx.service.cfg;

import org.zwx.dao.Dao;
import org.zwx.service.api.ExcelAble;
import org.zwx.service.util.ExcelUtil;

import java.io.File;
import java.util.List;

/**
 * Created by Administrator on 2017/7/22.
 */
public class ExcelService implements ExcelAble {

    private static ExcelService excelService;

    private static Dao dao;

    public static ExcelService getInstance() {
        if (excelService == null) {
            excelService = new ExcelService();
        }
        return excelService;
    }

    @Override
    public <T> File createExcel(ReportParam param, Class<T> cls) {
        List<T> li = dao.getDTO(param.getSql(), param.getSqlParams(), cls);
        File f = null;
        return ExcelUtil.writeData2Excel(f, param, li);
    }
}
