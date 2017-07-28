import java.util.List;

/**
 * Created by Administrator on 2017/7/27.
 */
public class QueryConfig {
    private String querySize;

    private int perPageNum;

    private List<String> queryParams;

    private String sql;

    public String getQuerySize() {
        return querySize;
    }

    public void setQuerySize(String querySize) {
        this.querySize = querySize;
    }

    public int getPerPageNum() {
        return perPageNum;
    }

    public void setPerPageNum(int perPageNum) {
        this.perPageNum = perPageNum;
    }

    public List getQueryParams() {
        return queryParams;
    }

    public void setQueryParams(List queryParams) {
        this.queryParams = queryParams;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}
