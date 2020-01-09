package com.csii.impladmin.entiy;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;



/**
 * @AUTHOR HG-captain
 * @Data 2020/1/7
 * @Description 模板类
 */
@Entity
@Table
@Data
public class MoBan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mbId;
    @Lob
    @Column(columnDefinition="Mediumtext")
    private String data;
    @Column
    @JsonFormat(shape= JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Timestamp createTime;
    @Column(nullable = false,unique = true)
    private String mbName;
    @Column(length = 500)
    private String desc;
    @Column(nullable = false)
    private String mbType;
    @JsonFormat(shape= JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @Column
    private Timestamp updateTime;

}
