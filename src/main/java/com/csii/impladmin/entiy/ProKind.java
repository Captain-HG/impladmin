package com.csii.impladmin.entiy;

import lombok.Data;

import javax.persistence.*;

/**
 * @AUTHOR HG-captain
 * @Data 2020/1/7
 * @Description
 */
@Data
@Entity
@Table
public class ProKind {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long pId;
    @Column(nullable = false)
    private Long kId;
}
