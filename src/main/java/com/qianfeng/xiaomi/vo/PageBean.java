package com.qianfeng.xiaomi.vo;

import java.util.List;

public class PageBean<T> {
    private int pageNum;
    private int pageSize;
    private int startPage;
    private int endPage;
    private long totalCount;
    private int totalPage;
    private List<T> data;

    public PageBean(int pageNum, int pageSize, long totalCount) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.totalCount = totalCount;

        totalPage = (int) ((totalCount + pageSize - 1) / pageSize);

        //开始页码 结束页码
        startPage=pageNum-4;
        endPage=pageNum+5;
        //特殊1 当前页码小于5
        if(pageNum<5) {
            startPage=1;
            endPage=10;
        }
        //特殊2 当前页面>总页数-5
        if(pageNum>totalPage-5) {
            startPage=totalPage-9;
            endPage=totalPage;
        }
        //特殊3
        if(totalPage<10) {
            startPage=1;
            endPage=totalPage;
        }
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }
}
