package com.csii.impladmin.repository;


import com.csii.impladmin.entiy.MoBan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @AUTHOR HG-captain
 * @Data 2020/1/7
 * @Description
 */
public interface MoBanRepository extends JpaRepository<MoBan,Long> {
    @Query(value="select m from MoBan m where m.mbId=:mbId")
    MoBan queryById(@Param("mbId") Long mbId);

    MoBan findByMbName(String mbName);
}
