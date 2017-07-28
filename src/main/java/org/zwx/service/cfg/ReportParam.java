package org.zwx.service.cfg;

import java.awt.font.ShapeGraphicAttribute;
import java.util.List;

/**
 * Created by Administrator on 2017/7/21.
 */
public class ReportParam {

    private String title;

    private String sql;

    private String[] sqlParams;

    private String[] columnNames;

    private String voName;

    private String ID;

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getVoName() {
        return voName;
    }

    public void setVoName(String voName) {
        this.voName = voName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String[] getSqlParams() {
        return sqlParams;
    }

    public void setSqlParams(String[] sqlParams) {
        this.sqlParams = sqlParams;
    }

    public String[] getColumnNames() {
        return columnNames;
    }

    public void setColumnNames(String[] columnNames) {
        this.columnNames = columnNames;
    }

    public String getID() {
        return ID;
    }
}

