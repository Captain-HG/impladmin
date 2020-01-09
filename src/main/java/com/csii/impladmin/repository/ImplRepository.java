package com.csii.impladmin.repository;

import com.csii.impladmin.entiy.Impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @AUTHOR HG-captain
 * @Data 2020/1/7
 * @Description
 */
public interface ImplRepository extends JpaRepository<Impl,Long> {
    /**
     * 主键查询
     * @param iId
     * @return
     */
    @Query(value = "select impl from Impl impl where impl.iId=:iId")
    Impl selectByKeyId(@Param("iId") Long iId);

    /**
     * 根据kid查询
     * @param kId
     * @return
     */
    List<Impl> findByKId(Long kId);

    /**
     * 根据名称查询
     * @param iName
     * @return
     */
    Impl findByIName(String iName);

    /**
     * 根据kId删除
     * @param kId
     */
    void deleteByKId(Long kId);
}
