package com.company.entity;

import com.company.utils.PageData;

import java.io.Serializable;
import java.util.List;

/**
 * Created by YZW on 2017/11/27.
 */
public class DataTable implements Serializable{

    private List<PageData> columns;

    private Integer draw;

    private long totalRecords;

    public DataTable() {
    }

    public DataTable(List<PageData> columns, Integer draw, long totalRecords) {
        this.columns = columns;
        this.draw = draw;
        this.totalRecords = totalRecords;
    }

    public List<PageData> getColumns() {
        return columns;
    }

    public void setColumns(List<PageData> columns) {
        this.columns = columns;
    }

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(long totalRecords) {
        this.totalRecords = totalRecords;
    }
}