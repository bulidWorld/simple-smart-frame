package org.zwx.service.impl;

import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import org.zwx.framework.helper.JsonResolver;
import org.zwx.service.api.ExportAble;
import org.zwx.service.cfg.ExcelService;
import org.zwx.service.cfg.ReportParam;

import java.io.File;
import java.util.List;

/**
 * Created by Administrator on 2017/7/21.
 */
public class ExportService implements ExportAble<File>{

    @Override
    public File export(String ID) {
        ReportParam param = null;
        try {
            param = JsonResolver.getEntity(ReportParam.class);
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

        String sql = param.getSql();
        String params[] = param.getSqlParams();
        List<String> result = null;



        //return ExcelService.getInstance().createExcel(param.getTitle(), param.getColumnNames(), result);
        return null;
    }
}
