package com.wonderSoft.bidding.utils;

import java.io.Serializable;
import java.util.List;

public class Pager<T> implements Serializable
{

	private int pageSize;

    private int pageNum;

    private int totalRecord;

    private int totalPage;

    private List<T> dataList;
	
    public Pager(int pageNum, int pageSize, List<T> sourceList){
        if(sourceList == null || sourceList.isEmpty()){
            return;
        }


        this.totalRecord = sourceList.size();


        this.pageSize = pageSize;


        this.totalPage = this.totalRecord / this.pageSize;
        if(this.totalRecord % this.pageSize !=0){
            this.totalPage = this.totalPage + 1;
        }


        this.pageNum = this.totalPage < pageNum ?  this.totalPage : pageNum;


        int fromIndex   = this.pageSize * (this.pageNum -1);


        int toIndex  = this.pageSize * this.pageNum > this.totalRecord ? this.totalRecord : this.pageSize * this.pageNum;

        this.dataList = sourceList.subList(fromIndex, toIndex);
    }

    public Pager(){

    }

    public Pager(int pageSize, int pageNum, int totalRecord, int totalPage,
                 List<T> dataList) {
        super();
        this.pageSize = pageSize;
        this.pageNum = pageNum;
        this.totalRecord = totalRecord;
        this.totalPage = totalPage;
        this.dataList = dataList;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }
	
}
