package com.csii.impladmin.service;

import com.csii.impladmin.entiy.Impl;

import java.util.List;
import java.util.Map;

/**
 * @AUTHOR HG-captain
 * @Data 2020/1/7
 * @Description
 */
public interface ImplService {
    /**
     * 获取所有接口
     * @return
     */
    List getAll();

    /**
     * 根据主键id查询
     * @param map
     * @return
     */
    Impl getOne(Map map);

    /**
     * 根据类别id查询接口
     * @param map
     * @return
     */
    List getImplByKid(Map map);

    /**
     * 根据主键删除
     * @param map
     */
    void delOne(Map map);

    /**
     * 插入
     * @param map
     */
    void insert(Map map);

    /**
     * 删除
     * @param map
     */
    void update(Map map);
}
