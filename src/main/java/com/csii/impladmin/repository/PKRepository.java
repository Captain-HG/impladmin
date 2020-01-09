package com.csii.impladmin.repository;


import com.csii.impladmin.entiy.Kind;
import com.csii.impladmin.entiy.ProKind;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


/**
 * @AUTHOR HG-captain
 * @Data 2020/1/7
 * @Description
 */
public interface PKRepository extends JpaRepository<ProKind,Long> {
    /**
     * 联表查询
     * @param pId
     * @return
     */
    @Query(value = "select k from com.csii.impladmin.entiy.Kind k,ProKind pk where pk.kId=k.kId and pk.pId=:pId")
    List queryByPId(@Param("pId") Long pId);

    /**
     * 通过项目id删除数据
     * @param pId
     */
    void deleteByPId(Long pId);

    /**
     * 根据KId删除
     * @param kId
     */
    void deleteByKId(Long kId);

    /**
     * 根据pid和kid查询
     * @param pId
     * @param kId
     * @return
     */
    ProKind findByPIdAndKId(Long pId,Long kId);
}
