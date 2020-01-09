package com.csii.impladmin.repository;

import com.csii.impladmin.entiy.Kind;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @AUTHOR HG-captain
 * @Data 2020/1/7
 * @Description
 */
public interface KindRepository extends JpaRepository<Kind,Long> {
    @Query("select kind from Kind kind where kind.kId=:kId")
    Kind queryById(@Param("kId") Long kId);

    Kind findByKName(String kName);

}
