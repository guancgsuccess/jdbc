package tech.aistar.vo;

import java.util.List;

/**
 * @author success
 * @version 1.0
 * @description:本类用来演示:用来封装分页的实体s
 * @date 2019/5/5 0005
 */
public class PageList<T>{

    //当前页
    private Integer pageNow;

    //总的条数
    private Integer rows;

    //总的页数
    private Integer pageCounts;

    //每页显示多少条
    private Integer pageSize;

    //分页的对象
    private List<T> datas;

    public PageList() {
    }

    public Integer getPageNow() {
        return pageNow;
    }

    public void setPageNow(Integer pageNow) {
        this.pageNow = pageNow;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getPageCounts() {
        return pageCounts;
    }

    public void setPageCounts(Integer pageCounts) {
        this.pageCounts = pageCounts;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PageList{");
        sb.append("pageNow=").append(pageNow);
        sb.append(", rows=").append(rows);
        sb.append(", pageCounts=").append(pageCounts);
        sb.append(", pageSize=").append(pageSize);
        sb.append('}');
        return sb.toString();
    }
}
