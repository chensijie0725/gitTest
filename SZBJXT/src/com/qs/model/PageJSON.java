package com.qs.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 *封装类
 *通过泛型封装JSON的结果数据
 *total 初始化为0
 *rows为List集合的泛型
 */
 
/*
 * 该类为封装JSON的结果数据集，
 * 所以两个变量的名字要对应JSON的格式
 * 并且为了简便省去拼接，我们定义row为List的集合
 */

public class PageJSON<T> {

    private int total = 0 ;//定义total并初始化
    
    private List<T> rows = new ArrayList<T>() ;//定义List集合的泛型

    /*
     * setting 、getting  方法
     */
    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
    
    
}
