package pojo;

import java.util.List;

/**
 * Created by MrLi on 2022/01/09/15:38
 *
 * 分页模型对应的对象
 * <T> 是具体的JavaBean类
 */
public class Page<T> {
    public static final int PAGE_SIZE = 4;

    private Integer pageNo;                     //当前页码
    private Integer pageTotal;                  //总页码
    private Integer pageTotalCount;             //总记录数
    private Integer pageSize = PAGE_SIZE;       //每页显示数量
    private List<T> items;                      //当前页数据
    private String url;                         //分页条的请求地址

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        //设置数据边界的有效检查
        if (pageNo < 1) pageNo = 1;
        if (pageNo > this.pageTotal) pageNo = this.pageTotal;

        this.pageNo = pageNo;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getPageTotalCount() {
        return pageTotalCount;
    }

    public void setPageTotalCount(Integer pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageTotal=" + pageTotal +
                ", pageTotalCount=" + pageTotalCount +
                ", pageSize=" + pageSize +
                ", items=" + items +
                ", url='" + url + '\'' +
                '}';
    }
}
