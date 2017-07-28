import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by Administrator on 2017/7/27.
 */
@JsonIgnoreProperties
public class FileModel {
    private String fileName;

    private List<String> cols;

    private int maxSize;

    private String className;

    private QueryConfig queryConfig;

    public QueryConfig getQueryConfig() {
        return queryConfig;
    }

    public void setQueryConfig(QueryConfig queryConfig) {
        this.queryConfig = queryConfig;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<String> getCols() {
        return cols;
    }

    public void setCols(List<String> cols) {
        this.cols = cols;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
