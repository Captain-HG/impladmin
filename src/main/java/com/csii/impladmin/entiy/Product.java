package com.csii.impladmin.entiy;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @AUTHOR HG-captain
 * @Data 2020/1/7
 * @Description 项目类
 */
@Data
@Entity
@Table
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pId;
    @Column(nullable = false,unique = true)
    private String pName;
    @Column(nullable = false)
    @JsonFormat(shape= JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Timestamp createTime;
    @Column
    @JsonFormat(shape= JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Timestamp updateTime;
}
