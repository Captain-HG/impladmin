package com.csii.impladmin.entiy;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @AUTHOR HG-captain
 * @Data 2020/1/7
 * @Description
 */
@Data
@Entity
@Table
public class Impl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iId;
    @Column(nullable = false,unique = true)
    private String iName;
    @Column
    private String sendType;
    @Column(length = 500)
    private String url;
    @Column(nullable = false)
    private  Long kId;
    @Lob
    @Column(columnDefinition="Mediumtext")
    private String reqData;
    @Lob
    @Column(columnDefinition="Mediumtext")
    private String rspData;
    @Column
    @JsonFormat(shape= JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Timestamp createTime;
    @Column
    @JsonFormat(shape= JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Timestamp updateTime;
}
