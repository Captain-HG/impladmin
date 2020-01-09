package com.csii.impladmin.repository;

import com.csii.impladmin.entiy.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @AUTHOR HG-captain
 * @Data 2020/1/7
 * @Description
 */
public interface ProductRepository extends JpaRepository<Product,Long> {
    /**
     * 根据主键查询
     * @param pId
     * @return
     */
    @Query(value = "select product from Product product where product.pId=:pId")
    Product queryById(@Param("pId") Long pId);

    Product findByPName(String pName);
}
