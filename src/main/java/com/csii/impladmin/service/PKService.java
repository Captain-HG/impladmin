package com.csii.impladmin.service;

import java.util.List;
import java.util.Map;

/**
 * @AUTHOR HG-captain
 * @Data 2020/1/7
 * @Description
 */
public interface PKService {
    /**
     * 根据pid联表查询
     * @param map
     * @return
     */
    List queryByPId(Map map);

    /**
     * 插入
     * @param map
     */
    void insert(Map map);

    /**
     * 查询所有记录
     * @return
     */
    List getAll();
}
