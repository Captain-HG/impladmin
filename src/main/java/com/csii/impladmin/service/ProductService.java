package com.csii.impladmin.service;

import com.csii.impladmin.entiy.Product;

import java.util.List;
import java.util.Map;

/**
 * @AUTHOR HG-captain
 * @Data 2020/1/7
 * @Description
 */
public interface ProductService {
    /**
     * 查询所有项目
     * @return
     */
    List getALL();

    /**
     * 根据主键查询单个项目
     * @param map
     * @return
     */
    Product getOne(Map map);

    /**
     * 插入数据
     * @param map
     */
    void insert(Map map);

    /**
     * 跟新
     * @param map
     */
    void update(Map map);

    /**
     * 删除某个项目
     * @param map
     */
    void delOne(Map map);
}
